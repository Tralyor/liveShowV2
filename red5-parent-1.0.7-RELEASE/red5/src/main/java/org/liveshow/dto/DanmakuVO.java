package org.liveshow.dto;

import org.liveshow.entity.Danmaku;

public class DanmakuVO{
    private  Danmaku danmaku;
    private String userName;
    private String userImg;

    public Danmaku getDanmaku() {
        return danmaku;
    }

    public void setDanmaku(Danmaku danmaku) {
        this.danmaku = danmaku;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
