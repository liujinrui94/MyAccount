package com.ljr.jizhang.ui.activity;

import android.view.View;

import com.ljr.jizhang.R;
import com.ljr.jizhang.framework.base.BaseActivity;
import com.ljr.jizhang.ui.presenter.AddAccountInfoPresent;

import org.xutils.view.annotation.ContentView;

import nucleus.factory.RequiresPresenter;
@RequiresPresenter(AddAccountInfoPresent.class)
@ContentView(R.layout.activity_add_account_info)
public class AddAccountInfoActivity extends BaseActivity<AddAccountInfoPresent> {
    @Override
    protected void initView() {
        initToolbar("新建项目", true);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
