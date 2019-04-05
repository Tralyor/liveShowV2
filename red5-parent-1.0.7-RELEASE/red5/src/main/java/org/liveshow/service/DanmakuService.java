package org.liveshow.service;

import org.liveshow.entity.Danmaku;

public interface DanmakuService {
    int addDanmaku(String userId, Integer roomId, String content);
}
