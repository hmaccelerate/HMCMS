package com.hm.cms.service;

import com.hm.cms.domain.Role;
import com.hm.common.service.BaseService;
import com.hm.common.utils.Page;

/**
 * Created by hmaccelerate on 2015/7/2.
 */
public interface RoleService extends BaseService {

    Page<Role> getRolePage(Integer pageNum, Integer pageSize);
}
