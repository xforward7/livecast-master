package com.aidingyun.ynlive.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.inter.IndexRefresh;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.di.component.DaggerSortComponent;
import com.aidingyun.ynlive.di.module.SortModule;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.contract.SortContract;
import com.aidingyun.ynlive.mvp.model.entity.CourseClassificationModel;
import com.aidingyun.ynlive.mvp.presenter.SortPresenter;
import com.aidingyun.ynlive.mvp.ui.activity.search.SearchCourseActivity;
import com.aidingyun.ynlive.mvp.ui.adapter.HomeAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.MenuAdapter;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SortFragment extends BaseFragment<SortPresenter> implements SortContract.View ,IndexRefresh {

    private List<CourseClassificationModel.DataBean> menuList = new ArrayList<>();
    private List<CourseClassificationModel.DataBean.TypeDataBeanX> homeList = new ArrayList<>();
    private List<Integer> showTitle;

    private ListView lv_menu;
    private ListView lv_home;

    private ImageView iv_search_btn;
    private EditText ed_search_course;
    private ImageView iv_delete;
    private TextView tv_cancle;

    private MenuAdapter menuAdapter;
    private HomeAdapter homeAdapter;
    private int currentItem;

    private ImageView iv_banner;
    private UpdateVersionUtils updateVersionUtils = null;

    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    public static SortFragment newInstance() {
        SortFragment fragment = new SortFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerSortComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sortModule(new SortModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        updateVersionUtils = new UpdateVersionUtils();
        preferences = getActivity().getSharedPreferences(getActivity().getPackageName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
        lv_menu = (ListView) view.findViewById(R.id.lv_menu);
        iv_banner = (ImageView) view.findViewById(R.id.iv_banner);
        lv_home = (ListView) view.findViewById(R.id.lv_home);

        iv_search_btn = (ImageView) view.findViewById(R.id.iv_search_btn);
        ed_search_course = (EditText) view.findViewById(R.id.ed_search_course);
        iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
        tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);


        ed_search_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchCourseActivity.start(getActivity(),"");
            }
        });

        showTitle = new ArrayList<>();

        homeAdapter = new HomeAdapter(getActivity(), homeList);
        lv_home.setAdapter(homeAdapter);

        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
//                tv_title.setText(menuList.get(position).getType_name());
//                lv_home.setSelection(showTitle.get(position));
                LoadImage.loadNormalImage(getActivity(),menuList.get(position).getBanner(),iv_banner);
                homeList = menuList.get(position).getType_data();
                homeAdapter = new HomeAdapter(getActivity(), homeList);
                lv_home.setAdapter(homeAdapter);
                homeAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getInfo();

    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }


    /**
     * post请求方式
     */

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
                    editor.putString("category",jsonInfo);
                    editor.commit();

                    for (int i = 0; i < ABaseService.courseClassificationModel.getData().size(); i++) {
                        showTitle.add(i);
                    }
                    menuList = ABaseService.courseClassificationModel.getData();
                    LoadImage.loadNormalImage(getActivity(),menuList.get(0).getBanner(),iv_banner);
                    menuAdapter = new MenuAdapter(getActivity(), menuList);
                    lv_menu.setAdapter(menuAdapter);
                    homeList = menuList.get(0).getType_data();
                    homeAdapter = new HomeAdapter(getActivity(), homeList);
                    lv_home.setAdapter(homeAdapter);
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

    }

    @Override
    public void doUIRefresh() {

    }
}
