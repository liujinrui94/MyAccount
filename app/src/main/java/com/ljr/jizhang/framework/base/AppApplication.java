package com.ljr.jizhang.framework.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;


import com.ljr.jizhang.dao.DaoMaster;
import com.ljr.jizhang.dao.DaoSession;
import com.ljr.jizhang.dao.MyOpenHelper;

import org.greenrobot.greendao.query.QueryBuilder;
import org.xutils.x;

import java.util.Stack;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/8/21 14:52
 * @description:
 */
public class AppApplication extends Application {

    public static AppApplication instance;

    public Stack<BaseActivity> allActivitys = new Stack<>();

    private MyOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public AppApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        x.Ext.init(this);
        x.Ext.setDebug(false);
        setDatabase();
    }


    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new MyOpenHelper(this, "ljraccount-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        QueryBuilder.LOG_SQL = true;
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


    public static AppApplication getInstance() {
        return instance;
    }

    public void addActivity(BaseActivity activity) {
        if (allActivitys == null) {
            allActivitys = new Stack<BaseActivity>();
        }
        allActivitys.add(activity);
    }

    public void finishActivity(BaseActivity activity) {
        if (activity != null) {
            allActivitys.remove(activity);
        }
    }

    public void finishAllActivity() {
        for (int i = 0, size = allActivitys.size(); i < size; i++) {
            if (null != allActivitys.get(i)) {
                allActivitys.get(i).finish();
            }
        }
        allActivitys.clear();
    }

    public void finishActivitys() {
        for (int i = 0, size = allActivitys.size(); i < size; i++) {
            if (allActivitys.size() == i) {
                break;
            }
//            if (null != allActivitys.get(i) && !(allActivitys.get(i) instanceof LoginActivity)) {
//                allActivitys.get(i).finish();
//            }

            if (null != allActivitys.get(i)) {
                allActivitys.get(i).finish();
            }
        }
        allActivitys.clear();
    }

    public void AppExit() {
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {

        }
    }

}
