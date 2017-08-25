package com.ljr.jizhang.ui.activity;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.ljr.jizhang.R;
import com.ljr.jizhang.adapter.TabFragmentAdapter;
import com.ljr.jizhang.framework.base.AppApplication;
import com.ljr.jizhang.framework.base.BaseActivity;
import com.ljr.jizhang.service.LocationService;
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
    public static String latitude, longitude, country, city, district, address;
    private LocationService locationService;

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

    @Override
    protected void onStart() {
        super.onStart();
        locationService = AppApplication.getInstance().locationService;
        locationService.registerListener(mListener);
        locationService.start();
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
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
