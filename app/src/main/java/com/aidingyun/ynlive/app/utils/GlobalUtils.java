package com.aidingyun.ynlive.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.transition.Slide;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aidingyun.core.utils.Kits;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.component.log.LogUtils;
import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.jess.arms.utils.ArmsUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yanzhenjie.sofia.Bar;
import com.yanzhenjie.sofia.Sofia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.text.TextUtils.isEmpty;

/**
 * <pre>
 *     project name: MVPArms-master
 *     author      : 翁嘉若
 *     create time : 2018/7/12 11:11
 *     desc        : 描述--//GlobalUtils
 * </pre>
 */

public class GlobalUtils {

    public static final String DATABASE_NAME = "DATABASE_NAME";

    public static final int FEED_PER_PAGE = 10;

    public static Bar statusBarTint(Activity activity) {
        return Sofia.with(activity)
                .statusBarDarkFont()
                .statusBarBackground(ContextCompat.getColor(activity, R.color.colorWhite))
                .navigationBarBackground(ContextCompat.getColor(activity, R.color.colorBlack));
    }




    public static void toolBarInit(Activity activity, String titleStr, boolean showBack) {
        TextView title = activity.findViewById(R.id.title);
        title.setText(titleStr);
        if (showBack) {
            View leftBtn = activity.findViewById(R.id.left_btn);
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setOnClickListener(v -> activity.finish());
        }
    }
    public static void toolBarInitRight(Activity activity, String titleStr,String titleStrRight, boolean showBack) {
        TextView title = activity.findViewById(R.id.title);
        TextView titleRight = activity.findViewById(R.id.right_title);
        title.setText(titleStr);
        titleRight.setText(titleStrRight);
        if (showBack) {
            View leftBtn = activity.findViewById(R.id.left_btn);
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setOnClickListener(v -> activity.finish());
        }
    }

    public static final String FILE_PROVIDER = "com.bloomsweet.tianbing.FileProvider";

