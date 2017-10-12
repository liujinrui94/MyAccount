package com.ljr.jizhang.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ljr.jizhang.R;
import com.ljr.jizhang.bean.SscBean;
import com.ljr.jizhang.framework.base.BaseRecyclerAdapter;
import com.ljr.jizhang.framework.base.SmartViewHolder;
import com.ljr.jizhang.framework.base.XBaseFragment;
import com.ljr.jizhang.framework.constant.Constant;
import com.ljr.jizhang.framework.refreshlayout.SmartRefreshLayout;
import com.ljr.jizhang.utils.GsonUtil;
import com.ljr.jizhang.utils.HttpUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


@ContentView(R.layout.fragment_home)
public class HomeFragment extends XBaseFragment {

    @ViewInject(R.id.fragment_home_smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @ViewInject(R.id.fragment_home_rlv)
    RecyclerView recyclerView;

    BaseRecyclerAdapter baseRecyclerAdapter;
    List<SscBean> list = new ArrayList<>();

    @Override
    protected void initView() {
        String response = " [{\"code\":\"7,1,4,7,0\",\"issue\":\"171012052\",\"date\":\"2017-10-12\"},{\"code\":\"8,3,4,7,2\",\"issue\":\"171012051\",\"date\":\"2017-10-12\"},{\"code\":\"9,0,5,1,9\",\"issue\":\"171012050\",\"date\":\"2017-10-12\"},{\"code\":\"6,6,6,9,0\",\"issue\":\"171012049\",\"date\":\"2017-10-12\"},{\"code\":\"5,4,2,7,1\",\"issue\":\"171012048\",\"date\":\"2017-10-12\"},{\"code\":\"0,4,1,9,8\",\"issue\":\"171012047\",\"date\":\"2017-10-12\"},{\"code\":\"3,1,1,9,5\",\"issue\":\"171012046\",\"date\":\"2017-10-12\"},{\"code\":\"4,9,3,5,8\",\"issue\":\"171012045\",\"date\":\"2017-10-12\"},{\"code\":\"8,8,3,4,3\",\"issue\":\"171012044\",\"date\":\"2017-10-12\"},{\"code\":\"4,5,2,8,8\",\"issue\":\"171012043\",\"date\":\"2017-10-12\"},{\"code\":\"6,6,7,8,4\",\"issue\":\"171012042\",\"date\":\"2017-10-12\"},{\"code\":\"6,8,0,0,7\",\"issue\":\"171012041\",\"date\":\"2017-10-12\"},{\"code\":\"1,2,8,2,8\",\"issue\":\"171012040\",\"date\":\"2017-10-12\"},{\"code\":\"2,5,1,5,8\",\"issue\":\"171012039\",\"date\":\"2017-10-12\"},{\"code\":\"0,9,4,5,0\",\"issue\":\"171012038\",\"date\":\"2017-10-12\"},{\"code\":\"4,0,5,2,8\",\"issue\":\"171012037\",\"date\":\"2017-10-12\"},{\"code\":\"6,1,1,3,7\",\"issue\":\"171012036\",\"date\":\"2017-10-12\"},{\"code\":\"8,2,9,9,9\",\"issue\":\"171012035\",\"date\":\"2017-10-12\"},{\"code\":\"6,7,1,2,6\",\"issue\":\"171012034\",\"date\":\"2017-10-12\"},{\"code\":\"3,1,2,2,9\",\"issue\":\"171012033\",\"date\":\"2017-10-12\"}]";
        list = GsonUtil.getInstance().fromJson(response,
                new TypeToken<List<SscBean>>() {
                }.getType());
        baseRecyclerAdapter = new BaseRecyclerAdapter<SscBean>(list, R.layout.item_lottery_openlist) {

            @Override
            protected void onBindViewHolder(SmartViewHolder holder, SscBean model, int position) {
                TextView textView = new TextView(getActivity());
                //这里的Textview的父layout是ListView，所以要用ListView.LayoutParams

            }
        };
        recyclerView.setAdapter(baseRecyclerAdapter);
    }


    @Override
    protected void initData() {

    }

}