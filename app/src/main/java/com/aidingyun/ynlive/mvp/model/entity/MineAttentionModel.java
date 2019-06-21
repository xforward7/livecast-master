package com.aidingyun.ynlive.mvp.model.entity;

import java.util.List;

public class MineAttentionModel {

    /**
     * code = success;
     *     data =     {
     *         list =         (
     *                         {
     *                 "attention_amounts" = 3;
     *                 "course_amounts" = 6;
     *                 courseid = 10015293;
     *                 "focus_time" = "2019-01-21 14:17:24.0";
     *                 "focused_userid" = 10008527;
     *                 "host_name" = "\U3010\U4e1a\U5185\U3011-\U5de1\U7ba1";
     *                 icon = "//pic.iidingyun.com//file/20181211/76051.jpg";
     *                 id = 10286162;
     *                 intro = "\U5404\U5927\U77e5\U540d\U8d22\U7ecf\U535a\U4e3b\Uff0c\U66fe\U53d7\U9080\U591a\U5bb6\U8d22\U7ecf\U5a92\U4f53\U7684\U91c7\U8bbf\Uff0c\U73b0\U4e3a\U67d0\U6295\U8d44\U516c\U53f8\U603b\U76d1\Uff0c\U62e5\U6709\U591a\U5e74\U5b9e\U76d8\U64cd\U4f5c\U7ecf\U9a8c\Uff0c\U5728\U80a1\U7968\U9886\U57df\U6709\U72ec\U5230\U7684\U89c1\U89e3\Uff0c\U5584\U957f\U6ce2\U6bb5\U548c\U77ed\U7ebf\U64cd\U4f5c\Uff0c\U8d8b\U52bf\U4e3a\U4e3b\U3002";
     *                 score = 5;
     *                 siteid = 71705;
     *                 userid = 10077893;
     *             }
     *         );
     *         pageCount = 10;
     *         thisPage = 1;
     *         totalCount = 1;
     *         totalPage = 1;
     *     };
     *     msg = "\U67e5\U8be2\U6210\U529f";
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

        List<AttentionBean> list;
        int pageCount;
        int thisPage;
        int totalCount;
        int totalPage;

        public List<AttentionBean> getList() {
            return list;
        }

        public void setList(List<AttentionBean> list) {
            this.list = list;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getThisPage() {
            return thisPage;
        }

        public void setThisPage(int thisPage) {
            this.thisPage = thisPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }
    }

    public static class AttentionBean {
        /**
         * "attention_amounts" = 3;
         * "course_amounts" = 6;
         * courseid = 10015293;
         * "focus_time" = "2019-01-21 14:17:24.0";
         * "focused_userid" = 10008527;
         * "host_name" = "\U3010\U4e1a\U5185\U3011-\U5de1\U7ba1";
         * icon = "//pic.iidingyun.com//file/20181211/76051.jpg";
         * id = 10286162;
         * intro = "\U5404\U5927\U77e5\U540d\U8d22\U7ecf\U535a\U4e3b\Uff0c\U66fe\U53d7\U9080\U591a\U5bb6\U8d22\U7ecf\U5a92\U4f53\U7684\U91c7\U8bbf\Uff0c\U73b0\U4e3a\U67d0\U6295\U8d44\U516c\U53f8\U603b\U76d1\Uff0c\U62e5\U6709\U591a\U5e74\U5b9e\U76d8\U64cd\U4f5c\U7ecf\U9a8c\Uff0c\U5728\U80a1\U7968\U9886\U57df\U6709\U72ec\U5230\U7684\U89c1\U89e3\Uff0c\U5584\U957f\U6ce2\U6bb5\U548c\U77ed\U7ebf\U64cd\U4f5c\Uff0c\U8d8b\U52bf\U4e3a\U4e3b\U3002";
         * score = 5;
         * siteid = 71705;
         * userid = 10077893;
         */

        private String focused_userid;
        private String icon;
        private String host_name;
        private String intro;
        private int course_amounts;
        private int attention_amounts;
        private String score;

        public String getFocused_userid() {
            return focused_userid;
        }

        public void setFocused_userid(String focused_userid) {
            this.focused_userid = focused_userid;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getHost_name() {
            return host_name;
        }

        public void setHost_name(String host_name) {
            this.host_name = host_name;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getCourse_amounts() {
            return course_amounts;
        }

        public void setCourse_amounts(int course_amounts) {
            this.course_amounts = course_amounts;
        }

        public int getAttention_amounts() {
            return attention_amounts;
        }

        public void setAttention_amounts(int attention_amounts) {
            this.attention_amounts = attention_amounts;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
