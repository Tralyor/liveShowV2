package org.liveshow.dao;

import org.apache.ibatis.annotations.Param;
import org.liveshow.entity.ClassComment;
import org.liveshow.entity.ClassCommentExample;

import java.util.List;

public interface ClassCommentMapper {
    int countByExample(ClassCommentExample example);

    int deleteByExample(ClassCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassComment record);

    int insertSelective(ClassComment record);

    List<ClassComment> selectByExample(ClassCommentExample example);

    ClassComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassComment record, @Param("example") ClassCommentExample example);

    int updateByExample(@Param("record") ClassComment record, @Param("example") ClassCommentExample example);

    int updateByPrimaryKeySelective(ClassComment record);

    int updateByPrimaryKey(ClassComment record);
}