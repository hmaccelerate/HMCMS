import com.hm.cms.domain.Article;
import com.hm.cms.service.ArticleService;
import com.hm.common.utils.Page;
import com.hm.common.utils.QueryHelper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by hmaccelerate on 2015/6/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class Test {

    @Autowired
    private ArticleService articleService;

    @org.junit.Test
    public void testAddArticle(){
        Article article=new Article();
        article.setUser_id(1);
        article.setTitle("测试");
        article.setContent("cesshi sdfjsl反垄断是解放路店");
        article.setUp_time(new Date());
        QueryHelper queryHelper = new QueryHelper();
        queryHelper.setNamespace("Article.addArticle").setValue(article);
        int right = articleService.addEntity(queryHelper);
    }

    @org.junit.Test
    public void testgetArticlePage(){
        Page<Article> page=articleService.getArticlePage(4, 3);
        for (Article a:page.getRecordList()){
            System.out.println(a.getTitle());
        }
    }


}
