package com.falconit.joyform.client.application.form.editing;

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
import com.falconit.joyform.client.application.form.util.Form;
import com.falconit.joyform.client.application.form.util.FormCRUD;
import com.falconit.joyform.client.application.util.jbpmclient.api.ContainerManager;
import com.falconit.joyform.client.application.util.jbpmclient.api.process.ProcessVariables;
import com.falconit.joyform.client.application.util.jbpmclient.api.process.ProcessesManager;
import com.falconit.joyform.client.application.util.jbpmclient.api.process.ProcessesVariablesMapping;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.stepper.MaterialStepper;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


public class FormEditingView extends ViewImpl implements FormEditingPresenter.MyView {
    interface Binder extends UiBinder<Widget, FormEditingView> {
    }

    private Form myForm = new Form( );
    private Long id = null;
    private java.util.List<Field> lstItem = new java.util.ArrayList<>();
    
    private java.util.List<Object[]> lstProjects, lstProcesses, lstTasks;
    
    
    @UiField
    MaterialStepper stepper;
        
    @UiField
    MaterialPanel holder, fieldholder;
    
    @UiField
    MaterialTextBox txtformName;
    
    @UiField 
    MaterialComboBox cbocontainer, cboprocess, cboformtype;

    /*
    @UiField
    MaterialTextArea txtbrief;
    */
    
    @UiField
    MaterialButton btnContinue4;    
    
