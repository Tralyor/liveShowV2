package org.liveshow.entity;

public class TeahRecor {
    private Integer id;

    private Integer classId;

    private String gmtStart;

    private String gmtEnd;

    private Integer classNum;

    public TeahRecor(Integer id, Integer classId, String gmtStart, String gmtEnd, Integer classNum) {
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

    public String getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(String gmtStart) {
        this.gmtStart = gmtStart == null ? null : gmtStart.trim();
    }

    public String getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd == null ? null : gmtEnd.trim();
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }
}