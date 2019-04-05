package org.liveshow.entity;

public class Danmaku {
    private Integer id;

    private String content;

    private Integer classId;

    private String gmtCreat;

    private String userId;

    public Danmaku(Integer id, String content, Integer classId, String gmtCreat, String userId) {
        this.id = id;
        this.content = content;
        this.classId = classId;
        this.gmtCreat = gmtCreat;
        this.userId = userId;
    }

    public Danmaku() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getGmtCreat() {
        return gmtCreat;
    }

    public void setGmtCreat(String gmtCreat) {
        this.gmtCreat = gmtCreat == null ? null : gmtCreat.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}