package com.example.production.Tab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.adapter.recyclerblog;
import com.example.production.uitl.PublicData;

import static android.content.ContentValues.TAG;

public class tab01_content extends AppCompatActivity {

    private String TAG = "";
    int views;
    TextView text_author,textView11,text_blogcount,text_fl,text_blogviews;
//            作者       攻略名称        攻略内容    攻略分类   浏览量


    @Override
    protected void onResume() {
        super.onResume();
        getviews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab01_content);
        text_author = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        text_blogcount = findViewById(R.id.text_blogcount);
        text_fl = findViewById(R.id.text_fl);
        text_blogviews = findViewById(R.id.text_blogviews);
        recyclerblog r = PublicData.getRecyclerblog();
        text_fl.setText(r.getFl());
        text_author.setText(r.getAuthor());
        text_blogcount.setText(r.getBlog());
        textView11.setText(r.getName());
        text_blogviews.setText(String.valueOf(r.getViews()+1));
//        getviews();
    }
    private void getviews() {
        SQL sql = new  SQL();
        sql.OpenDB(tab01_content.this);
        String strselect = " select * from " + SQL.MySQL.TABLEBLOG + " where blogname = '" + textView11.getText().toString() +"'";
        Cursor cursor = sql.selectDB(strselect);
        while (cursor.moveToNext()){
            views = cursor.getInt(cursor.getColumnIndex("blogviews"));
            views = views + 1;
        }
        String strupdata = " update " + SQL.MySQL.TABLEBLOG + " set blogviews = '" + views + "' where blogname = '"+ textView11.getText().toString() + "'";
        sql.updataDB(strupdata);
    }
}