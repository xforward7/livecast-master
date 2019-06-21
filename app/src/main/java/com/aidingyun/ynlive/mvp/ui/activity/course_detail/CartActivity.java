//package com.fresheasy.com;
//
//import java.lang.reflect.Type;
//import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.VolleyError;
//import com.appkefu.lib.service.KFMainService;
//import com.baoyz.swipemenulistview.SwipeMenuListView;
//import com.fresheasy.com.adapter.GridViewAdapter;
//import com.fresheasy.com.bean.CartGoods;
//import com.fresheasy.com.bean.CartGoods.goods_list;
//import com.fresheasy.com.bean.GoodsInfo;
//import com.fresheasy.com.bean.IBtnCallListener;
//import com.fresheasy.com.util.Constants;
//import com.fresheasy.com.util.Constants.Action;
//import com.fresheasy.com.util.Constants.Urls;
//import com.fresheasy.com.util.NativeHttpUtil;
//import com.fresheasy.com.util.NetWorkManager;
//import com.fresheasy.com.util.NumberUtils;
//import com.fresheasy.com.util.SPUtils;
//import com.fresheasy.com.util.StringUtils;
//import com.fresheasy.com.util.ThreadPoolManager;
//import com.fresheasy.com.view.MyListView;
//import com.fresheasy.com.view.SVProgressHUD;
//import com.google.gson.reflect.TypeToken;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
//import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
//import com.lib.json.JSONUtils;
//import com.lib.volley.HTTPUtils;
//import com.lib.volley.VolleyListener;
//import com.nostra13.universalimageloader.core.ImageLoader;
//
//public class CartActivity extends Activity implements OnClickListener{
//	private View mViewLogin;
//	private TextView mTvPrice; // 合计
//	private TextView mTvTotal; // 总额
//	private TextView mTvCount; // 选中商品数
//	private CheckBox mBtnCheckAll;
//	private CheckBox mBtnCheckAllEdit;
//	private SwipeMenuListView mListView;
//	private TextView mTvEdit;
//	private View layoutEditBar;
//	private View layoutPayBar;
//	private TextView mTvAddUp;
//	IBtnCallListener btnCallListener;
//
//	private double price; // 总价
//
//	private int num; // 选中的商品数
//
//	private boolean isEdit; // 是否正在编辑
//
//	private LinearLayout ll_cart;
//
//	private LinearLayout ll_price_all;
//
//	private String commodityresult;
//
//	private String CheckDeleteResult;
//
//	List<GoodsInfo> reCommodities = new ArrayList<GoodsInfo>();
//
//	private HashMap<String, Boolean> inCartMap = new HashMap<String, Boolean>();// 用于存放选中的项
//
//	GoodsInfo recommendCommodity;
//
//	goods_list inCart;
//
//	private String AddCommodityResult;
//
//	private GridView gridView_hotsell_commodity;
//
//	public static GridViewAdapter gridviewadapter;
//
//	private TextView compile;//编辑
//
//	private MyAdapter adapter;
//
//	private List<CartGoods> menuList = new ArrayList<CartGoods>();
//
//	private List<goods_list> categorychild;
//
//	int categorychildsize=0;
//
//	String token;
//
//	private PullToRefreshScrollView mPtrScrollView;
//
//	protected ThreadPoolManager threadPoolManager;
//
//	private NetWorkManager netWorkManager;
//	private static boolean isLogined;
//	private LinearLayout no_network;
//	View inflate = null;
//
//	String car_id = null;
//
//	RelativeLayout RelativeLayout1;
//
////	private RelativeLayout footer;
//
//	MainActivity activity = new MainActivity();
//
//	GroupViewHolder viewHolder = null;
//
//	int count = 0;
//
//
//	private OnCheckedChangeListener checkAllListener = new OnCheckedChangeListener() {
//
//		@Override
//		public void onCheckedChanged(CompoundButton buttonView,
//									 boolean isChecked) {
//			mBtnCheckAll.setChecked(isChecked);
//			mBtnCheckAllEdit.setChecked(isChecked);
//			if (isChecked) {
//				checkAll();
////				if (inCartMap.size()==0) {
////					mBtnCheckAll.setChecked(false);
////					mBtnCheckAllEdit.setChecked(false);
////				}
////				if (!isEdit) {
////					if (inCartMap.size()==0) {
////						mBtnCheckAll.setChecked(false);
////						mBtnCheckAllEdit.setChecked(false);
////
////					}
////				}
//			} else {
//				inCartMap.clear();
//			}
//			notifyCheckedChanged();
//			adapter.notifyDataSetChanged();
//		}
//
//	};
//	/**
//	 * 全选，将数据加入inCartMap
//	 */
//	private void checkAll() {
//		for (int i = 0; i < menuList.size(); i++) {
//			categorychild = menuList.get(i).getGoods_list();
//			for (int j = 0; j < categorychild.size(); j++) {
//				inCart = categorychild.get(j);
//				if (isEdit) {
//					inCartMap.put(categorychild.get(j).getRec_id(), true);
//				}else {
//					if (inCart.getIs_exit() == 1) {//未过期
//						if (inCart.getAdded().equals("1")) {//已下架
//							inCartMap.put(categorychild.get(j).getRec_id(), true);
//						}else {
//							continue;
//						}
//					}else {
//						continue;
//					}
//				}
//			}
//		}
//		if (!isEdit) {
//			if (inCartMap.size()==0) {
//				mBtnCheckAll.setChecked(false);
//				mBtnCheckAllEdit.setChecked(false);
//				SVProgressHUD.showInfoWithStatus(CartActivity.this, "购物车商品已过期!\n请重新添加商品!", SVProgressHUD.SVProgressHUDMaskType.None);
//			}
//
//		}
//
//	}
//
//	/**
//	 * 选中商品改变
//	 */
//	private void notifyCheckedChanged() {
//		price = 0;
//		num = 0;
//		for (int i = 0; i < menuList.size(); i++) {
//			categorychild = menuList.get(i).getGoods_list();
//			for (int j = 0; j < categorychild.size(); j++) {
//				Boolean isChecked = inCartMap.get(categorychild.get(j).getRec_id());
//				if (isChecked != null && isChecked) {
//					goods_list inCart = categorychild.get(j);
//					num += inCart.getGoods_number();
//					price += inCart.getGoods_price() * inCart.getGoods_number();
//				}
//			}
//		}
//		mTvPrice.setText(NumberUtils.formatPrice(price));
//		mTvTotal.setText("总额：" + NumberUtils.formatPrice(price));
//		mTvCount.setText("(" + num + ")");
//		mTvAddUp.setText("小计：" + NumberUtils.formatPrice(price));
//	}
//
//	/**
//	 * 通知更新购物车商品数量
//	 */
//	private void notifyInCartNumChanged() {
////		progressHUD.show();
//		// 通知主页刷新购物车商品数
//		Intent intent = new Intent();
//		int allNum = 0;
//		for (int i = 0; i < menuList.size(); i++) {
//			categorychild = menuList.get(i).getGoods_list();
//			for (int j = 0; j < categorychild.size(); j++) {
//				goods_list inCart = categorychild.get(j);
////					if (inCart.getIs_exit() == 1) {//排除过期商品
//				allNum += inCart.getGoods_number();
////					}else {
////						continue;
////					}
//
//			}
//
//		}
//		intent.putExtra("all_num", allNum);
//		intent.setAction(Constants.BROADCAST_FILTER.FILTER_CODE);
//		intent.putExtra(Constants.BROADCAST_FILTER.EXTRA_CODE,
//				Constants.INTENT_KEY.REFRESH_INCART);
//		sendBroadcast(intent);
//	}
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		isLogined = SPUtils.getLoinState(CartActivity.this, false);
//		token = SPUtils.getToken(CartActivity.this, null);
//		setContentView(R.layout.activity_expandable_listview);
//		mViewLogin = findViewById(R.id.layout_login_cart);
//		netWorkManager = NetWorkManager.getInstance(CartActivity.this);
//		mPtrScrollView = (PullToRefreshScrollView) findViewById(R.id.ptrScrollView_home);
////		mPtrScrollView.smoothScrollTo(0, 0);
//		no_network = (LinearLayout) findViewById(R.id.no_network);
//		layoutPayBar = findViewById(R.id.layout_pay_bar);
//		if(netWorkManager.isNetWorkAvailable(CartActivity.this)){//开启网络
//			if (isLogined) {
//				mViewLogin.setVisibility(View.GONE);
//				init();
//				initModle();
//				setOnListener();
//				mPtrScrollView.setVisibility(View.VISIBLE);
//				no_network.setVisibility(View.GONE);
//				if (reCommodities!=null) {
//					reCommodities.clear();
//					new GetListTask().execute();
//				}else {
//					new GetListTask().execute();
//				}
//				mPtrScrollView
//						.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
//							public void onRefresh(
//									PullToRefreshBase<ScrollView> refreshView) {
////						reCommodities.clear();
////						new GetListTask().execute();
//								if (isLogined) {
//									mViewLogin.setVisibility(View.GONE);
//									menuList.clear();
//									initData();
//								}
//							}
//						});
//				mPtrScrollView.smoothScrollTo(0, 0);
//
//			}else {
//				layoutPayBar.setVisibility(View.GONE);
//			}
//
//		}else {//关闭网络通道
//			layoutPayBar.setVisibility(View.GONE);
//			mPtrScrollView.setVisibility(View.GONE);
//			no_network.setVisibility(View.VISIBLE);
//		}
//
//	}
//
//	/**
//	 * 解析JsonArray获取菜单数据
//	 */
//	private void initData() {
//		// 异步下载JSON
//		HTTPUtils.getVolley(CartActivity.this, Urls.USER_CAR_LIST_URL+token,
//				new VolleyListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						Toast.makeText(CartActivity.this, "连接错误",2000).show();
//					}
//
//					@Override
//					public void onResponse(String arg0) {
//						Type type = new TypeToken<ArrayList<CartGoods>>() {
//						}.getType();
//						if (type!=null) {
//							ArrayList<CartGoods> list;
//							try {
//								list = JSONUtils.parseJSONArray(arg0, type);
//								menuList.addAll(list);
//								adapter.notifyDataSetChanged();
//								notifyInCartNumChanged();
//								mPtrScrollView.onRefreshComplete();// 关闭刷新动画
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
////							        Toast.makeText(getActivity(), "error"+menuList.size(),2000).show();
//							if (menuList.size() == 0) {
//								ll_cart.setVisibility(View.VISIBLE);
//								mListView.setVisibility(View.GONE);
////										mTvEdit.setVisibility(View.GONE);
//								layoutEditBar.setVisibility(View.GONE);
//								layoutPayBar.setVisibility(View.GONE);
//								isEdit = false;
//							} else {
//								mBtnCheckAll.setChecked(true);
//								ll_cart.setVisibility(View.GONE);
////										mTvEdit.setVisibility(View.VISIBLE);
//								mListView.setVisibility(View.VISIBLE);
//								if (isEdit) {
//									layoutEditBar.setVisibility(View.VISIBLE);
//								} else {
//									layoutPayBar.setVisibility(View.VISIBLE);
//								}
//							}
//						}
//
//					}
//				});
//	}
//
//	private void setOnListener() {
//		mTvEdit.setOnClickListener(this);
//		// 防止点击穿透
//		layoutEditBar.setOnClickListener(this);
//		layoutPayBar.setOnClickListener(this);
//		mBtnCheckAll.setOnCheckedChangeListener(checkAllListener);
//		mBtnCheckAllEdit.setOnCheckedChangeListener(checkAllListener);
//		findViewById(R.id.btn_login_cart).setOnClickListener(this);
//		findViewById(R.id.btn_delete).setOnClickListener(this);
//		findViewById(R.id.btn_pay).setOnClickListener(this);
//		findViewById(R.id.tv_goShop).setOnClickListener(this);
//	}
//
//	private void init() {
//		((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				CartActivity.this.finish();
//
//			}
//		});
////		footer = (RelativeLayout) findViewById(R.id.footer);
//		threadPoolManager = ThreadPoolManager.getInstance();
//		mPtrScrollView.setVisibility(View.VISIBLE);
//		ll_cart = (LinearLayout) findViewById(R.id.ll_cart);
//		mTvEdit = (TextView) findViewById(R.id.tv_edit_cart);
//		mTvPrice = (TextView) findViewById(R.id.tv_price);
//		mTvTotal = (TextView) findViewById(R.id.tv_total);
//		mTvCount = (TextView) findViewById(R.id.tv_count);
//		mBtnCheckAll = (CheckBox) findViewById(R.id.btn_check_all);
//		mBtnCheckAllEdit = (CheckBox) findViewById(R.id.btn_check_all_deit);
//		layoutEditBar = findViewById(R.id.layout_edit_bar);
//		gridView_hotsell_commodity = (GridView) findViewById(R.id.gridView_hotsell_commodity);
//		try {
//			gridviewadapter=new GridViewAdapter(CartActivity.this, reCommodities);
//			gridView_hotsell_commodity.setAdapter(gridviewadapter);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		gridView_hotsell_commodity.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//									int position, long id) {
//				Intent intent = new Intent(CartActivity.this,DetailActivity.class);
//				intent.putExtra(Constants.INTENT_KEY.INFO_TO_DETAIL, reCommodities.get(position).getGoodsId());
//				startActivity(intent);
//			}
//		});
//
//	}
//
//	@Override
//	public void onStart() {
//		super.onStart();
//		IntentFilter intentFilter = new IntentFilter();
//		intentFilter.addAction(Action.GET_USERINFO_STATE);
//		intentFilter.addAction(Action.REFLUSH_CODE);
//		registerReceiver(mXmppreceiver, intentFilter);
//
//	}
//
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		if (isLogined) {
//			mViewLogin.setVisibility(View.GONE);
//			if (menuList!=null) {
//				menuList.clear();
//				initData();
//			}else {
//				initData();
//			}
//		}else {
//			mViewLogin.setVisibility(View.VISIBLE);
//		}
//	}
//
//	@Override
//	public void onStop() {
//		super.onStop();
//		unregisterReceiver(mXmppreceiver);
//	}
//
//	private BroadcastReceiver mXmppreceiver = new BroadcastReceiver() {
//		@SuppressWarnings("static-access")
//		public void onReceive(Context context, Intent intent) {
//			String action = intent.getAction();
//			if (action.equals(Action.GET_USERINFO_STATE)) {
//				if(netWorkManager.isNetWorkAvailable(CartActivity.this)){
//					isLogined = intent.getBooleanExtra("islogin", false);
//					SPUtils.saveLoinState(CartActivity.this, isLogined);
//					mViewLogin.setVisibility(View.GONE);
//				}
//
//			}else if (action.equals(Action.REFLUSH_CODE)) {
//				if (menuList!=null) {
//					menuList.clear();
//					initData();
//				}
//
//			}
//
//
//		}
//	};
//
//	/**
//	 * 获取数字
//	 *
//	 * @param tvNum
//	 * @return
//	 */
//	private int getNum(TextView tvNum) {
//		String num = tvNum.getText().toString().trim();
//		return Integer.valueOf(num);
//
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//			case R.id.btn_login_cart: // 登录
//				gotoLogin();
//				break;
//			case R.id.btn_delete: // 删除
//				deleteInCart();
//				break;
//			case R.id.tv_edit_cart: // 编辑
//				editInCart();
//				break;
//			case R.id.btn_pay: // 结算
//				pay();
//				break;
//			case R.id.tv_goShop: // 去秒杀
//				Intent intent=new Intent(CartActivity.this,MainActivity.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//				startActivity(intent);
//				finish();
//				break;
//
//			default:
//				break;
//		}
//	}
//
//	/**
//	 * 编辑
//	 */
//	private void editInCart() {
//		isEdit = !isEdit;
//		if (isEdit) {
//			isEdit = true;
//			mTvEdit.setText("完成");
//			layoutPayBar.setVisibility(View.GONE);
//			layoutEditBar.setVisibility(View.VISIBLE);
//		} else {
//			isEdit = false;
//			mTvEdit.setText("编辑");
//			if (menuList.size()==0) {
//				layoutPayBar.setVisibility(View.GONE);
//			}else {
//				layoutPayBar.setVisibility(View.VISIBLE);
//			}
//			layoutEditBar.setVisibility(View.GONE);
//		}
//		notifyCheckedChanged();
//		adapter.notifyDataSetChanged();
//	}
//
//	private Handler deletecommodityHandler = new Handler() {
//		public void handleMessage(Message msg) {
//			if (TextUtils.isEmpty(CheckDeleteResult)) {
//			} else {
//
//				try {
//					JSONObject jsonObject2 =new JSONObject(CheckDeleteResult);
//					int code = jsonObject2.getInt("code");
//					if (code == 200) {
//						JSONObject dataObj=jsonObject2.getJSONObject("data");
//						categorychild.remove(inCart);
//						inCartMap.clear();
//						mBtnCheckAll.setChecked(false);
//						mBtnCheckAllEdit.setChecked(false);
//						notifyCheckedChanged();
//						notifyInCartNumChanged();
//						adapter.notifyDataSetChanged();
//						menuList.clear();
//						initData();
//						mTvEdit.setText("编辑");
//						isEdit = false;
//						String message = dataObj.getString("msg");
//						SVProgressHUD.showInfoWithStatus(CartActivity.this, message, SVProgressHUD.SVProgressHUDMaskType.None);
//					}else {
//						String error = jsonObject2.getString("error");
//						SVProgressHUD.showInfoWithStatus(CartActivity.this, error, SVProgressHUD.SVProgressHUDMaskType.None);
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//
//
//			}
//		};
//	};
//
//	/**
//	 * 删除选中项
//	 */
//	private void deleteInCart() {
//		int count = 0;
//		if (inCartMap.size() == 0) {
//			Toast.makeText(CartActivity.this, "您还没有选择商品哦！",2000).show();
//			return;
//		}
//		for (int i = 0; i < menuList.size(); i++) {
//			categorychild = menuList.get(i).getGoods_list();
//			for (int j = 0; j < categorychild.size(); j++) {
//				inCart = categorychild.get(j);
//				Boolean isChecked = inCartMap.get(inCart.getRec_id());
//				if (isChecked != null && isChecked) {
//					count++;
//					if (count == 1) {
//						car_id = inCart.getRec_id();
//					}
//					if (count>1) {
//						car_id += ","+inCart.getRec_id();
//					}
//				}
//			}
//		}
//		threadPoolManager.addTask(new Runnable() {
//			@Override
//			public void run() {
//				HashMap<String, String> param = new HashMap<String, String>();
//				param.put("key", token);
//				param.put("rec_id", car_id);
//				CheckDeleteResult = NativeHttpUtil.post(Urls.USER_CAR_DELETE_URL, param);
//				deletecommodityHandler.sendEmptyMessage(1);
//			}
//		});
//
//
////			init();
//	}
//
//	/**
//	 * 跳转到登录界面
//	 */
//	private void gotoLogin() {
//		Intent intent = new Intent(CartActivity.this, ActivityLogin.class);
//		startActivityForResult(intent, Constants.INTENT_KEY.LOGIN_REQUEST_CODE);
//	}
//
//	/**
//	 * 结算
//	 */
//	private void pay() {
//		int count = 0;
////				final goods_list inCart = null;
//		if (num == 0) {
//			Toast.makeText(CartActivity.this, "您还没有选择商品哦！",2000).show();
//		} else {
//			for (int i = 0; i < menuList.size(); i++) {
//				categorychild = menuList.get(i).getGoods_list();
//				for (int j = 0; j < categorychild.size(); j++) {
//					Boolean isChecked = inCartMap.get(categorychild.get(j).getRec_id());
//					if (isChecked != null && isChecked) {
//						inCart = categorychild.get(j);
//						count++;
//						if (count == 1) {
//							car_id = inCart.getRec_id();
//						}
//						if (count>1) {
//							car_id += ","+inCart.getRec_id();
//						}
////									SVProgressHUD.showInfoWithStatus(getActivity(), "部分商品已过期!\n请重新添加商品!", SVProgressHUD.SVProgressHUDMaskType.None);
//
//
//					}
//				}
//			}
////					categorychild.remove(inCart);
//			Intent intent = new Intent(CartActivity.this, BuynowActivity.class);
//			intent.putExtra("cart_id", car_id);
//			startActivity(intent);
////					finish();
//		}
//	}
//
//
//
//	private void initModle() {
//		mListView = (SwipeMenuListView) findViewById(R.id.listView_cart);
////		mListView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
//		View foot = getLayoutInflater().inflate(
//				R.layout.foot_cart_list, null);
//		mTvAddUp = (TextView) foot.findViewById(R.id.tv_add_up);
//		mListView.addFooterView(foot, null, false);
//		adapter = new MyAdapter(CartActivity.this);
//		mListView.setAdapter(adapter);
////		SwipeMenuCreator creator = new SwipeMenuCreator() {//向左滑动删除
////
////			@Override
////			public void create(SwipeMenu menu) {
////				SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
////				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
////						0x3F, 0x25)));
////				deleteItem.setWidth(ScreenUtils
////						.getScreenWidth(getActivity()) / 3);
////				deleteItem.setTitle("删除");
////				deleteItem.setTitleSize(18);
////				deleteItem.setTitleColor(Color.WHITE);
////				menu.addMenuItem(deleteItem);
////
////			}
////		};
////		mListView.setMenuCreator(creator);
////		mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
////			@Override
////			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
//////				deleteItem(position);
////				deleteInCart();
////			}
////		});
//	}
//
//	/**
//	 * 删除行
//	 */
//	private void deleteItem(int position) {
//		menuList.remove(position);
//		if (menuList.size() == 0) {
////			mTvNull.setVisibility(View.VISIBLE);
//		}
//		adapter.notifyDataSetChanged();
//	}
//
//
//	@SuppressLint("InflateParams")
//	class MyAdapter extends BaseAdapter{
//		private LayoutInflater inflater;
//		private Context context;
//		public MyAdapter(Context context){
//			this.context = context;
//			this.inflater=LayoutInflater.from(context);
//		}
//		@Override
//		public int getCount() {
//			return menuList.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//		@Override
//		public View getView(final int position, View convertView, ViewGroup parent) {
//			if (convertView == null) {
//				viewHolder = new GroupViewHolder();
//				convertView = inflater.inflate(R.layout.adapter_listview_carts, null);
//				viewHolder.cart_list = (MyListView) convertView.findViewById(R.id.cart_list);//布局
//				viewHolder.store_ll = (LinearLayout) convertView.findViewById(R.id.store_ll);
//				viewHolder.group_NameTV = (TextView) convertView.findViewById(R.id.tv_car_store_name);
//				convertView.setTag(viewHolder);
//			} else {
//				viewHolder = (GroupViewHolder) convertView.getTag();
//			}
//			categorychild = menuList.get(position).getGoods_list();
//			if(categorychild!=null){
//				viewHolder.cart_list.setAdapter(new CategoryGridAdapter(context, categorychild));
//			}
//			viewHolder.store_ll.setBackgroundColor(getResources().getColor(R.color.white));
//			try {
//				String categoryType = getDateToString(menuList.get(position).getDelivery_time()*1000);//
//				viewHolder.group_NameTV.setText("配送日期："+categoryType);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			return convertView;
//		}
//
//	}
//
//	class CategoryGridAdapter extends BaseAdapter {
//		private List<goods_list> categorychild;
//		private LayoutInflater inflater;
//		public CategoryGridAdapter(Context context,List<goods_list> categorychild) {
//			this.inflater=LayoutInflater.from(context);
//			this.categorychild=categorychild;
//		}
//
//
//		@Override
//		public View getView(final int position, View convertView, ViewGroup parent) {
//			final ChildViewHolder cholder;
//			if (convertView == null)
//			{
//				cholder = new ChildViewHolder();
//				convertView = inflater.inflate(R.layout.cart_goods_item, null);
//				cholder.commodity_linear = (LinearLayout) convertView
//						.findViewById(R.id.commodity_linear);//进入商品详情布局
//				cholder.btnCheck = (CheckBox) convertView.findViewById(R.id.cb_choice);
//
//				cholder.tv_type_color = (TextView) convertView
//						.findViewById(R.id.tv_type_color);
//				cholder.btnReduce = (Button) convertView
//						.findViewById(R.id.btn_cart_reduce);
//				cholder.btnAdd = (Button) convertView
//						.findViewById(R.id.btn_cart_add);
//				cholder.btnNumEdit = (Button) convertView
//						.findViewById(R.id.btn_cart_num_edit);
//				cholder.imgGoods = (ImageView) convertView
//						.findViewById(R.id.iv_adapter_list_pic);
//				cholder.tvGoodsName = (TextView) convertView
//						.findViewById(R.id.tv_goods_name);
//				cholder.tvGoodsPrice = (TextView) convertView
//						.findViewById(R.id.tv_cars_price);
//				convertView.setTag(cholder);
//			} else
//			{
//				cholder = (ChildViewHolder) convertView.getTag();
//			}
//			cholder.commodity_linear.setBackgroundColor(getResources().getColor(R.color.white));
//			try {
//				final goods_list mInCart = categorychild.get(position);
//				cholder.tvGoodsName.setText(mInCart.getGoods_name());
//				if (mInCart.getGoods_number() > 1) {
//					cholder.btnReduce.setEnabled(true);
//				} else {
//					cholder.btnReduce.setEnabled(false);
//				}
//				if (mInCart.getIs_exit() == 0) {
//					cholder.tv_type_color.setText("该商品已过配送时间，请重新下单！");
//					cholder.tv_type_color.setTextColor(getResources().getColor(R.color.menu_red));
//					cholder.tv_type_color.setTextSize(14);
//				}else {
//					if (mInCart.getAdded().equals("0")) {
//						cholder.tv_type_color.setText("该商品已下架，请重新下单！");
//						cholder.tv_type_color.setTextColor(getResources().getColor(R.color.menu_red));
//						cholder.tv_type_color.setTextSize(14);
//					}else {
//						cholder.tv_type_color.setText(mInCart.getStyle_2());//商品规格
//					}
//				}
//
//				cholder.tvGoodsPrice.setText(NumberUtils.formatPrice(mInCart.getGoods_price()));
//				cholder.btnNumEdit.setText("" + mInCart.getGoods_number());
//				String imgURLs = mInCart.getGoods_image();
//				if (StringUtils.isEmpty(imgURLs)) {
////						cholder.imgGoods.setImageResource(R.drawable.icon_defult);
//				} else {
//					ImageLoader.getInstance().displayImage(imgURLs, cholder.imgGoods);
//				}
//				// 避免由于复用触发onChecked()事件
//				cholder.btnCheck.setOnCheckedChangeListener(null);
//
//				if (isEdit) {//编辑的状态下过期的商品可以选择
//					cholder.btnCheck.setChecked(false);
////						cholder.btnCheck.setButtonDrawable(R.drawable.iv_check_0_select);
//					if (mInCart.getIs_exit() != 1) {
//						cholder.btnReduce.setEnabled(false);
//						cholder.btnAdd.setEnabled(false);
//					}
//					Boolean isChecked = inCartMap.get(mInCart.getRec_id());
//					if (isChecked != null && isChecked) {
//						cholder.btnCheck.setChecked(true);
//						cholder.btnCheck.setButtonDrawable(R.drawable.a12);
//					}
//				}else {
////						mBtnCheckAll.setOnCheckedChangeListener(null);
////						mBtnCheckAll.setChecked(true);
//
//					Boolean isChecked = inCartMap.get(mInCart.getRec_id());
//					if (isChecked != null && isChecked) {
//						if (cholder.btnCheck.isChecked()) {
//							notifyCheckedChanged();
//						}
//						if (mInCart.getIs_exit() == 1) {
//							if (mInCart.getGoods_number() > 1) {
//								cholder.btnReduce.setEnabled(true);
//							} else {
//								cholder.btnReduce.setEnabled(false);
//							}
//							cholder.btnAdd.setEnabled(true);
//							cholder.btnCheck.setChecked(true);
//
//						}else {
//							inCartMap.remove(mInCart.getRec_id());
//							mBtnCheckAll.setChecked(false);
//							mBtnCheckAllEdit.setOnCheckedChangeListener(checkAllListener);
//							cholder.btnReduce.setEnabled(false);
//							cholder.btnAdd.setEnabled(false);
//							cholder.btnCheck.setEnabled(false);
//							cholder.btnCheck.setButtonDrawable(R.drawable.bwh);
//						}
//					} else {
//						if (mInCart.getIs_exit() == 1) {
//							if (mInCart.getAdded().equals("0")) {
////									inCartMap.remove(mInCart.getRec_id());
////									mBtnCheckAll.setChecked(false);
////									mBtnCheckAllEdit.setOnCheckedChangeListener(checkAllListener);
//								cholder.btnReduce.setEnabled(false);
//								cholder.btnAdd.setEnabled(false);
//								cholder.btnCheck.setEnabled(false);
//								cholder.btnCheck.setButtonDrawable(R.drawable.bwh);
//							}else {
//								if (mInCart.getGoods_number() > 1) {
//									cholder.btnReduce.setEnabled(true);
//								} else {
//									cholder.btnReduce.setEnabled(false);
//								}
//								cholder.btnAdd.setEnabled(true);
//								cholder.btnCheck.setChecked(false);
//							}
////								cholder.btnCheck.setButtonDrawable(R.drawable.a13);
//						}else {
//							cholder.btnAdd.setEnabled(false);
//							cholder.btnReduce.setEnabled(false);
//							cholder.btnCheck.setEnabled(false);
//							cholder.btnCheck.setButtonDrawable(R.drawable.bwh);
//						}
//						cholder.btnCheck.setChecked(false);
//					}
//				}
//				cholder.commodity_linear.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						Intent intent = new Intent(CartActivity.this, DetailActivity.class);
//						intent.putExtra(Constants.INTENT_KEY.INFO_TO_DETAIL, mInCart.getGoods_id());
//						startActivityForResult(intent,
//								Constants.INTENT_KEY.REQUEST_CART_TO_DETAIL);
//
//					}
//				});
//				cholder.btnReduce.setOnClickListener(new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						int num = getNum(cholder.btnNumEdit);
//						num--;
//						mInCart.setGoods_number(num);
//						notifyInCartNumChanged();
//
//						if (num > 1) {
//							cholder.btnReduce.setEnabled(true);
//						} else {
//							cholder.btnReduce.setEnabled(false);
//						}
//						cholder.tvGoodsPrice.setText(NumberUtils.formatPrice(mInCart.getGoods_price()));
//						// 如果被选中，更新价格
//						if (cholder.btnCheck.isChecked()) {
//							notifyCheckedChanged();
//						}
//						cholder.btnNumEdit.setText("" + num);
//
//						threadPoolManager.addTask(new Runnable() {
//							@Override
//							public void run() {
//								HashMap<String, String> param = new HashMap<String, String>();
//								param.put("key", token);
//								param.put("rec_id", mInCart.getRec_id());
//								param.put("goods_number", cholder.btnNumEdit.getText().toString());
//								AddCommodityResult = NativeHttpUtil.post(Urls.USER_CAR_UPDATA_URL, param);
//								changecommodityHandler.sendEmptyMessage(1);
//							}
//						});
//					}
//				});
//				cholder.btnAdd.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
////											progressHUD.show();
//						cholder.btnReduce.setEnabled(true);
//						int num = getNum(cholder.btnNumEdit);
//						num++;
//						mInCart.setGoods_number(num);
//						notifyInCartNumChanged();
//
//						cholder.tvGoodsPrice.setText(NumberUtils.formatPrice(mInCart.getGoods_price()));
//						// 如果被选中，更新价格
//						if (cholder.btnCheck.isChecked()) {
//							notifyCheckedChanged();
//						}
//						cholder.btnNumEdit.setText("" + num);
//						threadPoolManager.addTask(new Runnable() {
//							@Override
//							public void run() {
//								HashMap<String, String> param = new HashMap<String, String>();
//								param.put("key", token);
//								param.put("rec_id", mInCart.getRec_id());
//								param.put("goods_number", cholder.btnNumEdit.getText().toString());
//								AddCommodityResult = NativeHttpUtil.post(Urls.USER_CAR_UPDATA_URL, param);
//								changecommodityHandler.sendEmptyMessage(1);
//							}
//						});
//					}
//				});
//
////			if (mInCart.getIs_exit() == 1) {
////				cholder.btnReduce.setEnabled(true);
////				cholder.btnAdd.setEnabled(true);
//				cholder.btnCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//					@Override
//					public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//						int index=0;
//						for (int i = 0; i < menuList.size(); i++) {
//							if (mInCart.getIs_exit() == 1) {
//								count = menuList.get(i).getGoods_list().size();
//								index += count;
//							}else {
//								continue;
//							}
//						}
//						if (isChecked) {
//							if (isEdit) {//编辑的状态下过期的商品可以选择并加入集合
//								inCartMap.put(mInCart.getRec_id(), isChecked);
//
//							}else {//否则要将过期商品排除并从集合删除
//								if (mInCart.getIs_exit() == 1) {
//									if (num > 1) {
//										cholder.btnReduce.setEnabled(true);
//									} else {
//										cholder.btnReduce.setEnabled(false);
//									}
//									cholder.btnAdd.setEnabled(true);
//									inCartMap.put(mInCart.getRec_id(), isChecked);
//								}else {
//									inCartMap.remove(mInCart.getRec_id());
//									cholder.btnReduce.setEnabled(false);
//									cholder.btnAdd.setEnabled(false);
//								}
//								// 如果所有项都被选中，则点亮全选按钮
//								if (inCartMap.size() == index) {
//									mBtnCheckAll.setChecked(true);
//									mBtnCheckAllEdit.setChecked(true);
//								}
//								notifyCheckedChanged();
//							}
//							cholder.btnCheck.setChecked(true);
//						} else {
//							cholder.btnCheck.setChecked(false);
//							// 如果之前是全选状态，则取消全选状态
//							if (inCartMap.size() == index) {
//								mBtnCheckAll.setOnCheckedChangeListener(null);
//								mBtnCheckAllEdit.setOnCheckedChangeListener(null);
//								mBtnCheckAll.setChecked(false);
//								mBtnCheckAllEdit.setChecked(false);
//								mBtnCheckAll.setOnCheckedChangeListener(checkAllListener);
//								mBtnCheckAllEdit.setOnCheckedChangeListener(checkAllListener);
//							}
//							inCartMap.remove(mInCart.getRec_id());
//						}
//						notifyCheckedChanged();
//					}
//				});
////							}else {
////								cholder.btnCheck.setButtonDrawable(R.drawable.bwh);
////								cholder.btnReduce.setEnabled(false);
////								cholder.btnAdd.setEnabled(false);
////								cholder.btnCheck.setEnabled(false);
////							}
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return convertView;
//		}
//
//		@Override
//		public int getCount() {
//			if (menuList.size() != 0) {
//				return categorychild.size();
//			}
//			return 0;
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return position;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		class ChildViewHolder {
//			LinearLayout commodity_linear;
//			CheckBox btnCheck;
//			Button btnAdd;
//			Button btnReduce;
//			Button btnNumEdit;
//			ImageView imgGoods;
//			TextView tvGoodsName;
//			TextView tvGoodsPrice;
//			TextView tv_type_color;
//		}
//
//	}
//
//	private Handler changecommodityHandler = new Handler() {
//		public void handleMessage(Message msg) {
//			if (TextUtils.isEmpty(AddCommodityResult)) {
//			} else {
//
//				try {
//					JSONObject jsonObject2 =new JSONObject(AddCommodityResult);
//					int code = jsonObject2.getInt("code");
//					if (code == 200) {
//						JSONObject dataObj=jsonObject2.getJSONObject("data");
////			        	 String message = dataObj.getString("msg");
////			        	 Toast.makeText(getActivity(), message,2000).show();
//					}else {
//						String error = jsonObject2.getString("error");
//						Toast.makeText(CartActivity.this, error,2000).show();
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//
//
//			}
//		};
//	};
//
//
//	class GroupViewHolder {
//		MyListView cart_list;
//		TextView group_NameTV;
//		LinearLayout store_ll;
//		CheckBox group_CB;
//	}
//
//
//	String Week;
//	private String getDateToString(long time) {
//		Date d = new Date(time);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
////		Date curdate = new Date(System.currentTimeMillis());
//		String pTime = sf.format(d);
//		Calendar c = Calendar.getInstance();
//		try {
//			c.setTime(sf.parse(pTime));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		switch (c.get(Calendar.DAY_OF_WEEK)) {
//			case 1:
//				Week = "星期天";
//				break;
//			case 2:
//				Week = "星期一";
//				break;
//			case 3:
//				Week = "星期二";
//				break;
//			case 4:
//				Week = "星期三";
//				break;
//			case 5:
//				Week = "星期四";
//				break;
//			case 6:
//				Week = "星期五";
//				break;
//			case 7:
//				Week = "星期六";
//				break;
//			default:
//				break;
//		}
//		return format.format(d)+" "+Week;
//	}
//
//	/**
//	 * 异步任务,获取数据
//	 *
//	 */
//	class GetListTask extends AsyncTask<String, Integer, Boolean> {
//
//
//		private Handler commodityHandler = new Handler() {
//			public void handleMessage(Message msg) {
//				if (TextUtils.isEmpty(commodityresult)) {
//				} else {
//
//					try {
//						JSONObject jsonObject2 =new JSONObject(commodityresult);
//						int code = jsonObject2.getInt("code");
//						if (code == 200) {
//							JSONObject dataObj=jsonObject2.getJSONObject("data");
//							JSONArray jsonArray = dataObj.getJSONArray("goodsList");
//							for (int i=0;i<jsonArray.length();i++)
//							{
//								JSONObject jsonObjectSon= (JSONObject)jsonArray.opt(i);
//								recommendCommodity = new GoodsInfo();
//								recommendCommodity.setGoodsId(jsonObjectSon.getString("goods_id"));
//								recommendCommodity.setGoodsName(jsonObjectSon .getString("goods_name"));
//								recommendCommodity.setGoodsPrice(Double.parseDouble(jsonObjectSon.getString("goods_min_price")));
//								recommendCommodity.setGoodsIcon(jsonObjectSon.getString("goods_image"));
//								reCommodities.add(recommendCommodity);
//							}
//						}else {
//							String error = jsonObject2.getString("error");
//							Toast.makeText(CartActivity.this, error,2000).show();
//						}
//						gridviewadapter.notifyDataSetChanged();
//						gridView_hotsell_commodity.setAdapter(gridviewadapter);
//						mPtrScrollView.smoothScrollTo(0, 0);
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//
//
//				}
//			};
//		};
//
//		@Override
//		protected Boolean doInBackground(String... params) {
//			try {
//				HashMap<String, String> param = new HashMap<String, String>();
//				param.put("wd", "android");
//				commodityresult = NativeHttpUtil.post(Urls.HOME_URL, param);
//				commodityHandler.sendEmptyMessage(1);
//
//				return true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//
//
//
//		}
//
//		@Override
//		protected void onPostExecute(Boolean result) {
//			super.onPostExecute(result);
//			if (result) {
//				init();
//				mPtrScrollView.onRefreshComplete();// 关闭刷新动画
//			}
//		}
//	}
//}
