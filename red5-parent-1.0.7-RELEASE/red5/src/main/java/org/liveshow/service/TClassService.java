package org.liveshow.service;

import org.liveshow.entity.Tclass;

public interface TClassService {
    Tclass queryTClassById(Integer id);
    boolean queryClassIsTeaching(Integer id);
    void updateTeaching(Tclass tclass);
}
