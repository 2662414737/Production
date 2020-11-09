package com.example.production.Amend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.adapter.ChatAdapter;
import com.example.production.adapter.recyclerchat;
import com.example.production.appContext.applicationContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Chat extends AppCompatActivity {
    private List<recyclerchat> recyclerchatsList = new ArrayList<>();
    RecyclerView chat;
    EditText edit_chat;
    Button btn_chat;
    private ChatAdapter chatAdapter;
    private String TAG;
    String strname = applicationContext.strname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chat = findViewById(R.id.chat);
        edit_chat = findViewById(R.id.edit_chat);
        btn_chat = findViewById(R.id.btn_chat);
        chat.setLayoutManager(new LinearLayoutManager(Chat.this, LinearLayoutManager.VERTICAL, false));
        chatAdapter = new ChatAdapter(recyclerchatsList);
        chat.setAdapter(chatAdapter);
        sqllist();
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=edit_chat.getText().toString();
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");
                final SQL sql = new SQL();
                sql.OpenDB(Chat.this);
                String strinsert = " insert into " + SQL.MySQL.TALUECHAT + "(username,friendname,chat) values('" + strname + "'," +
                        " '" + name + "','" + content+ "') ";
                sql.insertDB(strinsert);
                edit_chat.setText("");
                if (chatAdapter != null) {
//                    recyclerchat msg=new recyclerchat(content,recyclerchat.TYPE_SENT);
//                     recyclerchatsList.add(msg);
                    sqllist();
                    chatAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    private void sqllist() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        recyclerchatsList.clear();
        //将数据库内容放在recyclerview的子项上
        final SQL sql = new SQL();
        sql.OpenDB(Chat.this);
        // 我给好友发的消息
        String strselect = " select * from " + SQL.MySQL.TALUECHAT + " where username= '" + strname + "' and friendname='"+name+"'";
        final Cursor cursor = sql.selectDB(strselect);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String textchat = cursor.getString(cursor.getColumnIndex("chat"));
            recyclerchat recyclerchat = new recyclerchat(id,textchat, com.example.production.adapter.recyclerchat.TYPE_SENT);
            recyclerchatsList.add(recyclerchat);
        }

        // 查询好友消息
        String strselect2 = " select * from " + SQL.MySQL.TALUECHAT + " where username= '" + name + "' and friendname='"+strname+"'";
        System.out.println(strselect);
        System.out.println(strselect2);
        final Cursor cursor2 = sql.selectDB(strselect2);
        while (cursor2.moveToNext()) {
            int id = cursor2.getInt(cursor2.getColumnIndex("id"));
            String textchat = cursor2.getString(cursor2.getColumnIndex("chat"));
            recyclerchat recyclerchat = new recyclerchat(id,textchat, com.example.production.adapter.recyclerchat.TYPE_RECEIVED);
            recyclerchatsList.add(recyclerchat);
        }
        recyclerchatsList.sort(new Comparator<recyclerchat>() {
            @Override
            public int compare(recyclerchat o1, recyclerchat o2) {
                return o1.getId()-o2.getId();
            }
        });
        chat.scrollToPosition(recyclerchatsList.size() - 1);
        sql.CloseDB();
    }
}