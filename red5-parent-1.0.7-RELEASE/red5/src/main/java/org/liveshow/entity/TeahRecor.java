package org.liveshow.entity;

import java.util.Date;

public class TeahRecor {
    private Integer id;

    private Integer classId;

    private Date gmtStart;

    private Date gmtEnd;

    private Integer classNum;

    public TeahRecor(Integer id, Integer classId, Date gmtStart, Date gmtEnd, Integer classNum) {
        this.id = id;
        this.classId = classId;
        this.gmtStart = gmtStart;
        this.gmtEnd = gmtEnd;
        this.classNum = classNum;
    }

    public TeahRecor() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }
}