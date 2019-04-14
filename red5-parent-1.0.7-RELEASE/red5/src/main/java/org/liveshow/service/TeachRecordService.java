package org.liveshow.service;

import org.liveshow.entity.TeahRecor;

import java.util.List;

public interface TeachRecordService {
    TeahRecor getReMaxClassNum(Integer roomId);

    TeahRecor queryRecordByRid(Integer rid);

    List<TeahRecor> queryRecordByClassId(Integer classId);

    TeahRecor queryRecordByClassIdAndClassNum(Integer classId, Integer classNum);

    int addTeachRecord(Integer classId, Integer classNum);

    int updateTeachRecord(TeahRecor teahRecor);
}
