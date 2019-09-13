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
import com.google.gwt.http.client.Response;

/**
 *
 * @author User
 */
public class APIHelper {
    
        
    public interface APIHelperListener{
        public void success(String result);
        public void fail( String message, int stage);
    }
    
    public static final String STATUS_CREATED = "Created";
    public static final String STATUS_READY = "Ready";
    public static final String STATUS_RESERVED = "Reserved";
    public static final String STATUS_INPROGRESS = "InProgress";
    public static final String STATUS_SUSPENDED = "Suspended";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_FAILED = "Failed";
    public static final String STATUS_ERROR = "Error";
    public static final String STATUS_EXITED = "Exited";
    public static final String STATUS_OBSOLETE = "Obsolete";
    
    
    private APIHelperListener listener;
    
    public void tasksList( String status[], int first, int max, String user, String sortField, boolean asc ){
        
        
        //user=wbadmin&page=0&pageSize=10&sortOrder=true" -H "accept: application/json"
        
        StringBuilder sb = new StringBuilder();
        for( String s : status ){
            sb.append( sb.toString().isEmpty()? "status=" + s : "&status=" + s );
        }
        if( user != null )
            sb.append("&user=" + user );
        
        sb.append("&page=" + first );
        sb.append("&pageSize=" + max );
        sb.append("&sortOrder=" + asc );
        String url = Constants.url+ "queries/tasks/instances/admins?" + sb.toString();
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
     
    public void setListener(APIHelperListener listener){
        this.listener = listener;
    }
}
