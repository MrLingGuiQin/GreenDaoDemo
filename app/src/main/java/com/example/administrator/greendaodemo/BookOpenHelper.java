package com.example.administrator.greendaodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/10/17 0016
 * ***************************************
 */
public class BookOpenHelper extends SQLiteOpenHelper {

    public BookOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 通过static 内部类 实现单例模式
     */
    private static class Holder {
        // 创建bookstore_native.db数据库
        private static final BookOpenHelper INSTANCE = new BookOpenHelper(MyAppLication.INSTANCE, Constants.DB_NAME_BOOK_STORE_NATIVE, null, 1);
    }

    public static BookOpenHelper getInstance() {
        return Holder.INSTANCE;
    }

    public void onCreate(SQLiteDatabase db) {
        //创建book表  Long id, String name, String price, String author, int sell_num,String image_url
        db.execSQL("create table book(" +
                "id integer primary key autoincrement, " +
                "name varchar, " +
                "author varchar, " +
                "price varchar, " +
                "sell_num integer, " +
                "image_url varchar " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
