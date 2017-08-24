package com.ljr.jizhang.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import com.ljr.jizhang.R;
import com.ljr.jizhang.adapter.TabFragmentAdapter;
import com.ljr.jizhang.framework.base.BaseActivity;
import com.ljr.jizhang.ui.fragment.BacklogFragment;
import com.ljr.jizhang.ui.presenter.MainPresenter;
import com.ljr.jizhang.ui.presenter.TabMainPresenter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

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
    public List<Fragment> fragments = new ArrayList<>();
    private TabFragmentAdapter mTabFragmentAdapter;

    @ViewInject(R.id.tab_main_radio_group)
    private RadioGroup radioGroup;

    @Override
    protected void initView() {
        fragments.add(new BacklogFragment());
        fragments.add(new BacklogFragment());
        fragments.add(new BacklogFragment());
        fragments.add(new BacklogFragment());
        mTabFragmentAdapter = new TabFragmentAdapter(this, fragments, R.id.tab_content, radioGroup, 0);
        mTabFragmentAdapter.setOnRgsExtraCheckedChangedListener(new TabFragmentAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {


            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
