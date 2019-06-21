package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.CountDownUtil;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.di.component.DaggerPayComponent;
import com.aidingyun.ynlive.di.module.PayModule;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.contract.PayContract;
import com.aidingyun.ynlive.mvp.model.WXPayBean;
import com.aidingyun.ynlive.mvp.presenter.PayPresenter;
import com.alipay.sdk.app.PayTask;
import com.facebook.stetho.common.LogUtil;
import com.google.gson.Gson;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.lzy.okgo.model.Result;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vector.update_app.HttpManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class PayActivity extends BaseActivity<PayPresenter> implements PayContract.View{

    private String                      payMoney;
    private String                      orderInfo;
    private String                      BackInfo;
    private String                      BackAmount;
    private IWXAPI api;
    private WXPayBean wxPayBean;
    TextView tv_payment_price;
    TextView tv_pay_btn;
    RadioGroup radioGroup;
    RadioButton wechatpay,alipay;
    String pay_type = "";
    String recharge_fee = "";
    String orderid = "";
    /**
     * 获取权限使用的 RequestCode
     */
    private static final int PERMISSIONS_REQUEST_CODE = 1002;
    private static final int ZFB_SDK_PAY_FLAG = 1;
    public static void start(Context context,String recharge_fee) {
        Router.newIntent(context)
                .putString("recharge_fee",recharge_fee)
                .to(PayActivity.class)
                .launch();
    }
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPayComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .payModule(new PayModule(this))
                .build()
                .inject(this);
    }

    /**
     * 检查支付宝 SDK 所需的权限，并在必要的时候动态获取。
     * 在 targetSDK = 23 以上，READ_PHONE_STATE 和 WRITE_EXTERNAL_STORAGE 权限需要应用在运行时获取。
     * 如果接入支付宝 SDK 的应用 targetSdk 在 23 以下，可以省略这个步骤。
     */
    private void requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSIONS_REQUEST_CODE);

        } else {
            ToastUtils.show(this,getString(R.string.permission_already_granted));
        }
    }

    /**
     * 权限获取回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {

                // 用户取消了权限弹窗
                if (grantResults.length == 0) {
                    ToastUtils.show(this,getString(R.string.permission_rejected));
                    return;
                }

                // 用户拒绝了某些权限
                for (int x : grantResults) {
                    if (x == PackageManager.PERMISSION_DENIED) {
                        ToastUtils.show(this,getString(R.string.permission_rejected));
                        return;
                    }
                }

                // 所需的权限均正常获取
//                ToastUtils.show(this,getString(R.string.permission_granted));
            }
        }
    }

@Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pay; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInit(this, "支付", true);
        requestPermission();
        recharge_fee = getIntent().getStringExtra("recharge_fee");
        tv_payment_price = findViewById(R.id.tv_payment_price);
        tv_pay_btn = findViewById(R.id.tv_pay_btn);
        radioGroup = findViewById(R.id.radioGroup);
        wechatpay = findViewById(R.id.wechatpay);
        tv_payment_price.setText(recharge_fee+"元");
        wechatpay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    pay_type = "wx";
                }
            }
        });
        alipay = findViewById(R.id.alipay);
        alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    pay_type = "alipay";
                }
            }
        });
        api = WXAPIFactory.createWXAPI(this, "wx2fe1f8339f18cb97", false);
        api.registerApp("wx2fe1f8339f18cb97");

        tv_pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                Map<String, String> params = new ConcurrentSkipListMap<>();
                params.put("paytype", pay_type);//wx、alipay
                params.put("recharge_fee", recharge_fee);//充值金额
        //        params.put("actual_fee", typeid);//实际扣款金额（不传取充值金额）
                params.put("platform", "androd");//platform：androd 安卓调用必传参数
                UpdateVersionUtils.newInstance().postByName(Global.GET_RECHARGE_SERVICE_NAME,params, new HttpManager.Callback() {
                    @Override
                    public void onResponse(String result) {

                        Log.e("PayActivity","result++++++++++++++++"+result);

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            if (code.equals("SUCCESS")) {
                                JSONObject payment= jsonObject.getJSONObject("payment");
                                orderid = jsonObject.getString("orderid");
                                Gson gson = new Gson();
                                if (pay_type.equals("wx")){
                                wxPayBean = gson.fromJson(payment.toString(),WXPayBean.class);
                                long timeStamp = System.currentTimeMillis()/1000;
                                wxPayBean.setTimestamp(String.valueOf(timeStamp));
                                    String jsonInfo=gson.toJson(wxPayBean); //将对象转换成Json
                                    LogUtil.e("00000000000000000000000"+jsonInfo);
                                    WxPay();
                                }else if (pay_type.equals("alipay")){
                                    orderInfo = payment.getString("pay_data");
                                    Log.e("PayActivity","orderInfo++++++++++++++++"+orderInfo);
                                    if (checkAliPayInstalled()){
                                        zfbPay();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });
    }


    //微信支付
    private void WxPay()
    {

        isWeChatAppInstalled(this);
        PayReq req = new PayReq();
//            "appid": "wx2fe1f8339f18cb97",
//            "partnerid": "1486859792",
//            "prepayid": "wx171054032366800616b1a44b1929322336",
//            "package": "Sign=WXPay",
//            "noncestr": "O5N7vfb1llkDNsc19fDgWtjLXwTxfwjR",
//            "timestamp": 1545015243,
//            "sign": "BA4C9D8B8C12A6D6202FD0EEBB23F3CD"
        req.appId			= wxPayBean.getAppid();
        req.partnerId		= wxPayBean.getMch_id();
        req.prepayId		=wxPayBean.getPrepay_id();
        req.nonceStr		= wxPayBean.getNonce_str();
        req.timeStamp		= wxPayBean.getTimestamp();
        req.packageValue	= "Sign=WXPay";
        req.sign			= wxPayBean.getSign();
        //   Toast.makeText(YNGoldCoinActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }


    /**
     * 判断微信客户端是否存在
     *
     * @return true安装, false未安装
     */
    public  boolean isWeChatAppInstalled(Context context) {

        if(!api.isWXAppInstalled()) {
            ToastUtils.show(this,"没有安装微信");
            return true;
        } else {
            final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    if (pn.equalsIgnoreCase("com.tencent.mm")) {
                        ToastUtils.show(this,"已经安装微信");
                        return true;
                    }
                }
            }
            return false;
        }
    }


    public boolean checkAliPayInstalled() {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        return componentName != null;
    }


    /**
     * 支付宝支付
     */
    private void zfbPay()
    {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String,String> result = alipay.payV2(orderInfo,true);
                Message msg = new Message();
                msg.what = ZFB_SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ZFB_SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    ZFBPayResult payResult = new ZFBPayResult((Map<String, String>) msg.obj);

                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000"))
                    {

                        //刷新用户金币余额
                        RefreshAlipayCoin();
                        //刷新选择状态
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.show(PayActivity.this.getApplication(), "支付成功");
                    }
                    else
                    {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;
            }
        }
    };



    //支付宝支付成功后刷新金币回调
    private void RefreshAlipayCoin() {
//        mHandler.post(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                UserHttpUtils.newInstance().getAliPayBackGoldCoin(YNGoldCoinActivity.this, YNCommonConfig.GET_ALIPAY_GOLD_COIN_BACKURL, BackInfo,BackAmount, mHandler, YNCommonConfig.ALIPAY_BACK_COIN_FLAG, false);
//
//            }
//        });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

}
