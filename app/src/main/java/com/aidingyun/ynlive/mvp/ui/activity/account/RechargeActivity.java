package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.di.component.DaggerRechargeComponent;
import com.aidingyun.ynlive.di.module.RechargeModule;
import com.aidingyun.ynlive.mvp.contract.RechargeContract;
import com.aidingyun.ynlive.mvp.model.WXPayBean;
import com.aidingyun.ynlive.mvp.presenter.RechargePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class RechargeActivity extends BaseActivity<RechargePresenter> implements RechargeContract.View, View.OnClickListener {
    private LinearLayout mOne;
    private LinearLayout mTwo;
    private LinearLayout mThree;
    private LinearLayout mFour;
    private LinearLayout mFive;
    private LinearLayout mSix;
    /**
     * 请输入整数
     */
    private EditText mView4;
    /**
     * 立即充值
     */
    private TextView mTvRecharge;
    /**
     * 10金币
     */
    private TextView mOneGold;
    /**
     * ￥6.00
     */
    private TextView mOneMoney;
    /**
     * 30金币
     */
    private TextView mTwoGold;
    /**
     * ￥15.00
     */
    private TextView mTwoMoney;
    /**
     * 50金币
     */
    private TextView mThreeGold;
    /**
     * ￥30.00
     */
    private TextView mThreeMoney;
    /**
     * 70金币
     */
    private TextView mFourGold;
    /**
     * ￥40.00
     */
    private TextView mFourMoney;
    /**
     * 110金币
     */
    private TextView mFiveGold;
    /**
     * ￥50.00
     */
    private TextView mFiveMoney;
    /**
     * 150金币
     */
    private TextView mSixGold;
    /**
     * ￥60.00
     */
    private TextView mSixMoney;

    String recharge_fee = "";

    public static void start(Context context) {
        Router.newIntent(context)
                .to(RechargeActivity.class)
                .launch();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRechargeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .rechargeModule(new RechargeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_recharge; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInit(this, "充值", true);
        mOne = (LinearLayout) findViewById(R.id.one);
        mOne.setOnClickListener(this);
        mTwo = (LinearLayout) findViewById(R.id.two);
        mTwo.setOnClickListener(this);
        mThree = (LinearLayout) findViewById(R.id.three);
        mThree.setOnClickListener(this);
        mFour = (LinearLayout) findViewById(R.id.four);
        mFour.setOnClickListener(this);
        mFive = (LinearLayout) findViewById(R.id.five);
        mFive.setOnClickListener(this);
        mSix = (LinearLayout) findViewById(R.id.six);
        mSix.setOnClickListener(this);
        mView4 = (EditText) findViewById(R.id.view4);
        mTvRecharge = (TextView) findViewById(R.id.tv_recharge);
        mTvRecharge.setOnClickListener(this);
        mOneGold = (TextView) findViewById(R.id.one_gold);
        mOneMoney = (TextView) findViewById(R.id.one_money);
        mTwoGold = (TextView) findViewById(R.id.two_gold);
        mTwoMoney = (TextView) findViewById(R.id.two_money);
        mThreeGold = (TextView) findViewById(R.id.three_gold);
        mThreeMoney = (TextView) findViewById(R.id.three_money);
        mFourGold = (TextView) findViewById(R.id.four_gold);
        mFourMoney = (TextView) findViewById(R.id.four_money);
        mFiveGold = (TextView) findViewById(R.id.five_gold);
        mFiveMoney = (TextView) findViewById(R.id.five_money);
        mSixGold = (TextView) findViewById(R.id.six_gold);
        mSixMoney = (TextView) findViewById(R.id.six_money);

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






    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.one:
                recharge_fee = mOneGold.getText().toString().substring(0,mOneGold.getText().toString().length()-2);
                    mOne.setBackgroundResource(R.drawable.item_bg_blue_r4);
                    mOneGold.setTextColor(Color.WHITE);
                    mOneMoney.setTextColor(Color.WHITE);
//                    mOne.setSelected(true);
                    mTwo.setBackgroundResource(R.drawable.item_bg_r4);
                    mTwoGold.setTextColor(Color.parseColor("#419DFB"));
                    mTwoMoney.setTextColor(Color.parseColor("#A4B6CC"));

                    mThree.setBackgroundResource(R.drawable.item_bg_r4);
                    mThreeGold.setTextColor(Color.parseColor("#419DFB"));
                    mThreeMoney.setTextColor(Color.parseColor("#A4B6CC"));

                    mFour.setBackgroundResource(R.drawable.item_bg_r4);
                    mFourGold.setTextColor(Color.parseColor("#419DFB"));
                    mFourMoney.setTextColor(Color.parseColor("#A4B6CC"));

                    mFive.setBackgroundResource(R.drawable.item_bg_r4);
                    mFiveGold.setTextColor(Color.parseColor("#419DFB"));
                    mFiveMoney.setTextColor(Color.parseColor("#A4B6CC"));

                    mSix.setBackgroundResource(R.drawable.item_bg_r4);
                    mSixGold.setTextColor(Color.parseColor("#419DFB"));
                    mSixMoney.setTextColor(Color.parseColor("#A4B6CC"));

                break;
            case R.id.two:
                recharge_fee = mTwoGold.getText().toString().substring(0,mTwoGold.getText().toString().length()-2);
                    mTwo.setBackgroundResource(R.drawable.item_bg_blue_r4);
                    mTwoGold.setTextColor(Color.WHITE);
                    mTwoMoney.setTextColor(Color.WHITE);

                    mOne.setBackgroundResource(R.drawable.item_bg_r4);
                    mOneGold.setTextColor(Color.parseColor("#419DFB"));
                    mOneMoney.setTextColor(Color.parseColor("#A4B6CC"));

                    mThree.setBackgroundResource(R.drawable.item_bg_r4);
                    mThreeGold.setTextColor(Color.parseColor("#419DFB"));
                    mThreeMoney.setTextColor(Color.parseColor("#A4B6CC"));

                    mFour.setBackgroundResource(R.drawable.item_bg_r4);
                    mFourGold.setTextColor(Color.parseColor("#419DFB"));
                    mFourMoney.setTextColor(Color.parseColor("#A4B6CC"));

                    mFive.setBackgroundResource(R.drawable.item_bg_r4);
                    mFiveGold.setTextColor(Color.parseColor("#419DFB"));
                    mFiveMoney.setTextColor(Color.parseColor("#A4B6CC"));

                    mSix.setBackgroundResource(R.drawable.item_bg_r4);
                    mSixGold.setTextColor(Color.parseColor("#419DFB"));
                    mSixMoney.setTextColor(Color.parseColor("#A4B6CC"));

                break;
            case R.id.three:
                recharge_fee = mThreeGold.getText().toString().substring(0,mThreeGold.getText().toString().length()-2);
                mThree.setBackgroundResource(R.drawable.item_bg_blue_r4);
                mThreeGold.setTextColor(Color.WHITE);
                mThreeMoney.setTextColor(Color.WHITE);

                mOne.setBackgroundResource(R.drawable.item_bg_r4);
                mOneGold.setTextColor(Color.parseColor("#419DFB"));
                mOneMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mTwo.setBackgroundResource(R.drawable.item_bg_r4);
                mTwoGold.setTextColor(Color.parseColor("#419DFB"));
                mTwoMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mFour.setBackgroundResource(R.drawable.item_bg_r4);
                mFourGold.setTextColor(Color.parseColor("#419DFB"));
                mFourMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mFive.setBackgroundResource(R.drawable.item_bg_r4);
                mFiveGold.setTextColor(Color.parseColor("#419DFB"));
                mFiveMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mSix.setBackgroundResource(R.drawable.item_bg_r4);
                mSixGold.setTextColor(Color.parseColor("#419DFB"));
                mSixMoney.setTextColor(Color.parseColor("#A4B6CC"));
                break;
            case R.id.four:
                recharge_fee = mFourGold.getText().toString().substring(0,mFourGold.getText().toString().length()-2);
                mFour.setBackgroundResource(R.drawable.item_bg_blue_r4);
                mFourGold.setTextColor(Color.WHITE);
                mFourMoney.setTextColor(Color.WHITE);

                mOne.setBackgroundResource(R.drawable.item_bg_r4);
                mOneGold.setTextColor(Color.parseColor("#419DFB"));
                mOneMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mTwo.setBackgroundResource(R.drawable.item_bg_r4);
                mTwoGold.setTextColor(Color.parseColor("#419DFB"));
                mTwoMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mThree.setBackgroundResource(R.drawable.item_bg_r4);
                mThreeGold.setTextColor(Color.parseColor("#419DFB"));
                mThreeMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mFive.setBackgroundResource(R.drawable.item_bg_r4);
                mFiveGold.setTextColor(Color.parseColor("#419DFB"));
                mFiveMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mSix.setBackgroundResource(R.drawable.item_bg_r4);
                mSixGold.setTextColor(Color.parseColor("#419DFB"));
                mSixMoney.setTextColor(Color.parseColor("#A4B6CC"));
                break;
            case R.id.five:
                recharge_fee = mFiveGold.getText().toString().substring(0,mFiveGold.getText().toString().length()-2);
                mFive.setBackgroundResource(R.drawable.item_bg_blue_r4);
                mFiveGold.setTextColor(Color.WHITE);
                mFiveMoney.setTextColor(Color.WHITE);

                mOne.setBackgroundResource(R.drawable.item_bg_r4);
                mOneGold.setTextColor(Color.parseColor("#419DFB"));
                mOneMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mTwo.setBackgroundResource(R.drawable.item_bg_r4);
                mTwoGold.setTextColor(Color.parseColor("#419DFB"));
                mTwoMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mThree.setBackgroundResource(R.drawable.item_bg_r4);
                mThreeGold.setTextColor(Color.parseColor("#419DFB"));
                mThreeMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mFour.setBackgroundResource(R.drawable.item_bg_r4);
                mFourGold.setTextColor(Color.parseColor("#419DFB"));
                mFourMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mSix.setBackgroundResource(R.drawable.item_bg_r4);
                mSixGold.setTextColor(Color.parseColor("#419DFB"));
                mSixMoney.setTextColor(Color.parseColor("#A4B6CC"));
                break;
            case R.id.six:
                recharge_fee = mSixGold.getText().toString().substring(0,mSixGold.getText().toString().length()-2);
                mSix.setBackgroundResource(R.drawable.item_bg_blue_r4);
                mSixGold.setTextColor(Color.WHITE);
                mSixMoney.setTextColor(Color.WHITE);

                mOne.setBackgroundResource(R.drawable.item_bg_r4);
                mOneGold.setTextColor(Color.parseColor("#419DFB"));
                mOneMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mTwo.setBackgroundResource(R.drawable.item_bg_r4);
                mTwoGold.setTextColor(Color.parseColor("#419DFB"));
                mTwoMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mThree.setBackgroundResource(R.drawable.item_bg_r4);
                mThreeGold.setTextColor(Color.parseColor("#419DFB"));
                mThreeMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mFour.setBackgroundResource(R.drawable.item_bg_r4);
                mFourGold.setTextColor(Color.parseColor("#419DFB"));
                mFourMoney.setTextColor(Color.parseColor("#A4B6CC"));

                mFive.setBackgroundResource(R.drawable.item_bg_r4);
                mFiveGold.setTextColor(Color.parseColor("#419DFB"));
                mFiveMoney.setTextColor(Color.parseColor("#A4B6CC"));
                break;

            case R.id.tv_recharge:
                if (mView4.getText().toString().length()==0 && recharge_fee.trim().equals("")){
                    ToastUtils.show(RechargeActivity.this,"请选择充值金额！");
                    return;
                }
                if (!TextUtils.isEmpty(recharge_fee) && mView4.getText().toString().length()!=0){
                    ToastUtils.show(RechargeActivity.this,"只能选择一个充值金额！");
                    return;
                }
                if (mView4.getText().toString().length()==0){
                    PayActivity.start(this,recharge_fee);
                }else if (recharge_fee.trim().equals("")){
                    PayActivity.start(this,mView4.getText().toString().trim());
                }
                break;
        }
    }


}
