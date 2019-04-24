package org.liveshow.controller;

import org.liveshow.dto.Show;
import org.liveshow.entity.Tuser;
import org.liveshow.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Show doLogin(String loginName, String password,HttpServletRequest request, HttpServletResponse response){
        Show show = new Show();
        Tuser re = userService.queryHasUser(loginName, password);
        if ( re == null ) {
            show.setState(0);
            show.setMessage("用户名密码错误");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", re);
            show.setState(1);
            show.setMessage("登陆成功");

        }
        return show;
    }
    @RequestMapping("/loginOut")
    public String doLoginOut(HttpServletRequest request, HttpServletResponse response){
        Show show = new Show();
        request.getSession().setAttribute("user",null);
        return "redirect:/";
    }

}
