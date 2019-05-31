package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dao.ClassCommentMapper;
import org.liveshow.entity.ClassComment;
import org.liveshow.entity.ClassCommentExample;
import org.liveshow.service.EvaluateService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ClassEvaluateServiceImpl implements EvaluateService {

    @Resource
    private ClassCommentMapper classCommentMapper;

    @Override
    public int addComment(Integer classId, String userId, String content) {
        if ( classId == null || StringUtils.isBlank(userId) || StringUtils.isBlank(content)) {
            return 0;
        }
        ClassComment classComment = new ClassComment();
        classComment.setCommentId(userId);
        classComment.setClassId(classId);
        classComment.setContent(content);
        return classCommentMapper.insert(classComment);

    }

    @Override
    public ClassComment getUserComment(Integer classId, String userId) {
        if ( classId == null || StringUtils.isBlank(userId)) {
            return null;
        }
        ClassCommentExample example = new ClassCommentExample();
        ClassCommentExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(classId);
        criteria.andCommentIdEqualTo(userId);
        List<ClassComment> res = classCommentMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(res)) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public List<ClassComment> getCommentByClassId(Integer classId) {
        if ( classId == null ) {
            return null;
        }
        ClassCommentExample example = new ClassCommentExample();
        ClassCommentExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(classId);
        return classCommentMapper.selectByExample(example);
    }

    @Override
    public int updateUserComment(ClassComment classComment) {
        return classCommentMapper.updateByPrimaryKey(classComment);
    }
}
