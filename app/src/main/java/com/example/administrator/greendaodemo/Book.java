package com.example.administrator.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/10/19 0019
 * ***************************************
 */
@Entity
public class Book {

    // Id不能用int类型，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
    @Id(autoincrement = true)
    private Long id; // 主键 图书id
    // 书的名称
    @Unique // 标记该属性值必须在数据库中是唯一值，不可重复
    private String name;
    // 书的作者
    private String author;
    // 书的价格
    private String price;
    // 书的图标
    private String image_url;
    // 书的销量
    private int sell_num;

    @Generated(hash = 1332569597)
    public Book(Long id, String name, String author, String price, String image_url,
                int sell_num) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.image_url = image_url;
        this.sell_num = sell_num;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Book(String name, String author, String price, String image_url,
                int sell_num) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.image_url = image_url;
        this.sell_num = sell_num;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getSell_num() {
        return this.sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                ", image_url='" + image_url + '\'' +
                ", sell_num=" + sell_num +
                '}';
    }
}
