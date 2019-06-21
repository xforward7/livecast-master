package com.aidingyun.ynlive.mvp.ui.activity.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aidingyun.core.router.Router;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.greendao.GreenDaoHelper;
import com.aidingyun.ynlive.app.greendao.UserDao;
import com.aidingyun.ynlive.app.greendao.model.User;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.aidingyun.ynlive.mvp.model.entity.ScreenModel;
import com.aidingyun.ynlive.mvp.model.entity.SearchCourseModel;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemAdapterTypeCourse;
import com.aidingyun.ynlive.mvp.ui.adapter.ScreenAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.SearchScreenFirstAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.SearchScreenSecondAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.SearchScreenThirdAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.SearchViewGreenDaoAdapter;
import com.aidingyun.ynlive.mvp.ui.widget.GridViewForScrollView;
import com.google.gson.Gson;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class SearchCourseActivity extends BaseActivity implements View.OnClickListener {
    SmartRefreshLayout refreshLayout;
    private RelativeLayout network_fail;
    RecyclerView recycler_view;
    private ImageView iv_search_btn;
    private EditText ed_search_course;
    private ImageView iv_delete;
    private TextView tv_cancle;


    /**
     * 筛选列表
     */
    ListView screen_listview;
    ListView screen_listviewf;
    ListView screen_listviews;


    LinearLayout plate_linear;
    LinearLayout teach_linear;
    /**
     * 透明布局
     */
    LinearLayout transparent_linear;
    ListView teach_listview;
    ScreenAdapter teachAdapter;

    ListView comprehensive_listview;
    ScreenAdapter screenAdapter;

    private UpdateVersionUtils updateVersionUtils = null;

    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    /**
     * 没有搜索关键字
     */
    private LinearLayout no_input_course;
    /**
     * 有搜索关键字
     */
    private RelativeLayout input_course;

    /**
     *
     */
    private LinearLayout screen_linear;

    /**
     * 筛选界面
     */
    private GridViewForScrollView search_history_grideview,search_hot_grideview;
    private TextView tv_delete_history_btn;
    static String type_name = "";
    GreenDaoHelper helper;
    SearchViewGreenDaoAdapter adapter;
    SearchViewGreenDaoAdapter hotAdapter;
    RecycleItemAdapterTypeCourse courseAdapter;


    SearchScreenFirstAdapter searchScreenFirstAdapter;
    SearchScreenSecondAdapter searchScreenSecondAdapter;
    SearchScreenThirdAdapter searchScreenThirdAdapter;
    UserDao userDao;
    List<User> list;
    List<User> hotlist = new ArrayList<>();
    List<ScreenModel> screenModels = new ArrayList<>();
    List<ScreenModel> teachModels = new ArrayList<>();
    List<SearchCourseModel.ListBean> results = new ArrayList<>();
    List<CourseClassificationModel.DataBean> firstdataBeans = new ArrayList<>();
    List<CourseClassificationModel.DataBean.TypeDataBeanX> scenddataBeans = new ArrayList<>();
    List<CourseClassificationModel.DataBean.TypeDataBeanX.TypeDataBean> thirddataBeans = new ArrayList<>();
    String[] names = {"stetch", "直播", "免费", "PHP", "C", "C++", "NodeJs", "Hexo", "Android"};
    String[] comprehensive = {"综合排序", "好评率排序", "人气排序", "价格最低", "价格最高", "免费"};
    String[] teaching = {"全部", "直播", "人气排序", "点播", "一对一咨询", "付费问答"};


    String type;
    String typeid="";
     String second_typeid="";
     String third_typeid="";

    /**
     *tv_comprehensive_ranking 综合排序
     *tv_plate_ranking  板块分类
     * tv_teaching_method 授课方式
     */
    TextView tv_comprehensive_ranking,tv_plate_ranking,tv_teaching_method;
    TextView item_comprehensive_all,item_plate_all;
    boolean isComprehensive = false;
    boolean isPlate = false;
    boolean isTeach = false;
    public static void start(Context context,String typename) {
        type_name = typename;
        Router.newIntent(context)
                .putString("typename",typename)
                .to(SearchCourseActivity.class)
                .launch();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_search_course;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        updateVersionUtils = new UpdateVersionUtils();
        preferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
        refreshLayout = findViewById(R.id.refreshLayout);
        network_fail = findViewById(R.id.network_fail);
        network_fail.setOnClickListener(this);
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new GridLayoutManager(recycler_view.getContext(), 1, GridLayoutManager.VERTICAL, false));
        comprehensive_listview = findViewById(R.id.comprehensive_listview);
        screen_listview = findViewById(R.id.screen_listview);
        screen_listviewf = findViewById(R.id.screen_listviewf);
        screen_listviews = findViewById(R.id.screen_listviews);
        teach_listview = findViewById(R.id.teach_listview);

        item_comprehensive_all = findViewById(R.id.item_comprehensive_all);
        item_plate_all = findViewById(R.id.item_plate_all);

        firstdataBeans = ABaseService.courseClassificationModel.getData();
//        typeid = firstdataBeans.get(0).getTypeid();
        scenddataBeans = firstdataBeans.get(0).getType_data();
//        second_typeid = scenddataBeans.get(0).getTypeid();
        thirddataBeans = scenddataBeans.get(0).getType_data();
        searchScreenFirstAdapter = new SearchScreenFirstAdapter(this,firstdataBeans);
        screen_listview.setAdapter(searchScreenFirstAdapter);
        searchScreenSecondAdapter = new SearchScreenSecondAdapter(this,scenddataBeans);
        screen_listviewf.setAdapter(searchScreenSecondAdapter);
        searchScreenThirdAdapter = new SearchScreenThirdAdapter(this,thirddataBeans);
        screen_listviews.setAdapter(searchScreenThirdAdapter);
        screen_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchScreenFirstAdapter.setSelectItem(position);
                searchScreenFirstAdapter.notifyDataSetInvalidated();
                scenddataBeans = firstdataBeans.get(position).getType_data();
                typeid = firstdataBeans.get(position).getTypeid();
                item_comprehensive_all.setTextColor(getResources().getColor(R.color.material_black));
                searchScreenSecondAdapter = new SearchScreenSecondAdapter(SearchCourseActivity.this,scenddataBeans);
                screen_listviewf.setAdapter(searchScreenSecondAdapter);
                searchScreenSecondAdapter.notifyDataSetChanged();
                thirddataBeans = scenddataBeans.get(0).getType_data();
                searchScreenThirdAdapter = new SearchScreenThirdAdapter(SearchCourseActivity.this,thirddataBeans);
                screen_listviews.setAdapter(searchScreenThirdAdapter);
                searchScreenThirdAdapter.notifyDataSetInvalidated();
                searchCourseList("","",typeid,second_typeid,third_typeid,firstdataBeans.get(position).getType_name(),"","","","");
            }
        });

        screen_listviewf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchScreenSecondAdapter.setSelectItem(position);
                searchScreenSecondAdapter.notifyDataSetInvalidated();
                thirddataBeans = scenddataBeans.get(position).getType_data();
                second_typeid = scenddataBeans.get(position).getTypeid();
                item_plate_all.setTextColor(getResources().getColor(R.color.material_black));
                searchScreenThirdAdapter = new SearchScreenThirdAdapter(SearchCourseActivity.this,thirddataBeans);
                screen_listviews.setAdapter(searchScreenThirdAdapter);
                searchScreenThirdAdapter.notifyDataSetInvalidated();
                searchCourseList("","",typeid,second_typeid,third_typeid,scenddataBeans.get(position).getType_name(),"","","","");
            }
        });

        screen_listviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchScreenThirdAdapter.setSelectItem(position);
                searchScreenThirdAdapter.notifyDataSetInvalidated();
                third_typeid = thirddataBeans.get(position).getTypeid();
                searchCourseList("","",typeid,second_typeid,third_typeid,thirddataBeans.get(position).getType_name(),"","","","");
            }
        });

        item_comprehensive_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_comprehensive_all.setTextColor(getResources().getColor(R.color.material_red_400));
                searchScreenSecondAdapter.setSelectItem(-1);
                searchScreenThirdAdapter.setSelectItem(-1);
