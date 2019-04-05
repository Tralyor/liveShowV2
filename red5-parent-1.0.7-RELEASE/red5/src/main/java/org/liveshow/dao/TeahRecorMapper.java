package org.liveshow.dao;

import org.apache.ibatis.annotations.Param;
import org.liveshow.entity.TeahRecor;
import org.liveshow.entity.TeahRecorExample;

import java.util.List;

public interface TeahRecorMapper {
    int countByExample(TeahRecorExample example);

    int deleteByExample(TeahRecorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TeahRecor record);

    int insertSelective(TeahRecor record);

    List<TeahRecor> selectByExample(TeahRecorExample example);

    TeahRecor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TeahRecor record, @Param("example") TeahRecorExample example);

    int updateByExample(@Param("record") TeahRecor record, @Param("example") TeahRecorExample example);

    int updateByPrimaryKeySelective(TeahRecor record);

    int updateByPrimaryKey(TeahRecor record);
}