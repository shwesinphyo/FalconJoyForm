package com.falconit.joyform.client.application.form.editor;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.falconit.joyform.client.application.form.util.Field;
import com.falconit.joyform.client.application.form.util.WidgetGenerator;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.dnd.MaterialDnd;
import gwt.material.design.addins.client.dnd.js.JsDragOptions;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.*;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormEditorView extends ViewImpl implements FormEditorPresenter.MyView {
    interface Binder extends UiBinder<Widget, FormEditorView> {
    }

    private java.util.List<Field> lstItem = new java.util.ArrayList<>();
    //MaterialPanel items[];

    @UiField
    MaterialRow dropzoneContainer;

    @UiField
    MaterialPanel holder;
    @UiField
    MaterialTextBox txtid, txtname, txtlabel, txtplaceholder;
    @UiField 
    MaterialComboBox cbofield;
    @UiField 
    MaterialButton btnAdd;
    
    //@UiField
    //MaterialTextArea txtbrief;

    @Inject
    FormEditorView(Binder uiBinder) {
        
        initWidget( uiBinder.createAndBindUi( this ) );

        String ww[] = new String[]{Field.WIDGET_TEXT_BOX, Field.WIDGET_TEXT_AREA, Field.WIDGET_CHECK_BOX, Field.WIDGET_DATE_TIME };
        for( int i=1; i<=4; i++ ){
            Field f = new Field("RowId " + i, "Item " + i, "Option " + i);
            //f.setBackgroundColor(Color.WHITE);
            Field child = new Field("ColumnId " + i, "Item " + i, "Option " + i);
            child.setWidgetType(ww[i-1]);
            child.setPlaceHolder( "Item " + i );
            child.setLabel( "Item " + i );
            f.getChildren().add( child );
            lstItem.add( f );
        }
        /*
        lstItem.add( new Field( "RowId 5", "Item 5", "Option 5" ) );
        Field f = new Field("RowId " + 6, "Item " + 6, "Option " + 6);
        f.getChildren().add( new Field("RowId " + 6, "Item " + 6, "Option " + 6, "test group") );
        f.getChildren().add( new Field("RowId " + 7, "Item " + 7, "Option " + 7, "test group") );
        lstItem.add( f );
        */
        layout( );
        /*
        Window.alert("Remove from group testing");
        lstItem.get( lstItem.size() - 1).getChildren().get( lstItem.get( lstItem.size() - 1).getChildren().size() - 1 ).setGroup("");
        removeGroup( lstItem.size() - 1, lstItem.get( lstItem.size() - 1).getChildren().size() - 1 );
        
        Window.alert("Group testing");
        lstItem.get( 0 ).getChildren().get(0).setGroup( "g1" );
        lstItem.get( 1 ).getChildren().get(0).setGroup( "g1" );
        addGroup(  );
        */
    }
    
    private Field remove( String id ){
        for ( int i=0; i < lstItem.size(); i++)
            if( lstItem.get(i).getId().equals( id ))
                return lstItem.remove(i);
        
        return null;
    }
    
    private void verticalMove( MaterialWidget widget ){
        if( lstItem.size() < 2) return;
        
        try{
            Field field = remove( widget.getId());
            int tmpDistance = 100000;
            int position = -1;
            int topDif = 10000;
            int leftDif = 10000;
            for ( int i=0; i < lstItem.size(); i++ ){
                if( lstItem.get(i).getTop() > field.getTop() && lstItem.get(i).getTop() < tmpDistance ){
                    tmpDistance = lstItem.get(i).getTop();
                    position = i;
                    topDif = lstItem.get(i).getTop() - field.getTop();
                    leftDif = lstItem.get(i).getLeft() - field.getLeft();
                }
                
            }
        
            //if( topDif < 15 && leftDif >=7 ){
                //Window.alert("Column movement top=" + topDif +", left=" + leftDif);
            //}
            
            if( position >= 0 ){
                lstItem.add( position, field );
            }else{
                lstItem.add( field );
            }
            
            layout( );
        }catch(Exception ex){Window.alert(ex.getMessage());}
    }
    
    private void layout( ){
        
        //Collections.sort( lstItem, new Sortbyroll() );
        holder.clear( );
        //txtbrief.setText( txtbrief.getText() + "\n\n" );
        
        for( int i=0; i < lstItem.size(); i++ ){
            Field f = lstItem.get(i);
            if( f.getChildren().isEmpty()){
                --i;
                lstItem.remove(f); continue;
            }
            MaterialRow row;
            WidgetGenerator generator = new WidgetGenerator();
            try {
                row = generator.getWidget( f );
                MaterialDnd.draggable( row, JsDragOptions.create( Axis.VERTICAL ) );
                generator.createWidget(f, row);
                            
                row.addDragEndHandler(event -> {
                MaterialToast.fireToast("Added " );
                //txtbrief.setText( "" );
                int count = 0; 
                for ( Widget w : holder.getChildrenList() ){
                    lstItem.get( count ).setTop( w.getAbsoluteTop() );
                    lstItem.get( count ).setLeft( w.getAbsoluteLeft() );
                    //txtbrief.setText( txtbrief.getText() +"Event ->"+ w.getTitle() + ", top=" + w.getAbsoluteTop() + ", left=" + w.getAbsoluteLeft() + "\n" );
                    count++;
                }
                //txtbrief.setText( txtbrief.getText() + "\n\n" );
                verticalMove( row );

               });
                            
                holder.add( row );
                f.setTop( row.getAbsoluteTop() );
                f.setLeft( row.getAbsoluteLeft() );
                
            } catch (Exception ex) {
                Window.alert(ex.getMessage());
                Logger.getLogger(FormEditorView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    /**
     * grouping the components. Before call this method, the group name must be added to related component
     * e.g. lstItem.get(index).getChildren().get(index).setGroup(groupName);
     * @param groupName 
     */
    private void addGroup( ){
        for( int i=0; i < lstItem.size(); i++ ){
            Field f = lstItem.get( i );
            if( !f.getChildren().isEmpty() ){
                // pickup the first child column
                Field child = f.getChildren().get( 0 );
                // check the group name
                if( child.getGroup() != null && !child.getGroup().isEmpty()){
                    boolean found = false;
                    // find the related group name
                    for( int j=i +1; j < lstItem.size(); j++ ){
                        Field relative_child = lstItem.get( j ).getChildren().get( 0 );
                        if( relative_child.getGroup() != null 
                                && !relative_child.getGroup().isEmpty()
                                && child.getGroup().trim().equalsIgnoreCase(relative_child.getGroup().trim()) ){
                            Field r = lstItem.remove( j );
                            f.getChildren().addAll( r.getChildren() );
                            j--;
                            found = true;
                        }
                    }
                    // remove from group name for single component
                    if( !found && f.getChildren().size() == 1){
                        child.setGroup("");
                    }
                }
                
                // sorting by index
                Collections.sort(lstItem, new SortByIndex());
            }
        }
        layout( );
    }
    
    private void removeGroup( int rowIndex, int columnIndex ){
        
        if( rowIndex < 0 || columnIndex < 0 || rowIndex > lstItem.size() - 1) return;
        
        Field row = lstItem.get( rowIndex );
        if( row.getChildren( ).size() <= 1 || columnIndex > row.getChildren( ).size() - 1) return;
        
        if( columnIndex == row.getChildren().size() - 1){
            rowIndex++;
        }
        Field col = row.getChildren( ).remove( columnIndex );
        
        Field f = new Field( col.getId( ), col.getLabel( ), col.getName( ) );
        f.getChildren( ).add( col );
        lstItem.add( rowIndex, f );
        
        layout( );
    }
    
    class SortByIndex implements Comparator<Field> {
        @Override
        public int compare(Field a, Field b) {
            return a.getIndex() - b.getIndex(); 
        } 
    } 
    
}
