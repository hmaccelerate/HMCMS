package com.hm.cms.service.imp;

import com.hm.cms.domain.Article;
import com.hm.cms.service.ArticleService;
import com.hm.common.dao.DaoSupport;
import com.hm.common.service.imp.ServiceSupport;
import com.hm.common.utils.Page;
import com.hm.common.utils.QueryHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hmaccelerate on 2015/6/29.
 */
@Service
@Transactional
public class ArticleServiceImpl extends ServiceSupport implements ArticleService {
    public Page<Article> getArticlePage(Integer pageNum, Integer pageSize) {
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("Article.getArticlePage")
                .putParams("index", (pageNum - 1) * pageSize).putParams("pageSize", pageSize);
        List<Article> list = dao.findList(helper);
        helper.clear();
        helper.setNamespace("Article.countArticle");
        int countNum=this.findCount(helper);
        Page<Article> page=new Page<Article>(pageNum,pageSize,countNum,list);
        helper.clear();
        return page;
    }
}
