package com.hm.cms.domain;

import java.util.Date;

/**
 * Created by hmaccelerate on 2015/6/24.
 */
public class Article {
    private long ac_id;
    private  long user_id;
    private String title;
    private String content;
    private Date up_time;
    private long read_num;

    public long getAc_id() {
        return ac_id;
    }

    public void setAc_id(long ac_id) {
        this.ac_id = ac_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUp_time() {
        return up_time;
    }

    public void setUp_time(Date up_time) {
        this.up_time = up_time;
    }

    public long getRead_num() {
        return read_num;
    }

    public void setRead_num(long read_num) {
        this.read_num = read_num;
    }
}
