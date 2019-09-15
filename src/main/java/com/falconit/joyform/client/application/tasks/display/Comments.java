/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.tasks.display;

import gwt.material.design.client.ui.MaterialRow;

/**
 *
 * @author User
 */
public class Comments {
    
    private MaterialRow comments;
    private String container, taskId;
    
    public Comments(){
        
    }
    public Comments( MaterialRow comments, String container, String taskId){
        this.comments = comments;
        this.container = container;
        this.taskId = taskId;
    }
}
