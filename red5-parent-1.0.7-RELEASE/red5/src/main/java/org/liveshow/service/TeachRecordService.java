package org.liveshow.service;

import org.liveshow.entity.TeahRecor;

public interface TeachRecordService {
    TeahRecor getReMaxClassNum(Integer roomId);

    int addTeachRecord(Integer classId, Integer classNum);

    int updateTeachRecord(TeahRecor teahRecor);
}
