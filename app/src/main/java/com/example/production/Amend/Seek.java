package com.example.production.Amend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.adapter.BlogsAdapter;
import com.example.production.adapter.recyclerblog;
import com.example.production.uitl.MyPW;

import java.util.ArrayList;
import java.util.List;

public class Seek extends AppCompatActivity {
    private List<recyclerblog> mrecyclerList = new ArrayList<>();
    EditText edit_ss;
    RecyclerView ss;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        edit_ss = findViewById(R.id.edit_ss);
        ss = findViewById(R.id.ss);
        ss.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        ss.setAdapter(new BlogsAdapter(mrecyclerList));
        //实现搜索框回车搜索
        edit_ss.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    return true;
                }
                return false;
            }
        });
        edit_ss.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edit_ss.getText().toString().equals("")) {
                    String strselect = " and blogname like '%" + edit_ss.getText().toString() + "%' ";
                    Listsql(strselect);
                }
            }
        });
    }

    private void Listsql(String s) {
        mrecyclerList.clear();
        SQL sql = new SQL();
        sql.OpenDB(Seek.this);
        String strselect = " select * from " + SQL.MySQL.TABLEBLOG + "," + SQL.MySQL.TABLEUSER + " where blog.author = user.username " + s;
        Cursor cursor = sql.selectDB(strselect);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("blogname"));
            String fl = cursor.getString(cursor.getColumnIndex("blogclassify"));
            String blog = cursor.getString(cursor.getColumnIndex("blogcontent"));
            String author = cursor.getString(cursor.getColumnIndex("usernick"));
            int blogviews = cursor.getInt(cursor.getColumnIndex("blogviews"));
            recyclerblog recyclerblog = new recyclerblog(name, blog, fl, author, blogviews);
            mrecyclerList.add(recyclerblog);
        }
        cursor.close();
        sql.CloseDB();

    }
}