/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util;

/**
 *
 * @author apple
 */
public class Constants {
    public static final String containerId = "automation_1.0.0-SNAPSHOT";
    public static final String videoProcessId = "zayattv.video-processes";
    public static final String userProcessId = "user-processes";
    public static final String formProcessId = "form-processes";
    public static final String personProcessId = "customer-processes";
    public static final String campaignProcessId = "LoyaltyManagement.campaign-processes";
    public static final String url = "http://theburmapeople.com:8080/kie-server/services/rest/server/";
    public static final String credentials = "Basic d2JhZG1pbjp3YmFkbWlu";
    public static final String contentType = "application/json";
    public static final java.util.List<String> containerFilter = new java.util.ArrayList<>(); //{"DevTest_1.0.0-SNAPSHOT"};
    public static final java.util.List<String> skipFields = new java.util.ArrayList<>(); //{"DevTest_1.0.0-SNAPSHOT"};
    public static final java.util.List<String> processFilter = new java.util.ArrayList<>(); //{"DevTest_1.0.0-SNAPSHOT"};
    public static final String LANGUAGE = "en";
    public static final String FQDN_KEY_START = "com.";
    public static final String DEFAULT_FQDN = "com.falconit.automation.entity.Customer";
    public static final String DEFAULT_CONTAINER = "automation_1.0.0-SNAPSHOT";
    public static final String DEFAULT_PROCESS = "Personal-Information";
    public static final String DEFAULT_TASK = "Start up";
    public static final String PARENT_TASK = "refertask";
    public static final String OBJECT_TITLE = "objecttitle";
    public static final String PROFILE_CATEGORIES[] = new String[]{"Profile","Contact","Places","Works & education","Documents","Travel info","Family & relationships","Bio-metric","Healthcare","Others"};
            
    public static final String COOKIE_USER_NAME = "un";
    public static final String COOKIE_USER_ID = "uid";
    public static final String COOKIE_USER_PERSON_ID = "cid";
    public static final String COOKIE_USER_CREDENTIAL = "cdt";
    public static final String COOKIE_USER_ROLES = "roles";
    
    static{
        containerFilter.add("DevTest_1.0.0-SNAPSHOT");
        containerFilter.add("automation_1.0.0-SNAPSHOT");
    }
    
    static{
        processFilter.add("company-processes");
        processFilter.add("customer-processes");
        processFilter.add("form-processes");
        processFilter.add("user-processes");
    }
        
    static{
        skipFields.add("formowner");
        skipFields.add("formuser");
        skipFields.add("id");
        skipFields.add("customerId");
        skipFields.add("created");
        skipFields.add("updated");
        skipFields.add("status");
        //skipFields.add("department");
    }
}
