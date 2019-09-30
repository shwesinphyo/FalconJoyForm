/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.profile.personal;


import com.falconit.joyform.client.application.util.Constants;
import com.falconit.joyform.client.application.util.jbpmclient.HumanTaskHelper;
import com.falconit.joyform.shared.jsonconvert.ObjectConverter;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;

/**
 *
 * @author User
 */
public class PersonCRUD {
    /**
     * 
     * @param form created form object to save or update
     * @param isSave true for saving (don't add Id allow it null)
     */
    public void saveUpdate( java.util.Map<String, Object[]> maps, boolean isSave ){
        try{
            
            JSONObject json = new ObjectConverter().toJSON( maps );
            
            HumanTaskHelper helper = new HumanTaskHelper();
            helper.setListener(new HumanTaskHelper.HumanTaskHelperListener() {
                @Override
                public void success(String result) {

                    JSONObject jsonOnlineUser = JSONParser.parseStrict( result ).isObject();

                    JSONObject obj = jsonOnlineUser.get("object").isObject();
                    JSONObject objForm = obj.get("com.falconit.automation.entity.Customer").isObject();
                    try {
                        if( objForm != null ){
                            listener.success("Success");
                            if( isSave ){
                                java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm, false, false );
                                listener.success(maps);
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
            objForm.put("Customer", json);

            JSONObject obj = new JSONObject();
            if( isSave ){
                obj.put("action", new JSONString( "create" ));
            } else{
                obj.put("action", new JSONString( "update" ));
            }
            
            obj.put("object", objForm);
          
            helper.startInstances( Constants.personProcessId, obj.toString( ) );
        }catch(Exception ex){ex.printStackTrace();}
    }

    /**
     * Extract with container name
     * @param contaner  container name
     */
    public void getBy(String contaner ){
        //getBy( contaner, null, null, -1, 0 );
    }
    
    /**
     * Extract with container name and process name
     * @param contaner container name
     * @param process process name
     */
    public void getBy(String contaner, String process){
        //getBy( contaner, process, null, -1, 0 );
    }
    
    /**
     * Extract with container name, process name and task name
     * @param contaner container name
     * @param process process name
     * @param task task name
     */
    public void getBy(String contaner, String process, String task ){
        //getBy( contaner, process, task, -1, 0 );
    }
    
    /**
     * Extract with container name, process name and task with limit and offset
     * @param contaner container name or null for all forms
     * @param process process name, use null when container name was null
     * @param task task name, use null when container name and process name null
     * @param first offset value
     * @param max limit value
     */
    /*
    public void getBy(String contaner, String process, String task, int first, int max  ){
        java.util.List<java.util.Map<String, Object[]>> lst = new java.util.ArrayList<>( );
        
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
                            JSONObject objForm = obj.get("com.falconit.automation.entity.Customer").isObject();
                            try {
                                if( objForm != null ){
                                    listener.success("Success");
                                    
                                    //Window.alert("Result=" + objForm.toString());
                                    java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm, false, false );
                                    //Window.alert("Map size=" + maps.size());
                                    lst.add( maps );
                                }else{
                                    listener.fail( "Failed to get" );
                                }
                            } catch (Exception ex) {
                                listener.fail( ex.getMessage() );
                            }
                        }
                    }
                        
                    listener.success( lst );
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
                sb.append(" where f.container='" + contaner + "'");
            }
            
            if( process != null && !process.isEmpty() ){
                sb.append(" AND f.process='" + process + "'");
            }
            
            if( task != null && !task.isEmpty() ){
                sb.append( " AND f.task='" + task + "'" );
            }
            //sb.append( " WHERE f.status=1" );
            obj.put("query", new JSONString( sb.toString() ));
            
            if( max > 0)
                obj.put("limit", new JSONNumber( max ) );
            if( first > -1)
                obj.put("offset", new JSONNumber( first ) );

            //Window.alert("Request= " + obj.toString());
            helper.startInstances( Constants.formProcessId, obj.toString( ) );
        }catch(Exception ex){ex.printStackTrace();}
    }
*/
    public void list( ){
        list( -1, 0 );
    }
    
    public void list( int first, int max  ){
        java.util.List<java.util.Map<String, Object[]>> lst = new java.util.ArrayList<>();
        
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
                            JSONObject objForm = obj.get("com.falconit.automation.entity.Customer").isObject();
                            try {
                                if( objForm != null ){
                                    listener.success("Success");
                                    java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm, false, false );
                                    lst.add( maps );
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

            helper.startInstances( Constants.personProcessId, obj.toString( ) );
        }catch(Exception ex){ex.printStackTrace();}
    }
    
    
    public void get( long customerId ){
        try{
            
            JSONObject json = new JSONObject();
            json.put( "id", new JSONNumber(customerId) );
            
            HumanTaskHelper helper = new HumanTaskHelper();
            helper.setListener(new HumanTaskHelper.HumanTaskHelperListener() {
                @Override
                public void success(String result) {

                    JSONObject jsonOnlineUser = JSONParser.parseStrict( result ).isObject();

                    JSONObject obj = jsonOnlineUser.get("object").isObject();
                    JSONObject objForm = obj.get("com.falconit.automation.entity.Customer").isObject();
                    try {
                        if( objForm != null ){
                            listener.success("Success");
                            java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm, false, false );
                            listener.success( maps );
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
            objForm.put("Customer", json);

            JSONObject obj = new JSONObject();
            obj.put("action", new JSONString( "get" ));
            obj.put("object", objForm);
            
            helper.startInstances( Constants.personProcessId, obj.toString( ) );
        }catch(Exception ex){
            Window.alert(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
        
    public void solveFQDN( String query, String fqdn  ){
        
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
                            JSONObject objForm = obj.get( fqdn ).isObject();
                            try {
                                if( objForm != null ){
                                    listener.success("Success");
                                    java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( objForm, false, false );
                                    listener.fqdn( maps );
                                }else{
                                    listener.fail( "Failed to get" );
                                }
                            } catch (Exception ex) {
                                listener.fail( ex.getMessage() );
                            }
                        }
                        
                    }
                }

                @Override
                public void fail(String message, int stage) {
                    listener.fail( message );
                }
            });

            JSONObject obj = new JSONObject();
            obj.put("action", new JSONString( "query" ));
            obj.put("query", new JSONString( query ));

            helper.startInstances( Constants.formProcessId, obj.toString( ) );
        }catch(Exception ex){ex.printStackTrace();}
    }
     
    
    public interface CRUDListener{
        public void success(String result);
        public void success(java.util.Map<String, Object[]> result);
        public void success(java.util.List<java.util.Map<String, Object[]>> result);
        public void fail( String message);
        public void fqdn( java.util.Map<String, Object[]> maps );
    }
    
    private CRUDListener listener;
    public void setListener( CRUDListener listener ){
        this.listener = listener;
    }
}
