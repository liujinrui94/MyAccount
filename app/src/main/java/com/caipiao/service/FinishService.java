package com.caipiao.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.caipiao.framework.constant.Common;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/11/20 21:38
 * @description:
 */
public class FinishService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("FinishService","onCreate");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("FinishService","onBind");
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("FinishService","onStartCommand");
        String str = intent.getStringExtra("type").substring(9, 15);
        if (str.equals("finish")){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Common.FINISH_LOGIN, false);
            editor.apply();
        }else if (str.equals("openit")){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Common.FINISH_LOGIN, true);
            editor.apply();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
