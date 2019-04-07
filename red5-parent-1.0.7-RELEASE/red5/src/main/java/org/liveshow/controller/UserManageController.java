package org.liveshow.controller;

import org.apache.catalina.Session;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dto.Show;
import org.liveshow.entity.Tuser;
import org.liveshow.service.UserService;
import org.liveshow.util.SessionUtil;
import org.red5.server.messaging.IFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/userInfo")
public class UserManageController {
    @Resource
    private UserService userService;

    @RequestMapping("/self")
    public String getSelfInfo (HttpServletRequest request) {
        if ( SessionUtil.getUserInSession(request) == null ) {
           return "redirect:/";
        }
        return "manageV2/userManage";
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public Show changePassword(String password, String rPassword, HttpServletRequest request) {
        Show show = new Show();
        if (StringUtils.isBlank(password) || StringUtils.isBlank(rPassword) || !password.equals(rPassword)) {
            buildMessage(show, false, "参数不合法", 0);
            return show;
        }
        Tuser tuser = SessionUtil.getUserInSession(request);
        if ( tuser == null ) {
            buildMessage(show, false, "用户未登录", 0);
            return show;
        }
        tuser.setPassword(password);
        int res = userService.updateUser(tuser);
        if ( res > 0 ) {
            buildMessage(show, true, "密码修改成功，即将进入首页", 1);
            HttpSession session = request.getSession();
            session.setAttribute("user",null);
        } else {
            buildMessage(show, false, "密码修改失败", 0);
        }

        return show;
    }

    @RequestMapping("/img")
    @ResponseBody
    public Show uploadUserImg(HttpServletRequest request) {
        Show show = new Show();
        HttpSession session = request.getSession();
        Tuser user =(Tuser)session.getAttribute("user");
        if ( user == null || user.getUserName() == null || user.getUserId() == null ) {
            buildMessage(show, false, "用户未登录",0);
            return show;
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
        /**构建图片保存的目录**/
        String logoPathDir = "/static/img/closure/"+ dateformat.format(new Date());
        /**得到图片保存目录的真实路径**/
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
        /**根据真实路径创建目录**/
        File logoSaveFile = new File(logoRealPathDir);
        if(!logoSaveFile.exists())
            logoSaveFile.mkdirs();
        /**页面控件的文件流**/
        MultipartFile multipartFile = multipartRequest.getFile("file");
        /**获取文件的后缀**/
        String suffix = multipartFile.getOriginalFilename().substring
                (multipartFile.getOriginalFilename().lastIndexOf("."));
        /**使用UUID生成文件名称**/
        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称
        //String logImageName = multipartFile.getOriginalFilename();
        /**拼成完整的文件保存路径加文件**/
        String fileName = logoRealPathDir + File.separator   + logImageName;
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setImgAddress(fileName);

        int res = userService.updateUser(user);
        if ( res > 0 ) {
            buildMessage(show, true, "上传成功", 1);
        } else {
            buildMessage(show, false, "上传失败，请稍后再试", 0);
        }

        return show;
    }

    private void buildMessage (Show show, boolean success, String msg,int status) {
        show.setSuccess(success);
        show.setMessage(msg);
        show.setState(status);
    }
}
