package com.ljr.jizhang.framework.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljr.jizhang.framework.widget.BaseProgressDialog;
import com.ljr.jizhang.utils.AppLogger;

import org.xutils.x;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/10/12 10:01
 * @description:
 */
public abstract class XBaseFragment extends Fragment {
    private BaseProgressDialog progressDialog;
    private Context mContext;
    private View RootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AppLogger.i(getClass().getSimpleName() + " onCreate");
        mContext = getActivity();
        super.onCreate(savedInstanceState);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RootView = x.view().inject(this, inflater, container);
        initView();
        initData();
        return RootView;
    }

    public View getRootView() {
        return RootView;
    }

    public void setRootView(View rootView) {
        RootView = rootView;
    }


    protected abstract void initView();

    protected abstract void initData();


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
