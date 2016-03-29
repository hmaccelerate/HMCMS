package com.hm.cms.domain;

/**
 * Created by hmaccelerate on 2015/6/24.
 */
public class Menu {
    private long menu_id;
    private String menu_name;
    private String menu_url;
    private long fa_id;

    public long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(long menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public long getFa_id() {
        return fa_id;
    }

    public void setFa_id(long fa_id) {
        this.fa_id = fa_id;
    }
}
