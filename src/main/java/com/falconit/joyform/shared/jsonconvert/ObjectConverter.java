/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.shared.jsonconvert;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 *
 * @author apple
 */
public class ObjectConverter {
        
    public static final String JSON_INPUT_FIELD_TIMESTAMP = "java.sql.Timestamp";
    public static final String JSON_INPUT_FIELD_DATETIME = "java.util.Date";
    public static final String JSON_INPUT_FIELD_PASSWORD = "password";
    
    public static final String LISTBOX_DEFAULT_VALUE = "defaultValue";
    public static final String TYPE_LISTBOX = "listbox";
    public static final String TYPE_TIMESTAMP = "timestamp";
    public static final String TYPE_DATETIME = "datetime";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_DECIMAL = "decial";
    public static final String TYPE_NUMBER = "number";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_NULL = "null";
    public static final String TYPE_OBJECT = "object";
    public static final String TYPE_ARRAY = "array";
    

    public java.util.Map<String, Object[]> fromJSON( JSONObject json, boolean arrayRecursive, boolean objectRecursive ) throws Exception{
        java.util.Map<String, Object[]> maps = new java.util.HashMap<>();
        
        for( String key : json.keySet()){
            if( json.get(key).isArray() != null && arrayRecursive){
                try{
                    JSONArray array = json.get(key).isArray();
                    if( array!= null && array.size() > 0 ){

                        java.util.List<Object[]> mapArray = new java.util.ArrayList<>();

                        for( int i=0; i < array.size(); i++){
                            JSONObject object = array.get(i).isObject();
                            java.util.Map<String, Object[]> map =  fromJSON( object, arrayRecursive, objectRecursive );
                            mapArray.add( new Object[] { ObjectConverter.TYPE_OBJECT, map });
                        }
                        maps.put(key, new Object[] { ObjectConverter.TYPE_ARRAY, mapArray });
                    }
                }catch(Exception ex){}
            }else if( json.get(key).isObject() != null ){
                if( json.get(key).isObject().get( ObjectConverter.JSON_INPUT_FIELD_TIMESTAMP ) != null ){
                    maps.put(key, new Object[] {
                        ObjectConverter.TYPE_TIMESTAMP, 
                        (long) json.get(key).isObject().get(ObjectConverter.JSON_INPUT_FIELD_TIMESTAMP).isNumber().doubleValue(),
                    } );
                }else if( json.get(key).isObject().get( ObjectConverter.JSON_INPUT_FIELD_DATETIME ) != null ){
                    maps.put(key, new Object[] {
                        ObjectConverter.JSON_INPUT_FIELD_DATETIME, 
                        (long) json.get(key).isObject().get(ObjectConverter.JSON_INPUT_FIELD_DATETIME).isNumber().doubleValue(),
                    });
                }else{
                    if( objectRecursive ){
                        try{
                            JSONObject object = json.get(key).isObject();
                            java.util.Map<String, Object[]> map =  fromJSON( object, arrayRecursive, objectRecursive );
                            maps.put(key, new Object[] { ObjectConverter.TYPE_OBJECT, map });
                        }catch(Exception ex){}
                    }
                }
            }else if( json.get(key).isString() != null ){
                maps.put(key, new Object[] { ObjectConverter.TYPE_STRING, 
                    json.get(key).isString().stringValue()
                } );
            }else if( json.get(key).isNumber() != null ){
                double d = json.get(key).isNumber().doubleValue();
                if( d > (long )d )
                    maps.put(key, new Object[] { ObjectConverter.TYPE_DECIMAL, 
                        json.get(key).isNumber().doubleValue()
                    } );
                else
                    maps.put(key, new Object[] { ObjectConverter.TYPE_NUMBER, 
                        (long) json.get(key).isNumber().doubleValue()
                    } );
                
            }else if( json.get(key).isBoolean() != null ){
                maps.put(key, new Object[] { ObjectConverter.TYPE_BOOLEAN, 
                    json.get(key).isBoolean().booleanValue()
                } );
            }else if( json.get(key).isNull() != null ){
                maps.put(key, new Object[] { ObjectConverter.TYPE_NULL, null } );
//                Window.alert( ObjectConverter.TYPE_NULL + "," + key +"=" +json.get(key).isNull().toString() );
            }else{
                maps.put(key, new Object[] { ObjectConverter.TYPE_NULL,  null } );
            }
            System.out.println( key );
        }
        
        return maps;
    }
     
        
    public JSONObject toJSON( java.util.Map<String, Object[]> maps ) throws Exception{
        
        JSONObject json = new JSONObject( );
        
        for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
            
            if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_TIMESTAMP )){
                JSONObject date = new JSONObject();
                date.put( ObjectConverter.JSON_INPUT_FIELD_TIMESTAMP, new JSONNumber( Long.parseLong( entry.getValue()[1].toString()) ) );
                json.put( entry.getKey(), date );
                //Window.alert( entry.getValue()[0].toString() + "," + entry.getKey() +"=" + Long.parseLong( entry.getValue()[1].toString()) );
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_DATETIME )){
                JSONObject date = new JSONObject();
                date.put( ObjectConverter.JSON_INPUT_FIELD_DATETIME, new JSONNumber( Long.parseLong( entry.getValue()[1].toString()) ) );
                json.put( entry.getKey(), date );
                //Window.alert( entry.getValue()[0].toString() + "," + entry.getKey() +"=" + Long.parseLong( entry.getValue()[1].toString()) );
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_STRING )){
                //Window.alert( entry.getValue()[0].toString() + "," + entry.getKey() +"=" +  entry.getValue()[1].toString() );
                json.put( entry.getKey(), new JSONString(  entry.getValue()[1].toString() )  );
                
            }else if( entry.getValue()[0].toString().equals(ObjectConverter.TYPE_DECIMAL) ){
                //Window.alert( entry.getValue()[0].toString() + "," + entry.getKey() +"=" + Double.parseDouble( entry.getValue()[1].toString() ));
                json.put( entry.getKey(), new JSONNumber( Double.parseDouble( entry.getValue()[1].toString() ) ) );
                
            }else if( entry.getValue()[0].toString().equals(ObjectConverter.TYPE_NUMBER) ){
                //Window.alert( entry.getValue()[0].toString() + "," + entry.getKey() +"=" + Long.parseLong( entry.getValue()[1].toString()));
                json.put( entry.getKey(), new JSONNumber( Long.parseLong( entry.getValue()[1].toString()) ) );
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_BOOLEAN ) ){
               //Window.alert( entry.getValue()[0].toString() + "," + entry.getKey() +"=" + Boolean.parseBoolean( entry.getValue()[1].toString()) );
                json.put( entry.getKey(),  JSONBoolean.getInstance(  Boolean.parseBoolean( entry.getValue()[1].toString()) ) );
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_NULL ) ){
                if(  entry.getValue()[1] != null )
                    json.put( entry.getKey(), new JSONString( entry.getValue()[1].toString() ) );
                else
                    json.put( entry.getKey(), JSONNull.getInstance() );
                
            }
        }
        
        return json;
    }

}
