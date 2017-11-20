package com.caipiao.ui.activity;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.caipiao.adapter.TabFragmentAdapter;
import com.caipiao.service.LocationService;
import com.caipiao.ui.presenter.TabMainPresenter;
import com.ljr.jizhang.R;
import com.caipiao.framework.base.BaseActivity;
import com.caipiao.ui.fragment.HomeFragment;
import com.caipiao.ui.fragment.SSCFragment;

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
    public static String latitude, longitude, country, city, district, address;
    private LocationService locationService;

    @Override
    protected void initView() {
        fragments.add(new HomeFragment());
        fragments.add(SSCFragment.newInstance("http://5.9188.com/predict/ssq/"));
        fragments.add(SSCFragment.newInstance("http://112.74.102.204:86/m/tool.html"));
        fragments.add(SSCFragment.newInstance("http://112.74.102.204:86/m/yu.html"));
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
        //如果不做任何处理，浏览网页，点击系统“Back”键，整个Browser会调用finish()而结束自身，
        // 如果希望浏览的网 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件。
        if (keyCode == KeyEvent.KEYCODE_BACK && SSCFragment.webview.canGoBack()) {
            SSCFragment.webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onStart() {
        super.onStart();
//        locationService = AppApplication.getInstance().locationService;
//        locationService.registerListener(mListener);
//        locationService.start();
//        //注册监听
//        int type = getIntent().getIntExtra("from", 0);
//        if (type == 0) {
//            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
//        } else if (type == 1) {
//            locationService.setLocationOption(locationService.getOption());
//        }
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
                latitude = location.getLatitude() + "";
                longitude = location.getLongitude() + "";
                country = location.getCountry();
                city = location.getCity();
                district = location.getDistrict();
                address = location.getAddrStr();
            }
        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };

}
