/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util;

import com.google.gwt.user.client.Cookies;
import java.util.Date;

/**
 *
 * @author User
 */
public class CookieHelper {
    
        
    public static final int COOKIE_EXPIRE_DAYS = 30;
    public static final long MILLISECS_PER_DAY = 1000L * 60L * 60L * 24L;

    public static void setMyCookie(String name, String value, int days) {

        if (value == null) {
            Cookies.removeCookie( name );
            return;
        }
        //String v = Cookies.getCookie(name);
        //if (value.equals(v)) {
            // Now
            Date d = new Date();
            // Now + days
            d.setTime(d.getTime() + MILLISECS_PER_DAY * days);
            Cookies.setCookie(name, value, d);
        //}
    }

    public static void setMyCookie(String name, String value) {
            setMyCookie( name, value, COOKIE_EXPIRE_DAYS);
    }
    
    public static String getMyCookie( String name ){
        return Cookies.getCookie(name);
    }
}
