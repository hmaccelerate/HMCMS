package com.hm.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hm.cms.domain.Menu;
import com.hm.cms.domain.User;
import com.hm.cms.service.MenuService;
import com.hm.cms.service.UserService;
import com.hm.common.utils.QueryHelper;
import com.hm.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hmaccelerate on 2015/7/3.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/doLogin.htm")
    public String doLogin(User user, HttpServletRequest request) {
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("User.loginCheck").setValue(user);
        User correctUser = userService.findEntity(helper);
        if (correctUser != null) {
            request.getSession().setAttribute("correctUser", correctUser);
            return "admin/index";
        }
        return "admin/login";
    }


    @RequestMapping("/getTopUserMenu.htm")
    public void getTopUserMenu(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("correctUser");
        long role_id = user.getRole_id();
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("RoleMenu.getTopUserMenu").setValue(role_id);
        List<Menu> menuList = menuService.findEntities(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        object.put("menuList", menuList);
        object.put("role_id",role_id);
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    @RequestMapping("/getSecondUserMenu.htm")
    public void getSecondUserMenu(HttpServletResponse response, Long role_id, Long fa_id) {
        System.out.println(role_id);
        System.out.println(fa_id);
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("RoleMenu.getSecondUserMenu")
                .putParams("role_id", role_id).putParams("fa_id", fa_id);
        List<Menu> secondMenuList = menuService.findEntities(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        object.put("secondMenuList", secondMenuList);
        System.out.println(object.toJSONString());
        ResponseUtil.renderJson(response, object.toJSONString());
    }
}
