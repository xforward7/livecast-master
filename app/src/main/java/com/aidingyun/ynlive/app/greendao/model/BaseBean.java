package com.aidingyun.ynlive.app.greendao.model;

import com.tencent.bugly.crashreport.biz.UserInfoBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */
public class BaseBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String info;
    private int code;
    private int is_host;
    private int is_pays;

    public int getIs_host() {
        return is_host;
    }

    public void setIs_host(int is_host) {
        this.is_host = is_host;
    }

    public int getIs_pays() {
        return is_pays;
    }

    public void setIs_pays(int is_pay) {
        this.is_pays = is_pay;
    }

    private List<UserInfoBean> users;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<UserInfoBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfoBean> users) {
        this.users = users;
    }


}


