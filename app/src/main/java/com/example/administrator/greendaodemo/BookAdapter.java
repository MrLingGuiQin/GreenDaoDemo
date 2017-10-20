package com.example.administrator.greendaodemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/10/14 0014
 * ***************************************
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private List<Book> mBookStoreList = new ArrayList<>();

    public BookAdapter(List<Book> bookStoreList) {
        mBookStoreList.addAll(bookStoreList);
    }

    /**
     * 更新数据
     *
     * @param bookStoreList
     */
    public void setListData(List<Book> bookStoreList) {
        if (bookStoreList != null) {
            mBookStoreList.clear();
            mBookStoreList.addAll(bookStoreList);
            notifyDataSetChanged();
        }
    }


    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookAdapter.MyViewHolder holder, int position) {
        Book bookStore = mBookStoreList.get(position);
        holder.tvName.setText("书名：" + bookStore.getName());
        holder.tvAuthor.setText("作者：" + bookStore.getAuthor());
        holder.tvSellNum.setText("销量：" + bookStore.getSell_num());
        holder.tvPrice.setText("￥" + bookStore.getPrice());
        holder.imgBook.setImageResource(R.drawable.shihuangzhe);
    }


    @Override
    public int getItemCount() {
        return mBookStoreList.isEmpty() ? 0 : mBookStoreList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvAuthor;
        public TextView tvPrice;
        public TextView tvSellNum;
        public ImageView imgBook;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_book_name);
            tvAuthor = itemView.findViewById(R.id.tv_item_book_author);
            tvPrice = itemView.findViewById(R.id.tv_item_book_price);
            tvSellNum = itemView.findViewById(R.id.tv_item_book_sell);
            imgBook = itemView.findViewById(R.id.img_item_book);
        }
    }
}
