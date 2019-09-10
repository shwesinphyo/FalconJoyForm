/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.shared.entity;

import com.google.gwt.json.client.JSONObject;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author apple
 */
public class Videos implements Serializable{
    private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private String description;
	private String tags;
	private int status;
	private int vip;
	private String preview;
	private String thumb;
	private String videoPath;
	private String duration;
	private int width;
	private int height;
	private String size;
	private String type;
	private String youtubeId;
	private String vimeoId;
	private String dailymotionId;
	private Date createdAt;
	private Date updatedAt;
	private java.lang.String videoRefId;
	private java.lang.Long category;
	private int dislikeCount;
	private int likeCount;
	private int viewCount;
	private long userId;
        private String categoryName;
        private String channelName;

            
        public void fromJSON( JSONObject user ){
        setId( (long) user.get("id").isNumber().doubleValue() );
        setTitle( user.get("title").isString().stringValue() );
        setDescription( (user.get("description").toString().trim().equalsIgnoreCase("null") ? "" : user.get("description").toString().trim().replaceAll("\"", "") ));
        setTags( user.get("tags").isString().stringValue() );
        setStatus( Integer.parseInt(user.get("status").toString() ));
        setVip( Integer.parseInt( user.get("vip").toString()) );
        setPreview( ( user.get("preview").toString().trim().equalsIgnoreCase("null") ? "" : user.get("preview").toString().trim().replaceAll("\"", "") ) );
        setThumb( ( user.get("thumb").toString().trim().equalsIgnoreCase("null") ? "" : user.get("thumb").toString().trim().replaceAll("\"", "") ) );
        setVideoPath( (user.get("videoPath").toString().trim().equalsIgnoreCase("null") ? "" : user.get("videoPath").toString().trim().replaceAll("\"", "") ));
        setDuration( (user.get("duration").toString().trim().equalsIgnoreCase("null") ? "" : user.get("duration").toString().trim().replaceAll("\"", "") ));
        setWidth( Integer.parseInt( user.get("width").toString()) );
        setHeight( Integer.parseInt( user.get("height").toString()) );
        setSize( (user.get("size").toString().trim().equalsIgnoreCase("null") ? "" : user.get("size").toString().trim().replaceAll("\"", "") ));
        setType( (user.get("type").toString().trim().equalsIgnoreCase("null") ? "" : user.get("type").toString().trim().replaceAll("\"", "") ));
        setYoutubeId( ( user.get("youtubeId").toString().trim().equalsIgnoreCase("null") ? "" : user.get("youtubeId").toString().trim().replaceAll("\"", "") ));
        setVimeoId( (user.get("vimeoId").toString().trim().equalsIgnoreCase("null") ? "" : user.get("vimeoId").toString().trim().replaceAll("\"", "") ));
        setDailymotionId( (user.get("dailymotionId").toString().trim().equalsIgnoreCase("null") ? "" : user.get("dailymotionId").toString().trim().replaceAll("\"", "") ));
        setVideoRefId( (user.get("videoRefId").toString().trim().equalsIgnoreCase("null") ? "" : user.get("videoRefId").toString().trim().replaceAll("\"", "") ));
        setCategory( (long) user.get("category").isNumber().doubleValue() );
        setUserId( (long) user.get("userId").isNumber().doubleValue() );
        setDislikeCount( Integer.parseInt( user.get("dislikeCount").toString()) );
        setLikeCount( Integer.parseInt( user.get("likeCount").toString()) );
        setViewCount( Integer.parseInt( user.get("viewCount").toString()) );
        setCreatedAt( new java.util.Date( Long.parseLong(user.get("createdAt").isObject().get("java.sql.Timestamp").toString())) );
        setUpdatedAt( new java.util.Date( Long.parseLong(user.get("updatedAt").isObject().get("java.sql.Timestamp").toString())) );

    }
    
        
    public Videos() {
    }

    public Videos(String title, String description, String tags, int status, int vip, String preview, String thumb, String videoPath, String duration, int width, int height, String size, String type, String youtubeId, String vimeoId, String dailymotionId, Date createdAt, Date updatedAt, String videoRefId, Long category, int dislikeCount, int likeCount, int viewCount, long userId) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.status = status;
        this.vip = vip;
        this.preview = preview;
        this.thumb = thumb;
        this.videoPath = videoPath;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.size = size;
        this.type = type;
        this.youtubeId = youtubeId;
        this.vimeoId = vimeoId;
        this.dailymotionId = dailymotionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.videoRefId = videoRefId;
        this.category = category;
        this.dislikeCount = dislikeCount;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.userId = userId;
    }

    public Videos(Long id, String title, String description, String tags, int status, int vip, String preview, String thumb, String videoPath, String duration, int width, int height, String size, String type, String youtubeId, String vimeoId, String dailymotionId, Date createdAt, Date updatedAt, String videoRefId, Long category, int dislikeCount, int likeCount, int viewCount, long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.status = status;
        this.vip = vip;
        this.preview = preview;
        this.thumb = thumb;
        this.videoPath = videoPath;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.size = size;
        this.type = type;
        this.youtubeId = youtubeId;
        this.vimeoId = vimeoId;
        this.dailymotionId = dailymotionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.videoRefId = videoRefId;
        this.category = category;
        this.dislikeCount = dislikeCount;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.userId = userId;
    }

        
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getVimeoId() {
        return vimeoId;
    }

    public void setVimeoId(String vimeoId) {
        this.vimeoId = vimeoId;
    }

    public String getDailymotionId() {
        return dailymotionId;
    }

    public void setDailymotionId(String dailymotionId) {
        this.dailymotionId = dailymotionId;
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

    public String getVideoRefId() {
        return videoRefId;
    }

    public void setVideoRefId(String videoRefId) {
        this.videoRefId = videoRefId;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
        
        
        
        
}
