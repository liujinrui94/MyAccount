package com.ljr.jizhang.ui.activity;

import android.util.Log;
import android.view.View;

import com.ljr.jizhang.R;
import com.ljr.jizhang.bean.Account;
import com.ljr.jizhang.framework.base.BaseActivity;
import com.ljr.jizhang.framework.base.BaseRecyclerAdapter;
import com.ljr.jizhang.framework.base.SmartViewHolder;
import com.ljr.jizhang.framework.refreshlayout.SmartRefreshLayout;
import com.ljr.jizhang.framework.refreshlayout.api.RefreshLayout;
import com.ljr.jizhang.framework.refreshlayout.listener.OnRefreshListener;
import com.ljr.jizhang.presenter.MainPresenter;
import com.ljr.jizhang.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(MainPresenter.class)
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity<MainPresenter> {

    @ViewInject(R.id.main_activity_smartRefreshLayout)
    private SmartRefreshLayout mSmartRefreshLayout;

    int anInt = 0;
    private BaseRecyclerAdapter mBaseRecyclerAdapter;
    private List<Account> listAccount=new ArrayList<>();

    @Override
    protected void initView() {
        initToolbar("首页", this, true);
        onRefresh();

//        mBaseRecyclerAdapter= new BaseRecyclerAdapter<Account>(listAccount,R.layout.item_account) {
//            @Override
//            protected void onBindViewHolder(SmartViewHolder holder, Account model, int position) {
//
//            }
//        };
    }

    @Event(R.id.btn)
    private void clickRefer(View view) {
        getPresenter().getData();

    }

    protected void onRefresh() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                Account mAccount = new Account();
                anInt++;
                mAccount.setClientName(anInt+"aaaa");
                getPresenter().addData(mSmartRefreshLayout,mAccount);
            }
        });
    }
    public void replaceAccount(List<Account> list) {
//        mBaseRecyclerAdapter.refresh(list);
        Log.e("AAAAAA",list+"");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


}
