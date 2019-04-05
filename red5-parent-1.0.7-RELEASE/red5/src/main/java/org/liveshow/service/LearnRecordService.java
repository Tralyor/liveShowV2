package org.liveshow.service;

import org.liveshow.entity.LearnRecord;

import java.util.List;

public interface LearnRecordService  {
    int addLearnRecordService(String userId, Integer recordId);

    List<LearnRecord> queryRecordByTid(Integer tid);

    List<LearnRecord> queryRecordByTidAndGmtEndIsNull(Integer id);

    void updateByUserIds(List<LearnRecord> records);

    void updateByUserId(String userId, Integer classId);
}
