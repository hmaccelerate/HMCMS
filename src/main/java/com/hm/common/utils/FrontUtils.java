package com.hm.common.utils;

import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015-1-13.
 */
public class FrontUtils {


    /**
     * 404页面，请求资源未找到
     */
    public static final String PAGE_NOT_FOUND = "public/404";

    /**
     * 请求转发到主页面
     */
    public static final String FORWARD_TO_INDEX = "forward:/cms/index";
    /**
     * 跳到登录页面
     */
    public static final String FORWARD_LOGIN = "customer/user/login";
    /**
     * 用户session保存的字段
     */
    public static final String USER_KEY = "user";
    /**
     * 自动登录的key字段
     */
    public static final String AUTO_LOGIN_FIELD = "autoLogin";
    /**
     * at功能
     */
    public static final String AT_NICK_HTML = "<a href='/user/personal/:nick' style='color:#36A2FF'>:at</a>";
    /**
     * 重定向到主页
     */
    public static final String REDIRECT_TO_INDEX = "redirect:/";
    /**
     * 重定向到登陆注册
     */
    public static final String REDIRECT_TO_LOGIN = "redirect:/login_register.htm";

    /**
     * 获得请求地址的URI
     * @param request
     * @return
     */
    public static String getURI(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        String uri = helper.getOriginatingRequestUri(request);
        String context = helper.getOriginatingContextPath(request);
        return StringUtils.isEmpty(context) ? uri : uri.substring(context.length());
    }

    public static String substring(String content, int length){
        Pattern pattern = Pattern.compile("<[^>]+>|&nbsp");
        Matcher matcher = pattern.matcher(content);
        content = matcher.replaceAll("");
        return content.length()<length ? content : content.substring(0, length)+"···";
    }

}
