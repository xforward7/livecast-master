package com.aidingyun.ynlive.mvp.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aidingyun.core.utils.Kits;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.YeNeiApplicaiton;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.aidingyun.ynlive.mvp.model.entity.ServicepathModel;
import com.aidingyun.ynlive.mvp.model.api.Api;
import com.google.gson.Gson;
import com.vector.update_app.HttpManager;
import com.yanzhenjie.sofia.Sofia;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class SplashActivity extends AppCompatActivity {
    //    public static final int DEFAULT_SKIP = BuildConfig.DEBUG ? 0 : 3000;//总计X秒后自动跳转MainActivity
    public static final int DEFAULT_SKIP = 2000;//总计X秒后自动跳转MainActivity
    private TextView mSpJumpBtn;
    private  UpdateVersionUtils updateVersionUtils = null;

    private CountDownTimer countDownTimer = new CountDownTimer(DEFAULT_SKIP, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mSpJumpBtn.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            mSpJumpBtn.setText("跳过(" + 0 + "s)");
            doOnNextStep();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        updateVersionUtils = new UpdateVersionUtils();
        YeNeiApplicaiton.setScreenHight(Kits.Dimens.getVirtualKeyboard(this));
        Sofia.with(this)
                .statusBarDarkFont()
                .statusBarBackground(ContextCompat.getColor(this, R.color.colorPrimary))
                .navigationBarBackground(ContextCompat.getColor(this, R.color.colorPrimary));
        mSpJumpBtn = findViewById(R.id.sp_jump_btn);
        mSpJumpBtn.setVisibility(View.GONE);
//        todo 版本更新
        UpdateUtils.getInstance().setCheckResultListener(new UpdateUtils.CheckUpdateResult() {
            @Override
            public void hasNewApp() {
            }

            @Override
            public void noNewApp(String error) {
                mSpJumpBtn.setOnClickListener(view -> {
                    countDownTimer.cancel();
                    doOnNextStep();
                });
                countDownTimer.start();
            }

            @Override
            public void ignoreApp() {
                mSpJumpBtn.setOnClickListener(view -> {
                    countDownTimer.cancel();
                    doOnNextStep();
                });
                countDownTimer.start();
            }
        }).checkUpdate(this, true);

//        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
//        reqBody.put("num", "1");
//        reqBody.put("stuid", Global.account);
//        reqBody.put("buildingNo", selectBuilding + "");
        updateVersionUtils.asyncGet(Api.DUBUG_KEY, null, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("SplashActivity","get microservice config ======> "+Api.DUBUG_KEY);
                List<ServicepathModel.ServiceDataBean> serviceDataBeans = new ArrayList<ServicepathModel.ServiceDataBean>();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String siteid = jsonObject.getString("siteid");
                    ABaseService.siteid = siteid;
                   // Log.e("SplashActivity","siteid++++++++++++++++"+ABaseService.siteid);
                    SharedPreferences preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
                    preferences.edit().putString("siteid",ABaseService.siteid);
                    preferences.edit().commit();
                    Gson gson = new Gson();
                    ABaseService.servicepathModel = gson.fromJson(result, ServicepathModel.class);
                    for (ServicepathModel.ServiceDataBean serviceDataBean:ABaseService.servicepathModel.service_data) {
                        ServicepathModel.ServiceDataBean serviceDataBean1 = new ServicepathModel.ServiceDataBean();
                        serviceDataBean1.setService_name(serviceDataBean.service_name);
                        serviceDataBean1.setService_path(serviceDataBean.service_path);
                        serviceDataBeans.add(serviceDataBean1);
                    }
                    getInfo();
//                    for (int i = 0; i < ABaseService.servicepathModel.service_data.size(); i++) {
//                        if (ABaseService.servicepathModel.service_data.get(i).service_name.equals(Global.LOGIN_SERVICE_NAME)){
//                            String url = ABaseService.servicepathModel.service_data.get(i).service_path;
//                            Log.e("SplashActivity","url++++++++++++++++"+url);
//                        }
//                    }


//                    ToastUtils.show(SplashActivity.this,".......siteid="+siteid);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                ToastUtils.showError(SplashActivity.this,error);
            }
        });


    }

    public  void getInfo(){
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("type", "app");
        updateVersionUtils.postByName(Global.GET_TYPE_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("HomePageListFragment","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Gson gson = new Gson();
                    ABaseService.courseClassificationModel = gson.fromJson(result, CourseClassificationModel.class);
                    String jsonInfo=gson.toJson(ABaseService.courseClassificationModel); //将对象转换成Json
                    ABaseService.preferences.edit().putString("category",jsonInfo);
                    ABaseService.preferences.edit().commit();

                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
//                ToastUtils.showError(getActivity(),error);
            }
        });
    }

    private void doOnNextStep() {
//            LoginActivity.start(SplashActivity.this);
        MainActivity.start(SplashActivity.this);
        finish();
    }

}
