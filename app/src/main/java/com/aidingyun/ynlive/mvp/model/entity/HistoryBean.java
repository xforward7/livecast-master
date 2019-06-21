package com.aidingyun.ynlive.mvp.model.entity;

import java.util.List;

public class HistoryBean {

    /**
     * msg :
     * q : {"pageCount":"","thisPage":"","totalCount":2}
     * code : success
     * totalPage : 1
     * list : [{"section_title":"李青","create_time":"2018-12-18 15:36:11.0","hostid":"9755478","siteid":"71704","photo":"","id":"71","sectionid":"320","userid":"9928001","courseid":"9928562"},{"section_title":"桃花开","create_time":"2018-12-18 15:39:59.0","hostid":"","siteid":"71704","photo":"//pic.iidingyun.com/file/2778/course_Cover_default@2x-video.png","id":"72","sectionid":"303","userid":"9928001","courseid":"9928449"}]
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
         * section_title : 李青
         * create_time : 2018-12-18 15:36:11.0
         * hostid : 9755478
         * siteid : 71704
         * photo :
         * id : 71
         * sectionid : 320
         * userid : 9928001
         * courseid : 9928562
         */

        private String section_title;
        private String create_time;
        private String hostid;
        private String siteid;
        private String photo;
        private String id;
        private String sectionid;
        private String userid;
        private String courseid;

        public String getSection_title() {
            return section_title;
        }

        public void setSection_title(String section_title) {
            this.section_title = section_title;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getHostid() {
            return hostid;
        }

        public void setHostid(String hostid) {
            this.hostid = hostid;
        }

        public String getSiteid() {
            return siteid;
        }

        public void setSiteid(String siteid) {
            this.siteid = siteid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSectionid() {
            return sectionid;
        }

        public void setSectionid(String sectionid) {
            this.sectionid = sectionid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCourseid() {
            return courseid;
        }

        public void setCourseid(String courseid) {
            this.courseid = courseid;
        }
    }
}
