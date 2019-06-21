package com.aidingyun.ynlive.mvp.model.entity;

import java.util.List;

public class CourseOrderInfo {

    /**
     * code : SUCCESS
     * msg : 操作成功！
     * data : {"totalCount":2,"thisPage":1,"pageCount":10,"totalPage":1,"list":[{"room_id":"","goods_name":"芭蕾舞","create_time":"2018-12-14 13:46:04","orderid":"10036123","spreader":"","icon":"//pic.iidingyun.com/1000//file/20181204/75891.png","goods_id":"9957246","photo":"//pic.iidingyun.com/1000//file/20181205/75909.jpg","spread_person_name":"","title":"芭蕾舞","userid":"9928001","consume_fee":"1000.00","host_id":"9928592","organizationid":"","order_status":"1","update_time":"2018-12-14 13:46:04","consume_type":"1","spread_person":"","host_name":"吴老师","username":"东施效颦"},{"room_id":"","goods_name":"芭蕾舞","create_time":"2018-12-14 12:00:22","orderid":"10035969","spreader":"","icon":"//pic.iidingyun.com/1000//file/20181204/75891.png","goods_id":"9957246","photo":"//pic.iidingyun.com/1000//file/20181205/75909.jpg","spread_person_name":"","title":"芭蕾舞","userid":"9928001","consume_fee":"1000.00","host_id":"9928592","organizationid":"","order_status":"1","update_time":"2018-12-14 12:00:22","consume_type":"1","spread_person":"","host_name":"吴老师","username":"东施效颦"}]}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalCount : 2
         * thisPage : 1
         * pageCount : 10
         * totalPage : 1
         * list : [{"room_id":"","goods_name":"芭蕾舞","create_time":"2018-12-14 13:46:04","orderid":"10036123","spreader":"","icon":"//pic.iidingyun.com/1000//file/20181204/75891.png","goods_id":"9957246","photo":"//pic.iidingyun.com/1000//file/20181205/75909.jpg","spread_person_name":"","title":"芭蕾舞","userid":"9928001","consume_fee":"1000.00","host_id":"9928592","organizationid":"","order_status":"1","update_time":"2018-12-14 13:46:04","consume_type":"1","spread_person":"","host_name":"吴老师","username":"东施效颦"},{"room_id":"","goods_name":"芭蕾舞","create_time":"2018-12-14 12:00:22","orderid":"10035969","spreader":"","icon":"//pic.iidingyun.com/1000//file/20181204/75891.png","goods_id":"9957246","photo":"//pic.iidingyun.com/1000//file/20181205/75909.jpg","spread_person_name":"","title":"芭蕾舞","userid":"9928001","consume_fee":"1000.00","host_id":"9928592","organizationid":"","order_status":"1","update_time":"2018-12-14 12:00:22","consume_type":"1","spread_person":"","host_name":"吴老师","username":"东施效颦"}]
         */

        private int totalCount;
        private int thisPage;
        private int pageCount;
        private int totalPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getThisPage() {
            return thisPage;
        }

        public void setThisPage(int thisPage) {
            this.thisPage = thisPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * room_id :
             * goods_name : 芭蕾舞
             * create_time : 2018-12-14 13:46:04
             * orderid : 10036123
             * spreader :
             * icon : //pic.iidingyun.com/1000//file/20181204/75891.png
             * goods_id : 9957246
             * photo : //pic.iidingyun.com/1000//file/20181205/75909.jpg
             * spread_person_name :
             * title : 芭蕾舞
             * userid : 9928001
             * consume_fee : 1000.00
             * host_id : 9928592
             * organizationid :
             * order_status : 1
             * update_time : 2018-12-14 13:46:04
             * consume_type : 1
             * spread_person :
             * host_name : 吴老师
             * username : 东施效颦
             */

            private String room_id;
            private String goods_name;
            private String create_time;
            private String orderid;
            private String spreader;
            private String icon;
            private String goods_id;
            private String photo;
            private String spread_person_name;
            private String title;
            private String userid;
            private String consume_fee;
            private String host_id;
            private String organizationid;
            private String order_status;
            private String update_time;
            private String consume_type;
            private String spread_person;
            private String host_name;
            private String username;

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getOrderid() {
                return orderid;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
            }

            public String getSpreader() {
                return spreader;
            }

            public void setSpreader(String spreader) {
                this.spreader = spreader;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getSpread_person_name() {
                return spread_person_name;
            }

            public void setSpread_person_name(String spread_person_name) {
                this.spread_person_name = spread_person_name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getConsume_fee() {
                return consume_fee;
            }

            public void setConsume_fee(String consume_fee) {
                this.consume_fee = consume_fee;
            }

            public String getHost_id() {
                return host_id;
            }

            public void setHost_id(String host_id) {
                this.host_id = host_id;
            }

            public String getOrganizationid() {
                return organizationid;
            }

            public void setOrganizationid(String organizationid) {
                this.organizationid = organizationid;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getConsume_type() {
                return consume_type;
            }

            public void setConsume_type(String consume_type) {
                this.consume_type = consume_type;
            }

            public String getSpread_person() {
                return spread_person;
            }

            public void setSpread_person(String spread_person) {
                this.spread_person = spread_person;
            }

            public String getHost_name() {
                return host_name;
            }

            public void setHost_name(String host_name) {
                this.host_name = host_name;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
