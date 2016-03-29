package com.hm.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hm.cms.domain.Menu;
import com.hm.cms.domain.Role;
import com.hm.cms.domain.RoleMenu;
import com.hm.cms.service.MenuService;
import com.hm.cms.service.RoleService;
import com.hm.common.utils.Page;
import com.hm.common.utils.QueryHelper;
import com.hm.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hmaccelerate on 2015/7/2.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/addRole.htm")
    public void addRole(Role role, HttpServletResponse response) {
        QueryHelper queryHelper = new QueryHelper();
        queryHelper.setNamespace("Role.addRole").setValue(role);
        int right = roleService.addEntity(queryHelper);
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
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    @RequestMapping("/updateRoleListUI.htm")
    public String updateArticleListUI(HttpServletRequest request, Integer pageNum) {
        request.getSession().setAttribute("pageNum", pageNum);
        return "admin/role/roleList";
    }

    @RequestMapping("/loadRoleList.htm")
    public void loadArticleList(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNum = (Integer) request.getSession().getAttribute("pageNum");
        pageNum = pageNum == null ? 1 : pageNum;
        Page<Role> rolePage = roleService.getRolePage(pageNum, 5);
        JSONObject object = ResponseUtil.getJsonObject();
        object.put("rolePage", rolePage);
        System.out.println(object.toJSONString());
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    @RequestMapping("/delRole.htm")
    public void delArticle(HttpServletResponse response, long role_id) {
        System.out.println(role_id);
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("Role.deleteRole").setValue(role_id);
        int right = roleService.deleteEntity(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (right == 1) {
            object.put("statusCode", 200);
            object.put("message", "删除成功");
            object.put("nvaTabId", "");
            object.put("rel", "");
            object.put("callbackType", "forward");
            object.put("forwardUrl", "/admin/role/roleList.htm");
        } else {
            object.put("statusCode", 300);
            object.put("message", "删除失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    /**
     * 获取某篇角色id，并跳转到角色更新页面
     *
     * @param request
     * @param role_id
     * @return
     */
    @RequestMapping("/updateRoleUI.htm")
    public String updateRoleUI(HttpServletRequest request, long role_id) {
        request.getSession().setAttribute("role_id", role_id);
        return "admin/role/updateRole";
    }

    /**
     * 获取角色id，获取角色具体内容
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getRoleData.htm")
    public void getRoleData(HttpServletRequest request, HttpServletResponse response) {
        long role_id = (Long) request.getSession().getAttribute("role_id");
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("Role.getRoleData").setValue(role_id);
        Role role = roleService.findEntity(helper);
        System.out.println(role.getRole_id());
        JSONObject object = ResponseUtil.getJsonObject();
        if (role != null) {
            object.put("statusCode", 200);
            object.put("message", "获取成功");
            object.put("role", role);
        } else {
            object.put("statusCode", 300);
            object.put("message", "获取数据失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    /**
     * 表单提交，更新角色
     *
     * @param response
     * @param role
     */
    @RequestMapping("/updateRole")
    public void updateArticle(HttpServletResponse response, Role role) {
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("updateRole").setValue(role);
        int right = roleService.updateEntity(helper);
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
            object.put("message", "修改失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    @RequestMapping("/updateAuthorityUI.htm")
    public String updateAuthorityUI(HttpServletRequest request, long role_id) {
        request.getSession().setAttribute("role_id", role_id);
        System.out.println(role_id);
        return "admin/role/setAuthority";
    }

    @RequestMapping("/getRoleMenuData.htm")
    public void getRoleMenuData(HttpServletRequest request, HttpServletResponse response) {
        long role_id = (Long) request.getSession().getAttribute("role_id");
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("RoleMenu.getRoleMenuData").setValue(role_id);
        List<Menu> menuList = menuService.findEntities(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (menuList != null) {
            object.put("statusCode", 200);
            object.put("message", "获取成功");
            object.put("menuList", menuList);
            object.put("role_id", role_id);
        } else {
            object.put("statusCode", 300);
            object.put("message", "获取数据失败");
        }
        System.out.println(object.toJSONString());
        ResponseUtil.renderJson(response, object.toJSONString());
    }


    @RequestMapping("/addRoleMenu.htm")
    public void addRoleMenu(Long role_id, Long[] menuIds, HttpServletResponse response) {
        System.out.println(role_id);
        for(Long id:menuIds){
            System.out.println(id);
        }
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("RoleMenu.delRoleMenu").setValue(role_id);
        roleService.deleteEntity(helper);
        helper.clear();
        JSONObject object = ResponseUtil.getJsonObject();
        if (menuIds != null) {
            for (Long menuId : menuIds) {
                helper.setNamespace("RoleMenu.addRoleMenu").putParams("role_id", role_id).putParams("menu_id", menuId);
                roleService.addEntity(helper);
            }
            object.put("statusCode", 200);
            object.put("message", "设置成功");
            object.put("nvaTabId", "");
            object.put("rel", "");
            object.put("callbackType", "");
            object.put("forwardUrl", "");
        } else {
            object.put("statusCode", 300);
            object.put("message", "设置失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }


}
