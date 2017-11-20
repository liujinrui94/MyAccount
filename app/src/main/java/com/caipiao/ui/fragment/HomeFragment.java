package com.caipiao.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caipiao.R;
import com.caipiao.bean.SscBean;
import com.caipiao.framework.base.BaseRecyclerAdapter;
import com.caipiao.framework.base.SmartViewHolder;
import com.caipiao.framework.base.XBaseFragment;
import com.caipiao.framework.refreshlayout.SmartRefreshLayout;
import com.caipiao.utils.GsonUtil;
import com.google.gson.reflect.TypeToken;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        baseRecyclerAdapter = new BaseRecyclerAdapter<SscBean>(list, R.layout.item_lottery_openlist) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, SscBean model, int position) {
                holder.text(R.id.tv_kind, model.getIssue());
                LinearLayout linearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_number);
                linearLayout.removeAllViews();
                for (int i = 0; i < model.getCode().length(); i++) {
                    TextView tv = new TextView(getActivity());
                    //2.把信息设置为文本框的内容
                    tv.setText("" + model.getCode().charAt(i));
                    tv.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.tuoyuan_chengse));
                    tv.setGravity(Gravity.CENTER);
                    tv.setPadding(5, 5, 5, 5);
                    tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                    linearLayout.addView(tv);

                }

            }
        };
        recyclerView.setAdapter(baseRecyclerAdapter);
    }


    @Override
    protected void initData() {

    }

}