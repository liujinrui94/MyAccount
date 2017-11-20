package com.caipiao.ui.presenter;

import com.caipiao.bean.Account;
import com.caipiao.dao.DaoSession;
import com.caipiao.framework.base.AppApplication;
import com.caipiao.ui.fragment.BacklogFragment;

import java.util.ArrayList;
import java.util.List;

import nucleus.presenter.Presenter;

public class BacklogPresenter extends Presenter<BacklogFragment> {
    private DaoSession daoSession = AppApplication.getInstance().getDaoSession();
    private List<Account> accountList=new ArrayList<>();

    public void getData() {
        accountList = daoSession.getAccountDao().loadAll();
        getView().replaceAccount(accountList);
    }


}
