package com.ljr.jizhang.ui.presenter;

import android.util.Log;

import com.ljr.jizhang.bean.Account;
import com.ljr.jizhang.dao.DaoSession;
import com.ljr.jizhang.framework.base.AppApplication;
import com.ljr.jizhang.ui.fragment.BacklogFragment;

import java.util.ArrayList;
import java.util.List;

import nucleus.presenter.Presenter;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/8/24 16:26
 * @description:
 */
public class BacklogPresenter extends Presenter<BacklogFragment> {
    private DaoSession daoSession = AppApplication.getInstance().getDaoSession();
    private List<Account> accountList=new ArrayList<>();

    public void getData() {
        accountList = daoSession.getAccountDao().loadAll();
        getView().replaceAccount(accountList);
    }


}
