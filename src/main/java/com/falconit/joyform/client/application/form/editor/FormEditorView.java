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
import static com.falconit.joyform.client.application.form.util.Field.JSON_FIELD_LABEL;
import com.falconit.joyform.client.application.form.util.Form;
import com.falconit.joyform.client.application.form.util.FormCRUD;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.*;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormEditorView extends ViewImpl implements FormEditorPresenter.MyView {
    interface Binder extends UiBinder<Widget, FormEditorView> {
    }

    private Form myForm = new Form("Test name","test container", "test process", "test task");
    private java.util.List<Field> lstItem = new java.util.ArrayList<>();
    JSONObject json;
    //MaterialPanel items[];

    @UiField
    MaterialRow dropzoneContainer;

    @UiField
    MaterialPanel holder, holder1;
    @UiField
    MaterialTextBox txtid, txtname, txtlabel, txtplaceholder;
    @UiField 
    MaterialComboBox cbofield;
    @UiField 
    MaterialButton btnAdd;
    
    @UiField
    MaterialTextArea txtbrief;

    @Inject
    FormEditorView(Binder uiBinder) {
        
        initWidget( uiBinder.createAndBindUi( this ) );

        // this is form display mode
        myForm.setMode( Form.DISPLAY_MODE_DESIGNER );
        myForm.setMouseOverShadow(true);
        myForm.setItemListener(new Form.FormItemListener(){
            @Override
            public void onClick(Field field, int index) {
                MaterialToast.fireToast("Click on " + field.getName()+", index=" + index );
            }

            @Override
            public void mouseEnter(Field field, int index) {
                //MaterialToast.fireToast("Mouse enter " + field.getName()+", index=" + index );
            }

            @Override
            public void mouseExit(Field field, int index) {
                //MaterialToast.fireToast("Mouse exit " + field.getName()+", index=" + index );
            }

            @Override
            public void onEditClick(Field field, int index) {
                MaterialToast.fireToast("Click on Edit button " + field.getName()+", index=" + index );
            }

            @Override
            public void onDeleteClick(Field field, int index) {
                MaterialToast.fireToast("Click on Delete button " + field.getName()+", index=" + index );
            }

            @Override
            public void onUpClick(Field field, int index) {
            }

            @Override
            public void onDownClick(Field field, int index) {
            }
        });
        
        myForm.setName("Leave Approved");
        
        myForm.setContainer("DevTest_1.0.0-SNAPSHOT");
        myForm.setProcess("Leave-Request");
        
        myForm.setTask("Leave Approved");
        myForm.setCreated(new java.util.Date());
        myForm.setUpdated(new java.util.Date());
        myForm.setActors(new String[]{"everyone"});
        myForm.setGroups(new String[]{});
        myForm.setStatus(1);
        myForm.setFqdn("");
        myForm.setObjectName("");
        // adding child widget list to form
        myForm.setChild( lstItem );
        myForm.setId(null);
        
        /*
        String ww[] = new String[]{Field.WIDGET_TEXT_BOX, Field.WIDGET_TEXT_BOX_NUMBER, Field.WIDGET_CHECK_BOX, Field.WIDGET_DATE_TIME };
        for( int i=1; i<=4; i++ ){
            // adding widget
            addTestItem( ww[i-1], "RowId " + i, "Item " + i, "Option " + i, "Item " + i );
        }
        // adding widget
        addTestItem( Field.WIDGET_COMBO_BOX, "RowId 7", "Item 7", "Option 7", "Option 7" );
        
        // drawing the form
        myForm.render( holder );
        */
        
        
        
        /*
        // widget remove testing
        Window.alert("Remove from group testing");
        lstItem.get( lstItem.size() - 1).getChildren().get( lstItem.get( lstItem.size() - 1).getChildren().size() - 1 ).setGroup("");
        removeGroup( lstItem.size() - 1, lstItem.get( lstItem.size() - 1).getChildren().size() - 1 );
        
        Window.alert("Group testing");
        //widget grouping testing
        lstItem.get( 0 ).getChildren().get(0).setGroup( "g1" );
        lstItem.get( 1 ).getChildren().get(0).setGroup( "g1" );
        addGroup(  );
        */
    }
    
    private void addTestItem( String type, String id, 
            String name, String label, String placeHolder ){
        
        if( type.equals(Field.WIDGET_COMBO_BOX) 
                || type.equals(Field.WIDGET_RADIO_GROUP) ){
            
            java.util.Map<String,String> labelLang = new java.util.HashMap<>();
            labelLang.put( "en", label );
            // Combo box testing
            Field f = new Field( id, labelLang, name );
            
            Field child = new Field( id, labelLang, name );
            child.setWidgetType( type );

            child.setPlaceHolder( placeHolder );
            child.setLabel( labelLang );

            if( type.equals(Field.WIDGET_COMBO_BOX))
                child.setValue( "value 3" );// default selection
            else
                child.setValue( "Item value 3" );// default selection
            f.getChildren().add( child );

            // Items
            String comboData[] = new String[]{"value 1","value 2","value 3","value 4","value 5","value 6","value 7"};
            for( String s : comboData){
                Field comboItem = new Field( );
                comboItem.setValue( s );
                java.util.Map<String,String> labelItems = new java.util.HashMap<>();
                labelItems.put( "en", "Item " + s );
                comboItem.setLabel( labelItems );
                child.getChildren().add( comboItem );
            }
            lstItem.add( f );
        }else{
            java.util.Map<String,String> labelLang = new java.util.HashMap<>();
            labelLang.put( "en", label );
            
            Field f = new Field( id, labelLang, name );
            Field child = new Field( id, labelLang, name );
            child.setWidgetType( type );
            child.setPlaceHolder( label );
            child.setLabel( labelLang );
            //child.setReadOnly( true );
            
            f.getChildren().add( child );
            lstItem.add( f );
        }
    }
    
    
    @UiHandler("btnAdd")
    void onAdd(ClickEvent e) {
        addTestItem( cbofield.getSingleValue().toString(), txtid.getText(), txtname.getText(), txtlabel.getText(), txtplaceholder.getText() );
        myForm.render(holder);
    }
    
    @UiHandler("btnToJSON")
    void onToJSON(ClickEvent e) {
        try {
            json = myForm.toJSON();
            txtbrief.setText( json.toString() );
        } catch (Exception ex) {
            Window.alert( ex.getMessage() );
            Logger.getLogger( FormEditorView.class.getName()).log(Level.SEVERE, null, ex );
        }
    }
    
    @UiHandler("btnSave")
    void onSave(ClickEvent e) {
        try {
            //saveForm();
        } catch (Exception ex) {
            Window.alert( ex.getMessage() );
        }
    }
    
    @UiHandler("btnFromJSON")
    void onFromJSON(ClickEvent e) {
        try {
            Form form1 = new Form( );
            form1.setDraggable( false );
            form1.fromJSON( json, holder1 );
        } catch (Exception ex) {
            Window.alert( ex.getMessage() );
            Logger.getLogger( FormEditorView.class.getName()).log(Level.SEVERE, null, ex );
        }
    }
    
        
    private void saveForm(){
        FormCRUD crud = new FormCRUD();
        crud.setListener(new FormCRUD.CRUDListener(){
            @Override
            public void success(String result) {
                Window.alert( "Result = " + result );
            }

            @Override
            public void success(Form result) {
                
            }

            @Override
            public void success(List<Form> result) {
            }

            @Override
            public void fail(String message) {
                Window.alert( "Result = " + message );
            }

            @Override
            public void fqdn(Map<String, Object[]> maps) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        crud.saveUpdate( myForm, true );
    }

}
