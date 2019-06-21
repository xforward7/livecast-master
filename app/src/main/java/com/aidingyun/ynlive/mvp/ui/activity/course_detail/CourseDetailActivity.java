package com.aidingyun.ynlive.mvp.ui.activity.course_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.greendao.WatchRecordDao;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.WXPayBean;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.model.entity.LiveWatchBean;
import com.aidingyun.ynlive.mvp.ui.activity.account.YNRecordActivity;
import com.aidingyun.ynlive.mvp.ui.activity.download.DownloadCourseActivity;
import com.aidingyun.ynlive.mvp.ui.adapter.MyPagerAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemAdapterCourseList;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemCourseCommentItemAdapter;
import com.aidingyun.ynlive.mvp.ui.fragment.course_detail.CourseCommentListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.course_detail.CourseListFragment;
import com.aidingyun.ynlive.mvp.ui.fragment.course_detail.CourseReferralFragment;
import com.aidingyun.ynlive.mvp.ui.widget.CircleImageView;
import com.aidingyun.ynlive.mvp.ui.widget.DefinitionController;
import com.aidingyun.ynlive.mvp.ui.widget.DefinitionIjkVideoView;
import com.androidkun.xtablayout.XTabLayout;
import com.dueeeke.videoplayer.listener.PlayerEventListener;
import com.dueeeke.videoplayer.player.AbstractPlayer;
import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.google.gson.Gson;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentSkipListMap;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class CourseDetailActivity extends FragmentActivity implements PlayerEventListener {
    private XTabLayout mTabLayout;
    private ViewPager mViewPager;
    static String course_id = "";
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    String isLoad = "";
    LinearLayout tipTextView;
    TextView tv_buy_course_btn;
    CheckBox tv_study;
    CheckBox iv_collect_btn;
    CheckBox check_attention_btn;
    private DefinitionIjkVideoView surface_view;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private int currentPosition = 0;
    private boolean isPlaying;
    private UpdateVersionUtils updateVersionUtils = null;

    ImageView iv_cover;

    LinearLayout course_info;
    TextView tv_title;
    TextView tv_start;
    TextView tv_collent;
    TextView tv_price;
    TextView tv_unit;
    CircleImageView circle_head;
    TextView tv_teach_name;
    TextView tv_technical;
    TextView tv_teach_start;
    TextView tv_context;
    DefinitionController controller;
    LinkedHashMap<String, String> videos = new LinkedHashMap<>();
    LinearLayout course_list;
    RecyclerView recycler_view;

    RelativeLayout Live_lecture_series;
    TextView tv_total_course;
    TextView tv_many_course;
    RecycleItemAdapterCourseList courseAdapter;

    LinearLayout course_comment;
    RecyclerView comment_recycler_view;
    TextView tv_score;

    RecycleItemCourseCommentItemAdapter commentItemAdapter;

    List<CourseDetailInfo.SectionBean> sectionBeans = new ArrayList<>();

    LiveWatchBean liveWatchBean;
    AbstractPlayer player = null;

    List<CourseDetailInfo.SectionBean> commentsectionBeans = new ArrayList<>();
    Timer timer;
    {//构造代码块
        titles.add("课程介绍");
        titles.add("课程列表");
        titles.add("学员评价");
    }


    public static void start(Context context, String courseid) {
        course_id = courseid;
        Router.newIntent(context)
                .putString("courseid",courseid)
                .to(CourseDetailActivity.class)
                .launch();
    }

    final Handler handler = new Handler(){          // handle
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    exitTime ++;
                    if (exitTime == 20) {
                        ToastUtils.show(CourseDetailActivity.this,"插入成功"+msg.obj.toString());
//                        watchRecordDao.insertWatchRecord(liveWatchBean);
                        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
                        reqBody.put("op", "push");
                        reqBody.put("hostid", msg.obj.toString());
                        reqBody.put("sectionid", course_id);
                        updateVersionUtils.postByName(Global.GET_WATCH_HISTORY_SERVICE_NAME, reqBody, null);
                    }
            }
            super.handleMessage(msg);
        }
    };
    /**
     * 视频播放出错回调
     */
    @Override
    public void onError() {

    }
    /**
     * 视频播放完成回调
     */
    @Override
    public void onCompletion() {

        tipTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onInfo(int what, int extra) {

    }
    /**
     * 视频缓冲完毕，准备开始播放时回调
     */
    @Override
    public void onPrepared() {

    }

    @Override
    public void onVideoSizeChanged(int width, int height) {

    }

    public class MyThread implements Runnable {
        String host_id = "";
        public MyThread(String userid) {
            host_id = userid;
        }      // thread
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);     // sleep 1000ms
                    Message message = new Message();
                    message.what = 1;
                    message.obj = host_id;
                    handler.sendMessage(message);
                } catch (Exception e) {
                }
            }
        }
    }

    private long exitTime = 0;

    private void insert(String userid,String sectionid){
        timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
                        reqBody.put("op", "push");
                        reqBody.put("hostid", userid);
                        reqBody.put("sectionid", sectionid);
                        updateVersionUtils.postByName(Global.GET_WATCH_HISTORY_SERVICE_NAME, reqBody, new HttpManager.Callback() {
                            @Override
                            public void onResponse(String result) {
                                Log.e("CourseDetailActivity","result++++++++++++++++"+result);
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    String code = jsonObject.getString("code");
                                    if (code.equals("success")) {

                                    }
                                }catch (Exception e){
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
        };
        timer.schedule(task,20000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        updateVersionUtils = new UpdateVersionUtils();
        mTabLayout = findViewById(R.id.xTablayout);
        mViewPager = findViewById(R.id.view_pager);
        surface_view = findViewById(R.id.surface_view);
        tv_buy_course_btn = findViewById(R.id.tv_buy_course_btn);
        tv_study = findViewById(R.id.tv_study);
        iv_collect_btn = findViewById(R.id.iv_collect_btn);
        check_attention_btn = findViewById(R.id.check_attention_btn);
        tipTextView = findViewById(R.id.tipTextView);
        iv_cover = findViewById(R.id.iv_cover);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        ((ImageView)findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.iv_download)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadCourseActivity.start(CourseDetailActivity.this,course_id);
            }
        });
        ((TextView)findViewById(R.id.tv_title)).setText("我的订单");
        player = new IjkPlayer(this);
        controller = new DefinitionController(this);
        surface_view.setPlayerConfig(new PlayerConfig.Builder()
                .setCustomMediaPlayer(new IjkPlayer(this) {
                    @Override
                    public void setOptions() {
                        //精准seek
                        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
                    }
                })
                .autoRotate()//自动旋转屏幕
                .build());
//        videos.put("标清", "http://mov.bn.netease.com/open-movie/nos/flv/2017/07/24/SCP786QON_sd.flv");
//        videos.put("高清", "http://mov.bn.netease.com/open-movie/nos/flv/2017/07/24/SCP786QON_hd.flv");
//        videos.put("超清", "http://mov.bn.netease.com/open-movie/nos/flv/2017/07/24/SCP786QON_shd.flv");
//        getNetVideoBitmap();
//        surface_view.setDefinitionVideos(videos);
//        surface_view.setVideoController(controller);
//        surface_view.setTitle("韩雪：积极的悲观主义者");
//        surface_view.start();
        searchCourseDetail(course_id);
        tipTextView.setVisibility(View.GONE);//下节课程介绍
        iv_collect_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    collectOrCancle("lesson_add",course_id);
                }else{
                    collectOrCancle("lesson_remove",course_id);
                }
            }
        });
        check_attention_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Map<String, String> reqBody = new ConcurrentSkipListMap<>();
                    reqBody.put("op", "lesson_add");
                    reqBody.put("courseid", course_id);
                    updateVersionUtils.postByName(Global.GET_COLLECT_SERVICE_NAME, reqBody, new HttpManager.Callback() {
                        @Override
                        public void onResponse(String result) {
                            Log.e("CourseDetailActivity","result++++++++++++++++"+result);
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                String code = jsonObject.getString("code");
                                if (code.equals("success")) {
                                    check_attention_btn.setChecked(true);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(String error) {

                        }
                    });
                }else{
                    Map<String, String> reqBody = new ConcurrentSkipListMap<>();
                    reqBody.put("op", "lesson_remove");
                    reqBody.put("courseid", course_id);
                    updateVersionUtils.postByName(Global.GET_COLLECT_SERVICE_NAME, reqBody, new HttpManager.Callback() {
                        @Override
                        public void onResponse(String result) {
                            Log.e("CourseDetailActivity","result++++++++++++++++"+result);
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                String code = jsonObject.getString("code");
                                if (code.equals("success")) {
                                    check_attention_btn.setChecked(false);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(String error) {

                        }
                    });
                }
            }
        });
        tv_study.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){//
                    studyCourse("study",course_id);
                }
            }
        });

        tv_buy_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseDetailActivity.this,ConfirmOrderActivity.class));
            }
        });
