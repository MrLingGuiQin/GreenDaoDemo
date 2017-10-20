package com.example.administrator.greendaodemo;

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
 * statement: 使用 greenDao操作数据库 activity
 * auther: lingguiqin
 * date created : 2017/10/19 0019
 * ***************************************
 */
public class UseGreenDaoActivity extends AppCompatActivity {

    private StringBuilder mStringBuilder = new StringBuilder();
    private List<Book> mBookList = new ArrayList<>();
    private BookDao mBookDao;
    private RecyclerView mRecyclerView;
    private BookAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        // 从数据库查询所有的数据
        mBookDao = MyAppLication.INSTANCE.getDaoSession().getBookDao();
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

        // 以下查询结果请查看日志
        queryEq();
        queryLike();
        queryBetween();
        queryGt();
        queryGe();
        queryLt();
        queryLe();
        queryIsNull();
        queryIsNotNull();
        queryAllAsc();
        queryAllDesc();
        querySql();
    }

    // 添加一条书
    private Book addBook() {
        int index = mBookList.size();
        Book book = new Book((long) index,
                "幸福的拾荒者" + index,
                "胡歌" + index,
                index + ".00",
                "https://img13.360buyimg.com/n1/s200x200_16948/bdc0262d-ae08-4a3f-9c4a-f9dbeb82c419.jpg",
                index * 10);
        mBookList.add(book);
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
        mBookDao.insert(book);
    }

    // 插入多条数据
    public void insertInTx(List<Book> bookList) {
        mBookDao.insertInTx(bookList);
    }

    // 插入多条数据
    public void insertOrReplaceInTx(List<Book> bookList) {
        mBookDao.insertOrReplaceInTx(bookList);
    }

    // 删除数据
    public void delete(Book book) {
        mBookDao.delete(book);
        mBookDao.deleteInTx();
    }

    // 删除多条数据
    public void deleteInTx(List<Book> bookList) {
        mBookDao.deleteInTx(bookList);
    }

    // 修改数据
    public void update(Book book) {
        mBookDao.update(book);
    }

    // 修改多条数据
    public void updateInTx(List<Book> bookList) {
        mBookDao.updateInTx(bookList);
    }

    // 查询所有数据
    public void queryAll() {
        mBookList = mBookDao.loadAll();
//      mBookDao.queryBuilder().list();
    }

    // 按 equal = 条件查询数据
    // eg:查找符合 作者：胡歌1
    public void queryEq() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Author.eq("胡歌1")).list();
        logBooks(books, "输出作者是胡歌1 的书籍 ：\n");
    }

    // 按 notEqual != 条件查询数据
    // eg:查找不符合 作者：胡歌1
    public void queryNotEq() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Author.notEq("胡歌1")).list();
        logBooks(books, "输出作者不 是胡歌1 的书籍 ：\n");
    }

    // 按模糊条件查询数据
    // eg:查找 作者名字以胡歌开头的书籍
    // % 为占位通配符
    public void queryLike() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Author.like("胡歌%")).list();
        logBooks(books, "输出作者名字以胡歌开头的书籍 ：\n");
    }

    // 按参数区间条件查询数据
    // eg:查找 销量 20 - 100 本 的书籍
    public void queryBetween() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Sell_num.between(20, 100)).list();
        logBooks(books, "输出销量 20 - 100 本的书籍 ：\n");
    }

    // 按参数 > 条件查询数据
    // eg:查找 销量 >20 本 的书籍
    public void queryGt() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Sell_num.gt(20)).list();
        logBooks(books, "输出销量> 20 的书籍 ：\n");
    }

    // 按参数 >= 条件查询数据
    // eg:查找 销量 >=20 本 的书籍
    public void queryGe() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Sell_num.ge(20)).list();
        logBooks(books, "输出销量>= 20 的书籍 ：\n");
    }

    // 按参数 < 条件查询数据
    // eg:查找 销量 < 20 本 的书籍
    public void queryLt() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Sell_num.lt(20)).list();
        logBooks(books, "输出销量< 20 的书籍 ：\n");
    }

    // 按参数 <= 条件查询数据
    // eg:查找 销量 <=20 本 的书籍
    public void queryLe() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Sell_num.le(20)).list();
        logBooks(books, "输出销量<= 20 的书籍 ：\n");
    }

    // 按参数 isNull 条件查询数据
    // eg:查找 销量 = null 本 的书籍
    public void queryIsNull() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Sell_num.isNull()).list();
        logBooks(books, "输出销量= null 的书籍 ：\n");
    }

    // 按参数 isNotNull 条件查询数据
    // eg:查找 销量 <=null 本 的书籍
    public void queryIsNotNull() {
        List<Book> books = mBookDao.queryBuilder().where(BookDao.Properties.Sell_num.isNotNull()).list();
        logBooks(books, "输出销量 != null 的书籍 ：\n");
    }

    // 升序排序
    // eg:所有书籍按销量升序排序
    public void queryAllAsc() {
        List<Book> books = mBookDao.queryBuilder().orderAsc(BookDao.Properties.Sell_num).list();
        logBooks(books, "输出书籍按销量升序排序 ：\n");
    }


    // 升序排序
    // eg:所有书籍按销量升序排序
    public void queryAllDesc() {
        List<Book> books = mBookDao.queryBuilder().orderDesc(BookDao.Properties.Sell_num).list();
        logBooks(books, "输出书籍按销量降序排序 ：\n");
    }

    // 使用sql条件进行查询
    // eg:输出 销量：>= 20 的书籍
    public void querySql() {
        List<Book> books = mBookDao.queryBuilder()
                .where(new WhereCondition.StringCondition("_id IN (SELECT _id FROM BOOK WHERE SELL_NUM >= 20)")).list();
        logBooks(books, "输出 销量：>= 20 的书籍 ：\n");
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
        mBookList = mBookDao.loadAll();
        mAdapter.setListData(mBookList);
        logBooks(mBookList, logTitle);
    }

}
