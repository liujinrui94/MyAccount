package com.ljr.jizhang.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RadioGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.ljr.jizhang.R;
import com.ljr.jizhang.bean.Account;
import com.ljr.jizhang.framework.base.BaseFragment;
import com.ljr.jizhang.framework.base.BaseRecyclerAdapter;
import com.ljr.jizhang.framework.base.SmartViewHolder;
import com.ljr.jizhang.framework.refreshlayout.SmartRefreshLayout;
import com.ljr.jizhang.framework.refreshlayout.api.RefreshLayout;
import com.ljr.jizhang.framework.refreshlayout.listener.OnRefreshListener;
import com.ljr.jizhang.service.LocationService;
import com.ljr.jizhang.ui.activity.TabMainActivity;
import com.ljr.jizhang.ui.presenter.BacklogPresenter;
import com.ljr.jizhang.utils.AnimationUtil;
import com.ljr.jizhang.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import nucleus.factory.RequiresPresenter;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/8/24 16:21
 * @description:
 */
@RequiresPresenter(BacklogPresenter.class)
@ContentView(R.layout.fragment_backlog)
public class BacklogFragment extends BaseFragment<BacklogPresenter> {

    private BaseRecyclerAdapter baseRecyclerAdapter;
    private List<Account> accountList = new ArrayList<>();

    @ViewInject(R.id.fragment_backlog_smartRefreshLayout)
    private SmartRefreshLayout smartRefreshLayout;

    @ViewInject(R.id.fragment_backlog_fab_attention_button)
    private FloatingActionButton floatingActionButton;

    private RadioGroup radioGroup;


    @Override
    protected void initData() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getPresenter().getData();
            }
        });

    }

    @Event({R.id.fragment_backlog_fab_attention_button})
    private void doOnClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_backlog_fab_attention_button:

                addAnimation(radioGroup);
                break;


        }

    }

    private void addAnimation(RadioGroup radiogroup) {
        if (radiogroup.getVisibility() == View.VISIBLE) {
            radiogroup.setVisibility(View.INVISIBLE);
            radiogroup.setAnimation(AnimationUtil.moveToViewBottom());
        } else {
            radiogroup.setVisibility(View.VISIBLE);
            radiogroup.setAnimation(AnimationUtil.moveToViewLocation());
        }
    }


    @Override
    protected void initView() {
        radioGroup = (RadioGroup) getActivity().findViewById(R.id.tab_main_radio_group);
        baseRecyclerAdapter = new BaseRecyclerAdapter<Account>(accountList, R.layout.item_account) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Account model, int position) {

            }
        };


    }



    public void replaceAccount(List<Account> list) {
        baseRecyclerAdapter.refresh(list);

    }



}