//        insert();
        course_info = findViewById(R.id.course_info);

        tv_title = findViewById(R.id.tv_title);
        tv_start = findViewById(R.id.tv_start);
        tv_collent = findViewById(R.id.tv_collent);
        tv_price = findViewById(R.id.tv_price);
        tv_unit = findViewById(R.id.tv_unit);
        circle_head = findViewById(R.id.circle_head);
        tv_teach_name = findViewById(R.id.tv_teach_name);
        tv_technical = findViewById(R.id.tv_technical);
        tv_teach_start = findViewById(R.id.tv_teach_start);
        tv_context = findViewById(R.id.tv_context);


        course_list = findViewById(R.id.course_list);
        recycler_view = findViewById(R.id.recycler_view);
        Live_lecture_series = findViewById(R.id.Live_lecture_series);
        tv_total_course = findViewById(R.id.tv_total_course);
        tv_many_course = findViewById(R.id.tv_many_course);
        recycler_view.setLayoutManager(new GridLayoutManager(recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));


        course_comment = findViewById(R.id.course_comment);
        tv_score = findViewById(R.id.tv_score);
        comment_recycler_view = findViewById(R.id.comment_recycler_view);
        comment_recycler_view.setLayoutManager(new GridLayoutManager(comment_recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));


        mTabLayout.addTab(mTabLayout.newTab().setText("课程介绍"));
        mTabLayout.addTab(mTabLayout.newTab().setText("课程列表"));
        mTabLayout.addTab(mTabLayout.newTab().setText("学员评价"));
        // 设置监听
        mTabLayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                String text = (String) tab.getText();
