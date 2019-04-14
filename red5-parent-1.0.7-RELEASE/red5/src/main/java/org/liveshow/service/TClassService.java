package org.liveshow.service;

import org.liveshow.entity.Tclass;

import java.util.List;

public interface TClassService {
    Tclass queryTClassById(Integer id);
    List<Tclass> queryClassByIds(List<Integer> classId);
    List<Tclass> queryClassByCreatorId(String userId);
    int addTclass (String userId,String className, String classInfo);
    boolean queryClassIsTeaching(Integer id);
    void updateTeaching(Tclass tclass);
    void updateTeachInfo(Integer classId,String className,String classInfo);
}
