package com.aidingyun.ynlive.mvp.ui.activity.account;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.GlideEngine;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.di.component.DaggerUpdateUserInfoComponent;
import com.aidingyun.ynlive.di.module.UpdateUserInfoModule;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.contract.UpdateUserInfoContract;
import com.aidingyun.ynlive.mvp.presenter.UpdateUserInfoPresenter;
import com.aidingyun.ynlive.mvp.ui.widget.CircleImageView;
import com.facebook.stetho.common.LogUtil;
import com.google.gson.Gson;
import com.huantansheng.easyphotos.EasyPhotos;
import com.jakewharton.rxbinding2.view.RxView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.lzy.okgo.OkGo;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.jess.arms.utils.Preconditions.checkNotNull;


public class UpdateUserInfoActivity extends BaseActivity<UpdateUserInfoPresenter> implements UpdateUserInfoContract.View, View.OnClickListener {
    private CircleImageView mAvatar;
    private TextView mUpdateAvatar;
    private EditText mEdNickName;
    /**
     * 男
     */
    private RadioButton mRbBoy;
    /**
     * 女
     */
    private RadioButton mRbGirl;
    /**
     * 保密
     */
    private RadioButton mRbSecret;

    private RadioGroup mRgSex;

    private EditText mEdIntroduction;

    private TextView tv_submit_btn;

    String iconpath = "";
    String avatarpath = "";

    private UpdateVersionUtils updateVersionUtils = null;

    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    String gender = "";

    public static void start(Context context) {
        Router.newIntent(context)
                .to(UpdateUserInfoActivity.class)
                .launch();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUpdateUserInfoComponent
                .builder()
                .appComponent(appComponent)
                .updateUserInfoModule(new UpdateUserInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_update_user_info;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlobalUtils.statusBarTint(this);
        GlobalUtils.toolBarInit(this, "修改资料", true);
        updateVersionUtils = new UpdateVersionUtils();
        preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
        mAvatar = (CircleImageView) findViewById(R.id.avatar);
        mAvatar.setOnClickListener(this);
        mUpdateAvatar = (TextView) findViewById(R.id.tv_update_avatar);
        mUpdateAvatar.setOnClickListener(this);
        mEdNickName = (EditText) findViewById(R.id.ed_nick_name);
        mEdNickName.setOnClickListener(this);
        mRbBoy = (RadioButton) findViewById(R.id.rb_boy);
        mRbBoy.setOnClickListener(this);
        mRbGirl = (RadioButton) findViewById(R.id.rb_girl);
        mRbGirl.setOnClickListener(this);
        mRbSecret = (RadioButton) findViewById(R.id.rb_secret);
        mRbSecret.setOnClickListener(this);
        mRgSex = (RadioGroup) findViewById(R.id.rgSex);
        mEdIntroduction = (EditText) findViewById(R.id.ed_introduction);
        tv_submit_btn = (TextView) findViewById(R.id.tv_submit_btn);
        tv_submit_btn.setOnClickListener(this);
        mRgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                if (rb.getText().toString().trim().equals("保密")){
                    gender = "0";
                }else if (rb.getText().toString().trim().equals("女")){
                    gender = "1";
                }else if (rb.getText().toString().trim().equals("男")){
                    gender = "2";
                }
            }
        });

        LoadImage.loadCircleImage(UpdateUserInfoActivity.this,ABaseService.loginInfo.getIcon(),mAvatar);
        mEdNickName.setText(ABaseService.loginInfo.getUsername());
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
    protected void onDestroy() {
        super.onDestroy();
        //取消指定tag的请求
        OkGo.getInstance().cancelTag(this); //取消全部请求
//        OkGo.getInstance().cancelAll(); //取消OkHttpClient的全部请求
//        OkGo.cancelAll(new OkHttpClient());
//        OkGo.cancelTag(new OkHttpClient(),"且指定tag");
//        bitmap.recycle();
    }

    @Override
    public void killMyself() {
        finish();
    }

    private static final int REQUEST_CODE_CHOOSE = 1001;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.avatar:
            case R.id.tv_update_avatar:
                showDialog();
                break;
            case R.id.ed_nick_name:
                break;
            case R.id.rb_boy:
                break;
            case R.id.rb_girl:
                break;
            case R.id.rb_secret:
                break;
            case R.id.tv_submit_btn:
                changeUserinfo("",mEdNickName.getText().toString().trim(),iconpath,"avatar.png",gender,mEdIntroduction.getText().toString().trim());
                break;
        }
    }

    /**
     * 修改头像弹出dialog
     */
    private void showDialog() {
        View view = getLayoutInflater().inflate(R.layout.head_image_choose_dialog, null);
        Dialog mDialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        mDialog.setContentView(view,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = mDialog.getWindow();
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mDialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        mDialog.setCanceledOnTouchOutside(true);
        tvOnClickListenner(mDialog);
        mDialog.show();
    }

    /**
     * 修改头像弹出dialogh后的点击事件
     *
     * @param
     */
    private void tvOnClickListenner(Dialog mDialog) {
        TextView btn_takephoto = (TextView) mDialog.getWindow()
                .findViewById(R.id.btn_takephoto);
        TextView btn_choose_images = (TextView) mDialog.getWindow()
                .findViewById(R.id.btn_choose_images);
        TextView btn_cancel = (TextView) mDialog.getWindow()
                .findViewById(R.id.btn_cancel);

        RxView.clicks(btn_takephoto)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> EasyPhotos.createCamera(this)
                        .setFileProviderAuthority("com.aidingyun.ynlive.FileProvider")
                        .start(REQUEST_CODE_CHOOSE));

        RxView.clicks(btn_choose_images)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> EasyPhotos.createAlbum(this, false, new GlideEngine())
                        .start(REQUEST_CODE_CHOOSE));
        RxView.clicks(btn_cancel).subscribe(o -> mDialog.dismiss());
    }
    Bitmap bitmap;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            ArrayList<String> mSelected = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);
            if (mSelected.size() == 0) {
                ToastUtils.show(this, "头像选择失败!");
                return;
            }
