package com.ljr.jizhang.framework.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljr.jizhang.framework.widget.BaseProgressDialog;
import com.ljr.jizhang.utils.AppLogger;

import nucleus.presenter.Presenter;
import nucleus.view.NucleusSupportFragment;

public abstract class BaseFragment<P extends Presenter> extends NucleusSupportFragment<P> {
    private BaseProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AppLogger.i(getClass().getSimpleName() + " onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView();
        initData();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initData();

    protected abstract void initView();

    /**
     * 显示加载对话框
     */
    protected ProgressDialog progressShow(String dialogDetail) {
        if (progressDialog != null)
            progressDialog.cancel();
        progressDialog = new BaseProgressDialog(getActivity());
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

}