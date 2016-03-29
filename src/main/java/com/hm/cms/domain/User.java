package com.hm.cms.domain;

import java.util.Date;

/**
 * Created by hmaccelerate on 2015/6/24.
 */
public class User {
    private long user_id;
    private long role_id;
    private String user_name;
    private String user_password;
    private String user_description;
    private Date build_time;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public Date getBuild_time() {
        return build_time;
    }

    public void setBuild_time(Date build_time) {
        this.build_time = build_time;
    }
}
