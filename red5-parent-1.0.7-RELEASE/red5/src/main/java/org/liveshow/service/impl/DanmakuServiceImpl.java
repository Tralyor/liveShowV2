package org.liveshow.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.liveshow.dao.DanmakuMapper;
import org.liveshow.entity.Danmaku;
import org.liveshow.service.DanmakuService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class DanmakuServiceImpl implements DanmakuService {
    @Resource
    DanmakuMapper danmakuMapper;

    @Override
    public int addDanmaku(String userId, Integer roomId, String content) {
        if (StringUtils.isBlank(userId) || roomId == null || !StringUtils.isNumeric(roomId.toString()) ||StringUtils.isBlank(content) ) {
            return -1;
        }

        Danmaku danmaku = new Danmaku();
        danmaku.setClassId(roomId);
        danmaku.setUserId(userId);
        danmaku.setContent(content);
        danmaku.setGmtCreat(Long.toString(System.currentTimeMillis()));
        return danmakuMapper.insert(danmaku);
    }
}
