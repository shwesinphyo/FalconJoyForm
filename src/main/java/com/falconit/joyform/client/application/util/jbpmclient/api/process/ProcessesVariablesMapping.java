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
public class ProcessesVariablesMapping {
    
        
    public interface ProcessesVariablesMappingListener {
        public void success(java.util.Map<String, Object[]> maps);
        public void fail(String message, int stage );
    }
    private ProcessesVariablesMappingListener listener;
    
    public void setListener( ProcessesVariablesMappingListener listener){
        this.listener = listener;
    }
    
    
    public void list( String containerId, String processId ){

        //containers/DevTest_1.0.0-SNAPSHOT/processes/definitions/Leave-Request/tasks/users" -H "accept: application/json"
        String url = Constants.url+ "containers/" + containerId + "/processes/definitions/" + processId + "/tasks/users";
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
                  
                  try {
                        java.util.Map<String, Object[]> maps = new ObjectConverter().fromJSON( result, true, true );
                        listener.success( maps );
                  } catch (Exception ex) { }
                  
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
    "associatedEntities": [
      "#{name}"
    ],
    "taskInputMappings": {
      "reason": "String",
      "Comment": "Object",
      "TaskName": "Object",
      "allowFrom": "java.util.Date",
      "allowTo": "java.util.Date",
      "Skippable": "Object",
      "status": "String"
    },
    "taskOutputMappings": {},
    "task-id": "3",
    "task-name": "Leave Approved",
    "task-priority": 0,
    "task-comment": "Leave request approve",
    "task-created-by": "",
    "task-skippable": false,
    "task-form-name": "leave-approve"
}
*/