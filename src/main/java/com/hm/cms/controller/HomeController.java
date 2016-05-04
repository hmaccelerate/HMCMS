package com.hm.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hm.cms.domain.Article;
import com.hm.cms.service.ArticleService;
import com.hm.common.utils.QueryHelper;
import com.hm.common.utils.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hmaccelerate on 2015/7/3.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("/article.htm")
    public String article(){
        return "home/index";
    }

    @RequestMapping("/getArticle")
    public void getArticle(HttpServletResponse response){
        QueryHelper helper=new QueryHelper();
        helper.setNamespace("Article.getAllArticle");
        List<Article> articleList=articleService.findEntities(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (articleList != null) {
            object.put("statusCode", 200);
            object.put("message", "获取成功");
            object.put("articleList",articleList);
        } else {
            object.put("statusCode", 300);
            object.put("message", "获取数据失败");
        }
        System.out.println(object.toJSONString());
        ResponseUtil.renderJson(response, object.toJSONString());

    }
}