//                ToastUtils.show(CourseDetailActivity.this,"选中" + text);
                if (text.equals("课程介绍")){
                    course_info.setVisibility(View.VISIBLE);
                    course_list.setVisibility(View.GONE);
                    course_comment.setVisibility(View.GONE);
                }else if (text.equals("课程列表")){
                    course_info.setVisibility(View.GONE);
                    course_list.setVisibility(View.VISIBLE);
                    course_comment.setVisibility(View.GONE);
                }else if (text.equals("学员评价")){
                    course_info.setVisibility(View.GONE);
                    course_list.setVisibility(View.GONE);
                    course_comment.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
                String text = (String) tab.getText();
//                ToastUtils.show(CourseDetailActivity.this,"不要" + text + "了");
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
                String text = (String) tab.getText();
//                ToastUtils.show(CourseDetailActivity.this,"再次点击" + text);
            }
        });

    }



    public Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
            iv_cover.setImageBitmap(bitmap);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    private void initViewPager() {
         fragmentList.add(CourseReferralFragment.newInstance(isLoad));//
        fragmentList.add(CourseListFragment.newInstance(isLoad));//
        fragmentList.add(CourseCommentListFragment.newInstance("","",""));//
//        fragmentList.add(OtherFragment.newInstance());//其他

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
//        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
//        mViewPager.setOffscreenPageLimit(titles.size());
//        mViewPager.setAdapter(adapter);
//        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
//        mTabLayout.getTabAt(0).select();
        // 这样可以自定义tab的布局与内容了

    }

    private void studyCourse(String op, String courseid) {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("op", op);
        reqBody.put("courseid", courseid);
        updateVersionUtils.postByName(Global.GET_COURSE_STUDY_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("CourseDetailActivity","result++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")) {
                        ToastUtils.show(CourseDetailActivity.this,"成功加入学习!");
                            tv_study.setChecked(true);
                            tv_study.setText("已学习");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void collectOrCancle(String op,String courseid){
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("op", op);
        reqBody.put("courseid", courseid);
        updateVersionUtils.postByName(Global.GET_COLLECT_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("CourseDetailActivity","result++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")) {
                        if (op.equals("lesson_add")){
                            iv_collect_btn.setChecked(true);
                            iv_collect_btn.setText("已收藏");
                        }else if (op.equals("lesson_remove")){
                            iv_collect_btn.setChecked(false);
                            iv_collect_btn.setText("收藏");
                        }else if (op.equals("study")){
                            tv_study.setChecked(true);
                            tv_study.setText("已学习");
                        }

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        surface_view.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        surface_view.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        surface_view.release();
        if (timer != null) {
            timer.cancel();
        }
    }


    @Override
    public void onBackPressed() {
        if (!surface_view.onBackPressed()) {
            super.onBackPressed();
        }
    }

    private WatchRecordDao watchRecordDao;
    private void searchCourseDetail(String courseid) {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("courseid", courseid);
        updateVersionUtils.postByName(Global.GET_COURSE_DETAIL_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("SearchCourseActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
//                        watchRecordDao = new WatchRecordDao(CourseDetailActivity.this);
                        Gson gson = new Gson();
                        ABaseService.courseDetailInfo = gson.fromJson(result,CourseDetailInfo.class);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        liveWatchBean = gson.fromJson(jsonObject1.toString(),LiveWatchBean.class);
                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        liveWatchBean.setTime(sDateFormat.format(new java.util.Date()));

                        if (ABaseService.courseDetailInfo.getData().getCollectioned()==1){
//                            iv_collect_btn.setButtonDrawable(R.drawable.icon_collect_celected);
                            iv_collect_btn.setText("已收藏");
                            iv_collect_btn.setChecked(true);
                        }

                        if (ABaseService.courseDetailInfo.getData().getLessoned()==1){
                            tv_study.setChecked(true);
                            tv_study.setText("已学习");
                        }

                        if (ABaseService.courseDetailInfo.getData().getPaid()==1){
                            tv_buy_course_btn.setEnabled(false);
                            tv_buy_course_btn.setBackgroundResource(R.color.material_grey_400);
                            tv_buy_course_btn.setText("已购买");
                        }

                        tv_title.setText(ABaseService.courseDetailInfo.getData().getTitle());
                        tv_start.setText(ABaseService.courseDetailInfo.getData().getScore());
                        tv_collent.setText(ABaseService.courseDetailInfo.getData().getCollection() + "人在学");
                        if (ABaseService.courseDetailInfo.getData().getAllow().equals("0")) {
                            tv_price.setText("免费");
                            tv_unit.setVisibility(View.GONE);
                        } else if (ABaseService.courseDetailInfo.getData().getAllow().equals("1")) {
                            tv_price.setText("加密");
                            tv_unit.setVisibility(View.GONE);
                        } else if (ABaseService.courseDetailInfo.getData().getAllow().equals("2")) {
                            tv_price.setText(ABaseService.courseDetailInfo.getData().getPrice());
                        }
                        if (ABaseService.courseDetailInfo.getData().getCourse_type()==0) {
                            LoadImage.loadCircleImage(CourseDetailActivity.this, ABaseService.courseDetailInfo.getData().getPhoto(), circle_head);
                            tv_teach_name.setText(ABaseService.courseDetailInfo.getHost_data().getReal_name());
                            tv_technical.setText(ABaseService.courseDetailInfo.getHost_data().getProfessional());
                            tv_teach_start.setText(ABaseService.courseDetailInfo.getHost_data().getScore());
                            tv_context.setText(ABaseService.courseDetailInfo.getHost_data().getIntro());
                        }else {
                            LoadImage.loadCircleImage(CourseDetailActivity.this, ABaseService.courseDetailInfo.getOrganization_data().getOrganization_icon(), circle_head);
                            tv_teach_name.setText(ABaseService.courseDetailInfo.getOrganization_data().getName());
                            tv_teach_start.setText(ABaseService.courseDetailInfo.getOrganization_data().getScore());
                            tv_context.setText(ABaseService.courseDetailInfo.getOrganization_data().getOrganization_summary());
                        }


                        if (ABaseService.courseDetailInfo!=null){
                            sectionBeans = ABaseService.courseDetailInfo.getSection();
                        }
                        Live_lecture_series.setVisibility(ABaseService.courseDetailInfo.getData().getType().equals("0") ? View.VISIBLE:View.GONE);
                        courseAdapter = new RecycleItemAdapterCourseList(CourseDetailActivity.this,sectionBeans);
                        recycler_view.setAdapter(courseAdapter);
                        courseAdapter.setOnItemClickListener(new RecycleItemAdapterCourseList.MyItemClickListener() {
                            @Override
                            public void onItemClick(View view, int postion) {
                                surface_view.release();
                                surface_view.setUrl(sectionBeans.get(postion).getVideo());
                                surface_view.setVideoController(controller);
                                surface_view.setPlayerConfig(new PlayerConfig.Builder()
                                        .autoRotate()//自动旋转屏幕
//                                      .usingSurfaceView()//使用SurfaceView
                                        .setCustomMediaPlayer(player)
//                                      .setLooping()
                                        .build());
                                surface_view.start();

                                insert(sectionBeans.get(postion).getUserid(),sectionBeans.get(postion).getSectionid());
                            }
                        });

                        if (ABaseService.courseDetailInfo!=null){
                            sectionBeans = ABaseService.courseDetailInfo.getSection();
                        }
                        tv_score.setText(ABaseService.courseDetailInfo.getData().getScore());
                        commentItemAdapter = new RecycleItemCourseCommentItemAdapter(CourseDetailActivity.this,sectionBeans);
                        comment_recycler_view.setAdapter(commentItemAdapter);
//                        new Thread(new MyThread()).start();
                        isLoad = "true";
//                        initViewPager();
                        if (ABaseService.courseDetailInfo.getData().getAllow().equals("0")){
                            tv_buy_course_btn.setEnabled(false);
                            tv_buy_course_btn.setBackgroundResource(R.color.material_grey_400);
                        }
//                        surface_view.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
//                        surface_view.setVideoURI(Uri.parse("http://mov.bn.netease.com/open-movie/nos/flv/2017/01/03/SC8U8K7BC_hd.flv"));
//                        surface_view.start();
//                        rlChannels.setLayoutManager(new GridLayoutManager(rlChannels.getContext(), 2, GridLayoutManager.VERTICAL, false));
//                        adapter = new RecycleAdapter(getActivity(),ABaseService.homeCourseModel);
//                        rlChannels.setAdapter(adapter);
                    }else{
                        ToastUtils.showError(CourseDetailActivity.this,jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("SearchCourseActivity","updateVersionUtils++++++++++++++++"+error);
            }
        });

    }
}
