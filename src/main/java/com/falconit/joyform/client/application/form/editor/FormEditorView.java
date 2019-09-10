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

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.addins.client.dnd.MaterialDnd;
import gwt.material.design.addins.client.dnd.js.JsDragOptions;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.*;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FormEditorView extends ViewImpl implements FormEditorPresenter.MyView {
    interface Binder extends UiBinder<Widget, FormEditorView> {
    }

    private java.util.List<Field> lstItem = new java.util.ArrayList<>();
    //MaterialPanel items[];

    @UiField
    MaterialRow dropzoneContainer;

    @UiField
    MaterialColumn holder;
    
    //@UiField
    //MaterialTextArea txtbrief;

    @Inject
    FormEditorView(Binder uiBinder) {
        initWidget( uiBinder.createAndBindUi( this ) );

        for( int i=1; i<=4; i++ ){
            Field f = new Field("RowId " + i, "Item " + i, "Option " + i);
            f.getChildren().add( new Field("ColumnId " + i, "Item " + i, "Option " + i) );
            lstItem.add( f );
        }
        lstItem.add( new Field( "RowId 5", "Item 5", "Option 5" ) );
        Field f = new Field("RowId " + 6, "Item " + 6, "Option " + 6);
        f.getChildren().add( new Field("RowId " + 6, "Item " + 6, "Option " + 6, "test group") );
        f.getChildren().add( new Field("RowId " + 7, "Item " + 7, "Option " + 7, "test group") );
        lstItem.add( f );
        
        layout( );
        
        Window.alert("Remove from group testing");
        lstItem.get( lstItem.size() - 1).getChildren().get( lstItem.get( lstItem.size() - 1).getChildren().size() - 1 ).setGroup("");
        removeGroup( lstItem.size() - 1, lstItem.get( lstItem.size() - 1).getChildren().size() - 1 );
        
        Window.alert("Group testing");
        lstItem.get( 0 ).getChildren().get(0).setGroup( "g1" );
        lstItem.get( 1 ).getChildren().get(0).setGroup( "g1" );
        addGroup(  );
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
            
            MaterialRow row = new MaterialRow(); 
            row.addStyleName("test");
            
            row.setBackgroundColor(Color.WHITE);
            row.setGrid("s12");
            row.setShadow(1);
            row.setPadding(8);
            row.setMargin(4);
            row.setTitle( f.getLabel() );
            row.setId( f.getId() );
            MaterialDnd.draggable( row, JsDragOptions.create( Axis.VERTICAL ) );
            
            for( Field c : f.getChildren() ){
                int colSize = 12 / f.getChildren().size();
                MaterialColumn child = new MaterialColumn();
                row.add( child );
                
                child.setId( c.getId() );
                child.setTitle( c.getLabel() );
                child.setGrid("l" + colSize);
                child.add( new MaterialLink( c.getName() ) );// here should beß actual component
            }
            
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
            //txtbrief.setText( txtbrief.getText() +"Setup ->" + row.getTitle() + ", top=" + row.getAbsoluteTop() + ", left=" + row.getAbsoluteLeft() +"\n" );
           
            holder.add( row );
            f.setTop( row.getAbsoluteTop() );
            f.setLeft( row.getAbsoluteLeft() );
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
    
    class Field implements java.io.Serializable {
        
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
        // CSS
        private String height="";
        private String width="";// second priority for width
        private String margin="";
        private String marginTop="";
        private String marginRight="";
        private String marginBottom="";
        private String marginLeft="";
        private double padding=0.0;
        private double paddingTop=0.0;
        private double paddingRight=0.0;
        private double paddingBottom=0.0;
        private double paddingLeft=0.0;
        private String fontSize="";
        private String grid="m12 l12 s12";// first priority for width
        private Color backgroundColor;
        private Color textColor;
        private TextAlign textAlign;
        
        private java.util.List<Field> children = new java.util.ArrayList<> ();
        
        private Object widget;

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

        public String getMargin() {
            return margin;
        }

        public void setMargin(String margin) {
            this.margin = margin;
        }

        public String getMarginTop() {
            return marginTop;
        }

        public void setMarginTop(String marginTop) {
            this.marginTop = marginTop;
        }

        public String getMarginRight() {
            return marginRight;
        }

        public void setMarginRight(String marginRight) {
            this.marginRight = marginRight;
        }

        public String getMarginBottom() {
            return marginBottom;
        }

        public void setMarginBottom(String marginBottom) {
            this.marginBottom = marginBottom;
        }

        public String getMarginLeft() {
            return marginLeft;
        }

        public void setMarginLeft(String marginLeft) {
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
        
        
        public void fromJSON( JSONObject json ) throws Exception{
            
            setId( json.get(JSON_FIELD_ID).isString().stringValue());
            setLabel( json.get(JSON_FIELD_LABEL).isString().stringValue());
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
            setMargin( json.get(JSON_FIELD_MARGIN).isString().stringValue());
            setMarginTop( json.get(JSON_FIELD_MARGIN_TOP).isString().stringValue());
            setMarginRight( json.get(JSON_FIELD_MARGIN_RIGHT).isString().stringValue());
            setMarginBottom( json.get(JSON_FIELD_MARGIN_BOTTOM).isString().stringValue() );
            setMarginLeft( json.get(JSON_FIELD_MARGIN_LEFT).isString().stringValue() );
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
            
            if( json.get(JSON_FIELD_CHILDREN).isArray() != null ){
                JSONArray group = json.get(JSON_FIELD_CHILDREN).isArray();
                for( int i=0; i < group.size(); i++){
                    JSONObject child = group.get(i).isObject();
                    Field f = new Field();
                    f.fromJSON(child);
                    children.add(f);
                }
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
            json.put( JSON_FIELD_MARGIN, new JSONString( getMargin() ) );
            json.put( JSON_FIELD_MARGIN_TOP, new JSONString(getMarginTop()) );
            json.put( JSON_FIELD_MARGIN_RIGHT, new JSONString(getMarginRight()) );
            json.put( JSON_FIELD_MARGIN_BOTTOM, new JSONString(getMarginBottom()) );
            json.put( JSON_FIELD_MARGIN_LEFT, new JSONString(getMarginLeft()) );
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
            
            if( !children.isEmpty()){
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
    
    }

}
