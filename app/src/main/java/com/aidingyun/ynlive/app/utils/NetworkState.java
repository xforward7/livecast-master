package com.aidingyun.ynlive.app.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.aidingyun.ynlive.component.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 网络监控和网络状态检查
 */
public class NetworkState extends BroadcastReceiver {

    private final String TAG = getClass().getSimpleName();

    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_3G = 2;
    public static final int NETWORK_TYPE_2G = 3;
    public static final int NETWORK_TYPE_4G = 6;

    private static NetworkState instance = null;

    public static NetworkState getInstance() {
        if (null == instance) {
            instance = new NetworkState();
        }
        return instance;
    }

    public NetworkState() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String newApn = getApnValue();
        LogUtils.d(TAG, "NetworkStateReceiver ====== " + intent.getAction() + " apn:" + apn + " -> " + newApn + " Available:" + isNetworkAvailable(context));

        if (intent.getAction() == null)
            return;

        if (intent.getAction().compareTo(ConnectivityManager.CONNECTIVITY_ACTION) == 0) {
            notifyObservers(isNetworkConnected(context));
        }

        if (newApn != null && !newApn.equalsIgnoreCase(apn)) {
            notifyApnChanged(true);
        }
    }

    public String getApnName() {
        return apn;
    }

    public static final class APNName {
        public final static String NAME_NONE = "none";
        public final static String NAME_UNKNOWN = "unknown";
        public final static String NAME_CMNET = "cmnet";
        public final static String NAME_CMWAP = "cmwap";
        public final static String NAME_3GNET = "3gnet";
        public final static String NAME_3GWAP = "3gwap";
        public final static String NAME_UNINET = "uninet";
        public final static String NAME_UNIWAP = "uniwap";
        public final static String NAME_WIFI = "wifi";
        public final static String NAME_CTWAP = "ctwap";
        public final static String NAME_CTNET = "ctnet";
        public final static String NAME_CMCC = "cmcc";
        public final static String NAME_UNICOM = "unicom";
        public final static String NAME_CMCT = "cmct";
        public final static String NAME_777 = "#777";
    }

    public String getApnValue() {
        if (context == null)
            return APNName.NAME_NONE;

        try {
            NetworkInfo ifo = null;
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                ifo = connectivity.getActiveNetworkInfo();
            }

            if (null == ifo || !ifo.isConnected()) {
                return APNName.NAME_NONE;
            }

            if (ConnectivityManager.TYPE_WIFI == ifo.getType()) {
                return APNName.NAME_WIFI;
            }

            if (ifo.getExtraInfo() != null) {
                return ifo.getExtraInfo().toLowerCase();
            } else {
                return APNName.NAME_UNKNOWN;
            }
        } catch (Throwable e) {
            return APNName.NAME_UNKNOWN;
        }
    }

    // //////////////////////////////////////////////////////////
    private Context context = null;

    // 运营商信息
    private String providerName = null;
    private boolean loadProviderName = false;

    // 网络类型 2G 3G WIFI
    private static int networkType = 0;

    private String apn = APNName.NAME_NONE;

    public void setContext(Context context) {
        this.context = context;

//        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
//        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String IMSI = null;
//        try {
//            IMSI = telephony.getSubscriberId();
//        } catch (Throwable e) {
//            // empty.
//        }
//        if (null == IMSI || "".equals(IMSI)) {
//            providerName = "unknown";
//        } else if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
//            providerName = "ChinaMobile";
//        } else if (IMSI.startsWith("46001")) {
//            providerName = "ChinaUnicom";
//        } else if (IMSI.startsWith("46003")) {
//            providerName = "ChinaTelecom";
//        } else {
//            providerName = "unknown";
//        }

        // 监听网络变更状态
        IntentFilter filter = new IntentFilter(new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        context.registerReceiver(this, filter);

        // 初始化先检测网络
        isNetworkConnected(context);
    }

    public void unregisterReceiver() {
        context.unregisterReceiver(this);
    }

    // ////////////////////////////////////////////////////////////////////

    private List<NetworkStateListener> observers = new ArrayList<NetworkStateListener>();

    public boolean isNetworkConnected() {
        if (null == context) { // 系统启动时需要初始化context
            return true;
        }
        return isNetworkConnected(context);
    }

    //仅提供给upload模块使用
    public boolean isNetworkAvailable() {
        if (null == context) { // 系统启动时需要初始化context
            return true;
        }
        boolean available = false;

        NetworkInfo info = null;
        try {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            info = connMgr.getActiveNetworkInfo();
        } catch (Throwable e) {
            LogUtils.e(TAG, "fail to get active network info", e);
        }

        if (info == null) {
            return false;
        }

        available = info.isConnected();

        if (available) {

        } else {
            // 网络无效时输出当前网络类型
            LogUtils.e(TAG, "isNetworkEnable() : FALSE with TYPE = " + info.getType());
        }

        return available;
    }

    public String getProviderName() {
        if (!loadProviderName) {
            loadProviderName = true;
            // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
            TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String IMSI = null;
            try {
                IMSI = telephony.getSubscriberId();
            } catch (Throwable e) {
                // empty.
            }
            if (null == IMSI || "".equals(IMSI)) {
                providerName = "unknown";
            } else if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                providerName = "ChinaMobile";
            } else if (IMSI.startsWith("46001")) {
                providerName = "ChinaUnicom";
            } else if (IMSI.startsWith("46003")) {
                providerName = "ChinaTelecom";
            } else {
                providerName = "unknown";
            }
        }
        return providerName;
    }

    public int getNetworkType() {
        return networkType;
    }

    public void addListener(NetworkStateListener listener) {
        if (null == listener) {
            return;
        }
        synchronized (observers) {
            if (!observers.contains(listener)) {
                observers.add(listener);
            }
        }
    }

    public void removeListener(NetworkStateListener listener) {
        synchronized (observers) {
            observers.remove(listener);
        }
    }

    private void notifyObservers(boolean connected) {
        NetworkStateListener[] arrays = null;
        synchronized (observers) {
            int size = observers.size();
            arrays = new NetworkStateListener[size];
            observers.toArray(arrays);
        }

        if (null != arrays) {
            for (NetworkStateListener listener : arrays) {
                listener.onNetworkConnected(connected);
            }
        }
    }

    private void notifyApnChanged(boolean changed) {
        NetworkStateListener[] arrays = null;
        synchronized (observers) {
            int size = observers.size();
            arrays = new NetworkStateListener[size];
            observers.toArray(arrays);
        }

        if (null != arrays) {
            for (NetworkStateListener listener : arrays) {
                listener.onNetworkApnChanged(changed);
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo ifo = null;
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                ifo = connectivity.getActiveNetworkInfo();
            }

            return null != ifo && ifo.isConnected();
        } catch (Throwable e) {
            return true; // 异常时返回true，防止奇葩ROM抛异常返回false的话下载会中断
        }
    }

    public static boolean isNetworkConnected(Context context) {
        if (context == null)
            return false;

        try {
            // context.getSystemService(Context.CONNECTIVITY_SERVICE) may throw NullPointerException.
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            }
            //getAllNetworkInfo 在某些机型上会抛SecurityException
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo ifo : info) {
                    if (ifo == null) continue;
                    if (ifo.isConnectedOrConnecting()) {
                        getNetworkType(ifo);
                        return true;
                    }
                }
            }
        } catch (SecurityException e) {
            return true;
        } catch (Throwable e) {
            return false;
        }
        return false;
    }

    private static int getNetworkType(NetworkInfo networkInfo) {
        switch (networkInfo.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                networkType = NETWORK_TYPE_WIFI;
                break;
            case ConnectivityManager.TYPE_MOBILE:
                switch (networkInfo.getSubtype()) {
                    // EDGE (2.75G)
                    case TelephonyManager.NETWORK_TYPE_EDGE: // ~ 50-100 kbps
                        // GPRS (2.5G)
                    case TelephonyManager.NETWORK_TYPE_GPRS: // ~ 100 kbps
                        // CDMA: Either IS95A or IS95B (2G)
                    case TelephonyManager.NETWORK_TYPE_CDMA: // ~ 14-64 kbps
                        // 1xRTT (2G - Transitional)
                    case TelephonyManager.NETWORK_TYPE_1xRTT: // ~ 50-100 kbps
                        // iDen (2G)
                    case TelephonyManager.NETWORK_TYPE_IDEN: // ~25 kbps
                        networkType = NETWORK_TYPE_2G;
                        break;

                    // UMTS (3G)
                    case TelephonyManager.NETWORK_TYPE_UMTS: // ~ 400-7000 kbps
                        // EVDO revision 0 (3G)
                    case TelephonyManager.NETWORK_TYPE_EVDO_0: // ~ 400-1000 kbps
                        // EVDO revision A (3G - Transitional)
                    case TelephonyManager.NETWORK_TYPE_EVDO_A: // ~ 600-1400 kbps
                        // HSDPA (3G - Transitional)
                    case TelephonyManager.NETWORK_TYPE_HSDPA: // ~ 2-14 Mbps
                        // HSPA (3G - Transitional)
                    case TelephonyManager.NETWORK_TYPE_HSPA: // ~ 700-1700 kbps
                        // HSUPA (3G - Transitional)
                    case TelephonyManager.NETWORK_TYPE_HSUPA: // ~ 1-23 Mbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        networkType = NETWORK_TYPE_3G;
                        break;

                    case TelephonyManager.NETWORK_TYPE_LTE:
                        networkType = NETWORK_TYPE_4G;
                        break;

                    // Unknown
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                        break;
                }
                break;
            default: // Unknown
                networkType = NETWORK_TYPE_UNKNOWN;
                break;
        }
        return networkType;
    }

    public interface NetworkStateListener {
        void onNetworkConnected(boolean connected);

        void onNetworkApnChanged(boolean changed);
    }
}