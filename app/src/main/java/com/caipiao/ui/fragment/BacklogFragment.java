package com.caipiao.ui.fragment;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.RadioGroup;

import com.caipiao.bean.Account;
import com.caipiao.framework.base.BaseFragment;
import com.caipiao.framework.base.SmartViewHolder;
import com.caipiao.ui.presenter.BacklogPresenter;
import com.caipiao.utils.ToastUtils;
import com.ljr.jizhang.R;
import com.caipiao.framework.base.BaseRecyclerAdapter;
import com.caipiao.framework.refreshlayout.SmartRefreshLayout;
import com.caipiao.framework.refreshlayout.api.RefreshLayout;
import com.caipiao.framework.refreshlayout.listener.OnRefreshListener;
import com.caipiao.ui.activity.AddAccountInfoActivity;
import com.caipiao.utils.AnimationUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nucleus.factory.RequiresPresenter;

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
    protected void initView() {
        radioGroup = (RadioGroup) getActivity().findViewById(R.id.tab_main_radio_group);
        baseRecyclerAdapter = new BaseRecyclerAdapter<Account>(accountList, R.layout.item_account) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Account model, int position) {

            }
        };


    }

    @Override
    protected void initData() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                getPresenter().getData();
                final File sdDir = Environment.getExternalStorageDirectory();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
//                            File[] files = sdDir.listFiles();
//                            for (final File file : files) {
//                                if (file.getName().endsWith(".png")) {
//                                    final Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
//                                    getActivity().runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Log.e("TAG",bitmap.toString()+" "+file.getPath());
//                                            smartRefreshLayout.finishRefresh();
//                                        }
//                                    });
//                                }else {
//
//                                }
//                            }
                        getFile(sdDir);
                    }
                }.start();
            }
        });

    }

    public List<File> getFile(File file) {

        List<File> mFileList = new ArrayList();

        File[] fileArray = file.listFiles();
        for (File f : fileArray) {
            if (f.isFile()) {
                mFileList.add(f);
            } else {
                getFile(f);
            }
        }
        return mFileList;
    }

    @Event({R.id.fragment_backlog_fab_attention_button})
    void doOnClick(View view) {
        Intent mIntent = null;
        switch (view.getId()) {
            case R.id.fragment_backlog_fab_attention_button:
                mIntent = new Intent(getContext(), AddAccountInfoActivity.class);
                break;
            default:
                ToastUtils.getInstance().showShortToast(getResources().getString(R.string.keying_error));
                break;
        }
        startActivity(mIntent);
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


    public void replaceAccount(List<Account> list) {
        baseRecyclerAdapter.refresh(list);

    }


}
