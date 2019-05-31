package org.liveshow.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dto.Show;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.Tuser;
import org.liveshow.entity.UserClassMapping;
import org.liveshow.service.TClassService;
import org.liveshow.service.UserClassMappingService;
import org.liveshow.service.UserService;
import org.liveshow.util.SessionUtil;
import org.liveshow.util.ShowInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/classInfo")
public class ClassInfoController {
    @Resource
    private TClassService tClassService;
    @Resource
    private UserClassMappingService userClassMappingService;
    @Resource
    private UserService userService;

    @RequestMapping("/index")
    public String getClassIndex(HttpServletRequest request, Model model) {
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( tuser == null ) {
            return "redirect:/";
        }

        List<Tclass> res = tClassService.queryClassByCreatorId(tuser.getUserId());
        model.addAttribute("classInfo", res);
        return "/manageV2/classInfo";
    }

    @RequestMapping("/updateInfo")
    @ResponseBody
    public Show updateClassInfo(Integer classId, String className, String classInfo) {
        Show show = new Show();
        if ( classId != null && StringUtils.isNotBlank(className) && StringUtils.isNotBlank(classInfo) ) {
            tClassService.updateTeachInfo(classId, className,classInfo);
            ShowInfoUtil.buildShowInfo(show,true,"",1,null);
        }else {
            ShowInfoUtil.buildShowInfo(show,false,"参数错误",0,null);
        }

        return show;
    }

    @RequestMapping("/create")
    @ResponseBody
    public Show createClassInfo(String className, String classInfo,HttpServletRequest request) {
        Show show = new Show();
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( tuser == null ) {
            ShowInfoUtil.buildShowInfo(show,false,"用户未登录",0,null);
        }
        if (StringUtils.isBlank(className) || StringUtils.isBlank(classInfo)) {
            ShowInfoUtil.buildShowInfo(show,false,"参数错误",0,null);
            return show;
        }
        if ( tClassService.addTclass(tuser.getUserId(),className, classInfo) < 0 ) {
            ShowInfoUtil.buildShowInfo(show,false,"更新失败，请联系系统管理员",0,null);
        }
        ShowInfoUtil.buildShowInfo(show,true,"创建成功",1,null);
        return show;
    }

    @RequestMapping("/uploadUCmapping")
    @ResponseBody
    public Show uploadMapping(HttpServletRequest request, Integer classId){
        Show show = new Show();
        HttpSession session = request.getSession();
        Tuser user =(Tuser)session.getAttribute("user");
        Tclass tclass = tClassService.queryTClassById(classId);
        if ( user == null || user.getUserName() == null || user.getUserId() == null ) {
            ShowInfoUtil.buildShowInfo(show, false, "用户未登录",0,null);
            return show;
        }
        if ( tclass == null || !tclass.getCreaterId().equals(user.getUserId()) ) {
            ShowInfoUtil.buildShowInfo(show, false, "你不是该课程的拥有者或该课程不存在",0,null);
            return show;
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile multipartFile = multipartRequest.getFile("file");
        String fileName = multipartFile.getOriginalFilename();
        if ( StringUtils.isBlank(fileName) || !"txt".equalsIgnoreCase(fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()))){
            ShowInfoUtil.buildShowInfo(show, false, "文件后格式不对，请使用txt格式上传",0,null);
            return show;
        }
        try {
           userClassMappingService.deleteClassMapping(classId);
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader( multipartFile.getInputStream()));
           String buffer ;
           while((buffer = bufferedReader.readLine()) != null ) {

                if ( StringUtils.isBlank(buffer) ) {
                    ShowInfoUtil.buildShowInfo(show, false, buffer+"格式错误", 0, null);
                    return show;
                }

                Tuser tuser = userService.queryUserByUserId(buffer);
                if ( tuser == null ) {
                    ShowInfoUtil.buildShowInfo(show, false, buffer+"该用户还未注册", 0, null);
                    return show;
                }
                UserClassMapping userClassMapping = new UserClassMapping();
                userClassMapping.setUserId(buffer);
                userClassMapping.setClassId(classId);
                if ( userClassMappingService.insertMapping(userClassMapping) <= 0){
                    ShowInfoUtil.buildShowInfo(show, false, buffer+"系统错误", 0, null);
                    return show;
                }
           }

        } catch (IOException e) {
            ShowInfoUtil.buildShowInfo(show, false, "系统错误", 0, null);
            return show;
        }
        ShowInfoUtil.buildShowInfo(show,true, "上传成功",1,null);
        return show;
    }

    @RequestMapping("/usersInfo")
    @ResponseBody
    public Show getClassMappingUser(Integer classId) {
        Show show = new Show();
        List<Tuser> tusers = new ArrayList<>();
        List<UserClassMapping> uList = userClassMappingService.getClassUser(classId);

        if (CollectionUtils.isEmpty(uList)) {
            ShowInfoUtil.buildShowInfo(show, false, "该课程下没有对应的学生",0,null);
            return show;
        }

        uList.forEach(item-> {
            Tuser tuser = userService.queryUserByUserId(item.getUserId());
            Tuser tmp = new Tuser();
            tmp.setUserId(tuser.getUserId());
            tmp.setUserName(tuser.getUserName());
            tmp.setUserId(tuser.getUserId());
            tmp.setImgAddress(tuser.getImgAddress());
            tusers.add(tmp);
        });
        ShowInfoUtil.buildShowInfo(show,true,"",1,tusers);
        return show;
    }

}