    @Inject
    FormEditingView(Binder uiBinder) {
        
        initWidget( uiBinder.createAndBindUi( this ) );
        
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
                myForm.getChild().remove(index);
                myForm.render(holder);
                MaterialToast.fireToast("Click on Delete button " + field.getName()+", index=" + index );
            }
        });
        myForm.setChild( lstItem );
        myForm.setId(null);
        
        fieldholder.setDisplay( Display.BLOCK );
        //holder.setDisplay( Display.FLEX );
        loadProjects( );
        registercboProcessListener( );
        registercboFormListener();
    }

        
    @UiHandler({"btnContinue1", "btnContinue2", "btnContinue3", "btnContinue4"})
    void onNextStep(ClickEvent e){
        stepper.nextStep();
    }

    @UiHandler({"btnPrev1", "btnPrev2", "btnPrev3","btnPrev4" })
    void onPrevStep(ClickEvent e){
        stepper.prevStep();
    }
    
        
    @UiHandler({ "btnContinue4"})
    void onComplete(ClickEvent e){
        try {
            myForm.setName( txtformName.getText() );
            myForm.setContainer( cbocontainer.getSelectedValue().get(0).toString() );
            myForm.setProcess( cboprocess.getSelectedValue().get(0).toString() );

            myForm.setTask( cboformtype.getSelectedValue().get(0).toString() );
            myForm.setCreated(new java.util.Date());
            myForm.setUpdated(new java.util.Date());
            myForm.setActors(new String[]{"everyone"});
            myForm.setGroups(new String[]{});
            myForm.setStatus( 1 );
            myForm.setFqdn("");
            myForm.setObjectName("");
                        
            //txtbrief.setText( myForm.toJSON().toString() );
            saveForm();
        } catch (Exception ex) {
            Window.alert("Error "+ex.getMessage());
        }
    }
    
    @UiHandler({ "btnContinue2"})
    void onFormDesign(ClickEvent e){
        try {
            createForm( );
        } catch (Exception ex) {
            Window.alert("Error "+ex.getMessage());
        }
    }
    
    private void registercbocontainerListener(){
        cbocontainer.addValueChangeHandler(handler ->{
            loadProcesses();
        });
    }
    
        
    private void registercboProcessListener(){
        cboprocess.addValueChangeHandler(handler ->{
            try{
                if( cbocontainer.getSelectedValue() != null && cboprocess.getSelectedValue() != null)
                    processMappings( cbocontainer.getSelectedValue().get(0).toString(), cboprocess.getSelectedValue().get(0).toString() );
            }catch(Exception ex){}
        });
    }
    
        
    private void registercboFormListener(){
        cboformtype.addValueChangeHandler(handler ->{
            try{
                if( cbocontainer.getSelectedValue() != null && cboprocess.getSelectedValue() != null){
                    
                    if( cboformtype.getSelectedValue().get(0).toString().equals(Form.TASK_NAME_START_UP))
                        processVariables( cbocontainer.getSelectedValue().get(0).toString(), cboprocess.getSelectedValue().get(0).toString() );
                    else
                        processVariablesMapping( cbocontainer.getSelectedValue().get(0).toString(), cboprocess.getSelectedValue().get(0).toString() );
                }
            }catch(Exception ex){}
        });
    }
        
    private void loadProjects(){
        try{
            ContainerManager manager = new ContainerManager();
            manager.setListener(new ContainerManager.ContainerManagerListener(){
                @Override
                public void success(Map<String, Object[]> maps) {
                    
                    for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
                        
                        lstProjects = (java.util.List<Object[]>) entry.getValue()[1];
                        for( Object[] o : lstProjects ){
                            java.util.Map<String, Object[]> map = (java.util.Map<String, Object[]>) o[1];
                            cbocontainer.addItem( map.get("container-id")[1].toString(), map.get("container-id")[1].toString());
                        }
                    }
                    registercbocontainerListener();
                }

                @Override
                public void fail(String message, int stage) {
                }
            });
            
            manager.list();
        }catch(Exception ex){Window.alert(ex.getMessage());}
    }
        
    private void loadProcesses( ){
        try{
            MaterialLoader.loading( true );
            
            ProcessesManager manager = new ProcessesManager();
            manager.setListener(new ProcessesManager.ProcessesManagerListener(){
                @Override
                public void success(Map<String, Object[]> maps) {
                    MaterialLoader.loading( false );
                    cboprocess.clear();
                    cboformtype.clear();
                    for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
                        lstProcesses = (java.util.List<Object[]>) entry.getValue()[1];
                                                
                        for( Object[] o : lstProcesses ){//container-id
                            java.util.Map<String, Object[]> map = (java.util.Map<String, Object[]>) o[1];
                            if( map.get("container-id")[1].toString().equals(cbocontainer.getSelectedValue().get(0).toString()))
                                cboprocess.addItem( map.get("process-name")[1].toString(), map.get("process-id")[1].toString());
                        }
                    }
                }

                @Override
                public void fail(String message, int stage) {
                    MaterialLoader.loading( false );
                }
            });
            
            manager.list( null, null );
        }catch(Exception ex){Window.alert(ex.getMessage());}
    }
        
    private void processMappings( String container, String processId ){
        try{
            MaterialLoader.loading( true );
            ProcessesVariablesMapping manager = new ProcessesVariablesMapping();
            manager.setListener(new ProcessesVariablesMapping.ProcessesVariablesMappingListener(){
                @Override
                public void success(Map<String, Object[]> maps) {
                    cboformtype.clear();
                    
                    if( !maps.isEmpty())
                        cboformtype.addItem( "Start up form", Form.TASK_NAME_START_UP);
                    
                    for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
                        lstTasks = (java.util.List<Object[]>) entry.getValue()[1];
                                                
                        for( Object[] o : lstTasks ){
                            java.util.Map<String, Object[]> map = (java.util.Map<String, Object[]>) o[1];
                            cboformtype.addItem( map.get("task-name")[1].toString(), map.get("task-name")[1].toString());
                        }
                    }
                    
                    MaterialLoader.loading( false );
                }

                @Override
                public void fail(String message, int stage) {
                    MaterialLoader.loading( true );
                }
            });
            
            manager.list( container, processId );
        }catch(Exception ex){Window.alert(ex.getMessage());}
    }
    
    private void processVariablesMapping( String container, String processId ){
        try{
            MaterialLoader.loading( true );
            ProcessesVariablesMapping manager = new ProcessesVariablesMapping();
            manager.setListener(new ProcessesVariablesMapping.ProcessesVariablesMappingListener(){
                @Override
                public void success(Map<String, Object[]> maps) {

                    for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
                        java.util.List<Object[]> lstVariables = (java.util.List<Object[]>) entry.getValue()[1];
                                                
                        for( Object[] o : lstVariables ){
                            java.util.Map<String, Object[]> map = (java.util.Map<String, Object[]>) o[1];
                            if( cboformtype.getSelectedValue().get(0).toString().equals( map.get("task-name")[1].toString() )){
                                java.util.Map<String, Object[]> mapChild = (java.util.Map<String, Object[]>) map.get("taskOutputMappings")[1];
                                
                                Window.alert("Variables size = " + mapChild.size());
                                createFields( mapChild );
                            }
                        }
                    }
                    
                    MaterialLoader.loading( false );
                }

                @Override
                public void fail(String message, int stage) {
                    MaterialLoader.loading( true );
                }
            });
            
            manager.list( container, processId );
        }catch(Exception ex){Window.alert(ex.getMessage());}
    }


    private void processVariables( String container, String processId ){
        try{
            ProcessVariables manager = new ProcessVariables();
            manager.setListener(new ProcessVariables.ProcessVariablesListener(){
                @Override
                public void success(Map<String, Object[]> maps) {
                    Window.alert("Variables size = " + maps.size());
                    createFields( maps );
                }

                @Override
                public void fail(String message, int stage) {
                }
            });
            
            manager.list( container, processId );
        }catch(Exception ex){Window.alert(ex.getMessage());}
    }
    
    private void createFields( Map<String, Object[]> maps ){
        
        // check the form is already exist or not
        getForm();
        
        
        fieldholder.clear();
        
        for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
            Field f;
            MaterialRow row = new MaterialRow( );
            row.setGrid("l12 m12 s12");
            fieldholder.add(row);
            
            MaterialColumn colName = new MaterialColumn();
            colName.setGrid("l3 m3 s12");
            row.add(colName);
            
            MaterialTextBox txtName = new MaterialTextBox();
            txtName.setText( entry.getKey() );
            txtName.setPlaceholder("Input field name");
            colName.add(txtName);
            
            MaterialColumn colLabel = new MaterialColumn();
            colLabel.setGrid("l3 m3 s12");
            row.add(colLabel);
            
            MaterialTextBox txtLabel = new MaterialTextBox();
            txtLabel.setText( entry.getKey() );
            txtLabel.setPlaceholder("Label to show on form");
            colLabel.add( txtLabel );
            
            MaterialColumn coltype = new MaterialColumn();
            coltype.setGrid("l2 m2 s12");
            row.add(coltype);
            
            MaterialComboBox cbotype = new MaterialComboBox();
            cbotype.setPlaceholder("Input type");
            coltype.add(cbotype);
            cbotype.addItem("Text");
            cbotype.addItem("Number");
            cbotype.addItem("Choice");
            cbotype.addItem("Yes/No");
            cbotype.addItem("Date");
            
            MaterialColumn colgroup = new MaterialColumn();
            colgroup.setGrid("l2 m2 s12");
            row.add(colgroup);
            
            MaterialComboBox cbogroup = new MaterialComboBox();
            cbogroup.setPlaceholder("More rich");
            colgroup.add(cbogroup);
            
            String dType = entry.getValue()[1].toString();
            
            if( dType.equals("String")){
                cbotype.setSelectedIndex(0);
                //f = addTestItem( Field.WIDGET_TEXT_BOX, entry.getKey(), entry.getKey(), txtLabel.getText(), txtLabel.getText() );
            }
            else if( dType.equals("Integer") || dType.equals("Float")){
                cbotype.setSelectedIndex(1);
                //f = addTestItem( Field.WIDGET_TEXT_BOX_NUMBER, entry.getKey(), entry.getKey(), txtLabel.getText(), txtLabel.getText() );
            }
            else if( dType.equals("Boolean")){
                cbotype.setSelectedIndex(3);
                //f = addTestItem( Field.WIDGET_CHECK_BOX, entry.getKey(), entry.getKey(), txtLabel.getText(), txtLabel.getText() );
            }
            else if( dType.equals("java.util.Date")){
                cbotype.setSelectedIndex(4);
                //f = addTestItem( Field.WIDGET_DATE_TIME, entry.getKey(), entry.getKey(), txtLabel.getText(), txtLabel.getText() );
            }

            cbotype.addValueChangeHandler(handler ->{
                setInputType( cbotype.getSelectedValue().get(0).toString(), cbogroup );
            });
            
            MaterialAnchorButton btnremove = new MaterialAnchorButton();
            btnremove.setType( ButtonType.FLOATING );
            btnremove.setBackgroundColor( Color.RED );
            btnremove.setIconType( IconType.DELETE );
            btnremove.setSize( ButtonSize.MEDIUM );
            btnremove.setFloat(Style.Float.RIGHT);
            btnremove.setTooltip("Remove");
            btnremove.setMarginRight(5);
            row.add( btnremove );
            btnremove.addClickHandler(handler ->{
                fieldholder.remove(row);
                //lstItem.remove( f );
            });
        }
        
        //myForm.render(holder);
    }
    
    private void setInputType(String type, MaterialComboBox colgroup ){
        colgroup.clear();
        if( type.equals("Text")){
            colgroup.addItem("Single line", Field.WIDGET_TEXT_BOX);
            colgroup.addItem("Multi lines", Field.WIDGET_TEXT_AREA);
            colgroup.addItem("Rich text area", Field.WIDGET_TEXT_BOX_RICH);
            
        }else if( type.equals("Number")){
            colgroup.addItem("Number", Field.WIDGET_TEXT_BOX_NUMBER);
        }else if( type.equals("Choice")){
            colgroup.addItem("Dropdown choice", Field.WIDGET_COMBO_BOX);
            colgroup.addItem("Radio choice", Field.WIDGET_RADIO_GROUP);
        }else if( type.equals("Yes/No")){
            colgroup.addItem("Check box", Field.WIDGET_CHECK_BOX);
            colgroup.addItem("On/Off switch", Field.WIDGET_SWITCH);
        }else if( type.equals("Date")){
            colgroup.addItem("Date picker", Field.WIDGET_DATE_TIME);
        }
        
        colgroup.setSelectedIndex(0);
    }

    private void createForm( ){
        lstItem.clear();
        for ( Widget w : fieldholder.getChildrenList() ){
            MaterialRow row =  (MaterialRow) w;
            
            MaterialColumn colName =  (MaterialColumn) row.getChildrenList().get(0);
             MaterialTextBox txtName = (MaterialTextBox) colName.getChildrenList().get(0);
            
             MaterialColumn colLabel =  (MaterialColumn) row.getChildrenList().get(1);
             MaterialTextBox txtLabel = (MaterialTextBox) colLabel.getChildrenList().get(0);
             
             MaterialColumn coltype =  (MaterialColumn) row.getChildrenList().get(3);
             MaterialComboBox cbotype = (MaterialComboBox) coltype.getChildrenList().get(0);
             
             addTestItem( cbotype.getSelectedValue().get(0).toString(), txtName.getText(), 
            txtName.getText(), txtLabel.getText(), txtLabel.getText() );
        }
        
        myForm.render(holder);
    }
    
    private Field addTestItem( String type, String id, 
            String name, String label, String placeHolder ){
        
        Field f;
        if( type.equals(Field.WIDGET_COMBO_BOX)){
            // Combo box testing
            f = new Field( id, label, name );
            
            Field child = new Field( id, label, name );
            child.setWidgetType( Field.WIDGET_COMBO_BOX );

            child.setPlaceHolder( placeHolder );
            child.setLabel( label );

            child.setValue( "value 3" );// default selection
            f.getChildren().add( child );

            // Items
            String comboData[] = new String[]{"value 1","value 2","value 3","value 4","value 5","value 6","value 7"};
            for( String s : comboData){
                Field comboItem = new Field();
                comboItem.setValue( s );
                comboItem.setLabel("Item " + s);
                child.getChildren().add( comboItem );
            }
            lstItem.add( f );
        }else{
            f = new Field( id, label, name );
            Field child = new Field( id, label, name );
            child.setWidgetType( type );
            child.setPlaceHolder( label );
            child.setLabel( label );
            //child.setReadOnly( true );
            
            f.getChildren().add( child );
            lstItem.add( f );
        }
        return f;
    }
       
    private void saveForm(){
        
        MaterialLoader.loading( true );
        
        FormCRUD crud = new FormCRUD();
        crud.setListener(new FormCRUD.CRUDListener(){
            @Override
            public void success(String result) {
                MaterialLoader.loading( false );
                btnContinue4.setBackgroundColor( Color.GREEN );
                animate( btnContinue4 );
                resetColor( btnContinue4 );
            }

            @Override
            public void success(Form result) {
                
            }

            @Override
            public void success(List<Form> result) {
            }

            @Override
            public void fail(String message) {
                MaterialLoader.loading( false );
                Window.alert( "Result = " + message );
            }
        });
        
        if( id == null )
            crud.saveUpdate( myForm, true );
        else{
            myForm.setId( id );
            crud.saveUpdate( myForm, false );
        }
    }
   
    private void getForm( ){
        id = null;
        FormCRUD crud = new FormCRUD();
        crud.setListener(new FormCRUD.CRUDListener(){
            @Override
            public void success(String result) {
                //Window.alert( "Result = " + result );
            }

            @Override
            public void success(Form result) {
                
            }

            @Override
            public void success(List<Form> result) {
                if( !result.isEmpty() ){
                    id = result.get(0).getId();
                    //Window.alert( "Form already exist, found total= " + result.size() );
                }
            }

            @Override
            public void fail(String message) {
                Window.alert( "Result = " + message );
            }
        });
        
        crud.getBy( 
                cbocontainer.getSelectedValue().get(0).toString(), 
                cboprocess.getSelectedValue().get(0).toString(), 
                cboformtype.getSelectedValue().get(0).toString());
    }

        
    private void animate( Widget widget ){
        MaterialAnimation animation = new MaterialAnimation();
        animation.setTransition(Transition.BOUNCEOUT);
        animation.setDelay( 100 );
        animation.setDuration( 1000 * 2 );
        animation.setInfinite( false );        
        animation.animate( widget );
    }
    
    private void resetColor( MaterialButton widget ){
        new Timer(){
            @Override
            public void run() {
                widget.setBackgroundColor( Color.BLUE );
                Window.Location.assign( "#tasklist" );
            }
        }.schedule( 3500 );
    }

}
