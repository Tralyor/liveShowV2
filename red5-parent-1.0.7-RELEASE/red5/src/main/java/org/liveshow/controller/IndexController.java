package org.liveshow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Cjn on 2017/11/28.
 */
@Controller
public class IndexController {
    
    @RequestMapping("/index")
    public String getIndex(Model model){
       return "show/index";
    }
}
