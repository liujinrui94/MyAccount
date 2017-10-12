package com.ljr.jizhang.ui.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.ljr.jizhang.R;
import com.ljr.jizhang.bean.Account;
import com.ljr.jizhang.framework.base.BaseActivity;
import com.ljr.jizhang.framework.base.BaseRecyclerAdapter;
import com.ljr.jizhang.framework.refreshlayout.SmartRefreshLayout;
import com.ljr.jizhang.framework.refreshlayout.api.RefreshLayout;
import com.ljr.jizhang.framework.refreshlayout.listener.OnRefreshListener;
import com.ljr.jizhang.ui.presenter.MainPresenter;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import nucleus.factory.RequiresPresenter;
import rx.Observable;
import rx.functions.Action1;

@RequiresPresenter(MainPresenter.class)
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity<MainPresenter> {

    private WebView webview;
    private ProgressBar progressBar;

    @Override
    protected void initView() {
        webview = (WebView) findViewById(R.id.convenience_payment_wv);
        progressBar = (ProgressBar) findViewById(R.id.convenience_payment_progressbar);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        webview.loadUrl(getIntent().getStringExtra("url"));
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(client);
        webview.setWebChromeClient(chromeClient);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }

    private WebViewClient client = new WebViewClient() {
        // 防止加载网页时调起系统浏览器
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                progressBar.setVisibility(View.GONE);//加载完网页进度条消失
            } else {
                progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                progressBar.setProgress(i);//设置进度值
            }
            super.onProgressChanged(webView, i);
        }
    };

    @Override
    protected void onDestroy() {
        webview.stopLoading();
        webview.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果不做任何处理，浏览网页，点击系统“Back”键，整个Browser会调用finish()而结束自身，
        // 如果希望浏览的网 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件。
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
