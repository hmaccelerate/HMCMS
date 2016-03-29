package com.hm.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hm.cms.domain.Menu;
import com.hm.cms.service.MenuService;
import com.hm.common.utils.QueryHelper;
import com.hm.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hmaccelerate on 2015/7/2.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/getTopMenuData.htm")
    public void getTopMenuData(HttpServletResponse response) {
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("Menu.getTopMenu");
        List<Menu> menuList = menuService.findEntities(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (menuList.size() != 0) {
            object.put("statusCode", 200);
            object.put("menuList", menuList);
        } else {
            object.put("statusCode", 300);
            object.put("message", "获取菜单失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    @RequestMapping("/getSecondMenuData.htm")
    public void getSecondMenuData(HttpServletResponse response,Long fa_id){
        QueryHelper helper=new QueryHelper();
        helper.setNamespace("Menu.getSecondMenu").setValue(fa_id);
        List<Menu> secondMenuList=menuService.findEntities(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (secondMenuList.size() != 0) {
            object.put("statusCode", 200);
            object.put("secondMenuList", secondMenuList);
        } else {
            object.put("statusCode", 300);
            object.put("message", "获取菜单失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

}
