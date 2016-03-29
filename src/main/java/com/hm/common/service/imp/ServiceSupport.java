package com.hm.common.service.imp;

import com.hm.common.dao.imp.DaoSupportImp;
import com.hm.common.service.BaseService;
import com.hm.common.utils.QueryHelper;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hmaccelerate on 2015/6/23.
 */
@Repository
@Transactional
public class ServiceSupport extends DaoSupportImp implements BaseService {
    public int addEntity(QueryHelper helper) {
        return dao.append(helper);
    }

    public <E> E findEntity(QueryHelper helper) {
        return dao.findOne(helper);
    }

    public <E> List<E> findEntities(QueryHelper helper) {
        return dao.findList(helper);
    }

    public int deleteEntity(QueryHelper helper) {
        return dao.deleteOne(helper);
    }

    public int countTable(QueryHelper helper) {
        return dao.findCount(helper);
    }

    public int updateEntity(QueryHelper helper) {
        return dao.updateOne(helper);
    }
}
