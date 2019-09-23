/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util.jbpmclient.api.task;



import com.falconit.joyform.client.application.util.Constants;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

/**
 *
 * @author apple
 */
public class TaskStarter {
    
    public interface TaskStarterListener{
        public void success( String processInstanceId );
        public void fail( String message, int stage);
    }
    private TaskStarterListener listener;
    
    public void startInstances( String processId, String body, boolean assign ){
        startInstances(Constants.url, Constants.containerId, processId, body, assign);
    }
    public void startInstances(String url, String containerId, String processId, String body, boolean assign){
        
        if( body == null || body.isEmpty())
            body = "{}";
        
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, 
                //Constants.url+ "queries/tasks/instances/process/343" );
        url+ "containers/" + containerId + "/processes/" + processId + "/instances");
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        try {
          Request request = builder.sendRequest( body, new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 1);
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                String processInstanceId = response.getText().trim();
                if ( assign )
                    listener.success(processInstanceId);
                else
                    query( containerId, processInstanceId );
                
              } else {
                  listener.fail("Request error code=" + response.getStatusCode(), 1);
              }
            }
          });
        } catch (RequestException e) {
            listener.fail("Request error code=" + e.getMessage(), 1);
        }
    }
        
    
    public void query(String containerId, String processInstanceId ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, 
                Constants.url+ "queries/tasks/instances/process/" + processInstanceId );
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        try {
          Request request = builder.sendRequest( null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 2);
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                
                JSONObject jsonOnlineUser = JSONParser.parseStrict( response.getText() ).isObject();
                JSONArray arr = jsonOnlineUser.get("task-summary").isArray();
                if( arr != null && arr.size() > 0){
                    JSONObject obj = arr.get(0).isObject();
                    String taskId = obj.get("task-id").toString().trim();
                    String taskName = obj.get("task-name").isString().stringValue();
                    listener.success( taskId +":" + taskName);
                }else {
                    listener.fail("Null error, code=" + response.getStatusCode(), 2);
                  }
              } else {
                  listener.fail("Request error code=" + response.getStatusCode(), 2);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 2);
        }
    }
    
    
    public void setListener( TaskStarterListener listener ){
        this.listener = listener;
    }
    private static native String b64decode(String a) /*-{
        return window.atob(a);
      }-*/;
}
