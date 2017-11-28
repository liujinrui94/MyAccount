package com.caipiao.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.caipiao.R;

public class SSCFragment extends Fragment {

    WebView webview;
    ProgressBar progressBar;

    String URL;
    private View rootView;


    public static Fragment newInstance(String shareType) {
        SSCFragment newFragment = new SSCFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", shareType);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        URL = getArguments().getString("url");
//        URL="https://vipc.cn/qxc/trend?fr=shareToWeixinTimeline";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.ssc_fragment, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        webview = (WebView) rootView.findViewById(R.id.convenience_payment_fragment);
        progressBar = (ProgressBar) rootView.findViewById(R.id.convenience_payment_progressbar);
        webview.loadUrl(URL);
        webview.setVisibility(View.GONE);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setWebViewClient(client);
        webview.setWebChromeClient(chromeClient);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.setDownloadListener(new MyWebViewDownLoadListener());

    }

    private WebViewClient client = new WebViewClient() {
        //        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            if( url.startsWith("http:") || url.startsWith("https:") ) {
//                return false;
//            }
//            try{
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);
//            }catch(Exception e){}
//            return true;
//        }
// 防止加载网页时调起系统浏览器
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //编写 javaScript方法
            String javascript = "javascript:function hideOther() {"
                    + "document.getElementsByClassName('vMod_topBar_right')[0].style.display = 'none';" +
                    "}";
            //创建方法
            view.loadUrl(javascript);
            view.loadUrl("javascript:hideOther();");
            webview.setVisibility(View.VISIBLE);
//            view.loadUrl("javascript:hideOther();");
            //加载方法
//            view.loadUrl("javascript:hideOther();");
        }
    };


    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                progressBar.setVisibility(View.GONE);//加载完网页进度条消失
            } else {
//                progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
//                progressBar.setProgress(i);//设置进度值
            }
            super.onProgressChanged(webView, i);
        }
    };

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,

                                    long contentLength) {
            Uri uri = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            startActivity(intent);

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webview.stopLoading();
        webview.destroy();
    }


}

