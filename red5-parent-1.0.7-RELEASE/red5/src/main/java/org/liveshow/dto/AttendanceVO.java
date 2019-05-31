package org.liveshow.dto;

import java.util.List;

public class AttendanceVO {
    private List<AttendanceDTO> attendanceDTOs;
    private List<Integer> classNums;
    private List<Double> rateNums;
    private List<Integer> recoCounts;
    private List<Integer> recoSuccess;
    private int totalAttendanceNum;
    private int successAttendaceNum;

    public int getTotalAttendanceNum() {
        return totalAttendanceNum;
    }

    public void setTotalAttendanceNum(int totalAttendanceNum) {
        this.totalAttendanceNum = totalAttendanceNum;
    }

    public int getSuccessAttendaceNum() {
        return successAttendaceNum;
    }

    public void setSuccessAttendaceNum(int successAttendaceNum) {
        this.successAttendaceNum = successAttendaceNum;
    }

    public List<AttendanceDTO> getAttendanceDTOs() {
        return attendanceDTOs;
    }

    public void setAttendanceDTOs(List<AttendanceDTO> attendanceDTOs) {
        this.attendanceDTOs = attendanceDTOs;
    }

    public List<Integer> getClassNums() {
        return classNums;
    }

    public void setClassNums(List<Integer> classNums) {
        this.classNums = classNums;
    }

    public List<Double> getRateNums() {
        return rateNums;
    }

    public void setRateNums(List<Double> rateNums) {
        this.rateNums = rateNums;
    }

    public List<Integer> getRecoCounts() {
        return recoCounts;
    }

    public void setRecoCounts(List<Integer> recoCounts) {
        this.recoCounts = recoCounts;
    }

    public List<Integer> getRecoSuccess() {
        return recoSuccess;
    }

    public void setRecoSuccess(List<Integer> recoSuccess) {
        this.recoSuccess = recoSuccess;
    }
}
