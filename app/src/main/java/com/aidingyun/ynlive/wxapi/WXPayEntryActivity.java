package com.aidingyun.ynlive.wxapi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.ui.activity.account.PayActivity;
import com.facebook.stetho.common.LogUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vector.update_app.HttpManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI  api;
    private Handler mHandler=new Handler();
    private TextView mTvPayStatu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);
//        tintManager.setStatusBarTintColor(Color.rgb(31, 33, 39));
//          initView();
        api = WXAPIFactory.createWXAPI(this, "wx2fe1f8339f18cb97");
        api.handleIntent(getIntent(), this);
    }

    protected void initView() {
//      ImageView ivBack= (ImageView) findViewById(R.id.star_1_com_topbar_iv_left);
//        mTvPayStatu = (TextView) findViewById(R.id.wxpay_statu);
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        LogUtil.e(">>>>>>>>>>>>>>>>>>>>>>req>>>>>>>>>>>>>>"+req.getType());
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtil.e(resp.errCode+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resp.errStr);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:
                    //支付成功
//                    mTvPayStatu.setText("支付状态:支付成功");
                    ToastUtils.show(this,"支付成功");
                    //刷新用户账户余额
//                    RefreshWChatpayCoin();
                    finish();
                     break;
                case -1:
                    //错误
                    ToastUtils.show(this,"支付失败");
//                    mTvPayStatu.setText("支付状态:支付失败");
                    finish();
                    break;
                case -2:
                    //用户取消
//                    mTvPayStatu.setText("支付状态:支付取消");
                    ToastUtils.show(this,"支付取消");
                    finish();
                    break;
            }

        }
    }
    //微信支付成功后刷新金币回调
    private void RefreshWChatpayCoin() {
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                Map<String, String> params = new ConcurrentSkipListMap<>();
//                params.put("orderid", orderid);
                UpdateVersionUtils.newInstance().postByName(Global.GET_RECHARGE_CALLBACK_SERVICE_NAME,params, new HttpManager.Callback() {
                    @Override
                    public void onResponse(String result) {
                        Log.e("PayActivity","result++++++++++++++++"+result);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            if (code.equals("SUCCESS")) {
                                ToastUtils.show(WXPayEntryActivity.this,"回调支付成功");
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
//                UserHttpUtils.newInstance().getAliPayBackGoldCoin(WXPayEntryActivity.this, YNCommonConfig.GET_ALIPAY_GOLD_COIN_BACKURL,
//                                                                  YNGoldCoinActivity.wxBackBean.getNumber(), YNGoldCoinActivity.wxBackBean.getAmount(), mHandler, YNCommonConfig.ALIPAY_BACK_COIN_FLAG, false);

            }
        });

    }

}