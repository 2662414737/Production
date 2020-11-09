package com.example.production.Wjmm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.production.R;
import com.example.production.SQL.SQL;

public class Wjmm1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wjmm1);
        Toolbar  toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        Button btn_xyb = findViewById(R.id.btn_xyb);
        final EditText edit_zh = findViewById(R.id.edit_zh);
        btn_xyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userzh = edit_zh.getText().toString();
                SQL sql = new SQL();
                sql.OpenDB(Wjmm1.this);
                Cursor cursor = sql.selectDB("select * from "+ SQL.MySQL.TABLEUSER +" where username='"+ userzh +"'");
                if (!cursor.moveToNext()){
                    new AlertDialog.Builder(Wjmm1.this)
                            .setTitle("账号不存在!")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    edit_zh.setText("");
                                }
                            })
                            .show();
                } else {
                    Intent intent = new Intent(Wjmm1.this,Wjmm2.class);
                    intent.putExtra("userzh",userzh);
                    startActivity(intent);
                }

            }
        });
    }
}