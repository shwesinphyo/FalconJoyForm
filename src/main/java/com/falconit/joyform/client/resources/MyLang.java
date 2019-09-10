/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.resources;

/**
 *
 * @author User
 */

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;


public interface MyLang extends Messages{
    public static final MyLang LANG = GWT.create( MyLang.class);
    String heading();
    String login();
    
    String username();
    String channel();
    String email();
    
    String mobile();
    String created();
    String updated();
    String vip();
    String gender();
    String verified();
    String active();
    String acType();
    
    String name();
    String nameEn();
    String groupName();
    String slug();
    
    String title();
    String category();
    String premium();
    String like();
    String dislike();
    String view();
    String update();
    String edit();
    String close();
}