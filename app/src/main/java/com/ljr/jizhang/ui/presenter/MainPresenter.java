package com.ljr.jizhang.ui.presenter;

import com.ljr.jizhang.bean.Account;
import com.ljr.jizhang.dao.DaoSession;
import com.ljr.jizhang.framework.base.AppApplication;
import com.ljr.jizhang.framework.refreshlayout.SmartRefreshLayout;
import com.ljr.jizhang.framework.refreshlayout.api.RefreshLayout;
import com.ljr.jizhang.framework.refreshlayout.listener.OnRefreshListener;
import com.ljr.jizhang.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import nucleus.presenter.Presenter;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/8/23 16:55
 * @description: 首页
 */
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
