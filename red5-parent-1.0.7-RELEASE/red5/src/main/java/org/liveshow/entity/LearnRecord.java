package org.liveshow.entity;

import java.util.Date;

public class LearnRecord {
    private Integer id;

    private String userId;

    private Integer recordId;

    private Date gmtIn;

    private Date gmtOut;

    private Integer faceRegoCount;

    private Integer faceRegoSuccess;

    public LearnRecord(Integer id, String userId, Integer recordId, Date gmtIn, Date gmtOut, Integer faceRegoCount, Integer faceRegoSuccess) {
        this.id = id;
        this.userId = userId;
        this.recordId = recordId;
        this.gmtIn = gmtIn;
        this.gmtOut = gmtOut;
        this.faceRegoCount = faceRegoCount;
        this.faceRegoSuccess = faceRegoSuccess;
    }

    public LearnRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getGmtIn() {
        return gmtIn;
    }

    public void setGmtIn(Date gmtIn) {
        this.gmtIn = gmtIn;
    }

    public Date getGmtOut() {
        return gmtOut;
    }

    public void setGmtOut(Date gmtOut) {
        this.gmtOut = gmtOut;
    }

    public Integer getFaceRegoCount() {
        return faceRegoCount;
    }

    public void setFaceRegoCount(Integer faceRegoCount) {
        this.faceRegoCount = faceRegoCount;
    }

    public Integer getFaceRegoSuccess() {
        return faceRegoSuccess;
    }

    public void setFaceRegoSuccess(Integer faceRegoSuccess) {
        this.faceRegoSuccess = faceRegoSuccess;
    }
}