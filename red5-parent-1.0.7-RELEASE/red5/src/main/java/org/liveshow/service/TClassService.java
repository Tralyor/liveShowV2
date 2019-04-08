package org.liveshow.service;

import org.liveshow.entity.Tclass;

import java.util.List;

public interface TClassService {
    Tclass queryTClassById(Integer id);
    List<Tclass> queryClassByIds(List<Integer> classId);
    boolean queryClassIsTeaching(Integer id);
    void updateTeaching(Tclass tclass);
}
