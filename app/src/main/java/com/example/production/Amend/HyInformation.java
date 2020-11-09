package com.example.production.Amend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.production.MainHome;
import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.adapter.recyclerhy;
import com.example.production.appContext.applicationContext;
import com.example.production.uitl.ViewHyInformation;

public class HyInformation extends AppCompatActivity {
    TextView text_hymc, text_hyzh, textView16, text_hysr, text_hyah;
    //            好友名称    好友账号   好友性别   好友生日  好友爱好
    Button btn_schy, btn_hyxx;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_friends);
        text_hyah = findViewById(R.id.text_hyah);
        text_hymc = findViewById(R.id.text_hymc);
        text_hyzh = findViewById(R.id.text_hyzh);
        textView16 = findViewById(R.id.textView16);
        text_hysr = findViewById(R.id.text_hysr);
        btn_schy = findViewById(R.id.btn_schy);
        btn_hyxx = findViewById(R.id.btn_hyxx);
        recyclerhy hy = ViewHyInformation.getHy();
        text_hymc.setText(hy.getName());
        Intent intent = getIntent();
        name = intent.getStringExtra("friendname");
        Log.e("TAG-------------", "onCreate: " + name);

        final SQL sql = new SQL();
        sql.OpenDB(HyInformation.this);
        String strselect = " select * from " + SQL.MySQL.TABLEUSER + " where username='" + name + "'";
        Cursor cursor = sql.selectDB(strselect);
        while (cursor.moveToNext()) {
            text_hyah.setText(cursor.getString(cursor.getColumnIndex("gamehobby")));
            text_hysr.setText(cursor.getString(cursor.getColumnIndex("userbirthday")));
            text_hyzh.setText(cursor.getString(cursor.getColumnIndex("username")));
            textView16.setText(cursor.getString(cursor.getColumnIndex("gender")));
        }
        btn_schy.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "HyInformation";

            @Override
            public void onClick(View v) {
                Log.d(TAG, String.valueOf(text_hyzh));
                String strdelete = " delete from " + SQL.MySQL.TABLEUSER + " where username = '" + text_hyzh.getText().toString() + "'";
                sql.deleteDB(strdelete);
                Intent intent = new Intent(HyInformation.this, MainHome.class);
                intent.putExtra("id", 1);
                startActivity(intent);
            }
        });
        btn_hyxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HyInformation.this, Chat.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });


    }
}