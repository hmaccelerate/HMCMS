package com.hm.common.dao;

import com.hm.common.utils.QueryHelper;

import java.util.List;

/**
 * Created by hmaccelerate on 2015/6/23.
 */
public interface DaoSupport {
    int append(QueryHelper helper);

    <E> E findOne(QueryHelper helper);

    <E> List<E> findList(QueryHelper helper);

    int deleteOne(QueryHelper helper);

    int findCount(QueryHelper helper);

    int updateOne(QueryHelper helper);
}
