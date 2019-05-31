package org.liveshow.service;

import org.liveshow.entity.ClassComment;

import java.util.List;

public interface EvaluateService {
    int addComment(Integer classId, String userId, String content);
    ClassComment getUserComment(Integer classId, String userId);
    List<ClassComment>  getCommentByClassId(Integer classId);
    int updateUserComment(ClassComment classComment);
}
