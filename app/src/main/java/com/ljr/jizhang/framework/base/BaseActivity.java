package com.ljr.jizhang.framework.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ljr.jizhang.R;
import com.ljr.jizhang.framework.widget.BaseProgressDialog;
import com.ljr.jizhang.model.NetEventInterface;
import com.ljr.jizhang.receiver.NetBroadcastReceiver;
import com.ljr.jizhang.utils.AppLogger;
import com.ljr.jizhang.utils.SnackBarUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;

/**
 * @author: LiuJinrui
 * @time: 2017/8/21 13:44
 * @description:
 */
public abstract class BaseActivity<P extends Presenter> extends NucleusAppCompatActivity<P> implements NetEventInterface, View.OnClickListener {

    private int netMobile;//网络状态
    private NetBroadcastReceiver netBroadcastReceiver;//监控网络的广播
    private BaseProgressDialog progressDialog;
    private Snackbar snackbar;


    protected AppApplication app;
    @ViewInject(R.id.top_toolbar)
    protected Toolbar toolbar;
    @ViewInject(R.id.top_toolbar_tv)
    private TextView textTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
        app.getInstance().addActivity(this);
        AppLogger.i(getRunningActivityName(this) + " is running");
    }

    protected void initToolbar(String title, final BaseActivity context, boolean back) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        if (back) {
            toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        }
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app.finishActivity(context);
            }
        });
    }


    private String getRunningActivityName(Context mContext) {
        String contextString = mContext.toString();
        return contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
    }

    protected abstract void initView();


    protected abstract void initData();

    @Override
    public abstract void onClick(View view);


    /**
     * 显示加载对话框
     */
    protected ProgressDialog progressShow(String dialogDetail) {
        if (progressDialog == null) {
            progressDialog = new BaseProgressDialog(this);
        }
        progressDialog.setDialogDetail(dialogDetail);
        progressDialog.show();
        return progressDialog;
    }

    /**
     * 显示加载对话框
     */
    protected ProgressDialog progressShow() {
        return progressShow("");
    }

    /**
     * 取消加载对话框
     */
    protected void progressCancel() {
        if (progressDialog != null)
            progressDialog.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册广播
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netBroadcastReceiver, filter);
            netBroadcastReceiver.setNetEvent(this);//设置网络监听
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (netBroadcastReceiver != null) {
            //注销广播
            unregisterReceiver(netBroadcastReceiver);
        }
    }

    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
        isNetConnect();
    }

    private void isNetConnect() {
        if (snackbar == null) {
            snackbar = SnackBarUtils.indefiniteSnackbar(textTitle, "无网络连接，请检查网络设置");
            switch (netMobile) {
                case 1://wifi
                    if (snackbar.isShown()) {
                        snackbar.dismiss();
                    }
                    break;
                case 0://移动数据
                    if (snackbar.isShown()) {
                        snackbar.dismiss();
                    }
                    break;
                case -1://没有网络
                    snackbar.setAction("去设置", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    }).show();
                    break;
            }

        }
    }
}
