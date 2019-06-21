package com.aidingyun.ynlive.mvp.model.entity;

import java.util.List;

public class CourseDetailInfo {


    /**
     * code : success
     * msg : 获取成功
     * data : {"photo":"//pic.iidingyun.com/1000//file/20181205/75909.jpg","live_type":"","real_name":"吴老师","collection":"0","type":"1","title":"芭蕾舞","userid":"9928592","roomid":"","content":"芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞","organizationid":"","score":"0.0","price":"1000","second_typeid":"9755345","typeid":"9755328","peopel_count":"0","courseid":"9957246","status":"1","collectioned":0,"lessoned":1,"live_status":"0","begin_date":"","end_date":"","paid":1,"allow":"2","allow_name":"付费","live_time":" ","section_count":1,"type_name":"娱乐","second_type_name":"跳舞","course_type":0,"share_url":"8694880.iidingyun.com/web/course_details.html?courseid=9957246"}
     * section : [{"allow":"2","section_time":"","section_title":"芭蕾舞1","time_limit":"","display_order":"1","photo":"//pic.iidingyun.com/file/2778/course_Cover_default@2x-video.png","real_name":"吴老师","sectionid":"333","video":"http://1257878813.vod2.myqcloud.com/da6f6534vodcq1257878813/3b407fd05285890783407332890/IbEdawkAzvcA.mp4","type":"1","userid":"9928592","section_length":"","section_date":"","price":"0","playback":"0","courseid":"9957246","paid":"1","icon":"//pic.iidingyun.com//file/20181203/75825.jpg","professional":"召唤师","allow_name":"付费"}]
     * host_data : {"organizationid":"","score":"3.0","intro":"","icon":"//pic.iidingyun.com//file/20181203/75825.jpg","real_name":"吴老师","detail":"","followed":0,"host_id":"9928592","professional":"召唤师"}
     * organization_data : {"score":"4.08","name":"皮城警备","collectioned":0,"organization_summary":"皮城警备之德莱文皮城警备之德莱文皮城警备之德莱文皮城警备之德莱文！！！","organization_icon":"//pic.iidingyun.com//file/20181119/75443.jpg"}
     */

    private String code;
    private String msg;
    private DataBean data;
    private HostDataBean host_data;
    private OrganizationDataBean organization_data;
    private List<SectionBean> section;

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

    public HostDataBean getHost_data() {
        return host_data;
    }

    public void setHost_data(HostDataBean host_data) {
        this.host_data = host_data;
    }

    public OrganizationDataBean getOrganization_data() {
        return organization_data;
    }

    public void setOrganization_data(OrganizationDataBean organization_data) {
        this.organization_data = organization_data;
    }

    public List<SectionBean> getSection() {
        return section;
    }

    public void setSection(List<SectionBean> section) {
        this.section = section;
    }

    public static class DataBean {
        /**
         * photo : //pic.iidingyun.com/1000//file/20181205/75909.jpg
         * live_type :
         * real_name : 吴老师
         * collection : 0
         * type : 1
         * title : 芭蕾舞
         * userid : 9928592
         * roomid :
         * content : 芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞芭蕾舞
         * organizationid :
         * score : 0.0
         * price : 1000
         * second_typeid : 9755345
         * typeid : 9755328
         * peopel_count : 0
         * courseid : 9957246
         * status : 1
         * collectioned : 0
         * lessoned : 1
         * live_status : 0
         * begin_date :
         * end_date :
         * paid : 1
         * allow : 2
         * allow_name : 付费
         * live_time :
         * section_count : 1
         * type_name : 娱乐
         * second_type_name : 跳舞
         * course_type : 0
         * share_url : 8694880.iidingyun.com/web/course_details.html?courseid=9957246
         */

        private String photo;
        private String live_type;
        private String real_name;
        private String collection;
        private String type;
        private String title;
        private String userid;
        private String roomid;
        private String content;
        private String organizationid;
        private String score;
        private String price;
        private String second_typeid;
        private String typeid;
        private String peopel_count;
        private String courseid;
        private String status;
        private int collectioned;
        private int lessoned;
        private String live_status;
        private String begin_date;
        private String end_date;
        private int paid;
        private String allow;
        private String allow_name;
        private String live_time;
        private int section_count;
        private String type_name;
        private String second_type_name;
        private int course_type;
        private String share_url;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOrganizationid() {
            return organizationid;
        }

