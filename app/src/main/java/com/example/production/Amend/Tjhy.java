package com.example.production.Amend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.production.MainHome;
import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.appContext.applicationContext;

public class Tjhy extends AppCompatActivity {
    String strname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjhy);
        final EditText editText = findViewById(R.id.edit_zh);
        Button   btn_hy = findViewById(R.id.btn_hy);
        strname = applicationContext.strname;
        Log.e("---------",strname);
        btn_hy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQL sql = new SQL();
                sql.OpenDB(Tjhy.this);
                String name = editText.getText().toString();
                Log.e("---------",strname+"||||||||||||||||||||||"+name);
                if (Integer.parseInt(strname) > Integer.parseInt(name)) {
                    String strinsert = " insert into " + SQL.MySQL.TABLEFRIEND + "(username,friendname) values('" + strname + "', '"
                            + name + "') ";
                    sql.insertDB(strinsert);
                    Intent intent1 = new Intent(Tjhy.this, MainHome.class);
                    intent1.putExtra("id",1);
                    startActivity(intent1);
                }
                else {
                    String strinsert = " insert into " + SQL.MySQL.TABLEFRIEND + "(username,friendname) values('" + name + "', '"
                            + strname + "') ";
                    sql.insertDB(strinsert);
                    Intent intent1 = new Intent(Tjhy.this, MainHome.class);
                    intent1.putExtra("id",1);
                    startActivity(intent1);
                }
                sql.CloseDB();
            }
        });
    }
}