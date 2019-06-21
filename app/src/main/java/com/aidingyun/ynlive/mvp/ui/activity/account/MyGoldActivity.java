package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

public class MyGoldActivity extends BaseActivity implements View.OnClickListener {
    private TextView mRightTitle;
    private TextView mGold;
    /**
     * 立即充值
     */
    private TextView mTvRecharge;

    String balance = "";

    public static void start(Context context, String balance) {
        Router.newIntent(context)
                .putString("balance", balance)
                .to(MyGoldActivity.class)
                .launch();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_my_gold;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInitRight(this, "我的金币", "充值记录", true);
        balance = getIntent().getStringExtra("balance");
        mRightTitle = (TextView) findViewById(R.id.right_title);
        mRightTitle.setOnClickListener(this);
        mGold = (TextView) findViewById(R.id.view3);
        mTvRecharge = (TextView) findViewById(R.id.tv_recharge);
        mTvRecharge.setOnClickListener(this);
        mGold.setText(balance);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.right_title:
                ToastUtils.show(this, "充值记录");
                break;
            case R.id.tv_recharge:
                RechargeActivity.start(this);
                break;
        }
    }
}
