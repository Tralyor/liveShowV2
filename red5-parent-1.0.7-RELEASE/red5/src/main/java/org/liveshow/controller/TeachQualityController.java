package org.liveshow.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dto.*;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.TeahRecor;
import org.liveshow.entity.Tuser;
import org.liveshow.entity.UserClassMapping;
import org.liveshow.service.*;
import org.liveshow.util.SessionUtil;
import org.liveshow.util.ShowInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/quality")
public class TeachQualityController {
    @Resource
    private AttendanceService attendanceService;
    @Resource
    private TClassService tClassService;
    @Resource
    private UserClassMappingService userClassMappingService;
    @Resource
    private TeachRecordService teachRecordService;
    @Resource
    private UserService userService;

    @RequestMapping("/self")
    public String selfInfo(HttpServletRequest request, Model model) {
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( tuser == null ) {
            return "redirect:/";
        }
        List<UserClassMapping> res = userClassMappingService.getUserClass(tuser.getUserId());
        List<Integer> classIds = new ArrayList<>();
        res.forEach(val->classIds.add(val.getClassId()));
        List<Tclass> classes = tClassService.queryClassByIds(classIds);
        model.addAttribute("userClass",classes);
        return "/manageV2/userQuality";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Show queryUserClassQuality(Integer classId, HttpServletRequest request) {
        Show res = new Show();
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( classId == null ||  tuser == null ) {
            ShowInfoUtil.buildShowInfo(res, false, "参数不合法",0,null);
            return res;
        }

        AttendanceVO aRes = attendanceService.queryInfoByIdWrapped(classId, tuser.getUserId());
        ShowInfoUtil.buildShowInfo(res,true,null,1,aRes);
        return res;
    }

    @RequestMapping("/teach")
    public String teachView(Integer classId, Model model) {
        if ( classId == null ){
            return "redirect:/";
        }
        model.addAttribute("classId",classId);
        return "/manageV2/teachManager";
    }

    @RequestMapping("/teachView")
    @ResponseBody
    public Show queryTeachInfo(Integer classId){
        Show show = new Show();
        ClassAttendanceVO res = new ClassAttendanceVO();

        if ( classId == null ) {
            ShowInfoUtil.buildShowInfo(show,false,"参数错误",0,null);
            return show;
        }
        Tclass tclass = tClassService.queryTClassById(classId);
        if ( tclass == null ) {
            ShowInfoUtil.buildShowInfo(show,false,"未找到对应课程",0,null);
            return show;
        }



        List<TeahRecor> tRes = teachRecordService.queryRecordByClassId(classId);
        List<Integer> classNums = new ArrayList<>();

        //找到所有的用户
        List<UserClassMapping> uRes = userClassMappingService.getClassUser(classId);

        for ( TeahRecor teahRecor : tRes ) {
            Integer attendanceCout = 0;
            Integer attendanceSuccess = 0;
            Integer recoCount = 0;
            Integer recoSuccess = 0;

            for ( UserClassMapping userClassMapping : uRes ) {
                AttendanceDTO attendanceDTO = attendanceService.queryInfoByRidAndUis(teahRecor.getId(), userClassMapping.getUserId());
                if ( attendanceDTO == null ) {
                    continue;
                }
                attendanceCout ++;
                recoCount += attendanceDTO.getFaceRecoCount();
                recoSuccess += attendanceDTO.getFaceRecoSuccess();
                if ( Boolean.TRUE.equals(attendanceDTO.getAttendance()) ) {
                    attendanceSuccess++;
                }
            }
            res.getAttendanceCount().add(attendanceCout);
            res.getAttendanceSuccess().add(attendanceSuccess);

            BigDecimal bigDecimal = new BigDecimal(attendanceSuccess*100);
            BigDecimal bigDecimal1 = new BigDecimal(attendanceCout);
            if ( attendanceCout == 0 || attendanceSuccess == 0) {
                res.getAttendanceRate().add(0.0);
            }else {
                res.getAttendanceRate().add(bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP).doubleValue());
            }

            res.getFaceSuccess().add(recoSuccess);
            res.getFaceCount().add(recoCount);

            bigDecimal = new BigDecimal(recoSuccess*100);
            bigDecimal1 = new BigDecimal(recoCount);
            if ( recoCount == 0 || recoSuccess == 0) {
                res.getFaceRate().add(0.0);
            } else {
                res.getFaceRate().add(bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            classNums.add(teahRecor.getClassNum());
        }
        res.setClassNums(classNums);
        ShowInfoUtil.buildShowInfo(show, true,"",1,res);
        return show;
    }

    /**
     * 查询出未出勤的人员名单和人脸识别率成功率低于80%的名单
     * @param classId
     * @param classNum
     * @return
     */
    @RequestMapping("/recordIndicator")
    @ResponseBody
    public Show getIndicator(Integer classId, Integer classNum, HttpServletRequest request) {
        Show show = new Show();
        UnOkStudent res = new UnOkStudent();
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( tuser == null ) {
            ShowInfoUtil.buildShowInfo(show, false, "用户未登录", 0, null );
            return show;
        }
        if ( classId == null || classNum == null ) {
            ShowInfoUtil.buildShowInfo(show, false, "参数不合法", 0, null );
            return show;
        }

        TeahRecor teahRecor = teachRecordService.queryRecordByClassIdAndClassNum(classId, classNum);
        Tclass tclass = tClassService.queryTClassById(classId);
        if ( tclass == null || tclass.getCreaterId() == null || !tclass.getCreaterId().equals(tuser.getUserId()) ) {
            ShowInfoUtil.buildShowInfo(show, false, "找不到该课程或者用户不是该课程的拥有者", 0, null );
            return show;
        }
        if ( teahRecor == null ) {
            ShowInfoUtil.buildShowInfo(show, false, "找不到对应的课程节次", 0, null );
            return show;
        }

        List<UserClassMapping> uRes = userClassMappingService.getClassUser(classId);
        if (CollectionUtils.isEmpty(uRes)) {
            ShowInfoUtil.buildShowInfo(show, false, "该课程没有学生", 0, null );
            return show;
        }

        for (UserClassMapping um : uRes) {
            if ( um == null || StringUtils.isBlank(um.getUserId()) ) {
                continue;
            }
            AttendanceDTO tmp = attendanceService.queryInfoByRidAndUis(teahRecor.getId(), um.getUserId());
            Tuser uTmp =  userService.queryUserByUserId(um.getUserId());
            if ( tmp == null || !tmp.getAttendance()) {
               if ( uTmp == null ) {
                   continue;
               }
               Tuser uVO = new Tuser();
               uVO.setUserId(uTmp.getUserId());
               uVO.setUserName(uTmp.getUserName());
               res.getUnInStudents().add(uVO);
            }

            if ( tmp.getFaceRecoRate() < 80 ){
                Tuser uVO = new Tuser();
                uVO.setUserId(uTmp.getUserId());
                uVO.setUserName(uTmp.getUserName());
                res.getRecoTusers().add(uVO);
            }
        }
        ShowInfoUtil.buildShowInfo(show, true,"",1,res);
        return show;
    }

    @RequestMapping("/managerRecordInfo")
    @ResponseBody
    public Show manageInfo(Model model) {
        List<Tclass> tclasses =tClassService.queryAllClass();
        ManageDTO res = new ManageDTO();
        List<Integer> aRes = new ArrayList<>();
        List<Integer> sRes = new ArrayList<>();
        List<Double> rates = new ArrayList<>();
        List<String>  cRes = new ArrayList<>();
        tclasses.forEach(item->{
            Integer attendaceCount = 0;
            Integer sCount = 0;
            ClassAttendanceVO tmp = getClassInfoById(item.getId());
            for ( Integer t : tmp.getAttendanceCount()) {
                attendaceCount += t;
            }
            for ( Integer t : tmp.getAttendanceSuccess()) {
                sCount += t;
            }
            aRes.add(attendaceCount);
            sRes.add(sCount);
            cRes.add(item.getClassName());
            if ( attendaceCount == 0 || sCount == 0) {
                rates.add(0.0);
            }else {
                BigDecimal bigDecimal1 = new BigDecimal(sCount*100);
                BigDecimal bigDecimal2 = new BigDecimal(attendaceCount);
                rates.add(bigDecimal1.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        });
       res.setClassName(cRes);
       res.setPersonNums(aRes);
       res.setFactNums(sRes);
       res.setRates(rates);
       Show show = new Show();
       show.setData(res);
       show.setSuccess(true);
       show.setState(1);
       return show;
    }

    @RequestMapping("manageViews")
    public String manageView () {
        return "/manageV2/manageView";
    }

    public ClassAttendanceVO getClassInfoById(Integer classId){
        List<TeahRecor> tRes = teachRecordService.queryRecordByClassId(classId);
        List<Integer> classNums = new ArrayList<>();

        //找到所有的用户
        List<UserClassMapping> uRes = userClassMappingService.getClassUser(classId);
        ClassAttendanceVO res = new ClassAttendanceVO();
        for ( TeahRecor teahRecor : tRes ) {
            Integer attendanceCout = 0;
            Integer attendanceSuccess = 0;
            Integer recoCount = 0;
            Integer recoSuccess = 0;

            for ( UserClassMapping userClassMapping : uRes ) {
                AttendanceDTO attendanceDTO = attendanceService.queryInfoByRidAndUis(teahRecor.getId(), userClassMapping.getUserId());
                if ( attendanceDTO == null ) {
                    continue;
                }
                attendanceCout ++;
                recoCount += attendanceDTO.getFaceRecoCount();
                recoSuccess += attendanceDTO.getFaceRecoSuccess();
                if ( Boolean.TRUE.equals(attendanceDTO.getAttendance()) ) {
                    attendanceSuccess++;
                }
            }
            res.getAttendanceCount().add(attendanceCout);
            res.getAttendanceSuccess().add(attendanceSuccess);

            BigDecimal bigDecimal = new BigDecimal(attendanceSuccess*100);
            BigDecimal bigDecimal1 = new BigDecimal(attendanceCout);
            if ( attendanceCout == 0 || attendanceSuccess == 0) {
                res.getAttendanceRate().add(0.0);
            }else {
                res.getAttendanceRate().add(bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP).doubleValue());
            }

            res.getFaceSuccess().add(recoSuccess);
            res.getFaceCount().add(recoCount);

            bigDecimal = new BigDecimal(recoSuccess*100);
            bigDecimal1 = new BigDecimal(recoCount);
            if ( recoCount == 0 || recoSuccess == 0) {
                res.getFaceRate().add(0.0);
            } else {
                res.getFaceRate().add(bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            classNums.add(teahRecor.getClassNum());
        }
        res.setClassNums(classNums);
        return res;
    }
}
