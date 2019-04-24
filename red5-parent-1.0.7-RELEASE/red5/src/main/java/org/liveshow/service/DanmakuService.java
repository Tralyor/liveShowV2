package org.liveshow.service;

import org.liveshow.entity.Danmaku;

import java.util.List;

public interface DanmakuService {
    int addDanmaku(String userId, Integer techId, String content);
    List<Danmaku> queryDanmakuByTid(Integer tid);
}
