package com.aidingyun.ynlive.mvp.model.entity;

import java.util.List;

public class CollectLiveroomModel {

    /**
     *     "msg":"",
     *
     *     "q":
     *     {
     *         "pageCount":"", "thisPage":"", "totalCount":1
     *     },
     *
     *     "code":"success",
     *     "totalPage":1,
     *     "list":[
     *     {
     *         "course_id":"10023898",
     *         "image":"//pic.iidingyun.com//file/20181205/75925.jpg",
     *         "create_time":
     *         "2018-12-04 16:28:04.0",
     *         "ban_time":"",
     *         "forbid_status":"",
     *         "room_pwd":"",
     *         "label":
     *         "9755348",
     *         "rid":"55",
     *         "title":"系列课1 - 吴松啸",
     *         "big_type":"9755329",
     *         "host_id":
     *         "9928592",
     *         "room_name":"吴松啸",
     *         "room_fee":"",
     *         "section_id":"391",
     *         "parent_id":
     *         "1",
     *         "room_capacity":"60",
     *         "intro":
     *         "英雄联盟<span>英雄联盟<\/span><span>英雄联盟<\/span>英雄联盟<span>英雄联盟<\/span>英雄联盟<span>英雄联盟<\/span><span>英雄联盟<\/span><span>英雄联盟<\/span><span>英雄联盟<\/span><span>英雄联盟<\/span><span>英雄联盟<\/span>", "organization_id":
     *         "",
     *         "siteid":"71704",
     *         "id":"96978982",
     *         "virtual_num":"58",
     *         "welcome":
     *         "{\"pic\":\"\",\"text\":\"三生三世\"}",
     *         "room_type":"",
     *         "status":""
     *     }],
     *     "totalCount":1,
     *     "empty":false,
     *     "queryTO":
     *
     *     {
     *         "pageCount":"", "thisPage":"", "totalCount":1
     *     }
     */

    private String msg;
    private String code;
    private int totalPage;
    private List<RoomInfo> list;

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

    public List<RoomInfo> getList() {
        return list;
    }

    public void setList(List<RoomInfo> list) {
        this.list = list;
    }


    /**
     * "course_id":"10023898",
     * "image":"//pic.iidingyun.com//file/20181205/75925.jpg",
     * "create_time":
     * "2018-12-04 16:28:04.0",
     * "ban_time":"",
     * "forbid_status":"",
     * "room_pwd":"",
     * "label":
     * "9755348",
     * "rid":"55",
     * "title":"系列课1 - 吴松啸",
     * "big_type":"9755329",
     * "host_id":
     * "9928592",
     * "room_name":"吴松啸",
     * "room_fee":"",
     * "section_id":"391",
     * "parent_id":
     * "1",
     * "room_capacity":"60",
     * "intro":
     * "英雄联盟<span>英雄联盟<\/span><span>英雄联盟<\/span>英雄联盟<span>英雄联盟<\/span>英雄联盟<span>英雄联盟<\/span><span>英雄联盟<\/span><span>英雄联盟<\/span><span>英雄联盟<\/span><span>英雄联盟<\/span><span>英雄联盟<\/span>", "organization_id":
     * "",
     * "siteid":"71704",
     * "id":"96978982",
     * "virtual_num":"58",
     * "welcome":
     * "{\"pic\":\"\",\"text\":\"三生三世\"}",
     * "room_type":"",
     * "status":""
     */
    public static class RoomInfo {
        private String id;
        private String image;
        private String title;
        private String room_name;
        private String intro;
        private String create_time;
        private Boolean live_status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Boolean getLive_status() {
            return live_status;
        }

        public void setLive_status(Boolean live_status) {
            this.live_status = live_status;
        }
    }
}
