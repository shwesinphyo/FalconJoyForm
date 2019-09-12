/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util.jbpmclient;

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
public class HumanTaskHelper {
    
    public interface HumanTaskHelperListener{
        public void success(String result);
        public void fail( String message, int stage);
//        public void instances(String containerId, String processInstanceId);
//        public void query(String containerId, String taskId);
//        public void task(String containerId, String taskId);
//        public void released(String containerId, String taskId);
//        public void claimed(String containerId, String taskId);
//        public void started(String containerId, String taskId);
//        public void completed(String containerId, String taskId);
        
    }
    private HumanTaskHelperListener listener;
    
    public void startInstances( String processId, String body ){
        startInstances(Constants.url, Constants.containerId, processId, body);
    }
    public void startInstances(String url, String containerId, String processId, String body){
        
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, 
                //Constants.url+ "queries/tasks/instances/process/343" );
        url+ "containers/" + containerId + "/processes/" + processId + "/instances");
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        try {
          Request request = builder.sendRequest( body, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 1);
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                String processInstanceId = response.getText().trim();
                //Window.alert("processInstanceId="+processInstanceId);
                //listener.instances( containerId, processInstanceId);
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
                    //Window.alert("taskId="+taskId);
                    taskContents( containerId, taskId );
                    //listener.query( containerId, taskId);
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
    
    public void taskContents( String containerId, String taskId ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, 
                Constants.url + "containers/" + containerId + "/tasks/" + taskId + "/contents/input" );
        //Window.alert(Constants.url + "containers/" + containerId + "/tasks/" + taskId + "/contents/input");
        
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );
        
        try {
          Request request = builder.sendRequest( null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 3);
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                  //Window.alert("Code="+response.getStatusCode());
                  released( containerId, taskId );
                listener.success( response.getText() );
                //listener.task(containerId, taskId);
                
              } else {
                  listener.fail("Request error code=" + response.getStatusCode(), 3);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 3);
        }
    }
     
    public void released( String containerId, String taskId ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, 
                Constants.url + "containers/" + containerId + "/tasks/" + taskId + "/states/released" );
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        try {
          Request request = builder.sendRequest( null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 4);
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                //listener.released(containerId, taskId);
                claimed( containerId, taskId );
              } else {
                listener.fail("Request error code=" + response.getStatusCode(), 4);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 4);
        }
    }   
        
    public void claimed( String containerId, String taskId ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, 
                Constants.url + "containers/" + containerId + "/tasks/" + taskId + "/states/claimed" );
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        try {
          Request request = builder.sendRequest( null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 5);
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                //listener.claimed(containerId, taskId);
                started( containerId, taskId );
              } else {
                listener.fail("Request error code=" + response.getStatusCode(), 5);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 5);
        }
    }

    public void started( String containerId, String taskId ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, 
                Constants.url + "containers/" + containerId + "/tasks/" + taskId + "/states/started" );
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        try {
          Request request = builder.sendRequest( null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 6);
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                //listener.started(containerId, taskId);
                completed( containerId, taskId );
              } else {
                listener.fail("Request error code=" + response.getStatusCode(), 6);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 6);
        }
    }
   
    public void completed( String containerId, String taskId ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, 
                Constants.url + "containers/" + containerId + "/tasks/" + taskId + "/states/completed" );
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        try {
          Request request = builder.sendRequest( "{}", new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 7);
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                //listener.completed(containerId, taskId);
              } else {
                  listener.fail("Request error code=" + response.getStatusCode(), 7);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 7);
        }
    }
        
    public void setListener(HumanTaskHelperListener listener){
        this.listener = listener;
    }
    private static native String b64decode(String a) /*-{
        return window.atob(a);
      }-*/;
}
