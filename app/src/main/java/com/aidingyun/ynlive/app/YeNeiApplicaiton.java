package com.aidingyun.ynlive.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import com.aidingyun.ynlive.Module.WXRouteModule;
import com.aidingyun.ynlive.Module.WXValueModule;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.greendao.DaoMaster;
import com.aidingyun.ynlive.app.greendao.SQLiteOpenHelper;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ProcessUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.component.app.GlobalAppProxy;
import com.aidingyun.ynlive.component.app.GlobalContext;
import com.aidingyun.ynlive.component.app.RichText;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.aidingyun.ynlive.mvp.model.entity.LoginInfo;
import com.aidingyun.ynlive.mvp.ui.adapter.ImageAdapter;
import com.billy.cc.core.component.CC;
import com.google.gson.Gson;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.base.delegate.AppDelegate;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class YeNeiApplicaiton extends MultiDexApplication implements App {
    private AppLifecycles mAppDelegate;
    private SQLiteOpenHelper helper;
    private DaoMaster master;
    private UpdateVersionUtils updateVersionUtils = null;

    /**
     * 这里会在 {@link BaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        if (mAppDelegate == null)
            this.mAppDelegate = new AppDelegate(base);
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (mAppDelegate != null)
            this.mAppDelegate.onCreate(this);

        updateVersionUtils = new UpdateVersionUtils();
        ABaseService.preferences = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        ABaseService.islogin = ABaseService.preferences.getBoolean("islogin", false);

        Gson gson = new Gson();
        ABaseService.siteid = ABaseService.preferences.getString("siteid", "");
        String category = ABaseService.preferences.getString("category", "");
        ABaseService.courseClassificationModel = gson.fromJson(category, CourseClassificationModel.class);

        if (ABaseService.islogin) {
            ABaseService.token = ABaseService.preferences.getString("token", "");
            String result = ABaseService.preferences.getString("userinfo", "");
            ABaseService.loginInfo = gson.fromJson(result, LoginInfo.class);
        }

        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this, config);

        try {
            WXSDKEngine.registerComponent("richText", RichText.class);
            WXSDKEngine.registerModule("valueModule", WXValueModule.class);
            WXSDKEngine.registerModule("routeModule", WXRouteModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }

        CC.enableVerboseLog(true);
        CC.enableDebug(true);
        CC.init(this);
        CC.enableRemoteCC(true);

        //配置DB
        //updateDB();

        //init OkGo
        initOkGo();

        GlobalAppProxy.getInstance().onCreate(this);
        if (ProcessUtils.isMainProcess(this)) {
            GlobalAppProxy.getInstance().onCreateMainProcess(this);
        }

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                GlobalContext.setCurrActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (GlobalContext.getCurrActivity().getClass().equals(activity.getClass())) {
                    GlobalContext.setCurrActivity(null);
                }
            }
        });
    }


    private void updateDB() {
        //是否开启调试
//        MigrationHelper.DEBUG = true;
//        helper = new SQLiteOpenHelper(this, "history-search-db");
//        master = new DaoMaster(helper.getWritableDb());
    }

    HttpHeaders headers;
    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo"); //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY); //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO); builder.addInterceptor(loggingInterceptor); //全局的读取超时时间 基于前面的通道建立完成后，客户端终于可以向服务端发送数据了
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS); //全局的写入超时时间 服务器发回消息，可是客户端出问题接受不到了
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS); //全局的连接超时时间 http建立通道的时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS); //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this))); //使用数据库保持cookie，如果cookie不过期，则一直有效
        // builder.cookieJar(new CookieJarImpl(new DBCookieStore(this))); //使用内存保持cookie，app退出后，cookie消失
        // builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
        // === 配置https ===
        // 方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
//         方法二：自定义信任规则，校验服务端证书
//         HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
//         方法三：使用预埋证书，校验服务端证书（自签名证书）
//         HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
//         方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//         HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
//         builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
//         配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//         builder.hostnameVerifier(new SafeHostnameVerifier());
        // === 请求头 和 参数的 设置 === //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//

        if (ABaseService.islogin) {
            ABaseService.siteid = ABaseService.preferences.getString("siteid", "");
            ABaseService.token = ABaseService.preferences.getString("token","");
            headers = new HttpHeaders();
            headers.put("Authorization", ABaseService.token);
//         header不支持中文，不允许有特殊字符
            headers.put("siteid", ABaseService.siteid);
//            HttpParams params = new HttpParams(); params.put("commonParamsKey1", "commonParamsValue1");
//            // param支持中文,直接传,不要自己编码
//            params.put("commonParamsKey2", "这里支持中文参数");
        }

        OkGo.getInstance().init(this) //必须调用初始化
                .setOkHttpClient(builder.build()) //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE) //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE) //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)//全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0 //
                .addCommonHeaders(headers); //全局公共头
//         .addCommonParams(params);
         }



    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        if (ProcessUtils.isMainProcess(this)) {
            GlobalAppProxy.getInstance().onTerminate(this);
        }
        super.onTerminate();
        if (mAppDelegate != null)
            this.mAppDelegate.onTerminate(this);
    }

    /**
     * 将 {@link AppComponent} 返回出去, 供其它地方使用, {@link AppComponent} 接口中声明的方法所返回的实例, 在 {@link #getAppComponent()} 拿到对象后都可以直接使用
     *
     * @return AppComponent
     * @see ArmsUtils#obtainAppComponentFromContext(Context) 可直接获取 {@link AppComponent}
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(mAppDelegate, "%s cannot be null", AppDelegate.class.getName());
        Preconditions.checkState(mAppDelegate instanceof App, "%s must be implements %s", AppDelegate.class.getName(), App.class.getName());
        return ((App) mAppDelegate).getAppComponent();
    }

    private static int screenHeidth;//获取包含虚拟键的整体屏幕高度

    public static int getScreenHeidth() {
        return screenHeidth;
    }

    public static void setScreenHight(int virtualScreenHeidth) {
        screenHeidth = virtualScreenHeidth;
    }

    @Override
    public void onLowMemory() {
        if (ProcessUtils.isMainProcess(this)) {
            GlobalAppProxy.getInstance().onLowMemory();
        }
        super.onLowMemory();
    }


    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(android.R.color.transparent, android.R.color.white);
            layout.setDisableContentWhenRefresh(true);
            return new ClassicsHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            layout.setDisableContentWhenLoading(true);
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

}