        public void setOrganizationid(String organizationid) {
            this.organizationid = organizationid;
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

        public int getCollectioned() {
            return collectioned;
        }

        public void setCollectioned(int collectioned) {
            this.collectioned = collectioned;
        }

        public int getLessoned() {
            return lessoned;
        }

        public void setLessoned(int lessoned) {
            this.lessoned = lessoned;
        }

        public String getLive_status() {
            return live_status;
        }

        public void setLive_status(String live_status) {
            this.live_status = live_status;
        }

        public String getBegin_date() {
            return begin_date;
        }

        public void setBegin_date(String begin_date) {
            this.begin_date = begin_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public int getPaid() {
            return paid;
        }

        public void setPaid(int paid) {
            this.paid = paid;
        }

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

        public String getLive_time() {
            return live_time;
        }

        public void setLive_time(String live_time) {
            this.live_time = live_time;
        }

        public int getSection_count() {
            return section_count;
        }

        public void setSection_count(int section_count) {
            this.section_count = section_count;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getSecond_type_name() {
            return second_type_name;
        }

        public void setSecond_type_name(String second_type_name) {
            this.second_type_name = second_type_name;
        }

        public int getCourse_type() {
            return course_type;
        }

        public void setCourse_type(int course_type) {
            this.course_type = course_type;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }
    }

    public static class HostDataBean {
        /**
         * organizationid :
         * score : 3.0
         * intro :
         * icon : //pic.iidingyun.com//file/20181203/75825.jpg
         * real_name : 吴老师
         * detail :
         * followed : 0
         * host_id : 9928592
         * professional : 召唤师
         */

        private String organizationid;
        private String score;
        private String intro;
        private String icon;
        private String real_name;
        private String detail;
        private int followed;
        private String host_id;
        private String professional;

        public String getOrganizationid() {
            return organizationid;
        }

        public void setOrganizationid(String organizationid) {
            this.organizationid = organizationid;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getFollowed() {
            return followed;
        }

        public void setFollowed(int followed) {
            this.followed = followed;
        }

        public String getHost_id() {
            return host_id;
        }

        public void setHost_id(String host_id) {
            this.host_id = host_id;
        }

        public String getProfessional() {
            return professional;
        }

        public void setProfessional(String professional) {
            this.professional = professional;
        }
    }

    public static class OrganizationDataBean {
        /**
         * score : 4.08
         * name : 皮城警备
         * collectioned : 0
         * organization_summary : 皮城警备之德莱文皮城警备之德莱文皮城警备之德莱文皮城警备之德莱文！！！
         * organization_icon : //pic.iidingyun.com//file/20181119/75443.jpg
         */

        private String score;
        private String name;
        private int collectioned;
        private String organization_summary;
        private String organization_icon;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCollectioned() {
            return collectioned;
        }

        public void setCollectioned(int collectioned) {
            this.collectioned = collectioned;
        }

        public String getOrganization_summary() {
            return organization_summary;
        }

        public void setOrganization_summary(String organization_summary) {
            this.organization_summary = organization_summary;
        }

        public String getOrganization_icon() {
            return organization_icon;
        }

        public void setOrganization_icon(String organization_icon) {
            this.organization_icon = organization_icon;
        }
    }

    public static class SectionBean {
        /**
         * allow : 2
         * section_time :
         * section_title : 芭蕾舞1
         * time_limit :
         * display_order : 1
         * photo : //pic.iidingyun.com/file/2778/course_Cover_default@2x-video.png
         * real_name : 吴老师
         * sectionid : 333
         * video : http://1257878813.vod2.myqcloud.com/da6f6534vodcq1257878813/3b407fd05285890783407332890/IbEdawkAzvcA.mp4
         * type : 1
         * userid : 9928592
         * section_length :
         * section_date :
         * price : 0
         * playback : 0
         * courseid : 9957246
         * paid : 1
         * icon : //pic.iidingyun.com//file/20181203/75825.jpg
         * professional : 召唤师
         * allow_name : 付费
         */

        private String allow;
        private String section_time;
        private String section_title;
        private String time_limit;
        private String display_order;
        private String photo;
        private String real_name;
        private String sectionid;
        private String video;
        private String type;
        private String userid;
        private String section_length;
        private String section_date;
        private String price;
        private String playback;
        private String courseid;
        private String paid;
        private String icon;
        private String professional;
        private String allow_name;

        public String getAllow() {
            return allow;
        }

        public void setAllow(String allow) {
            this.allow = allow;
        }

        public String getSection_time() {
            return section_time;
        }

        public void setSection_time(String section_time) {
            this.section_time = section_time;
        }

        public String getSection_title() {
            return section_title;
        }

        public void setSection_title(String section_title) {
            this.section_title = section_title;
        }

        public String getTime_limit() {
            return time_limit;
        }

        public void setTime_limit(String time_limit) {
            this.time_limit = time_limit;
        }

        public String getDisplay_order() {
            return display_order;
        }

        public void setDisplay_order(String display_order) {
            this.display_order = display_order;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getSectionid() {
            return sectionid;
        }

        public void setSectionid(String sectionid) {
            this.sectionid = sectionid;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
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

        public String getSection_length() {
            return section_length;
        }

        public void setSection_length(String section_length) {
            this.section_length = section_length;
        }

        public String getSection_date() {
            return section_date;
        }

        public void setSection_date(String section_date) {
            this.section_date = section_date;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPlayback() {
            return playback;
        }

        public void setPlayback(String playback) {
            this.playback = playback;
        }

        public String getCourseid() {
            return courseid;
        }

        public void setCourseid(String courseid) {
            this.courseid = courseid;
        }

        public String getPaid() {
            return paid;
        }

        public void setPaid(String paid) {
            this.paid = paid;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getProfessional() {
            return professional;
        }

        public void setProfessional(String professional) {
            this.professional = professional;
        }

        public String getAllow_name() {
            return allow_name;
        }

        public void setAllow_name(String allow_name) {
            this.allow_name = allow_name;
        }
    }
}
