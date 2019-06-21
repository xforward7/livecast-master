package com.aidingyun.ynlive.mvp.model.entity;

import java.util.List;

public class CollectOrganizationModel {

    private String msg;
    private String code;
    private int totalPage;
    private List<CollectOrganizationModel.OrganizationInfo> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setList(List<OrganizationInfo> list) {
        this.list = list;
    }

    public List<OrganizationInfo> getList() {
        return list;
    }

    public static class OrganizationInfo {
        private String id;
        private String organization_icon;
        private String name;
        private String collection;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setOrganization_icon(String organization_icon) {
            this.organization_icon = organization_icon;
        }

        public String getOrganization_icon() {
            return organization_icon;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getCollection() {
            return collection;
        }
    }

}