    public static String formatTimeDetail(long timeStamp) {
        long curTimeMillis = System.currentTimeMillis();
        long diff = curTimeMillis - timeStamp;
        if (diff < 60 * 1000) {
            return "刚刚";
        } else if (diff <= 60 * 60 * 1000) {
            return diff / (60 * 1000) + "分钟前";
        } else if (diff <= 12 * 60 * 60 * 1000) {
            return diff / (60 * 60 * 1000) + "小时前";
        }
        Date curDate = new Date(curTimeMillis);
        int todayYearMillis = curDate.getYear() * 24 * 60 * 60 * 1000;
        int todayHoursSeconds = curDate.getHours() * 60 * 60;
        int todayMinutesSeconds = curDate.getMinutes() * 60;
        int todaySeconds = curDate.getSeconds();
        int todayMillis = (todayHoursSeconds + todayMinutesSeconds + todaySeconds) * 1000;
        long todayStartMillis = curTimeMillis - todayMillis;
        String simpleTimeStr = new SimpleDateFormat("HH:mm").format(new Date(timeStamp));
        if (timeStamp >= todayStartMillis) {
            return "今天 " + simpleTimeStr;
        }
        int oneDayMillis = 24 * 60 * 60 * 1000;
        long yesterdayStartMilis = todayStartMillis - oneDayMillis;
        if (timeStamp >= yesterdayStartMilis) {
            return "昨天 " + simpleTimeStr;
        }

        if (timeStamp > todayYearMillis) {
            return new SimpleDateFormat("MM-dd HH:mm").format(new Date(timeStamp));
        } else {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(timeStamp));
        }
    }

    public static String formatTimeSimple(long timeStamp) {
        long curTimeMillis = System.currentTimeMillis();
        long diff = curTimeMillis - timeStamp * 1000;
        if (diff < 60 * 1000) {
            return "刚刚";
        } else if (diff <= 60 * 60 * 1000) {
            return diff / (60 * 1000) + "分钟前";
        } else if (diff <= 12 * 60 * 60 * 1000) {
            return diff / (60 * 60 * 1000) + "小时前";
        }

        int todayYearMillis = (Calendar.getInstance().get(Calendar.YEAR) - 1900) * 24 * 60 * 60 * 1000;
        int todayHoursSeconds = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60 * 60;
        int todayMinutesSeconds = Calendar.getInstance().get(Calendar.MINUTE) * 60;
        int todaySeconds = Calendar.getInstance().get(Calendar.SECOND);
        int todayMillis = (todayHoursSeconds + todayMinutesSeconds + todaySeconds) * 1000;
        long todayStartMillis = curTimeMillis - todayMillis;
        if (timeStamp >= todayStartMillis) {
            return "今天";
        }
        int oneDayMillis = 24 * 60 * 60 * 1000;
        long yesterdayStartMilis = todayStartMillis - oneDayMillis;
        if (timeStamp >= yesterdayStartMilis) {
            return "昨天";
        }

        if (timeStamp > todayYearMillis) {
            return new SimpleDateFormat("MM-dd").format(new Date(timeStamp));
        } else {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date(timeStamp));
        }
    }

    public static RequestBody generateJsonStr(Map<String, String> options) {
        JSONObject result = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : options.entrySet()) {
                result.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return RequestBody.create(MediaType.parse("application/json"), result.toString());
    }

    public static RequestBody generateJson(Map<String, Object> options) {
        JSONObject result = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : options.entrySet()) {
                result.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return RequestBody.create(MediaType.parse("application/json"), result.toString());
    }


    public static boolean isPhone(String areaCode, String phone) {
        if (isEmpty(phone)) {
            return false;
        }

        if ("+86".equals(areaCode)) {
            String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
            if (phone.length() != 11) {
                return false;
            } else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                boolean isMatch = m.matches();
                return isMatch;
            }
        } else {
            return phone.length() > 3 && isNumeric(phone);
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }


    public static String numTransformer(int number) {
        if (number > -10000 && number < 10000) {
            return String.valueOf(number);
        } else {
            double d = (double) number;
            double num = d / 10000;//1.将数字转换成以万为单位的数字
            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();//2.转换后的数字四舍五入保留小数点后一位;
            return f1 + "万";
        }
    }

    public static void passwordVisible(EditText editText, boolean isHidden) {
        if (isHidden) {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); //editText可见
        } else {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());//editText不可见
        }
        editText.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = editText.getText();
        if (charSequence != null) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }


    /**
     * 设置TextView段落间距
     *
     * @param textView         给谁设置段距，就传谁
     * @param content          文字内容
     * @param paragraphSpacing 请输入段落间距（单位px）
     */
    public static void setParagraphSpacing(TextView textView, String content,
                                           int paragraphSpacing, int lineSpacingExtra) {
        if (!content.contains("\n")) {
            textView.setText(content);
            return;
        }
        content = content.replace("\n", "\n\r");

        int previousIndex = content.indexOf("\n\r");
        //记录每个段落开始的index，第一段没有，从第二段开始
        List<Integer> nextParagraphBeginIndexes = new ArrayList<>();
        nextParagraphBeginIndexes.add(previousIndex);
        while (previousIndex != -1) {
            previousIndex = content.indexOf("\n\r", previousIndex + 2);
            if (previousIndex != -1) {
                nextParagraphBeginIndexes.add(previousIndex);
            }
        }
        //把\r替换成透明长方形（宽:1px，高：字高+段距）
        SpannableString spanString = new SpannableString(content);
        Drawable d = ContextCompat.getDrawable(textView.getContext(), R.drawable.shape_paragraph_space);
        d.setBounds(0, 0,
                ArmsUtils.dip2px(textView.getContext(), lineSpacingExtra),
                ArmsUtils.dip2px(textView.getContext(), paragraphSpacing));

        for (int index : nextParagraphBeginIndexes) {
            // \r在String中占一个index
            spanString.setSpan(new ImageSpan(d), index + 1, index + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(spanString);
    }

    public static String getSubString(String data, int max) {
        if (data == null || data.length() <= max) {
            return data;
        }
        return data.substring(0, max - 1) + "...";
    }

    @SuppressLint("RtlHardcoded")
    public static void setTextViewDrawable(TextView textView,
                                           @DrawableRes int drawableRes,
                                           @Slide.GravityFlag int gravity) {
        Drawable drawable = ContextCompat.getDrawable(textView.getContext(), drawableRes);
        setTextViewDrawable(textView, drawable, gravity);
    }

    @SuppressLint("RtlHardcoded")
    public static void setTextViewDrawable(TextView textView,
                                           Drawable drawable,
                                           @Slide.GravityFlag int gravity) {
        if (gravity == Gravity.START || gravity == Gravity.LEFT) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        } else if (gravity == Gravity.END || gravity == Gravity.RIGHT) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        } else if (gravity == Gravity.TOP) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
        }
    }

    public static HashMap<String, String> dealFuckData(String fuckData) {
        HashMap<String, String> result = new HashMap<>();
        LogUtils.e("json", fuckData);
        try {

            if (fuckData != null && fuckData.startsWith("\ufeff")) {
                fuckData = fuckData.substring(1);
            }
            if (!Kits.Empty.check(fuckData)) {
                JSONObject jsonObject = new JSONObject(fuckData);
                Iterator iterator = jsonObject.keys();
                String key;
                String value;
                while (iterator.hasNext()) {
                    key = (String) iterator.next();
                    value = jsonObject.getString(key);
                    result.put(key, value);
                }
                return result;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 处理SmartRefreshLayout
     *
     * @param index  加载的页码
     * @param remain 是否还有更多 1是还有更多数据,0没有
     */
    public static void dealRefreshLoadMore(int index, int remain, RefreshLayout refreshLayout) {
        if (index == 0) {//加载第一页
            refreshLayout.finishRefresh();
            if (remain == 1) {
                refreshLayout.setNoMoreData(false);
                refreshLayout.finishLoadMore();//还有更多数据
            } else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else {
            if (remain == 1) {
                refreshLayout.finishLoadMore();//还有更多数据
            } else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }


    public static void unbindBadge(BadgeView badgeView) {
        if (badgeView != null) {
            badgeView.unbind();
        }
    }

    public static BadgeView bindBadge(View view, int count, int colorRes) {
        if (count != 0) {
            return bindBadge(view, numTransformer(count), colorRes);
        } else {
            return null;
        }
    }

    public static BadgeView bindBadge(View view, String countStr, int colorRes) {
        int defuaultTextSizeSp = 10;
        return BadgeFactory.create(view.getContext())
                .setTextColor(Color.WHITE)
                .setWidthAndHeight((int) Math.max(15f, countStr.length() * defuaultTextSizeSp * 0.8), 11)
                .setBadgeBackground(ContextCompat.getColor(view.getContext(), colorRes))
                .setTextSize(defuaultTextSizeSp)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setBadgeCount(countStr)
                .setShape(BadgeView.SHAPTE_ROUND_RECTANGLE)
                .setSpace(2, 4)
                .bind(view);
    }


    public static int getLineCount(String content) {
        return content.split(System.getProperty("line.separator")).length;
    }

    public static String getUrl(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath();
            System.out.println(uri.getQuery());
            return path;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Map<String, String> getUrlParams(String queryString) {
        Map<String, String> queryPairs = new ConcurrentHashMap<>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
                String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                if (!key.isEmpty()) {
                    queryPairs.put(key, value);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return queryPairs;
    }

    /**
     * 把内容复制到剪切板
     *
     * @param text    复制到剪切板的内容
     * @param context Context
     */
    public static void copyToClipboard(final String text, @NonNull Context context) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                context.getApplicationContext().getSystemService(
                        Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            clipboard.setPrimaryClip(ClipData.newPlainText(null, text));
            ToastUtils.show(context, "已复制");
        }
    }
//    public static long getAccountCount() {
//        return GreenDaoManager.getInstance(GlobalContext.getAppContext()).getDaoSession().getLoginResultEntityDao()
//                .queryBuilder().count();
//    }
}
