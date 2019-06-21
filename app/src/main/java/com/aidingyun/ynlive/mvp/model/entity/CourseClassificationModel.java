package com.aidingyun.ynlive.mvp.model.entity;

import java.util.List;

public class CourseClassificationModel {

    /**
     * code : success
     * msg : 获取成功
     * data : [{"typeid":"9755328","type_name":"娱乐","type":1,"banner":"http://pic.iidingyun.com//file/20181020/74648.png","type_data":[{"typeid":"9755344","type_name":"唱歌","pid":"9755328","type":2,"type_data":[{"typeid":"9755426","type_name":"英文歌","pid":"9755344","type":3},{"typeid":"9755427","type_name":"中文歌","pid":"9755344","type":3},{"typeid":"9755428","type_name":"粤语歌","pid":"9755344","type":3}]},{"typeid":"9755345","type_name":"跳舞","pid":"9755328","type":2,"type_data":[{"typeid":"9755429","type_name":"广场舞","pid":"9755345","type":3},{"typeid":"9755430","type_name":"拉丁舞","pid":"9755345","type":3},{"typeid":"9755431","type_name":"俄舞","pid":"9755345","type":3},{"typeid":"9755432","type_name":"天鹅舞","pid":"9755345","type":3}]},{"typeid":"9755346","type_name":"美女","pid":"9755328","type":2,"type_data":[{"typeid":"9755433","type_name":"小萝莉","pid":"9755346","type":3},{"typeid":"9755434","type_name":"清纯美女","pid":"9755346","type":3},{"typeid":"9755435","type_name":"小仙女","pid":"9755346","type":3}]}]},{"typeid":"9755329","type_name":"游戏","type":1,"banner":"//pic.iidingyun.com//file/20181020/74648.png","type_data":[{"typeid":"9755347","type_name":"英雄联盟","pid":"9755329","type":2,"type_data":[{"typeid":"9755436","type_name":"召唤师","pid":"9755347","type":3},{"typeid":"9755437","type_name":"召唤师峡谷","pid":"9755347","type":3}]},{"typeid":"9755348","type_name":"梦幻西游","pid":"9755329","type":2,"type_data":[{"typeid":"9755438","type_name":"比武大会","pid":"9755348","type":3},{"typeid":"9755439","type_name":"新科状元","pid":"9755348","type":3},{"typeid":"9755440","type_name":"十二门派","pid":"9755348","type":3}]},{"typeid":"9755349","type_name":"绝地求生","pid":"9755329","type":2,"type_data":[{"typeid":"9755441","type_name":"绝地海岛","pid":"9755349","type":3},{"typeid":"9755442","type_name":"热情雨林","pid":"9755349","type":3},{"typeid":"9755443","type_name":"激情沙漠","pid":"9755349","type":3}]},{"typeid":"9755352","type_name":"王者荣耀","pid":"9755329","type":2,"type_data":[{"typeid":"9755444","type_name":"最强王者","pid":"9755352","type":3},{"typeid":"9755445","type_name":"荣耀王者","pid":"9755352","type":3}]}]}]
     * hot_data : [{"name":"梦幻"},{"name":"大师"},{"name":"菜鸟"},{"name":"大神"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;
    private List<HotDataBean> hot_data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<HotDataBean> getHot_data() {
        return hot_data;
    }

    public void setHot_data(List<HotDataBean> hot_data) {
        this.hot_data = hot_data;
    }

    public static class DataBean {
        /**
         * typeid : 9755328
         * type_name : 娱乐
         * type : 1
         * banner : //pic.iidingyun.com//file/20181020/74648.png
         * type_data : [{"typeid":"9755344","type_name":"唱歌","pid":"9755328","type":2,"type_data":[{"typeid":"9755426","type_name":"英文歌","pid":"9755344","type":3},{"typeid":"9755427","type_name":"中文歌","pid":"9755344","type":3},{"typeid":"9755428","type_name":"粤语歌","pid":"9755344","type":3}]},{"typeid":"9755345","type_name":"跳舞","pid":"9755328","type":2,"type_data":[{"typeid":"9755429","type_name":"广场舞","pid":"9755345","type":3},{"typeid":"9755430","type_name":"拉丁舞","pid":"9755345","type":3},{"typeid":"9755431","type_name":"俄舞","pid":"9755345","type":3},{"typeid":"9755432","type_name":"天鹅舞","pid":"9755345","type":3}]},{"typeid":"9755346","type_name":"美女","pid":"9755328","type":2,"type_data":[{"typeid":"9755433","type_name":"小萝莉","pid":"9755346","type":3},{"typeid":"9755434","type_name":"清纯美女","pid":"9755346","type":3},{"typeid":"9755435","type_name":"小仙女","pid":"9755346","type":3}]}]
         */

        private String typeid;
        private String type_name;
        private int type;
        private String banner;
        private List<TypeDataBeanX> type_data;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public List<TypeDataBeanX> getType_data() {
            return type_data;
        }

        public void setType_data(List<TypeDataBeanX> type_data) {
            this.type_data = type_data;
        }

        public static class TypeDataBeanX {
            /**
             * typeid : 9755344
             * type_name : 唱歌
             * pid : 9755328
             * type : 2
             * type_data : [{"typeid":"9755426","type_name":"英文歌","pid":"9755344","type":3},{"typeid":"9755427","type_name":"中文歌","pid":"9755344","type":3},{"typeid":"9755428","type_name":"粤语歌","pid":"9755344","type":3}]
             */

            private String typeid;
            private String type_name;
            private String pid;
            private int type;
            private List<TypeDataBean> type_data;

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

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<TypeDataBean> getType_data() {
                return type_data;
            }

            public void setType_data(List<TypeDataBean> type_data) {
                this.type_data = type_data;
            }

            public static class TypeDataBean {
                /**
                 * typeid : 9755426
                 * type_name : 英文歌
                 * pid : 9755344
                 * type : 3
                 */

                private String typeid;
                private String type_name;
                private String pid;
                private int type;

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

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }
        }
    }

    public static class HotDataBean {
        /**
         * name : 梦幻
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
