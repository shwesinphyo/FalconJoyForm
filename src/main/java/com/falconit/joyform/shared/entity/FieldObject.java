/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.shared.entity;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.validator.RegExValidator;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import java.util.Map;

/**
 *
 * @author apple
 */
public class FieldObject implements java.io.Serializable{
    private String name;
    private String label = "";
    private String placeHolder = "";
    private String helperText;
    private Object value;// String, Boolean, Long, Double, Date, Map<Object,Object>
    private AbstractValueWidget widget;
    private boolean validate = false;
    private boolean allowBlank = false;
    private RegExValidator validator;
    private IconType iconType;
    private boolean readyOnly = false; // general
    private String grid; // general
    private WavesType waves = WavesType.DEFAULT; // general
    private Color backgroundColor; // general
    private Color textColor; // general
    private java.util.Map<Object, String> listValues;
    private Object listDefaultSelectedValue;
    private InputType type = InputType.TEXT;

    public FieldObject( String name, String label, AbstractValueWidget widget ) {
        this.name = name;
        this.label = label;
        this.widget = widget;
    }

    public void renderInput(){
        
        getWidget().setEnabled(readyOnly);
        if( grid != null )
            getWidget().setGrid(grid);
        
        getWidget().setWaves(waves);
        
        if( backgroundColor != null )
            getWidget().setBackgroundColor(backgroundColor);
        
        if( textColor != null )
            getWidget().setTextColor(textColor);
        
        
        if( getWidget() instanceof  MaterialTextBox  ){
            ((MaterialTextBox)getWidget()).setLabel(label);
            ((MaterialTextBox)getWidget()).setType( type );
            ((MaterialTextBox)getWidget()).setAutoValidate(validate);
            ((MaterialTextBox)getWidget()).setAllowBlank(allowBlank);
            if( iconType != null )
                ((MaterialTextBox)getWidget()).setIconType(iconType);
            ((MaterialTextBox)getWidget()).setPlaceholder(placeHolder);
            
            if( helperText != null )
                ((MaterialTextBox)getWidget()).setHelperText(helperText);
        }
        
        
        if( getWidget() instanceof  MaterialTextArea ){
            ((MaterialTextArea)getWidget()).setLabel(label);
            ((MaterialTextArea)getWidget()).setAutoValidate(validate);
            ((MaterialTextArea)getWidget()).setAllowBlank(allowBlank);
            if( iconType != null )
                ((MaterialTextArea)getWidget()).setIconType(iconType);
            ((MaterialTextArea)getWidget()).setPlaceholder(placeHolder);
            if( helperText != null )
                ((MaterialTextArea)getWidget()).setHelperText(helperText);
        }
        
        if( getWidget() instanceof  MaterialComboBox ){
            ((MaterialComboBox)getWidget()).setLabel(label);
            ((MaterialComboBox)getWidget()).setAutoValidate(validate);
            ((MaterialComboBox)getWidget()).setAllowBlank(allowBlank);
            ((MaterialComboBox)getWidget()).setPlaceholder(placeHolder);
            if( helperText != null )
                ((MaterialComboBox)getWidget()).setHelperText(helperText);
            
            
            int count = 0;
            for( java.util.Map.Entry<Object,String> entry : listValues.entrySet()){
                MaterialComboBox cbo = ( MaterialComboBox ) getWidget();
                if( entry.getValue() != null )
                    cbo.addItem( entry.getValue(), entry.getKey() );
                else
                    cbo.addItem( entry.getKey() );
                if( listDefaultSelectedValue != null && entry.getKey() == listDefaultSelectedValue ){
                    cbo.setSelectedIndex( count );
                }
                count++;
            }
        }
 
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public Object getValue() {
        if( getWidget() != null ){
            if( getWidget() instanceof  MaterialComboBox  ){
               value = ((MaterialComboBox) getWidget()).getSelectedValue().get(0);
            }else
                value = getWidget().getValue();
        }
        return value;
    }

    public void setValue( Object value ) {
        this.value = value;
        if( getWidget() instanceof MaterialComboBox ){
            //getWidget().setValue( value );
        }else{
            getWidget().setValue( value );
        }
    }

    public AbstractValueWidget getWidget() {
        return widget;
    }

    public void setWidget(AbstractValueWidget widget) {
        this.widget = widget;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public boolean isAllowBlank() {
        return allowBlank;
    }

    public void setAllowBlank(boolean allowBlank) {
        this.allowBlank = allowBlank;
    }

    public boolean isReadyOnly() {
        return readyOnly;
    }

    public void setReadyOnly(boolean readyOnly) {
        this.readyOnly = readyOnly;
    }

    public RegExValidator getValidator() {
        return validator;
    }

    public void setValidator(RegExValidator validator) {
        this.validator = validator;
    }

    public IconType getIconType() {
        return iconType;
    }

    public void setIconType(IconType iconType) {
        this.iconType = iconType;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public WavesType getWaves() {
        return waves;
    }

    public void setWaves(WavesType waves) {
        this.waves = waves;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Map<Object, String> getListValues() {
        return listValues;
    }

    public void setListValues(Map<Object, String> listValues) {
        this.listValues = listValues;
    }


    public InputType getType() {
        return type;
    }

    public void setType(InputType type) {
        this.type = type;
    }

    public Object getListDefaultSelectedValue() {
        return listDefaultSelectedValue;
    }

    public void setListDefaultSelectedValue(Object listDefaultSelectedValue) {
        this.listDefaultSelectedValue = listDefaultSelectedValue;
    }

    
    private void test(){
        widget = new MaterialComboBox<Long>();
        widget.getValue( );
    }
}
