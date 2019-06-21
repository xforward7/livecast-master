package com.aidingyun.ynlive.mvp.contract;

public class Global {
    public static final String HOST = "";
    public static final String GET_SITEID_URL = "http://8694880.iidingyun.com/get_siteid.vm";
    public static String REGISTER_SERVICE_NAME = "com.iiding.user.user_register";//注册
    public static String LOGIN_SERVICE_NAME = "com.iiding.user.user_login";//登录
    public static String FORGET_PASS_SERVICE_NAME = "com.iiding.user.forget_password";//忘记密码
    public static String CHANGE_PASS_SERVICE_NAME = "com.iiding.user.update_password";//修改密码
    public static String CHANGE_USERINFO_SERVICE_NAME = "com.iiding.user.update_user_info";//修改用户信息
    public static String GET_USERINFO_SERVICE_NAME = "com.iiding.user.host.host_info";//获取主播信息
    public static String CHANGE_PHONE_SERVICE_NAME = "com.iiding.user.update_phone";//修改手机
    public static String GET_ACCOUNT_USER_SERVICE_NAME = "com.iiding.user.account_info_select";//获取资金账户
    public static String CHECK_OLD_PHONE_SERVICE_NAME = "com.iiding.user.check_old_phone";//验证原手机
    public static String GET_HOST_COUNT_SERVICE_NAME = "com.iiding.user.host.count";//获取主播统计
    public static String GET_AUTHEN_INFO_SERVICE_NAME = "com.iiding.user.authentication.all_info";//获取认证信息
    public static String GET_LIVE_ROOM_CLASS_HOUR_SERVICE_NAME = "com.iiding.live.room.class_hour";//直播间个人课程表
    public static String GET_LIVE_RECORD_LIST_SERVICE_NAME = "com.iiding.live.room.record";//【直播课时】课时收入记录
    public static String GET_HOST_INFO_SERVICE_NAME = "com.iiding.user.host.user_info";//获取主播信息
    public static String GET_NORMAL_USERINFO_SERVICE_NAME = "com.iiding.common.module.get_user_detail";//获取用户详情 普通
    public static String GET_CLASS_TYPE_SERVICE_NAME = "com.iiding.common.type.type_select";//获取课程分类/兴趣爱好分类
    public static String PUBLIC_COMENT_AREA_SERVICE_NAME = "com.iiding.common.evaluate.evaluate_insert";//公共评论区
    public static String SEND_VERIFY_CODE_SERVICE_NAME = "com.iiding.common.tools.verify_code";//发送验证码
    public static String GET_EVALUATE_LIST_SERVICE_NAME = "com.iiding.common.evaluate.evaluate_select";//评论列表
    public static String GET_RECHARGE_MONEY_SERVICE_NAME = "com.iiding.common.pay.recharge_count";//获取充值统计金额
    public static String GET_ROOM_INFO_SERVICE_NAME = "com.iiding.live.room.room_info";//房间信息
    public static String UPLOAD_IMAGE_SERVICE_NAME = "com.iiding.orders.upload_receiveable_attach";//上传图片
    public static String GET_UPLOAD_VIDEO_SIGN_SERVICE_NAME = "com.iiding.common.tools.get_video_sign";//获取上传录播视频签名
    public static String GET_CONSUME_RECHARGE_TOTAL_SERVICE_NAME = "com.iiding.finance.count";//消费、充值总金额
    public static String GET_RECORD_VIDEO_INFORMATION_SERVICE_NAME = "com.iiding.common.tools.get_video_info";//获取录播视频信息
    public static String GET_TENCENT_CLOUD_INFO_SERVICE_NAME = "com.iiding.common.tools.get_tencent_info";//调用腾讯云API
    public static String GET_TENCENT_CLOUD_API_SERVICE_NAME = "com.iiding.common.tools.get_tencent_api";//调用腾讯云API


    public static String GET_INDEX_LIST_SERVICE_NAME = "com.iiding.course.app_index_select";//APP首页内容展示
    public static String GET_TYPE_SERVICE_NAME = "com.iiding.common.type.type_select";//获取课程分类/兴趣爱好分类
    public static String GET_COURSE_SEARCH_SERVICE_NAME = "com.iiding.course.live_course_select";//课程搜索展示
    public static String GET_COURSE_DETAIL_SERVICE_NAME = "com.iiding.course.live_course_select_detail";//课程详情展示
    public static String GET_COURSE_COMMENT_LIST_SERVICE_NAME = "com.iiding.common.evaluate.evaluate_select";//课程详情评论列表
    public static String GET_COURSE_BUY_SERVICE_NAME = "com.iiding.order.create_course_order";//课程购买
    public static String GET_WATCH_HISTORY_SERVICE_NAME = "com.yenei.micro.uc.history";//观看历史
    public static String GET_COLLECT_SERVICE_NAME = "com.yenei.micro.uc.favorite";//个人中心_我的收藏
    public static String GET_ADD_DOWNLOAD_COURSE_SERVICE_NAME = "com.iiding.user.my_download.course_download_insert";//添加下载课程
    public static String GET_DOWNLOAD_COURSE_LIST_QUERY_SERVICE_NAME = "com.iiding.user.my_download.course_download_query";//个人中心_下载列表

    public static String GET_COURSE_STUDY_SERVICE_NAME = "com.yenei.micro.uc.lesson";//课程我的学习列表、想学请求接口
    public static String GET_MINE_ATTENTION_SERVICE_NAME = "com.iiding.user.attention.attention_query_userid";//个人中心--我的关注
    public static String GET_ORDER_MANAGER_SERVICE_NAME = "com.iiding.user.order.order";//个人中心--订单管理
    public static String GET_ACCOUNT_SERVICE_NAME = "com.iiding.common.count.asset_account";//个人中心--获取资产
    public static String GET_RECHARGE_SERVICE_NAME = "com.iiding.common.pay.recharge";//个人中心--创建充值订单
    public static String GET_RECHARGE_CALLBACK_SERVICE_NAME = "com.iiding.common.pay.change_recharge";//个人中心--充值回调
}
