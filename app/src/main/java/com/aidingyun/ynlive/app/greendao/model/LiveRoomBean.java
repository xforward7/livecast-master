package com.aidingyun.ynlive.app.greendao.model;

/**
 * 直播首页实体类
 * Created by Administrator on 2017/1/6.
 */
public class LiveRoomBean extends BaseBean
{
    private String pid;
    private String id;
    private String userid;
    private String username;
    private String icon;// 用户头像
    private String push_address;// 推流地址
    private String play_address;// 播放地址
    private String room_id;// 房间id
    private String picture;// 直播封面

    private String title;// 直播标题
    private int is_attention;
    private int funs_count;
    private int focus_count;
    private int dynamic_count;

    private int acount;// 关注数量
    private int equipment;// 标识是移动端还是PC端

    private String attentionid;
    private String describes;
    private int user_status;
    private String status;
    private String time;
    private String detail;

    private int payCoin;
    private int living;// 0:休息中 1:直播中
//    private int right;
    private int live_status;
    private int lock;
    private int is_pay;
    private int is_selfManage;

    private int liveCount;// 观看人数(实际在线人数)
    private int liveNum;// 虚拟在线人数(虚拟加实际在线人数)
    private int vrNum;// 虚拟人数

    private int interest;
    private String streamcode;// 串流码
    private int num;//直播流的数量
    private int sex;

    private String tag;
    private String tag2;
    private double opinion;
    private double voice;
    private double write;

    private String articleTitle;
    private String articleContent;

    private String hostroom_id;
    private double contribution;
    private String pwd;


    //新增字段

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPush_address() {
        return push_address;
    }

    public void setPush_address(String push_address) {
        this.push_address = push_address;
    }

    public String getPlay_address() {
        return play_address;
    }

    public void setPlay_address(String play_address) {
        this.play_address = play_address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEquipment() {
        return equipment;
    }

    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }

    public String getAttentionid() {
        return attentionid;
    }

    public void setAttentionid(String attentionid) {
        this.attentionid = attentionid;
    }

    public String getDescribe() {
        return describes;
    }

    public void setDescribe(String describe) {
        this.describes = describe;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLiving() {
        return living;
    }

    public void setLiving(int living) {
        this.living = living;
    }

//    public int getRight() {
//        return right;
//    }
//
//    public void setRight(int right) {
//        this.right = right;
//    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIs_attention() {
        return is_attention;
    }

    public void setIs_attention(int is_attention) {
        this.is_attention = is_attention;
    }

    public int getAcount() {
        return acount;
    }

    public void setAcount(int acount) {
        this.acount = acount;
    }

    public int getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }

    public int getPayCoin() {
        return payCoin;
    }

    public void setPayCoin(int payCoin) {
        this.payCoin = payCoin;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public int getDynamic_count() {
        return dynamic_count;
    }

    public void setDynamic_count(int dynamic_count) {
        this.dynamic_count = dynamic_count;
    }

    public int getFuns_count() {
        return funs_count;
    }

    public void setFuns_count(int funs_count) {
        this.funs_count = funs_count;
    }

    public int getFocus_count() {
        return focus_count;
    }

    public void setFocus_count(int focus_count) {
        this.focus_count = focus_count;
    }

    public void setLive_status(int live_status) {
        this.live_status = live_status;
    }

    public int getLive_status() {
        return live_status;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStreamcode() {
        return streamcode;
    }

    public void setStreamcode(String streamcode) {
        this.streamcode = streamcode;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public double getOpinion() {
        return opinion;
    }

    public void setOpinion(double opinion) {
        this.opinion = opinion;
    }

    public double getVoice() {
        return voice;
    }

    public void setVoice(double voice) {
        this.voice = voice;
    }

    public double getWrite() {
        return write;
    }

    public void setWrite(double write) {
        this.write = write;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(int liveNum) {
        this.liveNum = liveNum;
    }

    public String getHostroom_id() {
        return hostroom_id;
    }

    public void setHostroom_id(String hostroom_id) {
        this.hostroom_id = hostroom_id;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    public int getVrNum() {
        return vrNum;
    }

    public void setVrNum(int vrNum) {
        this.vrNum = vrNum;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getIs_selfManage() {
        return is_selfManage;
    }

    public void setIs_selfManage(int is_selfManage) {
        this.is_selfManage = is_selfManage;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
