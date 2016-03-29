package com.hm.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hm.cms.domain.Article;
import com.hm.cms.service.ArticleService;
import com.hm.common.utils.Page;
import com.hm.common.utils.QueryHelper;
import com.hm.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by hmaccelerate on 2015/6/29.
 */

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
     * @param article
     * @param response
     */
    @RequestMapping("/addArticle.htm")
    public void addArticle(Article article, HttpServletResponse response) {
        article.setUser_id(1);
        article.setUp_time(new Date());
        QueryHelper queryHelper = new QueryHelper();
        queryHelper.setNamespace("Article.addArticle").setValue(article);
        int right = articleService.addEntity(queryHelper);
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

    @RequestMapping("/updateArticleListUI.htm")
    public String updateArticleListUI(HttpServletRequest request,Integer pageNum){
        request.getSession().setAttribute("pageNum",pageNum);
        return "admin/article/articleList";
    }

    @RequestMapping("/loadArticleList.htm")
    public void loadArticleList(HttpServletRequest request,HttpServletResponse response) {
        Integer pageNum=(Integer)request.getSession().getAttribute("pageNum");
        pageNum = pageNum == null ? 1 : pageNum;
        Page<Article> articlePage = articleService.getArticlePage(pageNum, 5);
        JSONObject object = ResponseUtil.getJsonObject();
        object.put("articlePage", articlePage);
        System.out.println(object.toJSONString());
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    /**
     * 删除文章
     * @param response
     * @param ac_id
     */
    @RequestMapping("/delArticle.htm")
    public void delArticle(HttpServletResponse response, long ac_id) {
        System.out.println(ac_id);
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("Article.deleteArticle").setValue(ac_id);
        int right = articleService.deleteEntity(helper);
        JSONObject object = ResponseUtil.getJsonObject();
        if (right == 1) {
            object.put("statusCode", 200);
            object.put("message", "删除成功");
            object.put("nvaTabId", "");
            object.put("rel", "");
            object.put("callbackType", "forward");
            object.put("forwardUrl", "/admin/article/articleList.htm");
        } else {
            object.put("statusCode", 300);
            object.put("message", "删除失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    /**
     * 获取某篇文章id，并跳转到文章更新页面
     * @param request
     * @param ac_id
     * @return
     */
    @RequestMapping("/updateArticleUI.htm")
    public String updateArticleUI(HttpServletRequest request, long ac_id) {
        request.getSession().setAttribute("ac_id", ac_id);
        return "admin/article/updateArticle";
    }

    /**
     * 获取文章id，获取文章具体内容
     * @param request
     * @param response
     */
    @RequestMapping("/getArticleData.htm")
    public void getArticleData(HttpServletRequest request, HttpServletResponse response) {
        long ac_id=(Long)request.getSession().getAttribute("ac_id");
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("Article.getArticleData").setValue(ac_id);
        Article article = articleService.findEntity(helper);
        System.out.println(article.getAc_id());
        JSONObject object = ResponseUtil.getJsonObject();
        if (article != null) {
            object.put("statusCode", 200);
            object.put("message", "获取成功");
            object.put("article",article);
        } else {
            object.put("statusCode", 300);
            object.put("message", "获取数据失败");
        }
        ResponseUtil.renderJson(response, object.toJSONString());
    }

    /**
     * 表单提交，更新文章
     * @param response
     * @param article
     */
    @RequestMapping("/updateArticle")
    public void  updateArticle(HttpServletResponse response,Article article){
        QueryHelper helper=new QueryHelper();
        helper.setNamespace("updateArticle").setValue(article);
       int right= articleService.updateEntity(helper);
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
