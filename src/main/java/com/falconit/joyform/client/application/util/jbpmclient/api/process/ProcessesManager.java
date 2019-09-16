/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util.jbpmclient.api.process;


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
public class ProcessesManager {
    
    public interface ProcessesManagerListener{
        public void success(java.util.Map<String, Object[]> maps);
        public void fail(String message, int stage );
    }
    private ProcessesManagerListener listener;
    
    public void setListener( ProcessesManagerListener listener){
        this.listener = listener;
    }

    public void list( String page, String pageSize ){

        StringBuilder sb = new StringBuilder();
        
        if( page != null && !page.isEmpty()){
            sb.append("?page=" + page );
            sb.append("&pageSize=" + pageSize );
        }
        

        String url = Constants.url+ "queries/processes/definitions" + sb.toString();
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, 
                url );
        //Window.alert(Constants.url + "containers/" + containerId + "/tasks/" + taskId + "/contents/input");
        
        builder.setHeader( "Content-Type", Constants.contentType );
        builder.setHeader( "Authorization", Constants.credentials );
        
        try {
          Request request = builder.sendRequest( null, new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                listener.fail("Request error", 3);
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode() || 201 == response.getStatusCode() ) {
                  JSONObject result = JSONParser.parseStrict( response.getText() ).isObject();
                  
                  //JSONArray repos = result.get("response").isArray();
                  //result = repos.get(0).isObject();
                  try {   
                          java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( result, true, true );
                          listener.success( maps );
                  } catch (Exception ex) {
                      
                  }
                  
              } else {
                  listener.fail("Request error code=" + response.getStatusCode(), 3);
              }
            }
          });
        } catch ( Exception e) {
            listener.fail("Request error code=" + e.getMessage(), 3);
        }
    }
}

/*
{
      "associatedEntities": null,
      "serviceTasks": null,
      "processVariables": null,
      "reusableSubProcesses": null,
      "nodes": null,
      "timers": null,
      "process-id": "zayattv.category-processes",
      "process-name": "category-processes",
      "process-version": "1.0",
      "package": "com.zwaregroup.zayattv",
      "container-id": "zayattv_1.0.1-SNAPSHOT",
      "dynamic": false
    },
*/