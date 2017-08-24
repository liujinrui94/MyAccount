package com.ljr.jizhang.ui.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.ljr.jizhang.R;
import com.ljr.jizhang.framework.base.BaseActivity;
import com.ljr.jizhang.framework.widget.BaseDialog;

public class SplashActivity extends BaseActivity  {
    private Context context;
    private BaseDialog downloadDialog;


    @Override
    protected void initView() {
        View view = View.inflate(this, R.layout.activity_splash, null);
        context = this;
        setContentView(view);
        into(view);
    }


    @Override
    protected void initData() {

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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 1000);
            }
        });
    }




}