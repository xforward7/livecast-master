package com.aidingyun.ynlive.mvp.ui.base;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.mvp.IPresenter;

/**
 * Created: AriesHoo on 2017/7/3 16:04
 * Function: title 基类
 * Desc:
 */

public abstract class IBaseActivity<P extends IPresenter> extends BaseActivity {

    protected String TAG = getClass().getSimpleName();


}
