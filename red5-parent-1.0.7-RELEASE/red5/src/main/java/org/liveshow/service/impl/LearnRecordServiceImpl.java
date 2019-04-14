package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dao.LearnRecordMapper;
import org.liveshow.dao.TeahRecorMapper;
import org.liveshow.entity.LearnRecord;
import org.liveshow.entity.LearnRecordExample;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.TeahRecor;
import org.liveshow.service.LearnRecordService;
import org.liveshow.service.TeachRecordService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Component
public class LearnRecordServiceImpl implements LearnRecordService {
    public static final String sqlTemplate = "update learn_record set gmt_out='%s' where id in (%s) AND gmt_out IS NULL";
    public static final String getSqlByUserAndId = "update learn_record set gmt_out='%s' WHERE record_id = '%s' AND user_id = '%s' AND gmt_out IS NULL ";
    public static final String getSqlFaceCount = "update learn_record set face_rego_count='%s', face_rego_success='%s' WHERE id = '%s' AND user_id = '%s'";


    @Resource
    private LearnRecordMapper learnRecordMapper;
    @Resource
    private TeachRecordService teachRecordService;

    @Override
    public int addLearnRecordService(String userId, Integer recordId) {
        if (StringUtils.isBlank(userId) || recordId == null || !StringUtils.isNumeric(recordId.toString()) ) {
            return -1;
        }

        LearnRecord learnRecord = new LearnRecord();
        learnRecord.setUserId(userId);
        learnRecord.setGmtIn(String.valueOf(System.currentTimeMillis()));
        learnRecord.setRecordId(recordId);
        learnRecord.setFaceRegoCount(0);
        learnRecord.setFaceRegoSuccess(0);
        return learnRecordMapper.insert(learnRecord);
    }

    @Override
    public List<LearnRecord> queryRecordByTid(Integer tid) {
        if ( tid == null ) {
            return null;
        }
        LearnRecordExample example = new LearnRecordExample();
        LearnRecordExample.Criteria criteria = example.createCriteria();
        criteria.andRecordIdEqualTo(tid);

        List<LearnRecord> res = learnRecordMapper.selectByExample(example);
        if ( CollectionUtils.isEmpty(res) ) {
            return null;
        }
        return res;
    }

    @Override
    public List<LearnRecord> queryRecordByTidAndGmtEndIsNull(Integer id) {
        List<LearnRecord> res = queryRecordByTid(id);
        if ( CollectionUtils.isEmpty(res) ) {
            return null;
        }
        Iterator iterator = res.iterator();
        while(iterator.hasNext()) {
            LearnRecord learnRecord =(LearnRecord) iterator.next();
            if (learnRecord.getGmtOut() != null) {
                iterator.remove();
            }
        }

        return res;
    }

    @Override
    public LearnRecord queryRecordByUidAndTidAndGmtOutNull(String userId, Integer recordId) {
        if ( StringUtils.isBlank(userId) || recordId == null ) {
            return null;
        }
        LearnRecordExample example = new LearnRecordExample();
        LearnRecordExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(userId);
        criteria.andRecordIdEqualTo(recordId);
        criteria.andGmtOutIsNull();
        List<LearnRecord> res = learnRecordMapper.selectByExample(example);
        if ( CollectionUtils.isEmpty(res)) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public List<LearnRecord> queryRecordByUidAndTidAndGmtOutNotNull(String userId, Integer recordId) {
        if ( StringUtils.isBlank(userId) || recordId == null ) {
            return null;
        }
        LearnRecordExample example = new LearnRecordExample();
        LearnRecordExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(userId);
        criteria.andRecordIdEqualTo(recordId);
        criteria.andGmtOutIsNotNull();
        List<LearnRecord> res = learnRecordMapper.selectByExample(example);

        return res;
    }

    @Override
    public void updateByUserIds(List<LearnRecord> records) {
        if ( CollectionUtils.isEmpty(records) ) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();

        records.forEach(val->{
            if ( val.getId()!=null) {
                stringBuffer.append(val.getId());
                stringBuffer.append(',');
            }
        });
        stringBuffer.append(-1);
        String sql = String.format(sqlTemplate,String.valueOf(System.currentTimeMillis()), stringBuffer.toString());
        learnRecordMapper.updateBySql(sql);
    }

    @Override
    public void updateByUserId(String userId, Integer classId) {
        TeahRecor teahRecor = teachRecordService.getReMaxClassNum(classId);
        if ( teahRecor == null ) {
            return;
        }

        String sql = String.format(getSqlByUserAndId,String.valueOf(System.currentTimeMillis()), teahRecor.getId().toString(), userId);
        learnRecordMapper.updateBySql(sql);

    }

    @Override
    public void updateRecordById(LearnRecord record) {
        String sql = String.format(getSqlFaceCount,record.getFaceRegoCount(),record.getFaceRegoSuccess(),record.getId(),record.getUserId());
        learnRecordMapper.updateBySql(sql);
    }


}
