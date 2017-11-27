package com.caipiao.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.caipiao.R;
import com.caipiao.framework.base.XBaseFragment;
import com.caipiao.framework.refreshlayout.SmartRefreshLayout;
import com.caipiao.framework.refreshlayout.api.RefreshLayout;
import com.caipiao.framework.refreshlayout.listener.OnRefreshListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/11/20 23:31
 * @description:
 */
@ContentView(R.layout.smart_refresh_layout_fragment)
public class SmartRefreshLayoutFragment extends XBaseFragment {



    private  WebView webview;
    @ViewInject(R.id.fragment_smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    protected Toolbar toolbar;
    String URL;

    public static Fragment newInstance(String shareType) {
        SmartRefreshLayoutFragment newFragment = new SmartRefreshLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", shareType);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        URL = getArguments().getString("url");
    }

    @Override
    protected void initView() {
        webview= (WebView) getRootView().findViewById(R.id.smartRefreshLayout_payment_fragment);
        toolbar= (Toolbar) getRootView().findViewById(R.id.top_toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                webview.loadUrl(URL);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( webview.canGoBack()) {
                    webview.goBack();
                }
            }
        });

    }
    @Override
    protected void initData() {
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.setWebViewClient(client);
        webview.setWebChromeClient(chromeClient);
        webview.loadUrl(URL);
    }

    private WebViewClient client = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    };

    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            if (title.length()>4){
                toolbar.setTitle(title.substring(0,4));
            }else {
                toolbar.setTitle(title);
            }



        }
        @Override
        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                smartRefreshLayout.finishRefresh();
            }
            super.onProgressChanged(webView, i);
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        webview.stopLoading();
        webview.destroy();
    }



}
