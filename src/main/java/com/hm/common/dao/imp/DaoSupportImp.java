package com.hm.common.dao.imp;

import com.hm.common.dao.DaoSupport;
import com.hm.common.utils.QueryHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by hmaccelerate on 2015/6/23.
 */
@SuppressWarnings("unchecked")
public class DaoSupportImp implements DaoSupport {
    @Autowired
    private SqlSessionFactory sessionFactory;

    public DaoSupportImp dao = this;

    public SqlSession getSession() {
        return sessionFactory.openSession();
    }


    /**
     * 获得一个简单的QueryHelper帮助类，默认的Alias是类名
     *
     * @return
     */
    public QueryHelper getHelper() {
        return new QueryHelper();
    }

    /**
     * 添加一个对象到数据库
     *
     * @param helper sql辅助类
     * @return
     */

    public int append(QueryHelper helper) {
        return getSession().insert(helper.getNameSpace(), helper.getValue());
    }

    /**
     * 查询单个对象的数据
     *
     * @param helper
     * @return
     */
    public <E> E findOne(QueryHelper helper) {
        return (E) (helper.hasValue()
                ? this.getSession().selectOne(helper.getNameSpace(), helper.getValue())
                : this.getSession().selectOne(helper.getNameSpace()));
    }

    /**
     * 查询多个对象的数据
     *
     * @param helper
     * @param <E>
     * @return
     */
    public <E> List<E> findList(QueryHelper helper) {
        SqlSession session = this.getSession();
        List<E> list = (List<E>) (helper.hasValue()
                ? session.selectList(helper.getNameSpace(), helper.getValue())
                : session.selectList(helper.getNameSpace()));
//        session.close();
        return list;
    }

    /**
     * 删除单个数据
     *
     * @param helper
     * @return
     */
    public int deleteOne(QueryHelper helper) {
        return helper.hasValue()
                ? this.getSession().delete(helper.getNameSpace(), helper.getValue())
                : this.getSession().delete(helper.getNameSpace());
    }

    /**
     * 获得一张表的数据总数
     *
     * @param helper
     * @return
     */
    public int findCount(QueryHelper helper) {
        return (Integer) (helper.hasValue()
                ? this.getSession().selectOne(helper.getNameSpace(), helper.getValue())
                : this.getSession().selectOne(helper.getNameSpace()));
    }

    /**
     * 更新单个数据
     *
     * @param helper
     * @return
     */
    public int updateOne(QueryHelper helper) {
        return helper.hasValue()
                ? this.getSession().update(helper.getNameSpace(), helper.getValue())
                : this.getSession().update(helper.getNameSpace());
    }
}
