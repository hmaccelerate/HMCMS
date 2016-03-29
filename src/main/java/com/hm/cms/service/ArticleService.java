package com.hm.cms.service;

import com.hm.cms.domain.Article;
import com.hm.common.service.BaseService;
import com.hm.common.utils.Page;

/**
 * Created by hmaccelerate on 2015/6/29.
 */
public interface ArticleService  extends BaseService {
    Page<Article> getArticlePage(Integer pageNum,Integer pageSize);
}
