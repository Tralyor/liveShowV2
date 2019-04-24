package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.liveshow.dao.DanmakuMapper;
import org.liveshow.entity.Danmaku;
import org.liveshow.entity.DanmakuExample;
import org.liveshow.service.DanmakuService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class DanmakuServiceImpl implements DanmakuService {
    @Resource
    DanmakuMapper danmakuMapper;

    /**
     * @param userId
     * @param roomId 学习记录
     * @param content
     * @return
     */
    @Override
    public int addDanmaku(String userId, Integer roomId, String content) {
        if (StringUtils.isBlank(userId) || roomId == null || !StringUtils.isNumeric(roomId.toString()) ||StringUtils.isBlank(content) ) {
            return 0;
        }

        Danmaku danmaku = new Danmaku();
        danmaku.setClassId(roomId);
        danmaku.setUserId(userId);
        danmaku.setContent(content);
        danmaku.setGmtCreat(Long.toString(System.currentTimeMillis()));
        return danmakuMapper.insert(danmaku);
    }

    @Override
    public List<Danmaku> queryDanmakuByTid(Integer tid) {
        if ( tid == null ) {
            return null;
        }
        DanmakuExample example = new DanmakuExample();
        DanmakuExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(tid);
        List<Danmaku> res =  danmakuMapper.selectByExample(example);
        return res;
    }
}
