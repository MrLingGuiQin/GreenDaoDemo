package com.example.administrator.greendaodemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;


/**
 * ***************************************
 * statement: 使用 android 原生操作数据库 activity
 * auther: lingguiqin
 * date created : 2017/10/19 0019
 * ***************************************
 */
public class UseNativeActivity extends AppCompatActivity {

    private StringBuilder mStringBuilder = new StringBuilder();
    private List<Book> mBookList = new ArrayList<>();
    private SQLiteDatabase mBookDao;
    private RecyclerView mRecyclerView;
    private BookAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        // 从数据库查询所有的数据
        mBookDao = MyAppLication.INSTANCE.getBookDao();
        queryAll();
        mAdapter = new BookAdapter(mBookList);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcy_book);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }


    // 添加数据
    public void addClick(View view) {
        insert(addBook());
        updateData2View("添加数据：\n");
    }

    // 删除数据
    public void deleteClick(View view) {
        if (mBookList.size() > 0) {
            delete(getBook(mBookList.size() - 1));
            updateData2View("删除数据：\n");
        }
    }

    // 修改数据
    public void updateClick(View view) {
        // 修改第一条数据
        if (mBookList.size() > 0) {
            Book book = mBookList.get(0);
            book.setName("第一行代码");
            book.setAuthor("郭霖");
            update(book);
            updateData2View("修改数据：\n");
        }
    }

    // 查询数据
    public void queryClick(View view) {
        updateData2View("全部数据：\n");
    }

    // 添加一条书
    private Book addBook() {
        int index = mBookList.size();
        Book book = new Book(
                "幸福的拾荒者" + index,
                "胡歌" + index,
                index + ".00",
                "https://img13.360buyimg.com/n1/s200x200_16948/bdc0262d-ae08-4a3f-9c4a-f9dbeb82c419.jpg",
                index * 10);
        return book;
    }


    // 根据索引值取书
    private Book getBook(int index) {
        if (mBookList.size() > index) {
            return mBookList.get(index);
        }
        return null;
    }

    // 插入数据
    public void insert(Book book) {
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("author", book.getAuthor());
        values.put("price", book.getPrice());
        values.put("sell_num", book.getSell_num());
        values.put("image_url", book.getImage_url());
        mBookDao.insert(Constants.DB_TABLE_NAME, null, values);
        updateData2View("添加数据 ：\n");
    }

    // 删除数据
    public void delete(Book book) {
        mBookDao.delete(Constants.DB_TABLE_NAME, "id=?", new String[]{book.getId() + ""});
        updateData2View("删除数据 ：\n");
    }


    // 修改数据
    public void update(Book book) {
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("author", book.getAuthor());
        values.put("price", book.getPrice());
        values.put("sell_num", book.getSell_num());
        values.put("image_url", book.getImage_url());
        mBookDao.update(Constants.DB_TABLE_NAME, values, "sell_num=?", new String[]{book.getSell_num() + ""});
        updateData2View("修改数据：\n");
    }


    // 查询所有数据
    public void queryAll() {
        Cursor cursor = mBookDao.query(Constants.DB_TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            mBookList.clear();
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Long id = cursor.getLong(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int sell_num = cursor.getInt(cursor.getColumnIndex("sell_num"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String image_url = cursor.getString(cursor.getColumnIndex("image_url"));
                mBookList.add(new Book(id, name, author, price, image_url, sell_num));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


    /**
     * 打印 books 的书籍
     *
     * @param books     书单
     * @param titleText 标题
     */
    private void logBooks(List<Book> books, String titleText) {
        mStringBuilder.delete(0, mStringBuilder.length());
        mStringBuilder.append(titleText);
        for (int i = 0; i < books.size(); i++) {
            mStringBuilder.append((i + 1) + "  ")
                    .append(books.get(i))
                    .append("\n");
        }
        Logger.d(mStringBuilder.toString());
    }

    /**
     * 刷新数据更新界面
     *
     * @param logTitle 打印日志的标题
     */
    private void updateData2View(String logTitle) {
        queryAll();
        mAdapter.setListData(mBookList);
        logBooks(mBookList, logTitle);
    }

}
