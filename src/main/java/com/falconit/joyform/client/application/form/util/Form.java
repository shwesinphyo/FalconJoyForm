/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import java.util.List;

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
        
        private Long id;
        private String name;
        private String container;
        private String process;
        private String task;
        private java.util.List<Field> child;

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

    public List<Field> getChild() {
        return child;
    }

    public void setChild(List<Field> child) {
        this.child = child;
    }
        
      public void fromJSON( JSONObject json )throws Exception{
        
          setId( (long) json.get(JSON_FORM_ID).isNumber().doubleValue());
          setName( json.get(JSON_FORM_NAME).isString().stringValue());
          setContainer( json.get(JSON_FORM_CONTAINER_ID).isString().stringValue());
          setProcess( json.get(JSON_PROCESS_ID).isString().stringValue());
          setTask( json.get(JSON_TASK_ID).isString().stringValue());
          
          JSONArray group = json.get(JSON_FORM_CHILD).isArray();
            for( int i=0; i < group.size(); i++){
                JSONObject objField = group.get(i).isObject();
                Field field = new Field();
                field.fromJSON( objField );
                child.add( field );
            }
      }
      
      public JSONObject toJSON() throws Exception{
          JSONObject json = new JSONObject( );
          
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
}
