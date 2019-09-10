/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.shared.entity;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apple
 */
public class CategoryGroup implements java.io.Serializable{
    private Long id;
    private String name;
    private String tags;
    private String cover;
    private Date createdAt;
    private Date updatedAt;
    private String nameEn;
    private int status;
    private java.util.List<Categories> categoryList;

        public void fromJSON(JSONObject user){
        setId( (long) user.get("id").isNumber().doubleValue() );
        setName( (user.get("name").toString().trim().equalsIgnoreCase("null") ? "" : user.get("name").toString().trim().replaceAll("\"", "") ));
        setTags( ( user.get("tags").toString().trim().equalsIgnoreCase("null") ? "" : user.get("tags").toString().trim().replaceAll("\"", "") ) );
        setCover( (user.get("cover").toString().trim().equalsIgnoreCase("null") ? "" : user.get("cover").toString().trim().replaceAll("\"", "") ));
        setCreatedAt( new java.util.Date( Long.parseLong(user.get("created").isObject().get("java.sql.Timestamp").toString())) );
        setUpdatedAt( new java.util.Date( Long.parseLong(user.get("updated").isObject().get("java.sql.Timestamp").toString())) );
        setNameEn( (user.get("nameEn").toString().trim().equalsIgnoreCase("null") ? "" : user.get("nameEn").toString().trim().replaceAll("\"", "") ));
        setStatus( Integer.parseInt(user.get("status").toString() ));

    }
    
    public JSONObject toJSON(){
        JSONObject user = new JSONObject();
        if( id != null )
            user.put("id", new JSONNumber( getId() ));
        user.put("name", new JSONString( getName() ));
        user.put("tags", new JSONString( getTags() ));
        user.put("cover", new JSONString( getCover() ));
        JSONObject cd = new JSONObject( );
        cd.put("java.sql.Timestamp", new JSONNumber( getCreatedAt().getTime() ));
        user.put("created", cd );
            
        JSONObject ud = new JSONObject( );
        ud.put("java.sql.Timestamp", new JSONNumber( getUpdatedAt().getTime() ));
        user.put("updated", ud );
        user.put("nameEn", new JSONString( getNameEn() ));
        user.put("status", new JSONNumber( getStatus() ));
        return user;
    }

    public CategoryGroup() {
    }

    public CategoryGroup(Long id, String name, String tags, String cover, Date createdAt, Date updatedAt, String nameEn, int status) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.cover = cover;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nameEn = nameEn;
        this.status = status;
    }

    public CategoryGroup(String name, String tags, String cover, Date createdAt, Date updatedAt, String nameEn, int status) {
        this.name = name;
        this.tags = tags;
        this.cover = cover;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nameEn = nameEn;
        this.status = status;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Categories> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Categories> categoryList) {
        this.categoryList = categoryList;
    }
    
    
    
    
}
