package com.aidingyun.webview;

import android.webkit.JavascriptInterface;

public class LoadHtmlCodeScript {
    public static String htmlCode;

    @JavascriptInterface
    public void getSource(String htmlCode) {
        LoadHtmlCodeScript.htmlCode = htmlCode;
    }
}