package com.aidingyun.ynlive.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhaotun on 2016/12/20.
 */

public class LoginInfo implements Parcelable {

    /**
     * roomid :
     * room_name :
     * username : 9928001
     * phone : 13530815470
     * userid : 9928001
     * user_status : 1
     * icon : //pic.iidingyun.com/file/2778/top_head portrait_default@2x-2.png
     * signature :
     * star_level : 0
     * siteid : 71704
     * token : eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NDM2MzUxNDcsInN1YiI6IntcInJvb21pZFwiOlwiXCIsXCJyb29tX25hbWVcIjpcIlwiLFwidXNlcm5hbWVcIjpcIjk5MjgwMDFcIixcInBob25lXCI6XCIxMzUzMDgxNTQ3MFwiLFwidXNlcmlkXCI6XCI5OTI4MDAxXCIsXCJ1c2VyX3N0YXR1c1wiOlwiMVwiLFwiaWNvblwiOlwiLy9waWMuaWlkaW5neXVuLmNvbS9maWxlLzI3NzgvdG9wX2hlYWQgcG9ydHJhaXRfZGVmYXVsdEAyeC0yLnBuZ1wiLFwic2lnbmF0dXJlXCI6XCJcIixcInN0YXJfbGV2ZWxcIjpcIjBcIixcInNpdGVpZFwiOlwiNzE3MDRcIn0iLCJpc3MiOiJ5ZW5laSIsImV4cCI6MTU0NDIzOTk0N30.HjkkCSs790DSE2qaaZN0lblgmclNwkkyhmu67uptaKI
     * expire : 604800
     * code : success
     * msg : 登录成功！
     */

    private String roomid="";
    private String room_name="";
    private String username="";
    private String phone="";
    private String userid="";
    private String user_status="";
    private String icon="";
    private String signature="";
    private String star_level="";
    private String siteid="";
    private String token="";
    private int expire=0;
    private String code="";
    private String msg="";


    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getStar_level() {
        return star_level;
    }

    public void setStar_level(String star_level) {
        this.star_level = star_level;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

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

    @Override
    public String toString() {
        return "LoginInfo{" +
                "roomid='" + roomid + '\'' +
                ", room_name='" + room_name + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", userid='" + userid + '\'' +
                ", user_status='" + user_status + '\'' +
                ", icon='" + icon + '\'' +
                ", signature='" + signature + '\'' +
                ", star_level='" + star_level + '\'' +
                ", siteid='" + siteid + '\'' +
                ", token='" + token + '\'' +
                ", expire=" + expire +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.roomid);
        dest.writeString(this.room_name);
        dest.writeString(this.username);
        dest.writeString(this.phone);
        dest.writeString(this.userid);
        dest.writeString(this.user_status);
        dest.writeString(this.icon);
        dest.writeString(this.signature);
        dest.writeString(this.star_level);
        dest.writeString(this.siteid);
        dest.writeString(this.token);
        dest.writeInt(this.expire);
        dest.writeString(this.code);
        dest.writeString(this.msg);
    }

    public LoginInfo(Parcel in) {
        this.roomid = in.readString();
        this.room_name = in.readString();
        this.phone = in.readString();
        this.userid = in.readString();
        this.user_status = in.readString();
        this.icon = in.readString();
        this.signature = in.readString();
        this.star_level = in.readString();
        this.siteid = in.readString();
        this.token = in.readString();
        this.msg = in.readString();
        this.expire = in.readInt();
        this.code = in.readString();
    }

    public static final Creator<LoginInfo> CREATOR = new Creator<LoginInfo>() {
        @Override
        public LoginInfo createFromParcel(Parcel source) {
            return new LoginInfo(source);
        }

        @Override
        public LoginInfo[] newArray(int size) {
            return new LoginInfo[size];
        }
    };
}
