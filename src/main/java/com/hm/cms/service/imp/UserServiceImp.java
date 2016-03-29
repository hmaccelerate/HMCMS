package com.hm.cms.service.imp;

import com.hm.cms.domain.User;
import com.hm.cms.service.UserService;
import com.hm.common.service.imp.ServiceSupport;
import com.hm.common.utils.Page;
import com.hm.common.utils.QueryHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hmaccelerate on 2015/6/29.
 */
@Service
@Transactional
public class UserServiceImp extends ServiceSupport implements UserService{
    public Page<User> getUserPage(Integer pageNum, Integer pageSize) {
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("User.getUserPage")
                .putParams("index", (pageNum - 1) * pageSize).putParams("pageSize", pageSize);
        List<User> list = dao.findList(helper);
        helper.clear();
        helper.setNamespace("User.countUser");
        int countNum=this.findCount(helper);
        Page<User> page=new Page<User>(pageNum,pageSize,countNum,list);
        helper.clear();
        return page;
    }
}
