//package com.ljr.jizhang.ui.activity;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.widget.ListView;
//
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//
//
//import static com.baidu.location.d.j.R;
//
//public class TestActivity extends AppCompatActivity {
//
//    private ListView liuchangbanList;
//    private liuchangbanListAdapter adapter;
//    private List<LiuchangbanModel.ResultlistBean> list;
//    private LiuchangbanModel resultlistBean;
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_liuchangban_list);
//
//
//        initToolBar(toolbar);
//        liuchangbanList = (ListView) findViewById(R.id.liuchangbanList);
//        adapter = new liuchangbanListAdapter();
//        liuchangbanList.setAdapter(adapter);
//
//        list = new ArrayList<>();
//        String data = getResources().getString(R.string.liuchangbanquanchang);
//        Gson gson = new Gson();
//        resultlistBean = gson.fromJson(data, LiuchangbanModel.class);
//        LogUtils.i("resultlistBean" + resultlistBean);
//        list.addAll(resultlistBean.getResultlist());
//
//        adapter.setData(list);
//
//
//    }
//}
