/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util.jbpmclient.api.process;


import com.falconit.joyform.client.application.util.Constants;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

/**
 *
 * @author User
 */
public class ProcessByContainer {
    
        
    public interface ProcessByContainerListener{
        public void success(String result);
        public void fail( String message, int stage);
    }

    private ProcessByContainerListener listener;
    
    public void processesList( String containerId, int first, int max, boolean asc ){
        
        StringBuilder sb = new StringBuilder();
        //containers/automation_1.0.0-SNAPSHOT/processes?page=0&pageSize=10&sortOrder=true" -H "accept: application/json"
        sb.append("&page=" + first );
        sb.append("&pageSize=" + max );
        sb.append("&sortOrder=" + asc );
        String url = Constants.url + "containers/" + containerId +"/processes?" + sb.toString();
        //Window.alert(url);
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, 
                url );
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
                listener.success( response.getText() );
              } else {
                  listener.fail("Request error code=" + response.getStatusCode(), 3);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 3);
        }
    }
     
    public void setListener( ProcessByContainerListener listener){
        this.listener = listener;
    }
}
