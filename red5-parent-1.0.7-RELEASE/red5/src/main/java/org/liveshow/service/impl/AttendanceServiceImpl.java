package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dto.AttendanceDTO;
import org.liveshow.dto.AttendanceVO;
import org.liveshow.entity.LearnRecord;
import org.liveshow.entity.TeahRecor;
import org.liveshow.service.AttendanceService;
import org.liveshow.service.LearnRecordService;
import org.liveshow.service.TeachRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class AttendanceServiceImpl implements AttendanceService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);

    @Resource
    private LearnRecordService learnRecordService;

    @Resource
    private TeachRecordService teachRecordService;

    @Override
    public List<AttendanceDTO> queryInfoById(Integer classId, String userId) {
        List<AttendanceDTO> res = new ArrayList<>();
        if ( classId == null ) {
            return null;
        }

        List<TeahRecor> tRes = teachRecordService.queryRecordByClassId(classId);
        if (CollectionUtils.isEmpty(tRes) ) {
            return null;
        }

        tRes.forEach(item->{
            try {
                AttendanceDTO attendanceDTO = buildAttendance(item, userId);
                res.add(attendanceDTO);
            } catch (NumberFormatException e) {
                logger.error("timeCastFailed item.id={}",item.getId());
            } catch (Exception e){
                logger.error("quality caculate failed item.id={}",item.getId());
            }
        });
        return res;
    }

    @Override
    public AttendanceVO queryInfoByIdWrapped(Integer classId, String userId) {
        List<AttendanceDTO> aRes = queryInfoById(classId, userId);
        AttendanceVO res = new AttendanceVO();
        if ( CollectionUtils.isEmpty(aRes) ) {
            return null;
        }
        res.setAttendanceDTOs(aRes);
        res.setSuccessAttendaceNum(caculateSuccessLearn(aRes));
        res.setTotalAttendanceNum(aRes.size());

        List<Integer> classNums = new ArrayList<>();
        List<Double> rateNum = new ArrayList<>();
        List<Integer> recoCount = new ArrayList<>();
        List<Integer> recoSuccess = new ArrayList<>();
        aRes.forEach(val->{
            classNums.add(val.getClassNum());
            rateNum.add(val.getFaceRecoRate());
            recoCount.add(val.getFaceRecoCount());
            recoSuccess.add(val.getFaceRecoSuccess());
        });

        res.setClassNums(classNums);
        res.setRateNums(rateNum);
        res.setRecoCounts(recoCount);
        res.setRecoSuccess(recoSuccess);

        return res;
    }

    @Override
    public AttendanceDTO queryInfoByRidAndUis(Integer recordId, String userId) {
        if ( recordId == null || StringUtils.isBlank(userId) ) {
            return null;
        }
        TeahRecor teahRecor = teachRecordService.queryRecordByRid(recordId);
        if ( teahRecor == null || teahRecor.getGmtEnd() == null ) {
            return null;
        }

       AttendanceDTO attendanceDTO =  buildAttendance(teahRecor, userId);
        return attendanceDTO;
    }


    private int caculateFaceRecoCount(List<LearnRecord> learnRecords) {
        int  res = 0;
        for ( LearnRecord val : learnRecords) {
            res += val.getFaceRegoCount();
        }
        return res;
    }

    private int caculateFaceRecoSuccess(List<LearnRecord> learnRecords) {
        int  res = 0;
        for ( LearnRecord val : learnRecords) {
            res += val.getFaceRegoSuccess();
        }
        return res;
    }

    private int caculateSuccessLearn(List<AttendanceDTO> attendanceDTOS ) {
        int res =0 ;
        for (AttendanceDTO val : attendanceDTOS ) {
            if (Boolean.TRUE.equals( val.getAttendance() ) ) {
                res ++;
            }
        }
        return res;
    }

    private AttendanceDTO buildAttendance(TeahRecor item, String userId) {
        if ( item == null || StringUtils.isBlank(userId ) ) {
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            attendanceDTO.setAttendance(false);
            attendanceDTO.setFaceRecoRate(0.0);
            attendanceDTO.setFaceRecoCount(0);
            attendanceDTO.setFaceRecoSuccess(0);
            return attendanceDTO;
        }
        Long itemTotalTime = Long.parseLong(item.getGmtEnd()) - Long.parseLong(item.getGmtStart());
        Long factTime = 0L;
        List<LearnRecord> lRes = learnRecordService.queryRecordByUidAndTidAndGmtOutNotNull(userId, item.getId());
        for ( LearnRecord val : lRes) {
            if ( Long.parseLong(item.getGmtStart()) <= Long.parseLong(val.getGmtIn()) &&
                    Long.parseLong(item.getGmtEnd()) >= Long.parseLong(val.getGmtOut())) {
                factTime += (Long.parseLong(val.getGmtOut()) - Long.parseLong(val.getGmtIn()));
            }
        }
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        if ( factTime != 0 && itemTotalTime !=0 && new Double(factTime) / new Double(itemTotalTime) > 0.85 ) {
            attendanceDTO.setAttendance(true);
        } else {
            attendanceDTO.setAttendance(false);
        }
        attendanceDTO.setClassId(item.getClassId());
        attendanceDTO.setClassNum(item.getClassNum());
        attendanceDTO.setFaceRecoCount(caculateFaceRecoCount(lRes));
        attendanceDTO.setFaceRecoSuccess(caculateFaceRecoSuccess(lRes));
        if (attendanceDTO.getFaceRecoSuccess() == 0 || attendanceDTO.getFaceRecoCount() == 0) {
            attendanceDTO.setFaceRecoRate(0.0);
        } else {
            BigDecimal bigDecimal = new BigDecimal(attendanceDTO.getFaceRecoSuccess()*100);
            BigDecimal bigDecimal1 = new BigDecimal(attendanceDTO.getFaceRecoCount());
            attendanceDTO.setFaceRecoRate(bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return attendanceDTO;
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(1100);
        BigDecimal bigDecimal1 = new BigDecimal(12);
        System.out.println(bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP).doubleValue()+"%");
        Double d1= new Double(1);
        Double d2= new Double(10);
        System.out.println(d1/d2);
    }
}
