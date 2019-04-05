package org.liveshow.entity;

public class UserClassMapping {
    private Integer id;

    private Integer classId;

    private String userId;

    public UserClassMapping(Integer id, Integer classId, String userId) {
        this.id = id;
        this.classId = classId;
        this.userId = userId;
    }

    public UserClassMapping() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}