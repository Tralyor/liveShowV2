package org.liveshow.service;

import org.liveshow.entity.TeahRecor;

import java.util.List;

public interface TeachRecordService {
    TeahRecor getReMaxClassNum(Integer roomId);

    List<TeahRecor> queryRecordByClassId(Integer classId);

    int addTeachRecord(Integer classId, Integer classNum);

    int updateTeachRecord(TeahRecor teahRecor);
}
