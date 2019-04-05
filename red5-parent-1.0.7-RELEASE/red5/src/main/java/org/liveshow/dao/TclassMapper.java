package org.liveshow.dao;

import org.apache.ibatis.annotations.Param;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.TclassExample;

import java.util.List;

public interface TclassMapper {
    int countByExample(TclassExample example);

    int deleteByExample(TclassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tclass record);

    int insertSelective(Tclass record);

    List<Tclass> selectByExample(TclassExample example);

    Tclass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tclass record, @Param("example") TclassExample example);

    int updateByExample(@Param("record") Tclass record, @Param("example") TclassExample example);

    int updateByPrimaryKeySelective(Tclass record);

    int updateByPrimaryKey(Tclass record);
}