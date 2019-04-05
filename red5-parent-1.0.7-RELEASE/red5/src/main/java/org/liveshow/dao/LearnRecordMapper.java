package org.liveshow.dao;

import org.apache.ibatis.annotations.Param;
import org.liveshow.entity.LearnRecord;
import org.liveshow.entity.LearnRecordExample;

import java.util.List;

public interface LearnRecordMapper {
    int countByExample(LearnRecordExample example);

    int deleteByExample(LearnRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LearnRecord record);

    int insertSelective(LearnRecord record);

    List<LearnRecord> selectByExample(LearnRecordExample example);

    LearnRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LearnRecord record, @Param("example") LearnRecordExample example);

    int updateByExample(@Param("record") LearnRecord record, @Param("example") LearnRecordExample example);

    int updateByPrimaryKeySelective(LearnRecord record);

    int updateByPrimaryKey(LearnRecord record);

    void updateBySql(String sql);
}