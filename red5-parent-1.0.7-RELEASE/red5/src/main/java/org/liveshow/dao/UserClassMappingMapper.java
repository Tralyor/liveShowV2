package org.liveshow.dao;

import org.apache.ibatis.annotations.Param;
import org.liveshow.entity.UserClassMapping;
import org.liveshow.entity.UserClassMappingExample;

import java.util.List;

public interface UserClassMappingMapper {
    int countByExample(UserClassMappingExample example);

    int deleteByExample(UserClassMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserClassMapping record);

    int insertSelective(UserClassMapping record);

    List<UserClassMapping> selectByExample(UserClassMappingExample example);

    UserClassMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserClassMapping record, @Param("example") UserClassMappingExample example);

    int updateByExample(@Param("record") UserClassMapping record, @Param("example") UserClassMappingExample example);

    int updateByPrimaryKeySelective(UserClassMapping record);

    int updateByPrimaryKey(UserClassMapping record);
}