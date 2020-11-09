package com.example.production.SQL;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.production.adapter.recyclerhy;

import java.util.ArrayList;
import java.util.List;

public class SQL {
    public MySQL mysql;
    public SQLiteDatabase db;

    //    读写
    public void OpenDB(Context context) {
        mysql = new MySQL(context);
        db = mysql.getWritableDatabase();

    }

    //    关闭数据库
    public void CloseDB() {
        if (db.isOpen()) {
            db.close();
        }

    }

    //     增加
    public void insertDB(String strinsert) {
        db.execSQL(strinsert);
    }

    //   删除
    public void deleteDB(String strdelete) {
        db.execSQL(strdelete);
    }

    //    改变
    public void updataDB(String strupdata) {
        db.execSQL(strupdata);
    }

    //    查询
    public Cursor selectDB(String strselect) {
        return db.rawQuery(strselect, null);
    }

    // 查询好友
    public List<recyclerhy> findFriend(Context c, String strname) {
        List<recyclerhy> recyclerList = new ArrayList<>();
        String strselectRight = " select * from " + MySQL.TABLEFRIEND + " as f," + MySQL.TABLEUSER +
                " as u where f.username = " + strname + " and f.friendname=u.username";

        String strselectLeft = " select * from " + MySQL.TABLEFRIEND + " as f," + MySQL.TABLEUSER +
                " as u where f.friendname = " + strname + " and f.username=u.username";
        OpenDB(c);
        Cursor cursorRight = selectDB(strselectRight);
        while (cursorRight.moveToNext()) {
            String name = cursorRight.getString(cursorRight.getColumnIndex("usernick"));
            String username = cursorRight.getString(cursorRight.getColumnIndex("username"));
            recyclerhy recyclerhy = new recyclerhy(name,username);
            recyclerList.add(recyclerhy);
        }
        Cursor cursorLeft = selectDB(strselectLeft);
        while (cursorLeft.moveToNext()) {
            String name = cursorLeft.getString(cursorLeft.getColumnIndex("usernick"));
            String username = cursorLeft.getString(cursorLeft.getColumnIndex("username"));
            recyclerhy recyclerhy = new recyclerhy(name,username);
            recyclerList.add(recyclerhy);
        }
        for (recyclerhy recyclerhy : recyclerList) {
            Log.e("TAG", recyclerhy.toString());
        }
        return recyclerList;
    }


    public static class MySQL extends SQLiteOpenHelper {
        public static final String DATABASE = "Info.db";
        public static final int VERSION = 1;
        public static final String TABLEUSER = " user ";
        public static final String TABLEFRIEND = " friend ";
        public static final String TABLEBLOG = " blog ";
        public static final String TALUECHAT = " chat ";

        public MySQL(@Nullable Context context) {
            super(context, DATABASE, null, VERSION);
        }

        //usernick 昵称 username 账号 password 密码 gender 性别 userbirthday 生日 gamehobby 爱好 userissue 问题 useranwer 答案；
        //username 我的账号 friendname 朋友账号 ;
        //username 我 friendname 朋友 chat消息
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(" create table " + TABLEUSER + "(id integer primary key autoincrement,usernick cher(10) ,username char(20) unique not null , " +
                    " password char(20) not null , gender cher(2) ,userbirthday cher(10), gamehobby cher(100), userissue cher(20), useranswer cher(20))");
            db.execSQL(" create table " + TABLEFRIEND + "( id integer primary key autoincrement ,username cher(10) not null , friendname cher(20) not null )");

            db.execSQL(" create table " + TABLEBLOG + "(id integer primary key autoincrement, blogname cher(50) not null , " +
                    " blogclassify cher(10) not null , blogcontent cher(100) not null,author cher(10) not null,blogviews INTEGER )");
            db.execSQL(" create table " + TALUECHAT + "( id integer primary key autoincrement , username cher(10) , friendname cher(10), chat cher(100))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

