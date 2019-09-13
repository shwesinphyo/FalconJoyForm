/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;

import com.falconit.joyform.client.application.util.Constants;
import com.falconit.joyform.client.application.util.jbpmclient.HumanTaskHelper;
import com.falconit.joyform.shared.jsonconvert.ObjectConverter;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;

/**
 *
 * @author User
 */
public class FormCRUD {
    /**
     * 
     * @param form created form object to save or update
     * @param isSave true for saving (don't add Id allow it null)
     */
    public void saveUpdate( Form form, boolean isSave ){
        try{
            
            if( isSave)
                form.setId( null );
            JSONObject json = new ObjectConverter().toJSON( fillData( form ) );
            
            HumanTaskHelper helper = new HumanTaskHelper();
            helper.setListener(new HumanTaskHelper.HumanTaskHelperListener() {
                @Override
                public void success(String result) {

                    JSONObject jsonOnlineUser = JSONParser.parseStrict( result ).isObject();

                    JSONObject obj = jsonOnlineUser.get("object").isObject();
                    JSONObject objForm = obj.get("com.falconit.automation.entity.Form").isObject();
                    try {
                        if( objForm != null ){
                            listener.success("Success");
                            if( isSave ){
                                java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm );
                                    form.setId( getFormBack( maps ).getId() );
                            }
                        }else{
                            listener.fail( "Failed to save" );
                        }
                    } catch (Exception ex) {
                        listener.fail( ex.getMessage() );
                    }
                }

                @Override
                public void fail(String message, int stage) {
                    listener.fail( message );
                }
            });

            JSONObject objForm = new JSONObject();
            objForm.put("Form", json);

            JSONObject obj = new JSONObject();
            if( isSave ){
                obj.put("action", new JSONString( "create" ));
            }
            else{
                obj.put("action", new JSONString( "update" ));
            }
            
            obj.put("object", objForm);

            helper.startInstances( Constants.formProcessId, obj.toString( ) );
        }catch(Exception ex){ex.printStackTrace();}
    }

    /**
     * Extract with container name
     * @param contaner  container name
     */
    public void getBy(String contaner ){
        getBy( contaner, null, null, -1, 0 );
    }
    
    /**
     * Extract with container name and process name
     * @param contaner container name
     * @param process process name
     */
    public void getBy(String contaner, String process){
        getBy( contaner, process, null, -1, 0 );
    }
    
    /**
     * Extract with container name, process name and task name
     * @param contaner container name
     * @param process process name
     * @param task task name
     */
    public void getBy(String contaner, String process, String task ){
        getBy( contaner, process, task, -1, 0 );
    }
    
    /**
     * Extract with container name, process name and task with limit and offset
     * @param contaner container name or null for all forms
     * @param process process name, use null when container name was null
     * @param task task name, use null when container name and process name null
     * @param first offset value
     * @param max limit value
     */
    public void getBy(String contaner, String process, String task, int first, int max  ){
        java.util.List<Form> lst = new java.util.ArrayList<>();
        
        try{
            HumanTaskHelper helper = new HumanTaskHelper();
            helper.setListener(new HumanTaskHelper.HumanTaskHelperListener() {
                @Override
                public void success(String result) {

                    JSONObject jsonOnlineUser = JSONParser.parseStrict( result ).isObject();
                    JSONArray groups = jsonOnlineUser.get("results").isArray();
                    
                    if( groups != null && groups.size() > 0 ){
                                            
                        for( int i=0; i < groups.size(); i++){
                            
                            JSONObject obj = groups.get(i).isObject();
                            JSONObject objForm = obj.get("com.falconit.automation.entity.Form").isObject();
                            try {
                                if( objForm != null ){
                                    listener.success("Success");
                                    java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm );
                                    lst.add( getFormBack( maps ) );
                                }else{
                                    listener.fail( "Failed to get" );
                                }
                            } catch (Exception ex) {
                                listener.fail( ex.getMessage() );
                            }
                        }
                        
                        listener.success( lst );
                    }
                }

                @Override
                public void fail(String message, int stage) {
                    listener.fail( message );
                }
            });

            JSONObject obj = new JSONObject();
            obj.put("action", new JSONString( "query" ));
            
            StringBuffer sb = new StringBuffer();
            sb.append("from Form f");
            if( contaner!=null && !contaner.isEmpty()){
                sb.append(" where f.container='" + contaner+"'");
            }
            
            if( process != null && !process.isEmpty() ){
                sb.append(" AND f.process='" + process+"'");
            }
            
            if( task != null && !task.isEmpty() ){
                sb.append(" AND f.task='" + task+"'");
            }
            
            if( max > 0)
                obj.put("limit", new JSONNumber( max ) );
            if( first > -1)
                obj.put("offset", new JSONNumber( first ) );

            helper.startInstances( Constants.formProcessId, obj.toString( ) );
        }catch(Exception ex){ex.printStackTrace();}
    }

    public void list( ){
        list( -1, 0 );
    }
    
    public void list( int first, int max  ){
        java.util.List<Form> lst = new java.util.ArrayList<>();
        
        try{
            HumanTaskHelper helper = new HumanTaskHelper();
            helper.setListener(new HumanTaskHelper.HumanTaskHelperListener() {
                @Override
                public void success(String result) {

                    JSONObject jsonOnlineUser = JSONParser.parseStrict( result ).isObject();
                    JSONArray groups = jsonOnlineUser.get("results").isArray();
                    
                    if( groups != null && groups.size() > 0 ){
                                            
                        for( int i=0; i < groups.size(); i++){
                            
                            JSONObject obj = groups.get(i).isObject();
                            JSONObject objForm = obj.get("com.falconit.automation.entity.Form").isObject();
                            try {
                                if( objForm != null ){
                                    listener.success("Success");
                                    java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm );
                                    lst.add( getFormBack( maps ) );
                                }else{
                                    listener.fail( "Failed to get" );
                                }
                            } catch (Exception ex) {
                                listener.fail( ex.getMessage() );
                            }
                        }
                        
                        listener.success( lst );
                    }
                }

                @Override
                public void fail(String message, int stage) {
                    listener.fail( message );
                }
            });

            JSONObject obj = new JSONObject();
            obj.put("action", new JSONString( "list" ));
            
            if( max > 0)
                obj.put("limit", new JSONNumber( max ) );
            if( first > -1)
                obj.put("offset", new JSONNumber( first ) );

            helper.startInstances( Constants.formProcessId, obj.toString( ) );
        }catch(Exception ex){ex.printStackTrace();}
    }
      
    public void get( long formId ){
        try{
            
            JSONObject json = new JSONObject();
            json.put( Form.JSON_FORM_ID, new JSONNumber(formId) );
            
            HumanTaskHelper helper = new HumanTaskHelper();
            helper.setListener(new HumanTaskHelper.HumanTaskHelperListener() {
                @Override
                public void success(String result) {

                    JSONObject jsonOnlineUser = JSONParser.parseStrict( result ).isObject();

                    JSONObject obj = jsonOnlineUser.get("object").isObject();
                    JSONObject objForm = obj.get("com.falconit.automation.entity.Form").isObject();
                    try {
                        if( objForm != null ){
                            listener.success("Success");
                            java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm );
                            listener.success( getFormBack( maps ) );
                        }else{
                            listener.fail( "Failed to get" );
                        }
                    } catch (Exception ex) {
                        listener.fail( ex.getMessage() );
                    }
                }

                @Override
                public void fail(String message, int stage) {
                    listener.fail( message );
                }
            });

            JSONObject objForm = new JSONObject( );
            objForm.put("Form", json);

            JSONObject obj = new JSONObject();
            obj.put("action", new JSONString( "get" ));
            obj.put("object", objForm);

            helper.startInstances( Constants.formProcessId, obj.toString( ) );
        }catch(Exception ex){ex.printStackTrace();}
    }
    
    private java.util.Map<String, Object[]> fillData( Form form ){
        java.util.Map<String, Object[]> maps = new java.util.HashMap<>();
        
        try{
            if( form.getId() != null ){
                maps.put( Form.JSON_FORM_ID, new Object[] { ObjectConverter.TYPE_NUMBER, form.getId()} );
            }

            maps.put( Form.JSON_FORM_NAME, new Object[] { ObjectConverter.TYPE_STRING, form.getName()} );

            maps.put( Form.JSON_FORM_CONTAINER_ID, new Object[] { ObjectConverter.TYPE_STRING, form.getContainer()} );
            maps.put( Form.JSON_PROCESS_ID, new Object[] { ObjectConverter.TYPE_STRING, form.getProcess()} );
            maps.put( Form.JSON_TASK_ID, new Object[] { ObjectConverter.TYPE_STRING, form.getTask()} );
            maps.put( Form.JSON_FORM_DATA, new Object[] { ObjectConverter.TYPE_STRING, form.toJSON().toString()} );
            
            StringBuffer sb = new StringBuffer();
            for( String actor : form.getActors() ){
                sb.append(sb.toString().isEmpty() ? actor : "," + actor);
            }
            maps.put( Form.JSON_FORM_PERMISSION_ACTORS, new Object[] { ObjectConverter.TYPE_STRING, sb.toString()} );
            
            sb = new StringBuffer();
            for( String group : form.getGroups() ){
                sb.append( sb.toString().isEmpty() ? group : "," + group );
            }
            maps.put( Form.JSON_FORM_PERMISSION_GROUPS, new Object[] { ObjectConverter.TYPE_STRING, sb.toString()} );
            
            maps.put( Form.JSON_FORM_CREATED, new Object[] { ObjectConverter.TYPE_TIMESTAMP, 
                form.getCreated() == null ? new java.util.Date().getTime() : form.getCreated().getTime()} );
            
            maps.put( Form.JSON_FORM_UPDATED, new Object[] { ObjectConverter.TYPE_TIMESTAMP, 
                form.getUpdated() == null ? new java.util.Date().getTime() : form.getUpdated().getTime()} );
            
            maps.put( Form.JSON_FORM_STATUS, new Object[] { ObjectConverter.TYPE_NUMBER, form.getStatus()} );
            
            maps.put( Form.JSON_FORM_OBJECT_NAME, new Object[] { ObjectConverter.TYPE_NUMBER, form.getObjectName()} );
            
            maps.put( Form.JSON_FORM_FQDN, new Object[] { ObjectConverter.TYPE_NUMBER, form.getFqdn()} );
            
        
        }catch(Exception ex){}
        
        return maps;
    }

    private Form getFormBack( java.util.Map<String, Object[]> maps ){
        Form form = new Form();
        
        try{
            form.setId( (long) maps.get( Form.JSON_FORM_ID )[1] );
            form.setName( (String) maps.get( Form.JSON_FORM_NAME )[1] );
            form.setContainer( (String) maps.get( Form.JSON_FORM_CONTAINER_ID )[1] );
            form.setProcess( (String) maps.get( Form.JSON_PROCESS_ID )[1] );
            form.setTask( (String) maps.get( Form.JSON_TASK_ID )[1] );
            //form.setContainer( (String) maps.get( Form.JSON_FORM_DATA )[1] );
            
            form.setFqdn( (String) maps.get( Form.JSON_FORM_FQDN )[1] );
            form.setObjectName( (String) maps.get( Form.JSON_FORM_OBJECT_NAME )[1] );
            form.setCreated( new java.util.Date( (long) maps.get( Form.JSON_FORM_CREATED )[1]) );
            form.setUpdated(new java.util.Date( (long) maps.get( Form.JSON_FORM_UPDATED )[1] ));
            
            form.setActors( ((String) maps.get( Form.JSON_FORM_PERMISSION_ACTORS )[1]).split(",") );
            form.setGroups( ((String) maps.get( Form.JSON_FORM_PERMISSION_GROUPS )[1]).split(",") );
            form.setStatus( (int) maps.get( Form.JSON_FORM_STATUS )[1] );
            
            
            form.fromJSON( JSONParser.parseStrict( (String) maps.get( Form.JSON_FORM_DATA )[1] ).isObject() );
            
            
        }catch(Exception ex){}
        
        return form;
    }
    
    public interface CRUDListener{
        public void success(String result);
        public void success(Form result);
        public void success(java.util.List<Form> result);
        public void fail( String message);
    }
    
    private CRUDListener listener;
    public void setListener( CRUDListener listener ){
        this.listener = listener;
    }
}
