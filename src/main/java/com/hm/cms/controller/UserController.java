package com.hm.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hm.cms.domain.Article;
import com.hm.cms.domain.Role;
import com.hm.cms.domain.User;
import com.hm.cms.service.RoleService;
import com.hm.cms.service.UserService;
import com.hm.common.utils.Page;
import com.hm.common.utils.QueryHelper;
import com.hm.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by hmaccelerate on 2015/6/29.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/updateAddUserUI.htm")
    public void updateAddUserUI(HttpServletResponse response){
        QueryHelper helper=new QueryHelper();
        helper.setNamespace("Role.getAllRole");
        List<Role> roleList=roleService.findEntities(helper);
        JSONObject object=ResponseUtil.getJsonObject();
        object.put("roleList",roleList);
        ResponseUtil.renderJson(response,object.toJSONString());
    }

    @RequestMapping("/addUser")
    public void addUser(User user,HttpServletResponse response){
        System.out.println(user.getRole_id());
        QueryHelper queryHelper = new QueryHelper();
        user.setBuild_time(new Date());
        queryHelper.setNamespace("User.addUser").setValue(user);
        int right = userService.addEntity(queryHelper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (right == 1) {
            object.put("statusCode", 200);
            object.put("message", "添加成功");
            object.put("nvaTabId", "");
            object.put("rel", "");
            object.put("callbackType", "");
            object.put("forwardUrl", "");

        } else {
            object.put("statusCode", 300);
            object.put("message", "添加失败");
        }
        System.out.println(object.toJSONString());
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    @RequestMapping("/updateUserListUI.htm")
    public String updateUserListUI(HttpServletRequest request,Integer pageNum){
        request.getSession().setAttribute("pageNum", pageNum);
        return "admin/user/userList";
    }

    @RequestMapping("/loadUserList.htm")
    public void loadUserList(HttpServletRequest request,HttpServletResponse response) {
        Integer pageNum=(Integer)request.getSession().getAttribute("pageNum");
        pageNum = pageNum == null ? 1 : pageNum;
        Page<User> userPage = userService.getUserPage(pageNum, 5);
        JSONObject object = ResponseUtil.getJsonObject();
        object.put("userPage", userPage);
        System.out.println(object.toJSONString());
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    /**
     * 删除用户
     * @param response
     * @param user_id
     */
    @RequestMapping("/delUser.htm")
    public void delUser(HttpServletResponse response, long user_id) {
        System.out.println(user_id);
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("User.deleteUser").setValue(user_id);
        int right = userService.deleteEntity(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (right == 1) {
            object.put("statusCode", 200);
            object.put("message", "删除成功");
            object.put("nvaTabId", "");
            object.put("rel", "");
            object.put("callbackType", "forward");
            object.put("forwardUrl", "/admin/user/userList.htm");
        } else {
            object.put("statusCode", 300);
            object.put("message", "删除失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    /**
     * 获取某篇用户id，并跳转到用户更新页面
     * @param request
     * @param user_id
     * @return
     */
    @RequestMapping("/updateUserUI.htm")
    public String updateUserUI(HttpServletRequest request, long user_id) {
        request.getSession().setAttribute("user_id", user_id);
        return "admin/user/updateUser";
    }

    /**
     * 获取用户id，获取用户具体内容
     * @param request
     * @param response
     */
    @RequestMapping("/getUserData.htm")
    public void getUserData(HttpServletRequest request, HttpServletResponse response) {
        long user_id=(Long)request.getSession().getAttribute("user_id");
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("User.getUserData").setValue(user_id);
        User user = userService.findEntity(helper);
        System.out.println(user.getUser_id());
        JSONObject object = ResponseUtil.getJsonObject();
        if (user != null) {
            object.put("statusCode", 200);
            object.put("message", "获取成功");
            object.put("user",user);
        } else {
            object.put("statusCode", 300);
            object.put("message", "获取数据失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    /**
     * 表单提交，更新用户
     * @param response
     * @param user
     */
    @RequestMapping("/updateUser")
    public void  updateUser(HttpServletResponse response,User user){
        QueryHelper helper=new QueryHelper();
        helper.setNamespace("User.updateUser").setValue(user);
        int right= userService.updateEntity(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (right == 1) {
            object.put("statusCode", 200);
            object.put("message", "修改成功");
            object.put("nvaTabId", "");
            object.put("rel", "");
            object.put("callbackType", "");
            object.put("forwardUrl", "");
        } else {
            object.put("statusCode", 300);
            object.put("message", "操作失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

}
