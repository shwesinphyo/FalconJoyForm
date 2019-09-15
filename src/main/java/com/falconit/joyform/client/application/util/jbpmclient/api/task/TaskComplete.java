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
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

/**
 *
 * @author apple
 */
public class TaskComplete {
    
    public interface TaskCompleteListener{
        public void success(String result);
        public void fail( String message, int stage);
    }
    private TaskCompleteListener listener;
     
    public void released(String url, String containerId, String taskId, String body ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, 
                url + "containers/" + containerId + "/tasks/" + taskId + "/states/released" );
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        Window.alert("URL=" + url + "containers/" + containerId + "/tasks/" + taskId + "/states/released");
        Window.alert("Body= " + body);
        try {
          Request request = builder.sendRequest( null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 4);
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                //listener.released(containerId, taskId);
                claimed(url, containerId, taskId, body );
              } else {
                listener.fail("Request error code=" + response.getStatusCode(), 4);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 4);
        }
    }   
        
    public void claimed( String url, String containerId, String taskId, String body ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, 
                url + "containers/" + containerId + "/tasks/" + taskId + "/states/claimed" );
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
                started( url, containerId, taskId, body );
              } else {
                listener.fail("Request error code=" + response.getStatusCode(), 5);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 5);
        }
    }

    public void started(String url,  String containerId, String taskId, String body ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, 
                url + "containers/" + containerId + "/tasks/" + taskId + "/states/started" );
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
                completed( url, containerId, taskId, body );
              } else {
                listener.fail("Request error code=" + response.getStatusCode(), 6);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 6);
        }
    }
   
    public void completed(String url,  String containerId, String taskId, String body ){
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, 
                url + "containers/" + containerId + "/tasks/" + taskId + "/states/completed" );
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );

        try {
            if( body == null || body.isEmpty())
                body = "{}";
          Request request = builder.sendRequest( body, new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 7);
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                listener.success( response.getStatusText() );
              } else {
                  listener.fail("Request error code=" + response.getStatusCode(), 7);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 7);
        }
    }
        
    public void setListener( TaskCompleteListener listener){
        this.listener = listener;
    }
    private static native String b64decode(String a) /*-{
        return window.atob(a);
      }-*/;
}
