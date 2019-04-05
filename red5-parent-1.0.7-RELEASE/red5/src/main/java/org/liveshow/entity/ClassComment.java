package org.liveshow.entity;

public class ClassComment {
    private Integer id;

    private Integer classId;

    private String commentId;

    private String content;

    private Integer level;

    public ClassComment(Integer id, Integer classId, String commentId, String content, Integer level) {
        this.id = id;
        this.classId = classId;
        this.commentId = commentId;
        this.content = content;
        this.level = level;
    }

    public ClassComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}