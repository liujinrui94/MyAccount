package com.caipiao.ui.presenter;

import com.caipiao.bean.Account;
import com.caipiao.dao.DaoSession;
import com.caipiao.framework.base.AppApplication;
import com.caipiao.framework.refreshlayout.api.RefreshLayout;
import com.caipiao.framework.refreshlayout.SmartRefreshLayout;
import com.caipiao.framework.refreshlayout.listener.OnRefreshListener;
import com.caipiao.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import nucleus.presenter.Presenter;

public class MainPresenter extends Presenter<MainActivity> implements OnRefreshListener {
    private DaoSession daoSession = AppApplication.getInstance().getDaoSession();
    private List<Account> accountList = new ArrayList<>();

    public void getData() {
        accountList = daoSession.getAccountDao().loadAll();


    }

    public void addData(SmartRefreshLayout mSmartRefreshLayout, Account account) {
        daoSession.getAccountDao().insert(account);
        mSmartRefreshLayout.finishRefresh();
    }

    public void getMoreData() {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData();
    }
}