//            iconpath = mSelected.get(0);
            bitmap = BitmapFactory.decodeFile(mSelected.get(0));
            iconpath = "data:image/jpeg;base64,"+Bitmap2StrByBase64(bitmap);
            mAvatar.setImageBitmap(bitmap);

//            mPresenter.compressAvatar(mSelected);
//            Glide.with(this)
//                    .load(mSelected.get(0))
//                    .apply(GlideManger.getInstance().userPicOptions)
//                    .into(mAvatar);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     * @param bit
     * @return
     */
    public String Bitmap2StrByBase64(Bitmap bit){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void changeUserinfo(String label, String username, String icon,String fileName, String gender, String signature) {

        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("fileData",icon);
        reqBody.put("fileName",fileName);
        reqBody.put("label", label);//兴趣爱好，二级分类ID，多个用英文逗号“,”间开
        reqBody.put("username", username);//用户名
//        reqBody.put("icon", icon);//头像
        reqBody.put("gender", gender);//性别 0：保密 1：女 2：男
        reqBody.put("signature", signature);//个人简介/签名
        updateVersionUtils.postByName(Global.CHANGE_USERINFO_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("UpdateUserInfoActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    JSONObject parm = jsonObject.getJSONObject("parm");

                    if (code.equals("success")){
                        ToastUtils.show(UpdateUserInfoActivity.this,jsonObject.getString("msg"));
                        ABaseService.loginInfo.setUsername(parm.getString("username"));
                        ABaseService.loginInfo.setSignature(parm.getString("signature"));
                        ABaseService.loginInfo.setIcon(parm.getString("icon"));
                        LogUtil.e("userinfo'''''''''''''''"+ABaseService.loginInfo.getIcon());
                        Gson gson = new Gson();
                        String jsonInfo=gson.toJson(ABaseService.loginInfo); //将对象转换成Json
                        LogUtil.e("userinfo'''''''''''''''"+jsonInfo);
                        editor.putString("userinfo",jsonInfo);
                        editor.commit();
                        finish();
                    }else{
                        ToastUtils.showError(UpdateUserInfoActivity.this,jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("UpdateUserInfoActivity","updateVersionUtils++++++++++++++++"+error);
            }
        });

    }



}
