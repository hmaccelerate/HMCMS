package com.hm.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hmaccelerate on 2015/6/23.
 */
public class QueryHelper {
    private String table;
    private String action;
    private Object value;
    private String namespace;
    private Map<String, Object> params;

    public void clear() {
        table = null;
        action = null;
        value = null;
        namespace = null;
        if (params != null)
            params.clear();
    }

    /**
     * 查看queryhelper里是否有数值
     *
     * @return
     */
    public boolean hasValue() {
        return value != null || (params != null && params.size() > 0);
    }

    /**
     * @param table mapper的名称空间
     * @return
     */
    public QueryHelper setTable(String table) {
        this.table = table;
        return this;
    }

    /**
     * @param action 具体的sql执行动作
     * @return
     */
    public QueryHelper setAction(String action) {
        this.action = action;
        return this;
    }

    /**
     * @param namespace 输入值要求为table值+ .+action值
     * @return
     */
    public QueryHelper setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * @param value 输入单个参数
     * @return
     */
    public QueryHelper setValue(Object value) {
        this.value = value;
        return this;
    }

    /**
     * 多个参数的情况
     *
     * @param key   键值
     * @param value 值
     * @return
     */
    public QueryHelper putParams(String key, Object value) {
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put(key, value);
        return this;
    }

    /**
     * @return 获取传入参数的值
     */
    public Object getValue() {
        return params == null || params.size() == 0 ? value : params;
    }

    /**
     * 完整的sql动作，包括操作的数据库表和sql动作
     * @return
     */
    public String getNameSpace() {
        return StringUtils.isEmpty(namespace) ? table + "." + action : namespace;
    }
}
