package com.example.administrator.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/10/17 0017
 * ***************************************
 */
public class MyAppLication extends Application {

    private static DaoSession mDaoSession;
    private SQLiteDatabase mBookDao;
    public static MyAppLication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        initLogger();
        // 配置数据库
        setupDatabaseGreenDao();
        setupDatabaseNative();
    }

    // 配置日志工具
    private void initLogger() {
        Logger.init(getString(R.string.app_name))
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // FULL 打印输出  、 NONE 不打印
                .methodOffset(2);
    }

    // 用android 原生的方式创建数据库
    private void setupDatabaseNative() {
        mBookDao = BookOpenHelper.getInstance().getWritableDatabase();
    }

    // 用greenDao 的方式创建数据库
    private void setupDatabaseGreenDao() {
        // 创建数据库为 bookstore_greendao.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME_BOOK_STORE_GREENDAO);
        // 获取可写数据库
        Database db = helper.getWritableDb();
        // 获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        // 获取dao管理者
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getBookDao() {
        return mBookDao;
    }
}
