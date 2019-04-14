package org.liveshow.controller;

import org.liveshow.dto.Show;
import org.liveshow.entity.LearnRecord;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.TeahRecor;
import org.liveshow.entity.Tuser;
import org.liveshow.service.LearnRecordService;
import org.liveshow.service.TClassService;
import org.liveshow.service.TeachRecordService;
import org.liveshow.service.UserClassMappingService;
import org.liveshow.service.impl.TeachRecordServiceImpl;
import org.liveshow.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/liveShow")
public class TeachShowController {
    @Resource
    private TClassService tClassService;

    @Resource
    private UserClassMappingService userClassMappingService;

    @Resource
    private LearnRecordService learnRecordService;

    @Resource
    private TeachRecordServiceImpl teachRecordService;

    @RequestMapping("/index/{roomId}")
    public String showPage(@PathVariable Integer roomId, HttpServletRequest req, Model model) {
        Tclass res = tClassService.queryTClassById(roomId);
        Tuser tuser = SessionUtil.getUserInSession(req);
        TeahRecor teahRecor = teachRecordService.getReMaxClassNum(roomId);

        if ( res != null && res.getId() != null && tuser != null && res.getCreaterId().equals(tuser.getUserId()) ) {
          //不过滤
        } else if ( tuser == null || res == null || !res.getTeaching() || teahRecor == null ) {
            return "redirect:/";
        }

        if ( userClassMappingService.isMapping(tuser.getUserId(), roomId) ) {
            learnRecordService.addLearnRecordService(tuser.getUserId(), teahRecor.getId());
        }
        model.addAttribute("room", res);
        return "show/liveShow";
    }

    @RequestMapping("/teach")
    @ResponseBody
    public Show changeState(Integer state, Integer classId, HttpServletRequest request) {
        Show show = new Show();
        Tuser tuser = SessionUtil.getUserInSession(request);
        Tclass res = tClassService.queryTClassById(classId);
        TeahRecor teahRecor = teachRecordService.getReMaxClassNum(classId);

        if ( state == null || classId == null || tuser == null) {
            buildRes(show, false ,"参数错误");
            return show;
        }

        if ( state == 1 ) {
            if (res.getCreaterId() != null && res.getCreaterId().equals(tuser.getUserId()) && teahRecor.getGmtEnd() != null) {
                if ( teahRecor == null ) {
                    teachRecordService.addTeachRecord(classId, 1);
                } else {
                    teachRecordService.addTeachRecord(classId, teahRecor.getClassNum()+1);
                }
                res.setTeaching(true);
                tClassService.updateTeaching(res);
                buildRes(show, true, "直播开启成功");
            } else {
                buildRes(show, true, "直播开启失败");
            }
        } else if ( state == 0){
            if ( teahRecor.getGmtEnd() == null ) {
                //获取当前课程下所有出去的记录为空的学习记录
                List<LearnRecord>  tmp = learnRecordService.queryRecordByTidAndGmtEndIsNull(teahRecor.getId());
                //更新改课程的课下对应的所有的学习结束的记录
                learnRecordService.updateByUserIds(tmp);
                teahRecor.setGmtEnd(String.valueOf(System.currentTimeMillis()));
                teachRecordService.updateTeachRecord(teahRecor);
                res.setTeaching(false);
                tClassService.updateTeaching(res);
                buildRes(show, true, "直播关闭成功");
            } else {
                buildRes(show, true, "直播关闭失败");
            }
        } else {
            buildRes(show, false, "参数错误");
        }
        return show;
    }

    @RequestMapping("/canSure/{roomId}")
    @ResponseBody
    public Show canSure(@PathVariable Integer roomId, HttpServletRequest req) {
        Show res = new Show();
        Tuser user = SessionUtil.getUserInSession(req);
        if ( user == null ) {
            buildRes(res, false,"用户未登录" );
            return res;
        }

        Tclass tclass = tClassService.queryTClassById(roomId);

        if ( tclass == null ) {
            buildRes(res, false,"不存在的房间" );
            return res;
        }

        if ( tclass  != null && tclass.getCreaterId() != null && tclass.getCreaterId().equals(user.getUserId())) {
            buildRes(res, true, "");
            return res;
        }

        if ( !tClassService.queryClassIsTeaching(roomId) ) {
            buildRes(res, false,"老师尚未开课" );
            return res;
        }

        buildRes(res, true, "");
        return res;
    }

    private void buildRes(Show res , boolean success, String message) {
        res.setSuccess(success);
        res.setMessage(message);
    }
}
