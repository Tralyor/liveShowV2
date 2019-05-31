package org.liveshow.dto;

import java.util.ArrayList;
import java.util.List;

public class ClassAttendanceVO {
    List<Integer> attendanceSuccess = new ArrayList<>();
    List<Integer> attendanceCount = new ArrayList<>();
    List<Double> attendanceRate = new ArrayList<>();
    List<Integer> faceSuccess = new ArrayList<>();
    List<Integer> faceCount = new ArrayList<>();
    List<Double> faceRate = new ArrayList<>();
    List<Integer> classNums = new ArrayList<>();

    public List<Integer> getAttendanceSuccess() {
        return attendanceSuccess;
    }

    public void setAttendanceSuccess(List<Integer> attendanceSuccess) {
        this.attendanceSuccess = attendanceSuccess;
    }

    public List<Integer> getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(List<Integer> attendanceCount) {
        this.attendanceCount = attendanceCount;
    }

    public List<Double> getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(List<Double> attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public List<Integer> getFaceSuccess() {
        return faceSuccess;
    }

    public void setFaceSuccess(List<Integer> faceSuccess) {
        this.faceSuccess = faceSuccess;
    }

    public List<Integer> getFaceCount() {
        return faceCount;
    }

    public void setFaceCount(List<Integer> faceCount) {
        this.faceCount = faceCount;
    }

    public List<Double> getFaceRate() {
        return faceRate;
    }

    public void setFaceRate(List<Double> faceRate) {
        this.faceRate = faceRate;
    }

    public List<Integer> getClassNums() {
        return classNums;
    }

    public void setClassNums(List<Integer> classNums) {
        this.classNums = classNums;
    }
}
