package com.hm.cms.service.imp;

import com.hm.cms.domain.Article;
import com.hm.cms.domain.Role;
import com.hm.cms.service.RoleService;
import com.hm.common.service.imp.ServiceSupport;
import com.hm.common.utils.Page;
import com.hm.common.utils.QueryHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hmaccelerate on 2015/7/2.
 */
@Service
@Transactional
public class RoleServiceImp extends ServiceSupport implements RoleService {
    public Page<Role> getRolePage(Integer pageNum, Integer pageSize) {
        QueryHelper helper = new QueryHelper();
        helper.setNamespace("Role.getRolePage")
                .putParams("index", (pageNum - 1) * pageSize).putParams("pageSize", pageSize);
        List<Role> list = dao.findList(helper);
        helper.clear();
        helper.setNamespace("Role.countRole");
        int countNum=this.findCount(helper);
        Page<Role> page=new Page<Role>(pageNum,pageSize,countNum,list);
        helper.clear();
        return page;
    }
}
