/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util.jbpmclient.api;

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
public class ContainerManager {
    
    public interface ContainerManagerListener{
        public void success(java.util.Map<String, Object[]> maps);
        public void fail(String message, int stage );
    }
    private ContainerManagerListener listener;
    
    public void setListener(ContainerManagerListener listener){
        this.listener = listener;
    }
        
    public void list( ){
        list( null, null, null, null );
    }
    
    public void list( String status ){
        list(  null,  null,  null, status );
    }
    
    public void list( String groupId, String artifactId, String version ){
        list(  groupId,  artifactId,  version, null );
    }
    
    public void list( String groupId, String artifactId, String version, String status ){

        StringBuilder sb = new StringBuilder();
        
        if( groupId != null && !groupId.isEmpty()){
            sb.append("?groupId=" + groupId );
            sb.append("&artifactId=" + artifactId );
            sb.append("&version=" + version );
        }
        
        if( status != null && !status.isEmpty()){
            if( !sb.toString().isEmpty() )
                sb.append("&");
            sb.append("?status=" + status );
        }
        
        String url = Constants.url+ "containers" + sb.toString();
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
                  JSONObject result = JSONParser.parseStrict( response.getText() ).isObject();
                  
                  //JSONArray repos = result.get("response").isArray();
                  //result = repos.get(0).isObject();
                  try {
                      if( result.get("type").isString().stringValue().equalsIgnoreCase("SUCCESS")){
                          JSONObject result1 = result.get("result").isObject();
                          
                          JSONObject result2 = result1.get("kie-containers").isObject();
                            
                          java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( result2 , true, true );
                          
                          //JSONArray arrJson = result.get("kie-container").isArray();
                          listener.success( maps );
                      }
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
    "container-id": "DevTest_1.0.0-SNAPSHOT",
    "release-id": {
      "group-id": "com.falconit",
      "artifact-id": "DevTest",
      "version": "1.0.0-SNAPSHOT"
    },
    "resolved-release-id": {
      "group-id": "com.falconit",
      "artifact-id": "DevTest",
      "version": "1.0.0-SNAPSHOT"
    },
    "status": "STARTED",
    "scanner": {
      "status": "DISPOSED",
      "poll-interval": null
    },
    "config-items": [
      {
        "itemName": "KBase",
        "itemValue": "",
        "itemType": "BPM"
      },
      {
        "itemName": "KSession",
        "itemValue": "",
        "itemType": "BPM"
      },
      {
        "itemName": "MergeMode",
        "itemValue": "MERGE_COLLECTIONS",
        "itemType": "BPM"
      },
      {
        "itemName": "RuntimeStrategy",
        "itemValue": "SINGLETON",
        "itemType": "BPM"
      }
    ],
    "messages": [
      {
        "severity": "INFO",
        "timestamp": {
          "java.util.Date": 1568489050105
        },
        "content": [
          "Release id successfully updated for container DevTest_1.0.0-SNAPSHOT"
        ]
      }
    ],
    "container-alias": "DevTest"
},
*/