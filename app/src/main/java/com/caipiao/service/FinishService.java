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

        return super.onStartCommand(intent, flags, startId);
    }
}
