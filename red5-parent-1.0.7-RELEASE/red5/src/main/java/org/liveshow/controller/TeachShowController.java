package org.liveshow.controller;

import org.liveshow.dto.Show;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.Tuser;
import org.liveshow.service.TClassService;
import org.liveshow.service.UserClassMappingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/liveShow")
public class TeachShowController {
    @Resource
    private TClassService tClassService;

    @Resource
    private UserClassMappingService userClassMappingService;

    @RequestMapping("/index/{roomId}")
    public String showPage(@PathVariable Integer roomId, HttpServletRequest req, Model model) {
        Tclass res = tClassService.queryTClassById(roomId);
        model.addAttribute("room", res);
        return "show/liveShow";
    }

    @RequestMapping("/canSure/{roomId}")
    @ResponseBody
    public Show canSure(@PathVariable Integer roomId, HttpServletRequest req) {
        Show res = new Show();
        HttpSession httpSession = req.getSession();
        Tuser user =(Tuser)httpSession.getAttribute("user");
        if ( user == null ) {
            buildRes(res, false,"用户未登录" );
            return res;
        }

        if ( !tClassService.queryClassIsTeaching(roomId) ) {
            buildRes(res, false,"老师尚未开课" );
            return res;
        }

//        if ( !userClassMappingService.isMapping(user.getUserId(), roomId) ) {
//            buildRes(res, false,"你不是这个班的学生" );
//            return res;
//        }

        buildRes(res, true, "");
        return res;
    }


    private void buildRes(Show res , boolean success, String message) {
        res.setSuccess(success);
        res.setMessage(message);
    }
}
