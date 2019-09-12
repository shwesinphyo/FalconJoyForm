/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;


import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 *
 * @author User
 */
public class WidgetGenerator {
    
    public MaterialRow getWidget( Field field ) throws Exception{
        MaterialRow row = new MaterialRow(); 
        row.addStyleName("test");
        
        
        if( field.getBackgroundColor() != null )
            row.setBackgroundColor( field.getBackgroundColor() );
        
        row.setGrid( field.getGrid() );
        //row.setShadow(1);
        //row.setPadding(8);
        //row.setMargin(4);
        if( field.getPadding() > 0)
            row.setPadding( field.getPadding() );
        if( field.getMargin() > 0)
            row.setMargin( field.getMargin() );
        
        row.setTitle( field.getLabel() );
        row.setId( field.getId() );
        
        return row;
    }
    
    public void createWidget( Field parent, MaterialRow row ){
        
        for( Field field : parent.getChildren() ){
            
            int colSize = 12 / parent.getChildren().size();
            MaterialColumn child = new MaterialColumn();
            child.setId( field.getId() );
            child.setTitle( field.getLabel() );
            child.setGrid("l" + colSize+" m" + colSize+" s12");
            row.add( child );
            
            if( field.getWidgetType().equals(Field.WIDGET_TEXT_BOX) 
                    || field.getWidgetType().equals(Field.WIDGET_TEXT_BOX_NUMBER)){
                MaterialTextBox widget = new MaterialTextBox();
                widget.setText( field.getValue() != null ? field.getValue().toString() : "" );
                widget.setType( field.getInputType() );
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setName(field.getName());
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
            }else if( field.getWidgetType().equals(Field.WIDGET_TEXT_AREA) ){
                MaterialTextArea widget = new MaterialTextArea();
                widget.setText( field.getValue() != null ? field.getValue().toString() : "" );
                widget.setType( field.getInputType() );
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setName(field.getName());
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setResizeRule(MaterialTextArea.ResizeRule.AUTO);
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
            }else if( field.getWidgetType().equals(Field.WIDGET_TEXT_BOX_RICH) ){
                MaterialRichEditor widget = new MaterialRichEditor();
                widget.setHTML( field.getValue() != null ? field.getValue().toString() : "" );
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setHeight( field.getHeight().isEmpty() ? "260px" : field.getHeight() );
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
            }else if( field.getWidgetType().equals(Field.WIDGET_CHECK_BOX) ){
                MaterialCheckBox widget = new MaterialCheckBox();
                widget.setText( field.getLabel() );
                widget.setValue( field.getValue() != null ? (boolean)field.getValue() : false  );
                widget.setName(field.getName());
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
            }else if( field.getWidgetType().equals(Field.WIDGET_DATE_TIME) ){
                MaterialDatePicker widget = new MaterialDatePicker();
                widget.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
                widget.setDetectOrientation( true );
                
                if( field.getValue() != null )
                    widget.setDate(new java.util.Date((long) field.getValue() ));
                else
                    widget.setDate(new java.util.Date());
                
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setAutoClose(true);
                widget.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
            }else if( field.getWidgetType().equals(Field.WIDGET_COMBO_BOX ) ){
                MaterialComboBox<String> widget = new MaterialComboBox<>();
                widget.setMultiple(true);
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setLabel(field.getLabel());
                
                int count = 0;
                for( Field data : field.getChildren()){
                    widget.addItem( data.getLabel(), data.getValue().toString() );
                    if( field.getValue().toString().equals( data.getValue().toString() ))
                        widget.setSelectedIndex( count );
                    count++;
                }
                
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
            }else{
                child.add( new MaterialLink( "Not implemented yet" ) );
            }
            
        }
    }
}
