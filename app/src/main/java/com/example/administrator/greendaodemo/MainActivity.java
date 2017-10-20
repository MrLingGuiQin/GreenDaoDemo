package com.example.administrator.greendaodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/10/17 0017
 * ***************************************
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 使用 greendao 点击事件
    public void useGreendaoClick(View v) {
        startActivity(new Intent(this, UseGreenDaoActivity.class));
    }

    // 使用 原生api 点击事件
    public void useNativeClick(View v) {
        startActivity(new Intent(this, UseNativeActivity.class));
    }
}
