package com.aidingyun.webview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidingyun.core.GlobalConfig;
import com.aidingyun.core.router.Router;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.yanzhenjie.sofia.Sofia;


/**
 * <pre>
 *     project : DigitalCurrencyTradingDemo
 *     @author : 李琼
 *     @date   : 2018\3\1 0001 17:18
 *     @desc   : 描述--//WebActivity 点击打开网页
 * </pre>
 */
public class WebActivity extends AppCompatActivity {
    public static final String DEFAULT_TITLE = "正在努力加载中";
    protected AgentWeb mAgentWeb;
    private AlertDialog mAlertDialog;
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            Log.i("Info", "BaseWebActivity onPageStarted");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            addClickListener(view);//待网页加载完全后设置图片点击的监听方法
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.i("Info", "progress:" + newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (DEFAULT_TITLE.equals(mTitle.getText().toString())) {
                mTitle.setText(title);
            }
        }
    };
    private TextView mTitle;

    public static void start(Activity context, @NonNull String url) {
        start(context, url, "");
    }

    public static void start(Context context, @NonNull String url, String title) {
        Router.newIntent(context)
                .to(WebActivity.class)
                .putString(GlobalConfig.KEY_WEBVIEW_URL, url)
                .putString(GlobalConfig.KEY_WEBVIEW_TITLE, title)
                .launch();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Sofia.with(this).invasionStatusBar().statusBarDarkFont();
        String titleDefined = getIntent().getStringExtra(GlobalConfig.KEY_WEBVIEW_TITLE);
        mTitle = findViewById(R.id.title);
        mTitle.setText(TextUtils.isEmpty(titleDefined) ? DEFAULT_TITLE : titleDefined);
        findViewById(R.id.left_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayout mLinearLayout = findViewById(R.id.container);
        long p = System.currentTimeMillis();
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, R.id.retry_view)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setWebLayout(new WebLayout(this))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(getUrl());
        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        mWebView.addJavascriptInterface(new LoadHtmlCodeScript(), "java_obj");

        long n = System.currentTimeMillis();
        Log.i("Info", "init used time:" + (n - p));
    }

    public String getUrl() {
        String url = getIntent().getStringExtra(GlobalConfig.KEY_WEBVIEW_URL);
        return TextUtils.isEmpty(url) ? "https://www.baidu.com" : url;
    }

    private void addClickListener(WebView webView) {
        //获取<html></html>的脚本
        webView.loadUrl("javascript:window.java_obj.getSource('<head>'+" +
                "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
    }

    private void showDialog() {

        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(this)
                    .setMessage("您确定要关闭该页面吗?")
                    .setNegativeButton("再逛逛", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null) {
                                mAlertDialog.dismiss();
                            }
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null) {
                                mAlertDialog.dismiss();
                            }
                            WebActivity.this.finish();
                        }
                    }).create();
            mAlertDialog.show();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
