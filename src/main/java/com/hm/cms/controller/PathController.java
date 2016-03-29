package com.hm.cms.controller;

import com.hm.common.controller.BaseController;
import com.hm.common.utils.FrontUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaomin on 2015/6/3.
 */
@Controller
@RequestMapping("/admin")
public class PathController extends BaseController {

    @RequestMapping("/**/*.htm")
    public String dynamic(HttpServletRequest request){
        String path = FrontUtils.getURI(request);
        path = path.replace(".htm", "");
        Pattern pattern = Pattern.compile("/");
        Matcher matcher = pattern.matcher(path);
        path = matcher.replaceFirst("");
        System.out.println(path);
        return path;
    }

    @RequestMapping("/login.htm")
    public String login() {
        return "admin/login";
    }

    @RequestMapping("/index.htm")
    public  String index(){
        return "admin/index";
    }

}
