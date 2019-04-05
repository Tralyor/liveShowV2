package org.liveshow.entity;

public class Tuser {
    private String userId;

    private String userName;

    private Integer type;

    private String password;

    private String imgAddress;

    public Tuser(String userId, String userName, Integer type, String password, String imgAddress) {
        this.userId = userId;
        this.userName = userName;
        this.type = type;
        this.password = password;
        this.imgAddress = imgAddress;
    }

    public Tuser() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress == null ? null : imgAddress.trim();
    }
}