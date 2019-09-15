/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util.jbpmclient.api.task;

import com.falconit.joyform.client.application.util.Constants;
import com.falconit.joyform.shared.jsonconvert.ObjectConverter;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

/**
 *
 * @author User
 */
public class TaskInputDataReader {
    
    public interface TaskInputDataReaderListener{
        public void result( String taskName, String nodeName, String Skippable, String actor, java.util.Map<String, Object[]> maps);
        public void fail(String message);
    }
    
    TaskInputDataReaderListener listener;
    public void setListener( TaskInputDataReaderListener listener ){
        this.listener = listener;
    }
    
    public void read( String container, String task  ){
        //containers/zayattv_1.0.1-SNAPSHOT/tasks/355/contents/input" -H "accept: application/json"
        String url = Constants.url+ "containers/" + container+"/tasks/" + task + "/contents/input";
        //Window.alert(url);
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url );
        //Window.alert( url );
        
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );
        
        try {
          Request request = builder.sendRequest( null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error");
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                  JSONObject result = JSONParser.parseStrict( response.getText() ).isObject();
                  try {
                      java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( result, true, true );
                      listener.result( 
                              maps.get("TaskName") != null ? maps.remove("TaskName")[1].toString(): null, 
                              maps.get("NodeName") != null ? maps.remove("NodeName")[1].toString() : null, 
                              maps.get("Skippable") != null ? maps.remove("Skippable")[1].toString() : null,
                              maps.get("ActorId") != null ? maps.remove("ActorId")[1].toString() : null,
                              maps);
                      
                  } catch (Exception ex) {
                      
                  }
              } else {
                  listener.fail("Request error code=" + response.getStatusCode());
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage() );
        }
    }
     
}
