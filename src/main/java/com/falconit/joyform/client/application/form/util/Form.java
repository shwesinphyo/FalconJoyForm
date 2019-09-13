/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;

import com.falconit.joyform.client.application.form.editor.FormEditorView;
import com.falconit.joyform.client.application.form.util.WidgetGenerator.WidgetGeneratorButtonClickListener;
import com.falconit.joyform.client.application.form.util.WidgetGenerator.WidgetGeneratorClickListener;
import com.falconit.joyform.client.application.form.util.WidgetGenerator.WidgetGeneratorMouseListener;
import com.falconit.joyform.shared.jsonconvert.ObjectConverter;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.addins.client.dnd.MaterialDnd;
import gwt.material.design.addins.client.dnd.js.JsDragOptions;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Axis;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialToast;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Form implements java.io.Serializable{
        public static final String JSON_FORM_CHILD = "child";
        public static final String JSON_FORM_ID = "id";
        public static final String JSON_FORM_NAME = "name";
        public static final String JSON_FORM_CONTAINER_ID = "container";
        public static final String JSON_PROCESS_ID = "process";
        public static final String JSON_TASK_ID = "task";
        public static final String JSON_FORM_DATA = "formdata";
        public static final String JSON_FORM_PERMISSION_ACTORS = "actors";
        public static final String JSON_FORM_PERMISSION_GROUPS = "groups";
        public static final String JSON_FORM_CREATED = "created";
        public static final String JSON_FORM_UPDATED = "updated";
        public static final String JSON_FORM_STATUS = "status";
        public static final String JSON_FORM_OBJECT_NAME = "objectName";
        public static final String JSON_FORM_FQDN = "fqdn";
        
        
        public static final String DISPLAY_MODE_READ_ONLY = "readonly";
        public static final String DISPLAY_MODE_FILL_UP = "fillup";
        public static final String DISPLAY_MODE_NO_FRAME_FILL_UP = "noframe";
        public static final String DISPLAY_MODE_DESIGNER = "designer";
        
        private Long id;
        private String name;
        private String container;
        private String process;
        private String task;
        private String mode = DISPLAY_MODE_FILL_UP;
        private boolean mouseOverShadow = false;
        private java.util.Date created;
        private java.util.Date updated;
        private String[] actors;
        private String[] groups;
        private int status = 1;
        private String objectName="";
        private String fqdn="";
        
        
        private boolean draggable = true;
        private java.util.List<Field> child;
        
    public interface FormItemListener{
        public void onClick( Field field, int index );
        public void mouseEnter( Field field, int index );
        public void mouseExit( Field field, int index );
        public void onEditClick( Field field, int index );
        public void onDeleteClick( Field field, int index );
    }
    
    private FormItemListener itemListener;
    public void setItemListener(FormItemListener itemListener){
        this.itemListener = itemListener;
    }
    
    public Form() {
    }

        
    public Form(Long id, String name, String container, String process, String task) {
        this.id = id;
        this.name = name;
        this.container = container;
        this.process = process;
        this.task = task;
    }

        
    public Form(String name, String container, String process, String task) {
        this.name = name;
        this.container = container;
        this.process = process;
        this.task = task;
    }

    public Form(String name, String container, String process, String task, List<Field> child) {
        this.name = name;
        this.container = container;
        this.process = process;
        this.task = task;
        this.child = child;
    }

    public Form(Long id, String name, String container, String process, String task, List<Field> child) {
        this.id = id;
        this.name = name;
        this.container = container;
        this.process = process;
        this.task = task;
        this.child = child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isMouseOverShadow() {
        return mouseOverShadow;
    }

    public void setMouseOverShadow(boolean mouseOverShadow) {
        this.mouseOverShadow = mouseOverShadow;
    }

    public List<Field> getChild() {
        return child;
    }

    public void setChild(List<Field> child) {
        this.child = child;
    }
    
    /**
     * Render the form with related fields and widgets
     * @param holder container widget to draw the form
     */    
    public void render( MaterialPanel holder ){
        
        if( child == null ) return;
        holder.clear( );
        
        for( int i=0; i < child.size(); i++ ){
            Field f = child.get(i);
            if( f.getChildren().isEmpty()){
                --i; 
                child.remove(f); continue;
            }
            MaterialRow row;
            WidgetGenerator generator = new WidgetGenerator();
            eventRegister( generator );
            
            try {
                row = generator.getWidget( f, i );
                generator.createWidget(f, row, i, getMode(), isMouseOverShadow() );
                            
                if( isDraggable()){
                    MaterialDnd.draggable( row, JsDragOptions.create( Axis.VERTICAL ) );
                    row.addDragEndHandler(event -> {
                    MaterialToast.fireToast("Added " );
                    //txtbrief.setText( "" );
                    int count = 0; 
                    for ( Widget w : holder.getChildrenList() ){
                        child.get( count ).setTop( w.getAbsoluteTop() );
                        child.get( count ).setLeft( w.getAbsoluteLeft() );
                        count++;
                    }
                    
                    //itemListener.widget( get( row.getId() ));
                    verticalMove( row, holder );

                   });
                }             
                holder.add( row );
                f.setTop( row.getAbsoluteTop() );
                f.setLeft( row.getAbsoluteLeft() );
                
            } catch (Exception ex) {
                Window.alert(ex.getMessage());
                Logger.getLogger(FormEditorView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    private void eventRegister(WidgetGenerator generator){
        if( getMode().equals(Form.DISPLAY_MODE_DESIGNER)){
            
            generator.setClickListener((Field field, int index) -> {
                if( itemListener != null ) itemListener.onClick(field, index);
            });
            generator.setMouseListener(new WidgetGeneratorMouseListener(){
                @Override
                public void mouseEnter(Field field, int index) {
                    if( itemListener != null ) itemListener.mouseEnter(field, index);
                }

                @Override
                public void mouseExit(Field field, int index) {
                    if( itemListener != null ) itemListener.mouseExit(field, index);
                }
            });
            generator.setButtonListener(new WidgetGeneratorButtonClickListener(){
                @Override
                public void onEditClick(Field field, int index) {
                    if( itemListener != null ) itemListener.onEditClick(field, index);
                }

                @Override
                public void onDeleteClick(Field field, int index) {
                    if( itemListener != null ) itemListener.onDeleteClick(field, index);
                }
            });
        }
    }
    
    private Field get( String id ){
        for ( int i=0; i < child.size(); i++)
            if( child.get(i).getId().equals( id ))
                return child.get(i);
        
        return null;
    }  
    
    /**
     * handling drag and drop movement
     * @param widget moving widget
     * @param holder parent widget of form
     */
    private void verticalMove( MaterialWidget widget, MaterialPanel holder ){
        if( child.size() < 2) return;
        
        try{
            Field field = remove( widget.getId());
            int tmpDistance = 100000;
            int position = -1;
            int topDif = 10000;
            int leftDif = 10000;
            for ( int i=0; i < child.size(); i++ ){
                if( child.get(i).getTop() > field.getTop() && child.get(i).getTop() < tmpDistance ){
                    tmpDistance = child.get(i).getTop();
                    position = i;
                    topDif = child.get(i).getTop() - field.getTop();
                    leftDif = child.get(i).getLeft() - field.getLeft();
                }
                
            }
        
            //if( topDif < 15 && leftDif >=7 ){
                //Window.alert("Column movement top=" + topDif +", left=" + leftDif);
            //}
            
            if( position >= 0 ){
                child.add( position, field );
            }else{
                child.add( field );
            }
            
            render( holder );
        }catch(Exception ex){Window.alert(ex.getMessage());}
    }
       
    private Field remove( String id ){
        for ( int i=0; i < child.size(); i++)
            if( child.get(i).getId().equals( id ))
                return child.remove(i);
        
        return null;
    }
    
    /**
     * grouping the components. Before call this method, the group name must be added to related component
     * e.g. lstItem.get(index).getChildren().get(index).setGroup(groupName);
     * @param holder parent container widget of form
     */
    public void addGroup( MaterialPanel holder ){
        for( int i=0; i < child.size(); i++ ){
            Field f = child.get( i );
            if( !f.getChildren().isEmpty() ){
                // pickup the first child column
                Field children = f.getChildren().get( 0 );
                // check the group name
                if( children.getGroup() != null && !children.getGroup().isEmpty()){
                    boolean found = false;
                    // find the related group name
                    for( int j=i +1; j < child.size(); j++ ){
                        Field relative_child = child.get( j ).getChildren().get( 0 );
                        if( relative_child.getGroup() != null 
                                && !relative_child.getGroup().isEmpty()
                                && children.getGroup().trim().equalsIgnoreCase(relative_child.getGroup().trim()) ){
                            Field r = child.remove( j );
                            f.getChildren().addAll( r.getChildren() );
                            j--;
                            found = true;
                        }
                    }
                    // remove from group name for single component
                    if( !found && f.getChildren().size() == 1){
                        children.setGroup("");
                    }
                }
                
                // sorting by index
                Collections.sort( child, new SortByIndex());
            }
        }
        render( holder );
    }
    
    public void removeGroup( int rowIndex, int columnIndex, MaterialPanel holder ){
        
        if( rowIndex < 0 || columnIndex < 0 || rowIndex > child.size() - 1) return;
        
        Field row = child.get( rowIndex );
        if( row.getChildren( ).size() <= 1 || columnIndex > row.getChildren( ).size() - 1) return;
        
        if( columnIndex == row.getChildren().size() - 1){
            rowIndex++;
        }
        Field col = row.getChildren( ).remove( columnIndex );
        
        Field f = new Field( col.getId( ), col.getLabel( ), col.getName( ) );
        f.getChildren( ).add( col );
        child.add( rowIndex, f );
        
        render( holder );
    }
       
    class SortByIndex implements Comparator<Field> {
        @Override
        public int compare(Field a, Field b) {
            return a.getIndex() - b.getIndex(); 
        } 
    } 
    
    /**
     * Form design generating from JSON object
     * @param json object of form design
     * @param holder container type widget to draw the form
     * @throws Exception 
     */
    public void fromJSON( JSONObject json, MaterialPanel holder) throws Exception {
        fromJSON( json );
        render ( holder );
    }
      /**
       * Form design generating from JSON object
       * @param json object of form design
       * @throws Exception 
       */
    public void fromJSON( JSONObject json )throws Exception{
        
          if( json.get(JSON_FORM_ID)!= null && json.get(JSON_FORM_ID).isNumber() != null )
            setId( (long) json.get(JSON_FORM_ID).isNumber().doubleValue());
          
          setName( json.get(JSON_FORM_NAME).isString().stringValue());
          setContainer( json.get(JSON_FORM_CONTAINER_ID).isString().stringValue());
          setProcess( json.get(JSON_PROCESS_ID).isString().stringValue());
          setTask( json.get(JSON_TASK_ID).isString().stringValue());
          
          JSONArray group = json.get(JSON_FORM_CHILD).isArray();
          //Window.alert( "Child size=" + group.size() );
            child = new java.util.ArrayList<>();
            for( int i=0; i < group.size(); i++){
                //Window.alert("Index=" + i);
                JSONObject objField = group.get(i).isObject();
                //Window.alert("From parent=" + objField.toString() );
                Field field = new Field();
                field.fromJSON( objField );
                child.add( field );
                //Window.alert("Child added=" + child.size() );
                
            }
            //Window.alert("Child size after reading=" + child.size());
            
      }

    /**
     * Form design to converting JSON object
     * @return JSON object
     * @throws Exception 
     */
    public JSONObject toJSON() throws Exception{
          JSONObject json = new JSONObject( );
          
          if( getId() != null )
            json.put( JSON_FORM_ID, new JSONNumber(getId()) );
          
          json.put( JSON_FORM_NAME, new JSONString(getName()) );
          json.put( JSON_FORM_CONTAINER_ID, new JSONString(getContainer()) );
          json.put( JSON_PROCESS_ID, new JSONString(getProcess()) );
          json.put( JSON_TASK_ID, new JSONString(getTask()) );
                      
          if( !child.isEmpty()){
            JSONArray group = new JSONArray();
            int count=0;
            for( Field f : child){
                JSONObject objChild = f.toJSON();
                group.set(count++, objChild);
            }
            json.put( JSON_FORM_CHILD, group );
        }
          
          return json;
      }
      
    /**
     * Picking up form field values and convert to JSON object for data processing in KIE server via API
     * @return JSON object for KIE server for data processing
     * @throws Exception 
     */
    public JSONObject getOutputDataForTask() throws Exception{
          java.util.Map<String, Object[]> maps = new java.util.HashMap<>();
          
          for( Field child : child ){
              for ( Field children : child.getChildren()){
                  maps.put( children.getName(), children.getBindValue());
              }
          }
          
          return new ObjectConverter().toJSON(maps);
      }
      
    /**
     * Filling data from Task Instance of KIE server
     * @param json Task JSON object from KIE server
     * @throws Exception 
     */
    public void bindWithTaskData( JSONObject json )throws Exception{
        java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( json );
        
        for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
            Outer: for( Field child : child ){
              for ( Field children : child.getChildren()){
                  if( children.getName().equals(entry.getKey())){
                      children.setValue( entry.getValue()[1] );
                      break Outer;
                  }
              }
            }
        }
    }
}
