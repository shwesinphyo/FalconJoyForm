package com.falconit.joyform.client.application.addins.dnd;

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

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.dnd.MaterialDnd;
import gwt.material.design.addins.client.dnd.constants.Restriction;
import gwt.material.design.addins.client.dnd.js.JsDragOptions;
import gwt.material.design.addins.client.dnd.js.JsDropOptions;
import gwt.material.design.addins.client.fileuploader.MaterialFileUploader;
import gwt.material.design.addins.client.inputmask.MaterialInputMask;
import gwt.material.design.addins.client.rating.MaterialRating;
import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.addins.client.signature.MaterialSignaturePad;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.*;
import gwt.material.design.jquery.client.api.JQueryElement;

import javax.inject.Inject;

import static gwt.material.design.jquery.client.api.JQuery.$;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DndViewBACKUP extends ViewImpl implements DndPresenter.MyView {
    interface Binder extends UiBinder<Widget, DndViewBACKUP> {
    }

    private java.util.List<Field> lstItem = new java.util.ArrayList<>();
    //MaterialPanel items[];

    @UiField
    MaterialRow dropzoneContainer;

    @UiField
    MaterialColumn placeContainer, holder;
    
    @UiField
    MaterialTextArea txtbrief;

    @Inject
    DndViewBACKUP(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        
/*
        MaterialDatePicker dp = new MaterialDatePicker();
        holder.add(dp);
        MaterialDnd.draggable(dp);
        MaterialDropDown dd = new MaterialDropDown();
        holder.add(dd);
        MaterialDnd.draggable(dd);
        MaterialCheckBox cb = new MaterialCheckBox();
        //MaterialDnd.draggable(cb);
        MaterialRadioButton rbtn = new MaterialRadioButton();
        //MaterialDnd.draggable(rbtn);
        MaterialRange rg = new MaterialRange();
        holder.add(rg);
        MaterialDnd.draggable(rg);
        MaterialSwitch sw = new MaterialSwitch();
        holder.add(sw);
        MaterialDnd.draggable( sw );
        MaterialTextBox tb = new MaterialTextBox();
        holder.add(tb);
        MaterialDnd.draggable( tb );
        MaterialTextArea ta = new MaterialTextArea();
        holder.add(ta);
        MaterialDnd.draggable( ta );
        MaterialComboBox cbo = new MaterialComboBox();
        holder.add(dp);
        MaterialDnd.draggable( cbo );
        MaterialFileUploader fupload = new MaterialFileUploader();
        holder.add(fupload);
        MaterialDnd.draggable( fupload );
        MaterialInputMask mask = new MaterialInputMask();
        holder.add(mask);
        MaterialDnd.draggable( mask );
        MaterialRating rate = new MaterialRating();
        holder.add(rate);
        MaterialDnd.draggable( rate );
        MaterialRichEditor re = new MaterialRichEditor();
        holder.add(re);
        MaterialDnd.draggable( re );
        MaterialSignaturePad sp = new MaterialSignaturePad();
        holder.add(sp);
        MaterialDnd.draggable( sp );
        MaterialButton btn = new MaterialButton();
        holder.add(btn);
        MaterialDnd.draggable( btn );
        */

        //items = new MaterialPanel[4];
        /*
        for( int i=0; i < items.length; i++){
            items[i] = new MaterialPanel();
            items[i].addStyleName("test");
            items[i].setBackgroundColor(Color.WHITE);
            items[i].setGrid("s12");
            items[i].setShadow(1);
            items[i].setPadding(8);
            items[i].setMargin(4);
            items[i].setTitle("Item " + (i+1) );
            items[i].add( new MaterialLink("Option " + (i+1) ) );
           MaterialDnd.draggable( items[i], JsDragOptions.create( Axis.VERTICAL ) );
            holder.add( items[i] );
            items[i].addDragEndHandler(event -> {
                MaterialToast.fireToast("Added " );
                txtbrief.setText( "" );
                for ( Widget w : holder.getChildrenList() ){
                    txtbrief.setText( txtbrief.getText() + w.getTitle()+", top=" + w.getAbsoluteTop()+", left=" + w.getAbsoluteLeft() +"\n" );
                }
                txtbrief.setText( txtbrief.getText() + "\n\n" );
               layout( ); 
           });
            //backgroundColor="WHITE" shadow="1" grid="s12" padding="8" margin="4"
        }
        */
        //MaterialDnd.draggable(item1, JsDragOptions.create(Axis.VERTICAL) );
        //MaterialDnd.draggable(item2, JsDragOptions.create(Axis.VERTICAL) );
        //MaterialDnd.draggable(item3, JsDragOptions.create(Axis.VERTICAL) );
        
        //MaterialDnd.draggable(item4);

        //MaterialDnd.dropzone( dropzoneContainer, JsDropOptions.create(".test"));

        dropzoneContainer.addDropActivateHandler(event -> {
            //MaterialToast.fireToast("Drop Activate");
        });

        dropzoneContainer.addDragEnterHandler(dragEnterEvent -> {
            placeContainer.setBackgroundColor(Color.BLUE);
            //MaterialToast.fireToast("Drag Enter");
        });

        dropzoneContainer.addDragLeaveHandler(event -> {
            /*
            JQueryElement target = $(event.getRelatedTarget());
            MaterialWidget widget = new MaterialWidget(target.asElement());
            placeContainer.remove( widget );
            holder.add( widget );
            placeContainer.setBackgroundColor(Color.GREY_LIGHTEN_2);
            */
            MaterialToast.fireToast("Remove");
        });

        dropzoneContainer.addDropHandler(event -> {
            
            JQueryElement target = $(event.getRelatedTarget());
            MaterialWidget widget = new MaterialWidget(target.asElement());
            //placeContainer.add( widget );
            
            MaterialToast.fireToast("Added " );
            txtbrief.setText( "" );
            int count = 0; 
            for ( Widget w : holder.getChildrenList() ){
                lstItem.get(count).setTop(w.getAbsoluteTop());
                lstItem.get(count++).setLeft( w.getAbsoluteLeft() );
                txtbrief.setText( txtbrief.getText() + w.getTitle()+", top=" + w.getAbsoluteTop()+", left=" + w.getAbsoluteLeft() +"\n" );
            }
            txtbrief.setText( txtbrief.getText() + "\n\n" );
            verticalMove( widget );
        });

        dropzoneContainer.addDropDeactivateHandler(event -> {
            //MaterialToast.fireToast("Drop Deactivate");
        });
        
        
                
        lstItem.add(new Field("Item 1", "Item 1", "Option 1"));
        lstItem.add(new Field("Item 2", "Item 2", "Option 2"));
        lstItem.add(new Field("Item 3", "Item 3", "Option 3"));
        lstItem.add(new Field("Item 4", "Item 4", "Option 4"));
        layout( );
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
                Window.alert("Column movement top=" + topDif +", left=" + leftDif);
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
        txtbrief.setText( txtbrief.getText() + "\n\n" );
        for( Field f : lstItem ){
            MaterialRow row = new MaterialRow(); 
            row.addStyleName("test");
            
            row.setBackgroundColor(Color.WHITE);
            row.setGrid("s12");
            row.setShadow(1);
            row.setPadding(8);
            row.setMargin(4);
            row.setTitle( f.getTitle() );
            row.setId( f.getId() );
            
            MaterialColumn child = new MaterialColumn();
            row.add( child );
            child.setId( f.getId() );
            child.setGrid("l6");
            child.add( new MaterialLink( f.getName() ) );
            
           MaterialDnd.draggable( row, JsDragOptions.create( Axis.VERTICAL ) );
           holder.add( row );
           f.setTop( row.getAbsoluteTop() );
           f.setLeft( row.getAbsoluteLeft() );
            txtbrief.setText( txtbrief.getText() + row.getTitle()+", top=" + row.getAbsoluteTop()+", left=" + row.getAbsoluteLeft() +"\n" );
            
                    
            row.addDragEndHandler(event -> {
            //item.getId();
            //JQueryElement target = $(event.getRelatedTarget());
            //MaterialWidget widget = row;//new MaterialWidget(target.asElement()); 
            //placeContainer.add( widget );
            
            MaterialToast.fireToast("Added " );
            txtbrief.setText( "" );
            int count = 0; 
            for ( Widget w : holder.getChildrenList() ){
                lstItem.get(count).setTop(w.getAbsoluteTop());
                lstItem.get(count++).setLeft( w.getAbsoluteLeft() );
                txtbrief.setText( txtbrief.getText() + w.getTitle()+", top=" + w.getAbsoluteTop()+", left=" + w.getAbsoluteLeft() +"\n" );
            }
            txtbrief.setText( txtbrief.getText() + "\n\n" );
            verticalMove( row );
                
           });
        }

    }
    
    class Sortbyroll implements Comparator<Field> {
        // Used for sorting in ascending order of 
        // roll number 
        @Override
        public int compare(Field a, Field b) 
        { txtbrief.setText( txtbrief.getText()+ a.getTitle()+"," + b.getTitle()+" > " + (a.getTop() - b.getTop()) + "\n" );
            return a.getTop() - b.getTop(); 
        } 
    } 
    
    class Field implements java.io.Serializable {
        private String id;
        private String title;
        private String name;
        private int top;
        private int left;
        private java.util.List<Field> children = new java.util.ArrayList<>();

        public Field() {
        }

        public Field(String id, String title, String name) {
            this.id = id;
            this.title = title;
            this.name = name;
        }

        public Field(String id, String title, String name, int top, int left) {
            this.id = id;
            this.title = title;
            this.name = name;
            this.top = top;
            this.left = left;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public List<Field> getChildren() {
            return children;
        }

        public void setChildren(List<Field> children) {
            this.children = children;
        }
        
        
    }
}