//                item_comprehensive_all.setBackgroundColor(getResources().getColor(R.color.colorLineGray));
//                scenddataBeans.clear();
//                searchScreenSecondAdapter.notifyDataSetChanged();
//                thirddataBeans.clear();
//                searchScreenThirdAdapter.notifyDataSetChanged();
                ByOpenOrClose(plate_linear,screen_linear,teach_linear,transparent_linear);
                searchCourseList("","","","","","","","","","");
            }
        });
        item_plate_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_plate_all.setTextColor(getResources().getColor(R.color.material_red_400));
                searchScreenThirdAdapter.setSelectItem(-1);
//                item_plate_all.setBackgroundColor(getResources().getColor(R.color.colorLineGray));
                ByOpenOrClose(plate_linear,screen_linear,teach_linear,transparent_linear);
//                thirddataBeans.clear();
//                searchScreenThirdAdapter.notifyDataSetChanged();
                searchCourseList("","","","","","","","","","");
            }
        });





        tv_comprehensive_ranking = findViewById(R.id.tv_comprehensive_ranking);
        tv_comprehensive_ranking.setOnClickListener(this);
        tv_plate_ranking = findViewById(R.id.tv_plate_ranking);
        tv_plate_ranking.setOnClickListener(this);
        tv_teaching_method = findViewById(R.id.tv_teaching_method);
        tv_teaching_method.setOnClickListener(this);

        screen_linear = findViewById(R.id.screen_linear);
        plate_linear = findViewById(R.id.plate_linear);
        teach_linear = findViewById(R.id.teach_linear);
        transparent_linear = findViewById(R.id.transparent_linear);
        transparent_linear.setOnClickListener(this);

        iv_search_btn = findViewById(R.id.iv_search_btn);
        ed_search_course = findViewById(R.id.ed_search_course);
        iv_delete = findViewById(R.id.iv_delete);
        tv_cancle = findViewById(R.id.tv_cancle);

        no_input_course = findViewById(R.id.no_input_course);
        input_course = findViewById(R.id.input_course);
        if (!TextUtils.isEmpty(type_name)){
            input_course.setVisibility(View.VISIBLE);
            no_input_course.setVisibility(View.GONE);
            iv_delete.setVisibility(View.VISIBLE);
            ed_search_course.setText(type_name);
            searchCourseList("","","","","",ed_search_course.getText().toString().trim(),"","","","");
        }else {
            iv_delete.setVisibility(View.GONE);
            no_input_course.setVisibility(View.VISIBLE);
            input_course.setVisibility(View.GONE);
            searchCourseList("","","","","","","","","","");
        }

        courseAdapter = new RecycleItemAdapterTypeCourse(this,results);
        recycler_view.setAdapter(courseAdapter);


        comprehensive_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 综合
                 */
                type_name = screenModels.get(position).getType_name();
                tv_comprehensive_ranking.setText(screenModels.get(position).getType_name());
                ByOpenOrClose(screen_linear,plate_linear,teach_linear,transparent_linear);
                screenAdapter.setSelectItem(position);
                screenAdapter.notifyDataSetChanged();
                searchCourseList("","","","","",screenModels.get(position).getType_name(),"","","","");
            }
        });

        teach_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 授课方式
                 */
                type_name = teachModels.get(position).getType_name();
                tv_teaching_method.setText(teachModels.get(position).getType_name());
                teachAdapter.setSelectItem(position);
                teachAdapter.notifyDataSetChanged();
                ByOpenOrClose(teach_linear,plate_linear,screen_linear,transparent_linear);
                searchCourseList("","","","","",teachModels.get(position).getType_name(),"","","","");
            }
        });


        /**
         * 没有搜索关键字
         */
        tv_delete_history_btn = findViewById(R.id.tv_delete_history_btn);
        tv_delete_history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delectAllDB();
            }
        });
        search_history_grideview = findViewById(R.id.search_history_grideview);
        search_hot_grideview = findViewById(R.id.search_hot_grideview);

        iv_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ed_search_course.getText().toString().trim())){
                    insertDB(ed_search_course.getText().toString().trim());
                }
            }
        });

        ed_search_course.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    iv_delete.setVisibility(View.VISIBLE);
                }else {
                    iv_delete.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        ed_search_course.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //在这里可以进行对软键盘Enter键判断,对应的就是EditText在xml设置的
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //在这个方法中处理你需要处理的事件
                    if (!TextUtils.isEmpty(ed_search_course.getText().toString().trim())){
                        insertDB(ed_search_course.getText().toString().trim());
                    }
                    searchCourseList("","","","","",ed_search_course.getText().toString().trim(),"","","","");
                    return true;
                }
                return false;
            }

        });

        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_search_course.setText("");
            }
        });

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_course.setVisibility(View.VISIBLE);
                no_input_course.setVisibility(View.GONE);
            }
        });

        search_history_grideview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ed_search_course.setText(list.get(position).getName());
                no_input_course.setVisibility(View.GONE);
                input_course.setVisibility(View.VISIBLE);
                searchCourseList("","","","","",ed_search_course.getText().toString().trim(),"","","","");
            }
        });

        initDbHelp();
        updateList();
        for (int i = 0; i < names.length; i++) {
            hotlist.add(new User(null,names[i]));
        }
        hotAdapter = new SearchViewGreenDaoAdapter(SearchCourseActivity.this, hotlist);
        search_hot_grideview.setAdapter(hotAdapter);
        hotAdapter.notifyDataSetChanged();
        search_hot_grideview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ed_search_course.setText(hotlist.get(position).getName());
                no_input_course.setVisibility(View.GONE);
                input_course.setVisibility(View.VISIBLE);
                searchCourseList("","","","","",ed_search_course.getText().toString().trim(),"","","","");
            }
        });

    }

    /**
     * 初始化数据库
     */
    private void initDbHelp() {
        helper = new GreenDaoHelper(this);
        userDao = helper.initDao().getUserDao();
    }



    private void insertDB(String name) {
        try {
                //删除已经存在重复的搜索历史
                List<User> list2 = userDao.queryBuilder()
                        .where(UserDao.Properties.Name.eq(name)).build().list();
                userDao.deleteInTx(list2);
                //添加
                if (!name.equals(""))
                    userDao.insert(new User(null, name));
            updateList();
        } catch (Exception e) {
            Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
        }

    }

    //清空数据库
    private void delectAllDB() {
        try {
            userDao.deleteAll();
            list.clear();
            adapter.notifyDataSetChanged();
//            searchGreendaoRl.setVisibility(View.VISIBLE);
            tv_delete_history_btn.setVisibility(View.GONE);
            Toast.makeText(this, "清空数据库", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("exception-----delete", "message:" + e.getMessage() + "");
        }
    }


    /**
     * 初始化adapter，更新list，重新加载列表
     */
    private void updateList() {
        //查询所有
        list = userDao.queryBuilder().list();
        //这里用于判断是否有数据
        if (list.size() == 0) {
//            searchGreendaoRl.setVisibility(View.VISIBLE);
            tv_delete_history_btn.setVisibility(View.GONE);
        } else {
//            searchGreendaoRl.setVisibility(View.GONE);
            tv_delete_history_btn.setVisibility(View.VISIBLE);
        }
        //list倒序排列
        Collections.reverse(list);
        adapter = new SearchViewGreenDaoAdapter(SearchCourseActivity.this, list);
        search_history_grideview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void searchCourseList(String userid
            ,String type,String typeid
            ,String second_typeid,String third_typeid,String keyword
            ,String order_by,String desc
            ,String recommend,String organizationid) {
//        if (TextUtils.isEmpty(keyword)){
//            ToastUtils.showError(SearchCourseActivity.this,"查询内容不能为空!");
//            return;
//        }

        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("userid", userid);
        reqBody.put("type", type);
        reqBody.put("typeid", typeid);
        reqBody.put("second_typeid", second_typeid);
        reqBody.put("third_typeid", third_typeid);
        reqBody.put("keyword", keyword);
        reqBody.put("order_by", order_by);
        reqBody.put("desc", desc);
        reqBody.put("recommend", recommend);
        reqBody.put("organizationid", organizationid);
        updateVersionUtils.postByName(Global.GET_COURSE_SEARCH_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("SearchCourseActivity","updateVersionUtils++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("success")){
                    Gson gson = new Gson();
                    SearchCourseModel searchCourseModel = gson.fromJson(result,SearchCourseModel.class);
                    results = searchCourseModel.getList();
//                        ToastUtils.showError(SearchCourseActivity.this,jsonObject.getString("msg"));
                        courseAdapter = new RecycleItemAdapterTypeCourse(SearchCourseActivity.this,results);
                        recycler_view.setAdapter(courseAdapter);
                    }else{
                        results.clear();
                        courseAdapter.notifyDataSetChanged();
                        ToastUtils.showError(SearchCourseActivity.this,jsonObject.getString("msg"));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_comprehensive_ranking:
                if (screenModels.size()!=0){
                    screenModels.clear();
                }
                for (int i = 0; i < comprehensive.length; i++) {
                    screenModels.add(new ScreenModel(""+i,comprehensive[i]));
                }
                screenAdapter = new ScreenAdapter(this,screenModels);
                comprehensive_listview.setAdapter(screenAdapter);
                ByOpenOrClose(screen_linear,plate_linear,teach_linear,transparent_linear);
                break;
            case R.id.tv_plate_ranking:
                ByOpenOrClose(plate_linear,screen_linear,teach_linear,transparent_linear);
                break;
            case R.id.tv_teaching_method:
                if (teachModels.size()!=0){
                    teachModels.clear();
                }
                for (int i = 0; i < teaching.length; i++) {
                    teachModels.add(new ScreenModel(""+i,teaching[i]));
                }
                teachAdapter = new ScreenAdapter(this,teachModels);
                teach_listview.setAdapter(teachAdapter);
                ByOpenOrClose(teach_linear,screen_linear,plate_linear,transparent_linear);
                break;
            case R.id.transparent_linear:
                CloseAll(teach_linear,screen_linear,plate_linear);
                break;
            case R.id.network_fail:
                searchCourseList("","","","","","","","","","");
                break;
        }
    }

    /**
     * 综合排序
     */
    private void ByOpenOrClose(LinearLayout isVisibility,LinearLayout isGone1,LinearLayout isGone2,LinearLayout isTransparent){
        if (isVisibility.getVisibility()==View.VISIBLE){
            isVisibility.setVisibility(View.GONE);
            isTransparent.setVisibility(View.GONE);
        }else{
            transparent_linear.setVisibility(View.VISIBLE);
            isVisibility.setVisibility(View.VISIBLE);
            isGone1.setVisibility(View.GONE);
            isGone2.setVisibility(View.GONE);
        }
    }

    private void CloseAll(LinearLayout linearLayout,LinearLayout linearLayout1,LinearLayout linearLayout2){
        if (linearLayout.getVisibility()==View.VISIBLE || linearLayout1.getVisibility()==View.VISIBLE || linearLayout2.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
            transparent_linear.setVisibility(View.GONE);
        }else {
            transparent_linear.setVisibility(View.GONE);
        }

    }

}
