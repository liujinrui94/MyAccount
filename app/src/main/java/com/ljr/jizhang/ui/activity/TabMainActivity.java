package com.ljr.jizhang.ui.activity;

import android.view.View;

import com.ljr.jizhang.R;
import com.ljr.jizhang.framework.base.BaseActivity;
import com.ljr.jizhang.ui.presenter.MainPresenter;
import com.ljr.jizhang.ui.presenter.TabMainPresenter;

import org.xutils.view.annotation.ContentView;

import nucleus.factory.RequiresPresenter;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/8/24 15:16
 * @description:
 */
@RequiresPresenter(TabMainPresenter.class)
@ContentView(R.layout.activity_tab_main)
public class TabMainActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
