package com.ljr.jizhang.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.ljr.jizhang.R;
import com.ljr.jizhang.framework.base.BaseActivity;
import com.ljr.jizhang.framework.constant.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @Override
    protected void initView() {
        Log.e("ID",JPushInterface.getRegistrationID(this));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1000);
    }


    @Override
    protected void initData() {


    }

    private void getData() {
        OkHttpUtils.get().url(Constant.Url)
                .build().execute(new StringCallback() {

            public void onError(Call call, Exception e, int id) {
                Intent intent = new Intent(getmContext(), TabMainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject data3 = jsonObject.optJSONObject("data");
                    if (data3 != null) {
                        if (data3.getString("show_url") != null) {

                            if (data3.getString("show_url").equals("1")) {
                                Intent intent = new Intent();
                                intent.putExtra("url", data3.getString("url"));
                                intent.setClass(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                return;
                            } else {
                                Intent intent = new Intent();
                                intent.setClass(SplashActivity.this, TabMainActivity.class);
                                startActivity(intent);
                                finish();
                                return;
                            }
                        }
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(SplashActivity.this, TabMainActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {

    }

    // 进入主程序的方法
    private void into(View view) {
        // 如果网络可用则判断是否第一次进入，如果是第一次则进入欢迎界面
        // 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);//透明度从0~1
        alphaAnimation.setDuration(2000);//持续时间
        // 给view设置动画效果
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            // 这里监听动画结束的动作，在动画结束的时候开启一个线程，这个线程中绑定一个Handler,并
            // 在这个Handler中调用goHome方法，而通过postDelayed方法使这个方法延迟500毫秒执行，达到
            // 达到持续显示第一屏500毫秒的效果
            @Override
            public void onAnimationEnd(Animation arg0) {

            }
        });
    }


}