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
    public static final String campaignProcessId = "LoyaltyManagement.campaign-processes";
    public static final String url = "http://theburmapeople.com:8080/kie-server/services/rest/server/";
    public static final String credentials = "Basic d2JhZG1pbjp3YmFkbWlu";
    public static final String contentType = "application/json";
    public static final java.util.List<String> containerFilter = new java.util.ArrayList<>(); //{"DevTest_1.0.0-SNAPSHOT"};
    public static final java.util.List<String> skipFields = new java.util.ArrayList<>(); //{"DevTest_1.0.0-SNAPSHOT"};
    public static final String LANGUAGE = "en";
    public static final String FQDN_KEY_START = "com.";
    
    static{
        containerFilter.add("DevTest_1.0.0-SNAPSHOT");
        containerFilter.add("automation_1.0.0-SNAPSHOT");
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
