package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.liveshow.dao.TeahRecorMapper;
import org.liveshow.entity.TeahRecor;
import org.liveshow.entity.TeahRecorExample;
import org.liveshow.service.TeachRecordService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class TeachRecordServiceImpl implements TeachRecordService {
    @Resource
    TeahRecorMapper teahRecorMapper;

    @Override
    public TeahRecor getReMaxClassNum(Integer roomId) {
        TeahRecorExample example = new TeahRecorExample();
        TeahRecorExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(roomId);
        List<TeahRecor> res = teahRecorMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(res)) {
            return null;
        }

        Collections.sort(res,(t1,t2)->{
           return t2.getClassNum().compareTo(t1.getClassNum());
        });

        return res.get(0);
    }

    @Override
    public TeahRecor queryRecordByRid(Integer rid) {
        if ( rid == null ) {
            return null;
        }
        TeahRecorExample teahRecorExample = new TeahRecorExample();
        TeahRecorExample.Criteria criteria = teahRecorExample.createCriteria();
        criteria.andIdEqualTo(rid);
        List<TeahRecor> res = teahRecorMapper.selectByExample(teahRecorExample);
        if ( CollectionUtils.isEmpty(res) ) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public List<TeahRecor> queryRecordByClassId(Integer classId) {
        if ( classId == null ) {
            return null;
        }

        TeahRecorExample teahRecorExample = new TeahRecorExample();
        TeahRecorExample.Criteria criteria = teahRecorExample.createCriteria();
        criteria.andClassIdEqualTo(classId);
        return teahRecorMapper.selectByExample(teahRecorExample);
    }

    @Override
    public TeahRecor queryRecordByClassIdAndClassNum(Integer classId, Integer classNum) {
        if ( classId == null || classNum == 0 ) {
            return null;
        }

        TeahRecorExample teahRecorExample = new TeahRecorExample();
        TeahRecorExample.Criteria criteria = teahRecorExample.createCriteria();
        criteria.andClassIdEqualTo(classId);
        criteria.andClassNumEqualTo(classNum);

        List<TeahRecor> res = teahRecorMapper.selectByExample(teahRecorExample);
        if ( CollectionUtils.isEmpty(res) ) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public int addTeachRecord(Integer classId, Integer classNum) {
        if ( classId == null || classNum == null ) {
            return 0;
        }

        TeahRecor teahRecor = new TeahRecor();
        teahRecor.setClassId(classId);
        teahRecor.setClassNum(classNum);
        teahRecor.setGmtStart(String.valueOf(System.currentTimeMillis()));

        return  teahRecorMapper.insert(teahRecor);
    }

    @Override
    public int updateTeachRecord(TeahRecor teahRecor) {
        if ( teahRecor == null || teahRecor.getId() == null ) {
            return 0;
        }
        return teahRecorMapper.updateByPrimaryKey(teahRecor);
    }
}
