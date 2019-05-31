package org.liveshow.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dto.ClassCommentVO;
import org.liveshow.dto.EvaluateVO;
import org.liveshow.dto.Show;
import org.liveshow.entity.ClassComment;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.Tuser;
import org.liveshow.entity.UserClassMapping;
import org.liveshow.service.EvaluateService;
import org.liveshow.service.TClassService;
import org.liveshow.service.UserClassMappingService;
import org.liveshow.service.UserService;
import org.liveshow.service.impl.ClassEvaluateServiceImpl;
import org.liveshow.util.SessionUtil;
import org.liveshow.util.ShowInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/evaluate")
public class ClassEvaluateController {

    @Resource
    private UserService userService;
    @Resource
    private UserClassMappingService userClassMappingService;
    @Resource
    private TClassService tClassService;
    @Resource
    private EvaluateService evaluateService;


    @RequestMapping("/teachView")
    public String evaluateTeachView(HttpServletRequest request){
        Tuser tuser = SessionUtil.getUserInSession(request);
        if (tuser == null ) {
            return "redirect:/";
        }
        return "manageV2/classEvaluatwTeach";
    }

    @RequestMapping("/stuView")
    public String evaluateStuView(HttpServletRequest request, Model model) {
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( tuser == null ) {
            return "redirect:/";
        }

        List<UserClassMapping> uc = userClassMappingService.getUserClass(tuser.getUserId());
        List<EvaluateVO> ev = new ArrayList<>();

        uc.forEach(val -> {
            EvaluateVO evaluateVO = new EvaluateVO();
            Tclass tclass = tClassService.queryTClassById(val.getClassId());
            ClassComment classComment =evaluateService.getUserComment(val.getClassId(), tuser.getUserId());
            evaluateVO.setTclass(tclass);
            evaluateVO.setClassComment(classComment);
            ev.add(evaluateVO);
        });
        model.addAttribute("userClass", ev);
        return "/manageV2/classEvaluateStu";
    }

    @RequestMapping("/addEvaluate")
    @ResponseBody
    public Show addEvaluate(Integer classId, String content, HttpServletRequest request) {
        Show show = new Show();
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( tuser == null ) {
            ShowInfoUtil.buildShowInfo(show,false,"用户未登录",0,null);
            return show;
        }
        if ( classId == null || StringUtils.isBlank(content) ) {
            ShowInfoUtil.buildShowInfo(show,false,"参数不合法",0,null);
            return show;
        }

        ClassComment classComment = evaluateService.getUserComment(classId, tuser.getUserId());
        if ( classComment == null ) {
            evaluateService.addComment(classId,tuser.getUserId(), content);
        } else {
            classComment.setContent(content);
            evaluateService.updateUserComment(classComment);
        }
        ShowInfoUtil.buildShowInfo(show, true, "修改成功",1,null);
        return show;
    }

    @RequestMapping("/classComment")
    @ResponseBody
    public Show getComment(Integer classId , HttpServletRequest request){
        Show show = new Show();
        Tuser tuser = SessionUtil.getUserInSession(request);
        Tclass tclass = tClassService.queryTClassById(classId);
        if ( tuser == null ) {
            ShowInfoUtil.buildShowInfo(show,false,"用户未登录",0,null);
            return show;
        }
        if ( tclass == null || tclass.getCreaterId() == null || !tclass.getCreaterId().equals(tuser.getUserId())){
            ShowInfoUtil.buildShowInfo(show,false,"未查到对应课程或你不是该课程的拥有者",0,null);
            return show;
        }
        List<ClassComment>  res = evaluateService.getCommentByClassId(classId);
        List<ClassCommentVO> vres = new ArrayList<>();

        res.forEach(item->{
            Tuser tmp = userService.queryUserByUserId(item.getCommentId());
            ClassCommentVO classCommentVO = new ClassCommentVO();
            if ( tmp != null ) {
                classCommentVO.setClassComment(item);
                classCommentVO.setImgUrl(tmp.getImgAddress());
                classCommentVO.setUserName(tmp.getUserName());
                vres.add(classCommentVO);
            }
        });

        if ( CollectionUtils.isEmpty(vres) ) {
            ShowInfoUtil.buildShowInfo(show,true,"该课程尚未有人评价",0,null);
        } else {
            ShowInfoUtil.buildShowInfo(show,true,"查询成功",1,vres);
        }
        return show;
    }
}
