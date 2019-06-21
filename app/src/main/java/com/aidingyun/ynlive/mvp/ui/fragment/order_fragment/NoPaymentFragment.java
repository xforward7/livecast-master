package com.aidingyun.ynlive.mvp.ui.fragment.order_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.service.ABaseService;
import com.aidingyun.ynlive.app.utils.LoadImage;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.aidingyun.ynlive.app.utils.UpdateVersionUtils;
import com.aidingyun.ynlive.mvp.contract.Global;
import com.aidingyun.ynlive.mvp.model.entity.CollocationPackageBean;
import com.aidingyun.ynlive.mvp.model.entity.CourseDetailInfo;
import com.aidingyun.ynlive.mvp.model.entity.CourseOrderInfo;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.RecycleItemAdapterCourseList;
import com.aidingyun.ynlive.mvp.ui.adapter.order.CollocationListAdapter;
import com.aidingyun.ynlive.mvp.ui.adapter.order.RecycleItemAdapterOrder;
import com.aidingyun.ynlive.mvp.ui.widget.CircleImageView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vector.update_app.HttpManager;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;


public class NoPaymentFragment extends Fragment {

    private UpdateVersionUtils updateVersionUtils = null;
    private ExpandableListView elv_collocation;
    private List<CollocationPackageBean> collocationList;
    List<CourseOrderInfo.DataBean.ListBean> courseBeans;
    RecycleItemAdapterOrder orderAdapter;
    RecyclerView recyclerView;
    SmartRefreshLayout refreshLayout;
    public static NoPaymentFragment newInstance() {
//        Bundle args = new Bundle();
        NoPaymentFragment fragment = new NoPaymentFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_collocation_fragment, container, false);
        updateVersionUtils = new UpdateVersionUtils();
        elv_collocation = view.findViewById(R.id.elv_collocation);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.VERTICAL, false));
//        initData();
//        network_fail = view.findViewById(R.id.network_fail);//网络连接失败布局
        return view;
    }

    private void initData() {
        collocationList = new ArrayList<>();
        CollocationPackageBean collocation_1 = new CollocationPackageBean();
        CollocationPackageBean collocation_2 = new CollocationPackageBean();

        collocation_1.setTotalPrice(new BigDecimal(897));
        collocation_1.setDiscountFee(new BigDecimal(20));
        collocation_1.setName("818国货套餐3");
        List<CollocationPackageBean.CollocationSkuBean> goodsList_1 = new ArrayList<>();
        goodsList_1.add(new CollocationPackageBean.CollocationSkuBean("Meizu/魅族 魅蓝 note3 全网通 手机 银白 16GB", "http://img11.hqbcdn.com/product/07/0a/070ac7abd57c6d9251d89547f3d62501.jpg"));
        goodsList_1.add(new CollocationPackageBean.CollocationSkuBean("VR PLUS 智能眼镜vr虚拟现实头盔 3D沉浸式 暴风魔镜 vr plus 智能头盔 白色", "http://img15.hqbcdn.com/product/c6/10/c610075082199955a8d5dcf2aa765b17.jpg"));
        collocation_1.setCollocationSkuDoList(goodsList_1);

        collocation_2.setTotalPrice(new BigDecimal(1034));
        collocation_2.setDiscountFee(new BigDecimal(26));
        collocation_2.setName("超值套餐");
        List<CollocationPackageBean.CollocationSkuBean> goodsList_2 = new ArrayList<>();
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("Meizu/魅族 魅蓝 note3 全网通 手机 银白 16GB", "http://img11.hqbcdn.com/product/07/0a/070ac7abd57c6d9251d89547f3d62501.jpg"));
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("Uka/优加 Meizu/魅族 魅蓝 note3全覆盖全屏钢化玻璃膜 白色", "http://img8.hqbcdn.com/product/9c/15/9c15571aa92905ea1edafb0a288f1ebb.jpg"));
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("SanDisk/闪迪 至尊高速MicroSDHC-TF移动存储卡 Class10-48MB/S 升级版 16G", "http://img14.hqbcdn.com/product/29/cd/29cda69f5036b38454b6592f96fde774.jpg"));
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("Huawei/华为 AM116 金属耳机 三键线控耳机 尊爵版", "http://img9.hqbcdn.com/product/0a/90/0a905d9988c91fb0625d9ee44377c8e0.jpg"));
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("Lesimo/梵简 初见系列10000毫安充电宝 手机平板通用 移动电源 黑色", "http://img11.hqbcdn.com/product/67/3a/673ac0343758ce64e97c2d9986cbbef3.jpg"));
        collocation_2.setCollocationSkuDoList(goodsList_2);

        collocationList.add(collocation_1);
        collocationList.add(collocation_2);
        elv_collocation.setAdapter(new CollocationListAdapter(getActivity(), elv_collocation, collocationList));
//        elv_collocation.expandGroup(0);//默认展开第一个
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); //获取参数
        Bundle arguments = getArguments(); //改变值
//        mTv.setText(arguments.getString("tag"));
        getOrderList(ABaseService.loginInfo.getUserid(),"");
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                if (courseBeans.size()!=0){
//                    courseBeans.clear();
//                }
//                getOrderList(ABaseService.loginInfo.getUserid(),"");
//                orderAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });

//        开始下拉
//        mRefreshLayout.setEnableRefresh(true);//启用刷新
//        mRefreshLayout.setEnableLoadmore(true);//启用加载
//        关闭下拉
//        mRefreshLayout.finishRefresh();
//        mRefreshLayout.finishLoadmore();

        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                getOrderList(ABaseService.loginInfo.getUserid(),"");
//                orderAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }
        });
    }


    private void getOrderList(String userid,String query_date) {
        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
        reqBody.put("userid", userid);
        reqBody.put("status", "0");
        reqBody.put("query_date", query_date);
        updateVersionUtils.postByName(Global.GET_ORDER_MANAGER_SERVICE_NAME, reqBody, new HttpManager.Callback() {
            @Override
            public void onResponse(String result) {
                Log.e("NoPaymentFragment","result++++++++++++++++"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("SUCCESS")){
                        Gson gson = new Gson();
                        CourseOrderInfo courseOrderInfo = gson.fromJson(result,CourseOrderInfo.class);
                        courseBeans = courseOrderInfo.getData().getList();
                        orderAdapter = new RecycleItemAdapterOrder(getActivity(),courseBeans);
                        recyclerView.setAdapter(orderAdapter);
                    }else{
                        ToastUtils.showError(getActivity(),jsonObject.getString("msg"));
                    }
                }catch (Exception e){
                    e.getMessage();
                }

            }

            @Override
            public void onError(String error) {
                Log.e("NoPaymentFragment","error++++++++++++++++"+error);
            }
        });

    }

}
