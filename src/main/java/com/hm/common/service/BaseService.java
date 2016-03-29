package com.hm.common.service;

import com.hm.common.utils.QueryHelper;

import java.util.List;

/**
 * Created by hmaccelerate on 2015/6/23.
 */
public interface BaseService {
    int addEntity(QueryHelper helper);

    <E> E findEntity(QueryHelper helper);

    <E> List<E> findEntities(QueryHelper helper);

    int deleteEntity(QueryHelper helper);

    int countTable(QueryHelper helper);

    int updateEntity(QueryHelper helper);
}
