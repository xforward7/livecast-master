package com.aidingyun.ynlive.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeCourseModel {


    /**
     * code : success
     * msg : 获取成功
     * banner_data : [{"advertisement_name":"","advertisement_picture":"//pic.iidingyun.com/1000//file/20181102/75085.png","advertisement_link":"","advertisement_place":"0"}]
     * choice_course_data : [{"allow":"2","new":1,"allow_name":"付费","photo":"//pic.iidingyun.com//file/20181205/75910.jpg","live_type":"","real_name":"吴老师","collection":"1","title":"绝地求生","type":"1","userid":"9928592","score":"0.0","price":"2000","second_typeid":"9755349","typeid":"9755329","courseid":"9957248","live_status":"0"},{"allow":"2","new":1,"allow_name":"付费","photo":"//pic.iidingyun.com/1000//file/20181205/75909.jpg","live_type":"","real_name":"吴老师","collection":"0","title":"芭蕾舞","type":"1","userid":"9928592","score":"0.0","price":"1000","second_typeid":"9755345","typeid":"9755328","courseid":"9957246","live_status":"0"},{"allow":"0","new":1,"allow_name":"免费","photo":"//pic.iidingyun.com//file/20181205/75908.jpg","live_type":"","real_name":"吴老师","collection":"0","title":"炫舞","type":"1","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755345","typeid":"9755328","courseid":"9957244","live_status":"0"},{"allow":"2","new":1,"allow_name":"付费","photo":"//pic.iidingyun.com//file/20181205/75913.jpg","live_type":"","real_name":"吴老师","collection":"0","title":"维恩","type":"1","userid":"9928592","score":"0.0","price":"1000","second_typeid":"9755347","typeid":"9755329","courseid":"9957251","live_status":"0"},{"allow":"0","new":1,"allow_name":"免费","photo":"//pic.iidingyun.com//file/20181205/75931.jpg","live_type":"0","real_name":"吴老师","collection":"0","title":"角色","type":"0","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755352","typeid":"9755329","courseid":"9957289","live_status":"0"},{"allow":"0","new":1,"allow_name":"免费","photo":"//pic.iidingyun.com/1000//file/20181205/75930.jpg","live_type":"0","real_name":"吴老师","collection":"0","title":"决战","type":"0","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755349","typeid":"9755329","courseid":"9957288","live_status":"0"},{"allow":"0","new":1,"allow_name":"免费","photo":"//pic.iidingyun.com//file/20181205/75929.jpg","live_type":"","real_name":"吴老师","collection":"0","title":"王者","type":"1","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755352","typeid":"9755329","courseid":"9957287","live_status":"0"}]
     * type_data : [{"typeid":"9755328","type_name":"娱乐","app_top_pic":"//pic.iidingyun.com//file/20181205/75950.jpg","type_course_data":[{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com/1000//file/20181205/75912.jpg","live_type":"0","real_name":"吴老师","collection":"0","title":"大主播","type":"0","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755346","typeid":"9755328","courseid":"9957250","live_status":"0"},{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com//file/20181205/75911.jpg","live_type":"0","real_name":"吴老师","collection":"0","title":"合唱","type":"0","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755344","typeid":"9755328","courseid":"9957249","live_status":"0"},{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com/1000//file/20181201/75821.jpg","live_type":"0","real_name":"杨二宝","collection":"0","title":"房子","type":"0","userid":"9755478","score":"0.0","price":"0","second_typeid":"9755345","typeid":"9755328","courseid":"9928497","live_status":"0"},{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com//file/20181201/75819.jpg","live_type":"0","real_name":"杨二宝","collection":"0","title":"花园","type":"0","userid":"9755478","score":"0.0","price":"0","second_typeid":"9755345","typeid":"9755328","courseid":"9928494","live_status":"0"},{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com//file/20181129/75724.jpg","live_type":"0","real_name":"杨二宝","collection":"7","title":"表情包","type":"0","userid":"9755478","score":"0.0","price":"0","second_typeid":"9755344","typeid":"9755328","courseid":"9911912","live_status":"1"}]},{"typeid":"9755329","type_name":"游戏","app_top_pic":"//pic.iidingyun.com//file/20181205/75949.jpg","type_course_data":[{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com//file/20181205/75928.jpg","live_type":"0","real_name":"吴老师","collection":"0","title":"绝地","type":"0","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755349","typeid":"9755329","courseid":"9957286","live_status":"0"},{"allow":"2","allow_name":"付费","photo":"//pic.iidingyun.com/1000//file/20181123/75604.jpg","live_type":"0","real_name":"杨二宝","collection":"0","title":"斧头","type":"0","userid":"9755478","score":"0.0","price":"1000","second_typeid":"9755347","typeid":"9755329","courseid":"9872590","live_status":"0"}]}]
     * host_data : {"host_list":[{"score":"0","groom_count":"2","groom_title":"123","price":"500","hostid":"9795474","photo":"//pic.iidingyun.com//file/20181205/75952.jpg","groom_courseid":"9956909","real_name":"梁冠","professional":"歌神","fans":"1"},{"score":"1","groom_count":"7","groom_title":"表情包","price":"500","hostid":"9755478","photo":"//pic.iidingyun.com//file/20181204/75872.jpg","groom_courseid":"9911912","real_name":"V587Jie","professional":"无敌战神","fans":"2"},{"score":"4.1","groom_count":"6","groom_title":"拉克丝","price":"500","hostid":"9928453","photo":"//pic.iidingyun.com//file/20181201/75804.jpeg","groom_courseid":"9928467","real_name":"欧老师","professional":"跳舞专家","fans":"2"}],"banner":"//pic.iidingyun.com/1000//file/20181205/75927.jpg"}
     */

    private String code;
    private String msg;
    private HostDataBean host_data;
    private List<BannerDataBean> banner_data;
    private List<ChoiceCourseDataBean> choice_course_data;
    private List<TypeDataBean> type_data;

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

    public HostDataBean getHost_data() {
        return host_data;
    }

    public void setHost_data(HostDataBean host_data) {
        this.host_data = host_data;
    }

    public List<BannerDataBean> getBanner_data() {
        return banner_data;
    }

    public void setBanner_data(List<BannerDataBean> banner_data) {
        this.banner_data = banner_data;
    }

    public List<ChoiceCourseDataBean> getChoice_course_data() {
        return choice_course_data;
    }

    public void setChoice_course_data(List<ChoiceCourseDataBean> choice_course_data) {
        this.choice_course_data = choice_course_data;
    }

    public List<TypeDataBean> getType_data() {
        return type_data;
    }

    public void setType_data(List<TypeDataBean> type_data) {
        this.type_data = type_data;
    }

    public static class HostDataBean {
        /**
         * host_list : [{"score":"0","groom_count":"2","groom_title":"123","price":"500","hostid":"9795474","photo":"//pic.iidingyun.com//file/20181205/75952.jpg","groom_courseid":"9956909","real_name":"梁冠","professional":"歌神","fans":"1"},{"score":"1","groom_count":"7","groom_title":"表情包","price":"500","hostid":"9755478","photo":"//pic.iidingyun.com//file/20181204/75872.jpg","groom_courseid":"9911912","real_name":"V587Jie","professional":"无敌战神","fans":"2"},{"score":"4.1","groom_count":"6","groom_title":"拉克丝","price":"500","hostid":"9928453","photo":"//pic.iidingyun.com//file/20181201/75804.jpeg","groom_courseid":"9928467","real_name":"欧老师","professional":"跳舞专家","fans":"2"}]
         * banner : //pic.iidingyun.com/1000//file/20181205/75927.jpg
         */

        private String banner;
        private List<HostListBean> host_list;

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public List<HostListBean> getHost_list() {
            return host_list;
        }

        public void setHost_list(List<HostListBean> host_list) {
            this.host_list = host_list;
        }

        public static class HostListBean {
            /**
             * score : 0
             * groom_count : 2
             * groom_title : 123
             * price : 500
             * hostid : 9795474
             * photo : //pic.iidingyun.com//file/20181205/75952.jpg
             * groom_courseid : 9956909
             * real_name : 梁冠
             * professional : 歌神
             * fans : 1
             */

            private String score;
            private String groom_count;
            private String groom_title;
            private String price;
            private String hostid;
            private String photo;
            private String groom_courseid;
            private String real_name;
            private String professional;
            private String fans;

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getGroom_count() {
                return groom_count;
            }

            public void setGroom_count(String groom_count) {
                this.groom_count = groom_count;
            }

            public String getGroom_title() {
                return groom_title;
            }

            public void setGroom_title(String groom_title) {
                this.groom_title = groom_title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getHostid() {
                return hostid;
            }

            public void setHostid(String hostid) {
                this.hostid = hostid;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getGroom_courseid() {
                return groom_courseid;
            }

            public void setGroom_courseid(String groom_courseid) {
                this.groom_courseid = groom_courseid;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getProfessional() {
                return professional;
            }

            public void setProfessional(String professional) {
                this.professional = professional;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }
        }
    }

    public static class BannerDataBean {
        /**
         * advertisement_name :
         * advertisement_picture : //pic.iidingyun.com/1000//file/20181102/75085.png
         * advertisement_link :
         * advertisement_place : 0
         */

        private String advertisement_name;
        private String advertisement_picture;
        private String advertisement_link;
        private String advertisement_place;

        public String getAdvertisement_name() {
            return advertisement_name;
        }

        public void setAdvertisement_name(String advertisement_name) {
            this.advertisement_name = advertisement_name;
        }

        public String getAdvertisement_picture() {
            return advertisement_picture;
        }

        public void setAdvertisement_picture(String advertisement_picture) {
            this.advertisement_picture = advertisement_picture;
        }

        public String getAdvertisement_link() {
            return advertisement_link;
        }

        public void setAdvertisement_link(String advertisement_link) {
            this.advertisement_link = advertisement_link;
        }

        public String getAdvertisement_place() {
            return advertisement_place;
        }

        public void setAdvertisement_place(String advertisement_place) {
            this.advertisement_place = advertisement_place;
        }
    }

    public static class ChoiceCourseDataBean {
        /**
         * allow : 2
         * new : 1
         * allow_name : 付费
         * photo : //pic.iidingyun.com//file/20181205/75910.jpg
         * live_type :
         * real_name : 吴老师
         * collection : 1
         * title : 绝地求生
         * type : 1
         * userid : 9928592
         * score : 0.0
         * price : 2000
         * second_typeid : 9755349
         * typeid : 9755329
         * courseid : 9957248
         * live_status : 0
         */

        private String allow;
        @SerializedName("new")
        private int newX;
        private String allow_name;
        private String photo;
        private String live_type;
        private String real_name;
        private String collection;
        private String title;
        private String type;
        private String userid;
        private String score;
        private String price;
        private String second_typeid;
        private String typeid;
        private String courseid;
        private String live_status;

        public String getAllow() {
            return allow;
        }

        public void setAllow(String allow) {
            this.allow = allow;
        }

        public int getNewX() {
            return newX;
        }

        public void setNewX(int newX) {
            this.newX = newX;
        }

        public String getAllow_name() {
            return allow_name;
        }

        public void setAllow_name(String allow_name) {
            this.allow_name = allow_name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
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

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getCourseid() {
            return courseid;
        }

        public void setCourseid(String courseid) {
            this.courseid = courseid;
        }

        public String getLive_status() {
            return live_status;
        }

        public void setLive_status(String live_status) {
            this.live_status = live_status;
        }
    }

    public static class TypeDataBean {
        /**
         * typeid : 9755328
         * type_name : 娱乐
         * app_top_pic : //pic.iidingyun.com//file/20181205/75950.jpg
         * type_course_data : [{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com/1000//file/20181205/75912.jpg","live_type":"0","real_name":"吴老师","collection":"0","title":"大主播","type":"0","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755346","typeid":"9755328","courseid":"9957250","live_status":"0"},{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com//file/20181205/75911.jpg","live_type":"0","real_name":"吴老师","collection":"0","title":"合唱","type":"0","userid":"9928592","score":"0.0","price":"0","second_typeid":"9755344","typeid":"9755328","courseid":"9957249","live_status":"0"},{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com/1000//file/20181201/75821.jpg","live_type":"0","real_name":"杨二宝","collection":"0","title":"房子","type":"0","userid":"9755478","score":"0.0","price":"0","second_typeid":"9755345","typeid":"9755328","courseid":"9928497","live_status":"0"},{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com//file/20181201/75819.jpg","live_type":"0","real_name":"杨二宝","collection":"0","title":"花园","type":"0","userid":"9755478","score":"0.0","price":"0","second_typeid":"9755345","typeid":"9755328","courseid":"9928494","live_status":"0"},{"allow":"0","allow_name":"免费","photo":"//pic.iidingyun.com//file/20181129/75724.jpg","live_type":"0","real_name":"杨二宝","collection":"7","title":"表情包","type":"0","userid":"9755478","score":"0.0","price":"0","second_typeid":"9755344","typeid":"9755328","courseid":"9911912","live_status":"1"}]
         */

        private String typeid;
        private String type_name;
        private String app_top_pic;
        private List<TypeCourseDataBean> type_course_data;

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getApp_top_pic() {
            return app_top_pic;
        }

        public void setApp_top_pic(String app_top_pic) {
            this.app_top_pic = app_top_pic;
        }

        public List<TypeCourseDataBean> getType_course_data() {
            return type_course_data;
        }

        public void setType_course_data(List<TypeCourseDataBean> type_course_data) {
            this.type_course_data = type_course_data;
        }

        public static class TypeCourseDataBean {
            /**
             * allow : 0
             * allow_name : 免费
             * photo : //pic.iidingyun.com/1000//file/20181205/75912.jpg
             * live_type : 0
             * real_name : 吴老师
             * collection : 0
             * title : 大主播
             * type : 0
             * userid : 9928592
             * score : 0.0
             * price : 0
             * second_typeid : 9755346
             * typeid : 9755328
             * courseid : 9957250
             * live_status : 0
             */

            private String allow;
            private String allow_name;
            private String photo;
            private String live_type;
            private String real_name;
            private String collection;
            private String title;
            private String type;
            private String userid;
            private String score;
            private String price;
            private String second_typeid;
            private String typeid;
            private String courseid;
            private String live_status;

            public String getAllow() {
                return allow;
            }

            public void setAllow(String allow) {
                this.allow = allow;
            }

            public String getAllow_name() {
                return allow_name;
            }

            public void setAllow_name(String allow_name) {
                this.allow_name = allow_name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
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

            public String getCollection() {
                return collection;
            }

            public void setCollection(String collection) {
                this.collection = collection;
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

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getCourseid() {
                return courseid;
            }

            public void setCourseid(String courseid) {
                this.courseid = courseid;
            }

            public String getLive_status() {
                return live_status;
            }

            public void setLive_status(String live_status) {
                this.live_status = live_status;
            }
        }
    }
}
