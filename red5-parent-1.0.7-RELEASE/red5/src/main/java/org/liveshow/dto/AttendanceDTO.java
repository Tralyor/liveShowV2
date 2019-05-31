package org.liveshow.dto;

public class AttendanceDTO {
    private Integer classId;
    private Integer classNum;
    private Boolean attendance;
    private int faceRecoCount;
    private int faceRecoSuccess;
    private Double faceRecoRate;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public int getFaceRecoCount() {
        return faceRecoCount;
    }

    public void setFaceRecoCount(int faceRecoCount) {
        this.faceRecoCount = faceRecoCount;
    }

    public int getFaceRecoSuccess() {
        return faceRecoSuccess;
    }

    public void setFaceRecoSuccess(int faceRecoSuccess) {
        this.faceRecoSuccess = faceRecoSuccess;
    }

    public Double getFaceRecoRate() {
        return faceRecoRate;
    }

    public void setFaceRecoRate(Double faceRecoRate) {
        this.faceRecoRate = faceRecoRate;
    }
}
