/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.shared.entity;


import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import java.util.Date;

/**
 *
 * @author apple
 */
public class Users implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private String email;
    private String channel;
    private int status;
    private int level;// 0=visitor, 1=super admin, 2= channel
    private int isVip;// 0= visitor, 1=premium
    private String fbLogin;
    private String twLogin;
    private String goLogin;
    private String cover;
    private String avatar;
    private int verified;
    private String description;
    private String fbPage;
    private String twPage;
    private String goPage;
    private String inPage;
    private String website;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;
    private String mobile;
    private Date dob;
    private String gender;

    
    public void fromJSON(JSONObject user){
        setId( (long) user.get("id").isNumber().doubleValue() );
        setUsername( user.get("username").isString().stringValue() );
        setPassword( user.get("password").isString().stringValue() );
        setEmail( ( user.get("email").toString().trim().equalsIgnoreCase("null") ? "" : user.get("email").toString().trim().replaceAll("\"", "") ) );
        setChannel( ( user.get("channel").toString().trim().equalsIgnoreCase("null") ? "" : user.get("channel").toString().trim().replaceAll("\"", "") ) );
        
        setStatus( Integer.parseInt(user.get("status").toString() ));
        setLevel( Integer.parseInt( user.get("level").toString()) );
        setIsVip( Integer.parseInt( user.get("isVip").toString()) );
        
        setFbLogin( (user.get("fbLogin").toString().trim().equalsIgnoreCase("null") ? "" : user.get("fbLogin").toString().trim().replaceAll("\"", "") ));
        setTwLogin( (user.get("twLogin").toString().trim().equalsIgnoreCase("null") ? "" : user.get("twLogin").toString().trim().replaceAll("\"", "") ));
        setGoLogin( (user.get("goLogin").toString().trim().equalsIgnoreCase("null") ? "" : user.get("goLogin").toString().trim().replaceAll("\"", "") ));
        setCover( (user.get("cover").toString().trim().equalsIgnoreCase("null") ? "" : user.get("cover").toString().trim().replaceAll("\"", "") ));
        setAvatar( ( user.get("avatar").toString().trim().equalsIgnoreCase("null") ? "" : user.get("avatar").toString().trim().replaceAll("\"", "") ));
        
        setVerified( Integer.parseInt( user.get("verified").toString()) );
        
        setDescription( (user.get("description").toString().trim().equalsIgnoreCase("null") ? "" : user.get("description").toString().trim().replaceAll("\"", "") ));
        setFbPage( (user.get("fbPage").toString().trim().equalsIgnoreCase("null") ? "" : user.get("fbPage").toString().trim().replaceAll("\"", "") ));
        setTwPage( (user.get("twPage").toString().trim().equalsIgnoreCase("null") ? "" : user.get("twPage").toString().trim().replaceAll("\"", "") ));
        setGoPage( (user.get("goPage").toString().trim().equalsIgnoreCase("null") ? "" : user.get("goPage").toString().trim().replaceAll("\"", "") ));
        setInPage( (user.get("inPage").toString().trim().equalsIgnoreCase("null") ? "" : user.get("inPage").toString().trim().replaceAll("\"", "") ));
        setWebsite( (user.get("website").toString().trim().equalsIgnoreCase("null") ? "" : user.get("website").toString().trim().replaceAll("\"", "") ));
        setMobile( (user.get("mobile").toString().trim().equalsIgnoreCase("null") ? "" : user.get("mobile").toString().trim().replaceAll("\"", "") ));
        setGender((user.get("gender").toString().trim().equalsIgnoreCase("null") ? "" : user.get("gender").toString().trim().replaceAll("\"", "") ));
        setRememberToken( (user.get("rememberToken").toString().trim().equalsIgnoreCase("null") ? "" : user.get("rememberToken").toString().trim().replaceAll("\"", "") ));
        
        if( !user.get("dob").toString().equals("null"))
            setDob( new java.util.Date( Long.parseLong(user.get("dob").isObject().get("java.sql.Timestamp").toString())) );
        setCreatedAt( new java.util.Date( Long.parseLong(user.get("createdAt").isObject().get("java.sql.Timestamp").toString())) );
        setUpdatedAt( new java.util.Date( Long.parseLong(user.get("updatedAt").isObject().get("java.sql.Timestamp").toString())) );

    }
    
    public JSONObject toJSON(){
        JSONObject user = new JSONObject();
        if( id != null )
            user.put("id", new JSONNumber( getId() ));
        user.put("username", new JSONString( getUsername() ));
        user.put("password", new JSONString( getPassword() ));
        user.put("email", new JSONString( getEmail() ));
        user.put("channel", new JSONString( getChannel() ));
        user.put("status", new JSONNumber( getStatus() ));
        user.put("level", new JSONNumber( getLevel() ));
        user.put("isVip", new JSONNumber( getIsVip() ));
        user.put("fbLogin", new JSONString( getFbLogin() ));
        
        user.put("twLogin", new JSONString( getTwLogin() ));
        user.put("goLogin", new JSONString( getGoLogin() ));
        user.put("cover", new JSONString( getCover() ));
        user.put("avatar", new JSONString( getAvatar() ));
        user.put("verified", new JSONNumber( getVerified() ));
        user.put("description", new JSONString( getDescription() ));
        user.put("fbPage", new JSONString( getFbPage() ));
        user.put("twPage", new JSONString( getTwPage() ));
        user.put("goPage", new JSONString( getGoPage() ));
        user.put("inPage", new JSONString( getInPage() ));
        user.put("website", new JSONString( getWebsite() ));
        user.put("mobile", new JSONString( getMobile() ));
        user.put("gender", new JSONString( getGender() ));
        user.put("rememberToken", new JSONString( getRememberToken() ));
        if( getDob() != null){
            JSONObject dob = new JSONObject();
            dob.put("java.sql.Timestamp", new JSONNumber( getDob().getTime() ));
            user.put("dob", dob );
        }else{
            user.put("dob", null );
        }
        
        JSONObject cd = new JSONObject( );
        cd.put("java.sql.Timestamp", new JSONNumber( getCreatedAt().getTime() ));
        user.put("createdAt", cd );
            
        JSONObject ud = new JSONObject( );
        ud.put("java.sql.Timestamp", new JSONNumber( getUpdatedAt().getTime() ));
        user.put("updatedAt", ud );
        return user;
    }
    
    public Users() {
    }

    public Users(String username, String password, String email, String channel, String mobile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.channel = channel;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public String getFbLogin() {
        return fbLogin;
    }

    public void setFbLogin(String fbLogin) {
        this.fbLogin = fbLogin;
    }

    public String getTwLogin() {
        return twLogin;
    }

    public void setTwLogin(String twLogin) {
        this.twLogin = twLogin;
    }

    public String getGoLogin() {
        return goLogin;
    }

    public void setGoLogin(String goLogin) {
        this.goLogin = goLogin;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFbPage() {
        return fbPage;
    }

    public void setFbPage(String fbPage) {
        this.fbPage = fbPage;
    }

    public String getTwPage() {
        return twPage;
    }

    public void setTwPage(String twPage) {
        this.twPage = twPage;
    }

    public String getGoPage() {
        return goPage;
    }

    public void setGoPage(String goPage) {
        this.goPage = goPage;
    }

    public String getInPage() {
        return inPage;
    }

    public void setInPage(String inPage) {
        this.inPage = inPage;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /*
    @JsonCreator
    public Users( @JsonProperty( "id" ) Long id, 
            @JsonProperty( "username" ) String username, 
            @JsonProperty( "password" ) String password, 
            @JsonProperty( "email" ) String email, 
            @JsonProperty( "channel" ) String channel, 
            @JsonProperty( "status" ) int status, 
            @JsonProperty( "level" ) int level, 
            @JsonProperty( "isVip" ) int isVip, 
            @JsonProperty( "fbLogin" ) String fbLogin, 
            @JsonProperty( "twLogin" ) String twLogin, 
            @JsonProperty( "goLogin" ) String goLogin, 
            @JsonProperty( "cover" ) String cover, 
            @JsonProperty( "avatar" ) String avatar, 
            @JsonProperty( "verified" ) int verified, 
            @JsonProperty( "description" ) String description, 
            @JsonProperty( "fbPage" ) String fbPage, 
            @JsonProperty( "twPage" ) String twPage, 
            @JsonProperty( "goPage" ) String goPage, 
            @JsonProperty( "inPage" ) String inPage, 
            @JsonProperty( "website" ) String website, 
            @JsonProperty( "rememberToken" ) String rememberToken, 
            //@JsonProperty( "createdAt" ) Date createdAt, 
            //@JsonProperty( "updatedAt" ) Date updatedAt, 
            @JsonProperty( "mobile" ) String mobile, 
            //@JsonProperty( "dob" ) Date dob, 
            @JsonProperty( "gender" ) String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.channel = channel;
        this.status = status;
        this.level = level;
        this.isVip = isVip;
        this.fbLogin = fbLogin;
        this.twLogin = twLogin;
        this.goLogin = goLogin;
        this.cover = cover;
        this.avatar = avatar;
        this.verified = verified;
        this.description = description;
        this.fbPage = fbPage;
        this.twPage = twPage;
        this.goPage = goPage;
        this.inPage = inPage;
        this.website = website;
        this.rememberToken = rememberToken;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
        this.mobile = mobile;
//        this.dob = dob;
        this.gender = gender;
    }
    */

    public Users(Long id, String username, String password, String email, String channel, int status, int level, int isVip, String fbLogin, String twLogin, String goLogin, String cover, String avatar, int verified, String description, String fbPage, String twPage, String goPage, String inPage, String website, String rememberToken, Date createdAt, Date updatedAt, String mobile, Date dob, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.channel = channel;
        this.status = status;
        this.level = level;
        this.isVip = isVip;
        this.fbLogin = fbLogin;
        this.twLogin = twLogin;
        this.goLogin = goLogin;
        this.cover = cover;
        this.avatar = avatar;
        this.verified = verified;
        this.description = description;
        this.fbPage = fbPage;
        this.twPage = twPage;
        this.goPage = goPage;
        this.inPage = inPage;
        this.website = website;
        this.rememberToken = rememberToken;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.mobile = mobile;
        this.dob = dob;
        this.gender = gender;
    }
    
    
}
