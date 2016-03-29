package com.hm.cms.service;

import com.hm.cms.domain.User;
import com.hm.common.service.BaseService;
import com.hm.common.utils.Page;

/**
 * Created by hmaccelerate on 2015/6/29.
 */
public interface UserService extends BaseService{
    Page<User> getUserPage(Integer pageNum, Integer pageSizes);


}
