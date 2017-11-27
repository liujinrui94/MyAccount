package com.caipiao.ui.activity;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.caipiao.R;
import com.caipiao.adapter.TabFragmentAdapter;
import com.caipiao.framework.base.BaseActivity;
import com.caipiao.framework.constant.Constant;
import com.caipiao.ui.fragment.HomeFragment;
import com.caipiao.ui.fragment.SSCFragment;
import com.caipiao.ui.fragment.SmartRefreshLayoutFragment;
import com.caipiao.ui.presenter.TabMainPresenter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import nucleus.factory.RequiresPresenter;


@RequiresPresenter(TabMainPresenter.class)
@ContentView(R.layout.activity_tab_main)
public class TabMainActivity extends BaseActivity {
    public List<Fragment> fragments = new ArrayList<>();
    private TabFragmentAdapter mTabFragmentAdapter;

    @ViewInject(R.id.tab_main_radio_group)
    private RadioGroup radioGroup;

    @Override
    protected void initView() {
//        fragments.add(new HomeFragment());

        fragments.add(SSCFragment.newInstance(Constant.ZHANJI));
//        fragments.add(SmartRefreshLayoutFragment.newInstance(Constant.ZHANJI));
        fragments.add(SmartRefreshLayoutFragment.newInstance(Constant.DALETOU));
        fragments.add(SmartRefreshLayoutFragment.newInstance(Constant.DALETOU_ZIXUN));
        fragments.add(SSCFragment.newInstance(Constant.SHISHICAI));
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
//        locationService.unregisterListener(mListener); //注销掉监听
//        locationService.stop(); //停止定位服务
        super.onStop();
    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
            }
        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };

}
