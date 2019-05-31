package org.liveshow.dto;

import org.liveshow.entity.Tuser;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 未出勤的学生和人脸识别率小于20%的学生
 */
public class UnOkStudent {
    private List<Tuser> unInStudents = new ArrayList<>();
    private List<Tuser> recoTusers = new ArrayList<>();

    public List<Tuser> getUnInStudents() {
        return unInStudents;
    }

    public void setUnInStudents(List<Tuser> unInStudents) {
        this.unInStudents = unInStudents;
    }

    public List<Tuser> getRecoTusers() {
        return recoTusers;
    }

    public void setRecoTusers(List<Tuser> recoTusers) {
        this.recoTusers = recoTusers;
    }
}
