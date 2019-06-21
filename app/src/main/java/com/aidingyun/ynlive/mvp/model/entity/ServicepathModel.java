package com.aidingyun.ynlive.mvp.model.entity;

import java.util.List;

public class ServicepathModel {



    public String code;
    public String msg;
    private String siteid;
    public List<ServiceDataBean> service_data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public List<ServiceDataBean> getService_data() {
        return service_data;
    }

    public void setService_data(List<ServiceDataBean> service_data) {
        this.service_data = service_data;
    }

    public static class ServiceDataBean {
        /**
         * service_name : 服务测试
         * service_path : http://s3.iidingyun.com/test_service.vm
         */

        public String service_name;
        public String service_path;

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getService_path() {
            return service_path;
        }

        public void setService_path(String service_path) {
            this.service_path = service_path;
        }
    }
}
