/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;

import com.falconit.joyform.shared.jsonconvert.ObjectConverter;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import java.util.List;

/**
 *
 * @author User
 */
    public class Field implements java.io.Serializable {
    
        public static final String WIDGET_ROW = "row";
        public static final String WIDGET_TEXT_BOX = "textbox";
        public static final String WIDGET_TEXT_AREA = "textarea";
        public static final String WIDGET_TEXT_BOX_RICH = "textboxrich";
        public static final String WIDGET_TEXT_BOX_NUMBER = "textboxnumber";
        public static final String WIDGET_RANGE = "range";
        
        // single true or false
        public static final String WIDGET_CHECK_BOX = "checkbox";
        public static final String WIDGET_SWITCH = "switch";
        
        // multiple selection
        public static final String WIDGET_COMBO_BOX = "combo";
        public static final String WIDGET_CHECK_BOX_GROUP = "checkboxgroup";
        public static final String WIDGET_RADIO_GROUP = "radiogroup";
        
        // 
        public static final String WIDGET_DATE_TIME = "datetime";
        public static final String WIDGET_FILE_UPLOADER = "upload";
        public static final String WIDGET_MEDIA = "media";
        public static final String WIDGET_TABLE = "table";
        
    
        public static final String JSON_FIELD_CHILDREN = "children";
        public static final String JSON_FIELD_ID = "id";
        public static final String JSON_FIELD_LABEL = "label";
        public static final String JSON_FIELD_VALUE = "value";
        public static final String JSON_FIELD_NAME = "name";
        public static final String JSON_FIELD_TOP = "top";
        public static final String JSON_FIELD_LEFT = "left";
        public static final String JSON_FIELD_GROUP = "group";
        public static final String JSON_FIELD_INDEX = "index";
        public static final String JSON_FIELD_WIDGET_NAME = "widgetType";
        public static final String JSON_FIELD_HIDE = "hide";
        
        // priority group start
        private String id="";
        private String label = "";
        private Object value;
        private String name="";// this name also use for process data
        private int top=0;
        private int left=0;
        private String group="";
        private int index = 0;
        private String widgetType="";
        private boolean hide=false;
        // priority group end
        
        
        public static final String JSON_FIELD_PLACE_HOLDER = "placeHolder";
        public static final String JSON_FIELD_HELPER_TEXT = "helperText";
        public static final String JSON_FIELD_VALIDATE = "validate";
        public static final String JSON_FIELD_ALLOW_BLANK = "allowBlank";
        public static final String JSON_FIELD_READ_ONLY = "readOnly";
        public static final String JSON_FIELD_ICON_TYPE = "iconType";
        public static final String JSON_FIELD_WAVES = "waves";
        public static final String JSON_FIELD_INPUT_TYPE = "inputType";
        // decoration
        private String placeHolder = "";
        private String helperText="";
        private boolean validate = false;
        private boolean allowBlank = true;
        private boolean readOnly = false;
        private IconType iconType;
        private WavesType waves = WavesType.DEFAULT; // general
        private InputType inputType = InputType.TEXT;
        
        
        public static final String JSON_FIELD_HEIGHT = "height";
        public static final String JSON_FIELD_WIDTH = "width";
        public static final String JSON_FIELD_MARGIN = "margin";
        public static final String JSON_FIELD_MARGIN_TOP = "marginTop";
        public static final String JSON_FIELD_MARGIN_RIGHT = "marginRight";
        public static final String JSON_FIELD_MARGIN_BOTTOM = "marginBottom";
        public static final String JSON_FIELD_MARGIN_LEFT = "marginLeft";
        public static final String JSON_FIELD_PADDING = "padding";
        public static final String JSON_FIELD_PADDING_TOP = "paddingTop";
        public static final String JSON_FIELD_PADDING_RIGHT = "paddingRight";
        public static final String JSON_FIELD_PADDING_BOTTOM = "paddingBottom";
        public static final String JSON_FIELD_PADDING_LEFT = "paddingLeft";
        public static final String JSON_FIELD_FONT_SIZE = "fontSize";
        public static final String JSON_FIELD_GRID = "grid";
        public static final String JSON_FIELD_BACKGROUND_COLOR = "backgroundColor";
        public static final String JSON_FIELD_TEXT_COLOR = "textColor";
        public static final String JSON_FIELD_TEXT_ALIGN = "textAlign";
        public static final String JSON_FIELD_MIN = "min";
        public static final String JSON_FIELD_MAX = "max";
        // CSS
        private String height="";
        private String width="";// second priority for width
        private double margin=0.0;
        private double marginTop=0.0;
        private double marginRight=0.0;
        private double marginBottom=0.0;
        private double marginLeft=0.0;
        private double padding=0.0;
        private double paddingTop=0.0;
        private double paddingRight=0.0;
        private double paddingBottom=0.0;
        private double paddingLeft=0.0;
        private String fontSize="";
        private String grid="l12 m12 s12";// first priority for width
        private Color backgroundColor;
        private Color textColor;
        private TextAlign textAlign;
        private double min;
        private double max;
        
        private java.util.List<Field> children = new java.util.ArrayList<> ();
        
        private Object widget;

    public Object getWidget() {
        return widget;
    }

        public Field() {
        }

        public Field( Object value, String name ) {
            this.value = value;
            this.name = name;
        }

        public Field( String id, String label, String name ) {
            this.id = id;
            this.label = label;
            this.name = name;
        }

        public Field(String id, String label, String name, String group) {
            this.id = id;
            this.label = label;
            this.name = name;
            this.group = group;
        }

        public Field(String id, String label, String name, int top, int left) {
            this.id = id;
            this.label = label;
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

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
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

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getWidgetType() {
            return widgetType;
        }

        public void setWidgetType(String widgetType) {
            this.widgetType = widgetType;
        }

        public boolean isHide() {
            return hide;
        }

        public void setHide(boolean hide) {
            this.hide = hide;
        }

        public String getPlaceHolder() {
            return placeHolder;
        }

        public void setPlaceHolder(String placeHolder) {
            this.placeHolder = placeHolder;
        }

        public String getHelperText() {
            return helperText;
        }

        public void setHelperText(String helperText) {
            this.helperText = helperText;
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

        public boolean isReadOnly() {
            return readOnly;
        }

        public void setReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
        }

        public IconType getIconType() {
            return iconType;
        }

        public void setIconType(IconType iconType) {
            this.iconType = iconType;
        }

        public WavesType getWaves() {
            return waves;
        }

        public void setWaves(WavesType waves) {
            this.waves = waves;
        }

        public InputType getInputType() {
            return inputType;
        }

        public void setInputType(InputType inputType) {
            this.inputType = inputType;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public double getMargin() {
            return margin;
        }

        public void setMargin(double margin) {
            this.margin = margin;
        }

        public double getMarginTop() {
            return marginTop;
        }

        public void setMarginTop(double marginTop) {
            this.marginTop = marginTop;
        }

        public double getMarginRight() {
            return marginRight;
        }

        public void setMarginRight(double marginRight) {
            this.marginRight = marginRight;
        }

        public double getMarginBottom() {
            return marginBottom;
        }

        public void setMarginBottom(double marginBottom) {
            this.marginBottom = marginBottom;
        }

        public double getMarginLeft() {
            return marginLeft;
        }

        public void setMarginLeft(double marginLeft) {
            this.marginLeft = marginLeft;
        }

        public double getPadding() {
            return padding;
        }

        public void setPadding(double padding) {
            this.padding = padding;
        }

        public double getPaddingTop() {
            return paddingTop;
        }

        public void setPaddingTop(double paddingTop) {
            this.paddingTop = paddingTop;
        }

        public double getPaddingRight() {
            return paddingRight;
        }

        public void setPaddingRight(double paddingRight) {
            this.paddingRight = paddingRight;
        }

        public double getPaddingBottom() {
            return paddingBottom;
        }

        public void setPaddingBottom(double paddingBottom) {
            this.paddingBottom = paddingBottom;
        }

        public double getPaddingLeft() {
            return paddingLeft;
        }

        public void setPaddingLeft(double paddingLeft) {
            this.paddingLeft = paddingLeft;
        }

        public String getFontSize() {
            return fontSize;
        }

        public void setFontSize(String fontSize) {
            this.fontSize = fontSize;
        }

        public String getGrid() {
            return grid;
        }

        public void setGrid(String grid) {
            this.grid = grid;
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

        public TextAlign getTextAlign() {
            return textAlign;
        }

        public void setTextAlign(TextAlign textAlign) {
            this.textAlign = textAlign;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }
 
        public void fromJSON( JSONObject json ) throws Exception{
            setId( json.get(JSON_FIELD_ID).isString().stringValue());
            //Window.alert(json.toString());
            
            setLabel( json.get(JSON_FIELD_LABEL).isString().stringValue());
            if( json.get(JSON_FIELD_VALUE).isNull() != null )
                setValue( null );
            else
                setValue( ( json.get(JSON_FIELD_VALUE).isString() == null ? null : json.get(JSON_FIELD_VALUE).isString().stringValue() ));
            setName( json.get(JSON_FIELD_NAME).isString().stringValue());
            setTop( (int) json.get(JSON_FIELD_TOP).isNumber().doubleValue() );
            setLeft((int) json.get(JSON_FIELD_LEFT).isNumber().doubleValue());
            setGroup( json.get(JSON_FIELD_GROUP).isString().stringValue());
            setIndex((int) json.get(JSON_FIELD_INDEX).isNumber().doubleValue());
            setWidgetType( json.get(JSON_FIELD_WIDGET_NAME).isString().stringValue());
            setHide( json.get(JSON_FIELD_HIDE).isBoolean().booleanValue() );

            setPlaceHolder( json.get(JSON_FIELD_PLACE_HOLDER).isString().stringValue());
            setHelperText( json.get(JSON_FIELD_HELPER_TEXT).isString().stringValue() );
            
            setValidate( json.get(JSON_FIELD_VALIDATE).isBoolean().booleanValue());
            setAllowBlank( json.get(JSON_FIELD_ALLOW_BLANK).isBoolean().booleanValue());
            setReadOnly( json.get(JSON_FIELD_READ_ONLY).isBoolean().booleanValue());
            setIconType( (json.get(JSON_FIELD_ICON_TYPE).isString() == null ? null : IconType.valueOf( json.get(JSON_FIELD_ICON_TYPE).isString().stringValue())) );
            setWaves( (json.get(JSON_FIELD_WAVES).isString() == null ? null : WavesType.valueOf( json.get(JSON_FIELD_WAVES).isString().stringValue()) ));
            setInputType( (json.get(JSON_FIELD_INPUT_TYPE).isString() == null ? null : InputType.valueOf( json.get(JSON_FIELD_INPUT_TYPE).isString().stringValue())) );

            setHeight( json.get(JSON_FIELD_HEIGHT).isString().stringValue());
            setWidth( json.get(JSON_FIELD_WIDTH).isString().stringValue() );
            setMargin( json.get(JSON_FIELD_MARGIN).isNumber().doubleValue() );
            setMarginTop( json.get(JSON_FIELD_MARGIN_TOP).isNumber().doubleValue());
            setMarginRight( json.get(JSON_FIELD_MARGIN_RIGHT).isNumber().doubleValue());
            setMarginBottom( json.get(JSON_FIELD_MARGIN_BOTTOM).isNumber().doubleValue() );
            setMarginLeft( json.get(JSON_FIELD_MARGIN_LEFT).isNumber().doubleValue() );
            setPadding( json.get(JSON_FIELD_PADDING).isNumber().doubleValue() );
            
            setPaddingTop( json.get(JSON_FIELD_PADDING_TOP).isNumber().doubleValue() );
            setPaddingRight( json.get(JSON_FIELD_PADDING_RIGHT).isNumber().doubleValue() );
            setPaddingBottom( json.get(JSON_FIELD_PADDING_BOTTOM).isNumber().doubleValue() );
            setPaddingLeft( json.get(JSON_FIELD_PADDING_LEFT).isNumber().doubleValue());
            setFontSize( json.get(JSON_FIELD_FONT_SIZE).isString().stringValue());
            setGrid( json.get(JSON_FIELD_GRID).isString().stringValue() );
            setBackgroundColor( (json.get(JSON_FIELD_BACKGROUND_COLOR).isString() == null ? null : Color.valueOf( json.get(JSON_FIELD_BACKGROUND_COLOR).isString().stringValue())) );
            setTextColor( (json.get(JSON_FIELD_TEXT_COLOR).isString() == null ? null : Color.valueOf(json.get(JSON_FIELD_TEXT_COLOR).isString().stringValue())) );
            setTextAlign( (json.get(JSON_FIELD_TEXT_ALIGN).isString() == null ? null : TextAlign.valueOf(json.get(JSON_FIELD_TEXT_ALIGN).isString().stringValue())) );
            
            setMin( json.get(JSON_FIELD_MIN).isNumber().doubleValue() );
            setMax( json.get(JSON_FIELD_MAX).isNumber().doubleValue() );
            
            if( json.get(JSON_FIELD_CHILDREN) != null ){
                //Window.alert("Child exist");
                if( json.get(JSON_FIELD_CHILDREN).isArray() != null ){
                    JSONArray group = json.get(JSON_FIELD_CHILDREN).isArray();
                    //Window.alert("Child Size=" + group.size());
                    for( int i=0; i < group.size(); i++){
                        JSONObject child = group.get(i).isObject();
                        Field f = new Field();
                        f.fromJSON(child);
                        children.add(f);
                        //Window.alert("Child added in Field=" + children.size() );
                    }
                }
            }else{
                //Window.alert("No more child");
            }
        }
 
        public JSONObject toJSON() throws Exception{
            
            JSONObject json = new JSONObject( );
            
            json.put( JSON_FIELD_ID, new JSONString(getId()) );
            json.put( JSON_FIELD_LABEL, new JSONString(getLabel()) );
            json.put( JSON_FIELD_VALUE, ( getValue() == null ? JSONNull.getInstance() : new JSONString(getValue().toString()) ) );
            json.put( JSON_FIELD_NAME, new JSONString(getName()) );
            json.put( JSON_FIELD_TOP, new JSONNumber(getTop()) );
            json.put( JSON_FIELD_LEFT, new JSONNumber(getLeft()) );
            json.put( JSON_FIELD_GROUP, new JSONString(getGroup()) );
            json.put( JSON_FIELD_INDEX, new JSONNumber(getIndex()) );
            json.put( JSON_FIELD_WIDGET_NAME, new JSONString(getWidgetType()) );
            json.put( JSON_FIELD_HIDE, JSONBoolean.getInstance(isHide()) );
            
            json.put( JSON_FIELD_PLACE_HOLDER, new JSONString(getPlaceHolder()) );
            json.put( JSON_FIELD_HELPER_TEXT, new JSONString(getHelperText()) );
            json.put( JSON_FIELD_VALIDATE, JSONBoolean.getInstance( isValidate() ) );
            json.put( JSON_FIELD_ALLOW_BLANK, JSONBoolean.getInstance(isAllowBlank()) );
            json.put( JSON_FIELD_READ_ONLY, JSONBoolean.getInstance(isReadOnly()) );
            json.put( JSON_FIELD_ICON_TYPE, ( getIconType() == null ?  JSONNull.getInstance() : new JSONString(getIconType().name()) ));
            json.put( JSON_FIELD_WAVES, ( getWaves() == null ?  JSONNull.getInstance() :  new JSONString(getWaves().name()) ));
            json.put( JSON_FIELD_INPUT_TYPE,( getInputType() == null ?  JSONNull.getInstance() :  new JSONString(getInputType().name()) ));
        
            json.put( JSON_FIELD_HEIGHT, new JSONString(getHeight()) );
            json.put( JSON_FIELD_WIDTH, new JSONString(getWidth()) );
            json.put( JSON_FIELD_MARGIN, new JSONNumber( getMargin() ) );
            json.put( JSON_FIELD_MARGIN_TOP, new JSONNumber(getMarginTop()) );
            json.put( JSON_FIELD_MARGIN_RIGHT, new JSONNumber(getMarginRight()) );
            json.put( JSON_FIELD_MARGIN_BOTTOM, new JSONNumber(getMarginBottom()) );
            json.put( JSON_FIELD_MARGIN_LEFT, new JSONNumber(getMarginLeft()) );
            json.put( JSON_FIELD_PADDING, new JSONNumber(getPadding()) );
            
            json.put( JSON_FIELD_PADDING_TOP, new JSONNumber( getPaddingTop() ) );
            json.put( JSON_FIELD_PADDING_RIGHT, new JSONNumber(getPaddingRight()) );
            json.put( JSON_FIELD_PADDING_BOTTOM, new JSONNumber(getPaddingBottom()) );
            json.put( JSON_FIELD_PADDING_LEFT, new JSONNumber(getPaddingLeft()) );
            json.put( JSON_FIELD_FONT_SIZE, new JSONString(getFontSize()) );
            json.put( JSON_FIELD_GRID, new JSONString(getGrid()) );
            json.put( JSON_FIELD_BACKGROUND_COLOR, (getBackgroundColor() == null ? JSONNull.getInstance() : new JSONString(getBackgroundColor().name()) ));
            json.put( JSON_FIELD_TEXT_COLOR, (getTextColor() == null ? JSONNull.getInstance() : new JSONString(getTextColor().name()) ));
            json.put( JSON_FIELD_TEXT_ALIGN, (getTextAlign() == null ? JSONNull.getInstance() : new JSONString(getTextAlign().name()) ));
            
            json.put( JSON_FIELD_MIN, new JSONNumber(getMin()) );
            json.put( JSON_FIELD_MAX, new JSONNumber(getMax()) );
            
            if( children!= null && !children.isEmpty()){
                JSONArray group = new JSONArray();
                int count=0;
                for( Field f : children){
                    JSONObject child = f.toJSON();
                    group.set(count++, child);
                }
                json.put( JSON_FIELD_CHILDREN, group );
            }
            
            return json;
        }
    
        public void bind( Object widget){
            this.widget = widget;
        }
        
        public Object[] getBindValue( ){
            if( getWidgetType().equals(Field.WIDGET_TEXT_BOX) ){
                String value = ((MaterialTextBox) widget).getText().trim();
                return new Object[] { ObjectConverter.TYPE_STRING, value };
                
            }else if( getWidgetType().equals(Field.WIDGET_TEXT_BOX_NUMBER) ){
                double value = Double.parseDouble( ((MaterialTextBox) widget).getText().trim());
                if( value > (long )value )
                    return new Object[] { ObjectConverter.TYPE_DECIMAL, value };
                else
                    return new Object[] { ObjectConverter.TYPE_NUMBER, value };
                
            }else if( getWidgetType().equals(Field.WIDGET_TEXT_AREA) ){
                String value = ((MaterialTextArea) widget).getText().trim();
                return new Object[] { ObjectConverter.TYPE_STRING, value };
                
            }else if( getWidgetType().equals(Field.WIDGET_TEXT_BOX_RICH) ){
                String value = ((MaterialRichEditor) widget).getHTML();
                return new Object[] { ObjectConverter.TYPE_STRING, value };
                
            }else if( getWidgetType().equals(Field.WIDGET_CHECK_BOX) ){
                boolean value = ((MaterialCheckBox) widget).getValue();
                return new Object[] { ObjectConverter.TYPE_BOOLEAN, value };
                
            }else if( getWidgetType().equals(Field.WIDGET_DATE_TIME) ){
                java.util.Date value = ((MaterialDatePicker) widget).getDate();
                return new Object[] { ObjectConverter.JSON_INPUT_FIELD_TIMESTAMP, value.getTime() };
                
            }else if( getWidgetType().equals(Field.WIDGET_COMBO_BOX) ){
                java.util.List value = ((MaterialComboBox) widget).getSelectedValues();
                StringBuilder sb = new StringBuilder();
                for( Object o : value){
                    sb.append(sb.toString().isEmpty() ? o.toString() : "," + o.toString());
                }
                return new Object[] { ObjectConverter.TYPE_STRING, sb.toString() };
                
            }
            else
                return null;
        }
    }

