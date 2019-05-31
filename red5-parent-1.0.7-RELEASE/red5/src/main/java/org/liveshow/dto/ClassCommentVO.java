package org.liveshow.dto;

import org.liveshow.entity.ClassComment;

public class ClassCommentVO {
    private ClassComment classComment;
    private String userName;
    private String imgUrl;

    public ClassComment getClassComment() {
        return classComment;
    }

    public void setClassComment(ClassComment classComment) {
        this.classComment = classComment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
