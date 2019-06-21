package com.aidingyun.ynlive.mvp.model.entity;


import java.util.List;

public class CollectCourseModel {


    /**
     * msg :
     * q : {"pageCount":"","thisPage":"","totalCount":2}
     * code : success
     * totalPage : 1
     * list : [{"praise_rate":"0","check_reason":"","display_order":"4","live_type":"","real_name":"test","recommend":"1","title":"酷炫","type":"1","userid":"","content":"酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫","score":"4","price":"0","second_typeid":"9755349","third_typeid":"9755441","siteid":"71704","typeid":"9755329","organization_status":"1","create_time":"2018-12-01 16:23:02.0","photo":"//pic.iidingyun.com//file/20181201/75799.jpg","collection":"2","roomid":"","check_status":"1","organizationid":"9928018","peopel_count":"21","courseid":"9928450","status":"1"},{"praise_rate":"0","check_reason":"","display_order":"2","live_type":"0","real_name":"小欧欧","recommend":"1","title":"周杰伦演唱会","type":"0","userid":"9928453","content":"周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会周杰伦演唱会","score":"0","price":"","second_typeid":"9755344","third_typeid":"9755427","siteid":"71704","typeid":"9755328","organization_status":"1","create_time":"2018-12-01 17:16:05.0","photo":"//pic.iidingyun.com/1000//file/20181201/75812.jpg","collection":"3","roomid":"96978980","check_status":"1","organizationid":"","peopel_count":"0","courseid":"9928470","status":"1"}]
     * totalCount : 2
     * empty : false
     * queryTO : {"pageCount":"","thisPage":"","totalCount":2}
     */

    private String msg;
    private QBean q;
    private String code;
    private int totalPage;
    private int totalCount;
    private boolean empty;
    private QueryTOBean queryTO;
    private List<ListBean> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public QBean getQ() {
        return q;
    }

    public void setQ(QBean q) {
        this.q = q;
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public QueryTOBean getQueryTO() {
        return queryTO;
    }

    public void setQueryTO(QueryTOBean queryTO) {
        this.queryTO = queryTO;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class QBean {
        /**
         * pageCount :
         * thisPage :
         * totalCount : 2
         */

        private String pageCount;
        private String thisPage;
        private int totalCount;

        public String getPageCount() {
            return pageCount;
        }

        public void setPageCount(String pageCount) {
            this.pageCount = pageCount;
        }

        public String getThisPage() {
            return thisPage;
        }

        public void setThisPage(String thisPage) {
            this.thisPage = thisPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class QueryTOBean {
        /**
         * pageCount :
         * thisPage :
         * totalCount : 2
         */

        private String pageCount;
        private String thisPage;
        private int totalCount;

        public String getPageCount() {
            return pageCount;
        }

        public void setPageCount(String pageCount) {
            this.pageCount = pageCount;
        }

        public String getThisPage() {
            return thisPage;
        }

        public void setThisPage(String thisPage) {
            this.thisPage = thisPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class ListBean {
        /**
         * praise_rate : 0
         * check_reason :
         * display_order : 4
         * live_type :
         * real_name : test
         * recommend : 1
         * title : 酷炫
         * type : 1
         * userid :
         * content : 酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫酷炫
         * score : 4
         * price : 0
         * second_typeid : 9755349
         * third_typeid : 9755441
         * siteid : 71704
         * typeid : 9755329
         * organization_status : 1
         * create_time : 2018-12-01 16:23:02.0
         * photo : //pic.iidingyun.com//file/20181201/75799.jpg
         * collection : 2
         * roomid :
         * check_status : 1
         * organizationid : 9928018
         * peopel_count : 21
         * courseid : 9928450
         * status : 1
         */

        private String praise_rate;
        private String check_reason;
        private String display_order;
        private String live_type;
        private String real_name;
        private String recommend;
        private String title;
        private String type;
        private String userid;
        private String content;
        private String score;
        private String price;
        private String second_typeid;
        private String third_typeid;
        private String siteid;
        private String typeid;
        private String organization_status;
        private String create_time;
        private String photo;
        private String collection;
        private String roomid;
        private String check_status;
        private String organizationid;
        private String peopel_count;
        private String courseid;
        private String status;

        public String getPraise_rate() {
            return praise_rate;
        }

        public void setPraise_rate(String praise_rate) {
            this.praise_rate = praise_rate;
        }

        public String getCheck_reason() {
            return check_reason;
        }

        public void setCheck_reason(String check_reason) {
            this.check_reason = check_reason;
        }

        public String getDisplay_order() {
            return display_order;
        }

        public void setDisplay_order(String display_order) {
            this.display_order = display_order;
        }

        public String getLive_type() {
            return live_type;
        }

        public void setLive_type(String live_type) {
            this.live_type = live_type;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSecond_typeid() {
            return second_typeid;
        }

        public void setSecond_typeid(String second_typeid) {
            this.second_typeid = second_typeid;
        }

        public String getThird_typeid() {
            return third_typeid;
        }

        public void setThird_typeid(String third_typeid) {
            this.third_typeid = third_typeid;
        }

        public String getSiteid() {
            return siteid;
        }

        public void setSiteid(String siteid) {
            this.siteid = siteid;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getOrganization_status() {
            return organization_status;
        }

        public void setOrganization_status(String organization_status) {
            this.organization_status = organization_status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public String getCheck_status() {
            return check_status;
        }

        public void setCheck_status(String check_status) {
            this.check_status = check_status;
        }

        public String getOrganizationid() {
            return organizationid;
        }

        public void setOrganizationid(String organizationid) {
            this.organizationid = organizationid;
        }

        public String getPeopel_count() {
            return peopel_count;
        }

        public void setPeopel_count(String peopel_count) {
            this.peopel_count = peopel_count;
        }

        public String getCourseid() {
            return courseid;
        }

        public void setCourseid(String courseid) {
            this.courseid = courseid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
