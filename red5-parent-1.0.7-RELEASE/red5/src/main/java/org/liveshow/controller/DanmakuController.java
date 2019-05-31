package org.liveshow.controller;

import org.apache.commons.collections.CollectionUtils;
import org.liveshow.dto.DanmakuVO;
import org.liveshow.dto.Show;
import org.liveshow.entity.Danmaku;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.TeahRecor;
import org.liveshow.entity.Tuser;
import org.liveshow.service.DanmakuService;
import org.liveshow.service.TClassService;
import org.liveshow.service.TeachRecordService;
import org.liveshow.service.UserService;
import org.liveshow.util.SessionUtil;
import org.liveshow.util.ShowInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/danmaku")
public class DanmakuController {

    @Resource
    private UserService userService;

    @Resource
    private DanmakuService danmakuService;

    @Resource
    private TClassService tClassService;
    @Resource
    private TeachRecordService teachRecordService;

    @RequestMapping("/view")
    public String danmakuView(HttpServletRequest request) {
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( tuser == null || tuser.getType() == 0) {
            return "redirect:/";
        }
        return "manageV2/danmakuView";
    }

    @RequestMapping("/danmakuData")
    @ResponseBody
    public Show danmakuData(Integer classId, Integer classNum, HttpServletRequest request) {
        Show show = new Show();
        Tuser tuser = SessionUtil.getUserInSession(request);
        if (classId == null || classNum == null ) {
            ShowInfoUtil.buildShowInfo(show, false,"参数不合法",0,null);
            return show;
        }
        Tclass tclass = tClassService.queryTClassById(classId);
        if ( tclass == null || tuser == null || !tclass.getCreaterId().equals(tuser.getUserId())) {
            ShowInfoUtil.buildShowInfo(show, false,"不是该门科的负责人",0,null);
            return show;
        }
        TeahRecor teahRecor = teachRecordService.queryRecordByClassIdAndClassNum(classId, classNum);
        if ( teahRecor == null ) {
            ShowInfoUtil.buildShowInfo(show, false,"没有该课程",0,null);
            return show;
        }
        List<Danmaku> res = danmakuService.queryDanmakuByTid(teahRecor.getId());
        List<DanmakuVO> vres = new ArrayList<>();
        res.forEach(item->{
            DanmakuVO danmakuVO = new DanmakuVO();
            danmakuVO.setDanmaku(item);
            Tuser tmpUser = userService.queryUserByUserId(item.getUserId());
            if ( tmpUser != null ) {
                danmakuVO.setUserName(tmpUser.getUserName());
                danmakuVO.setUserImg(tmpUser.getImgAddress());
                vres.add(danmakuVO);
            }
        });
        if (CollectionUtils.isEmpty(vres)) {
            ShowInfoUtil.buildShowInfo(show, true, "未查询到弹幕",0, vres);
        } else {
            ShowInfoUtil.buildShowInfo(show, true, "",1, vres);
        }
        return show;
    }
}
