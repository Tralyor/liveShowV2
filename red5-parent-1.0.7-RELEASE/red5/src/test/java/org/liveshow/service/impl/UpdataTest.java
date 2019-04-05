package org.liveshow.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.liveshow.dao.LearnRecordMapper;
import org.liveshow.entity.LearnRecord;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-*.xml",
})
public class UpdataTest {
    @Resource
    LearnRecordMapper learnRecordMapper;

    @Test
    public void updateInfo() {
        learnRecordMapper.updateBySql("update learn_record set gmt_out='1554451128389' where id in (1,2,44,5)");
    }
}
