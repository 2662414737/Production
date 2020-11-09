package com.example.production.Amend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.production.R;
import com.example.production.SQL.SQL;

public class mmbh extends AppCompatActivity {
    EditText edit_wt,edit_da,edit_zh;
    Button btn_tjmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmbh);
        edit_wt = findViewById(R.id.edit_wt);
        edit_da = findViewById(R.id.edit_da);
        edit_zh = findViewById(R.id.edit_zh);
        btn_tjmb = findViewById(R.id.btn_tjmb);

        btn_tjmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQL sql = new SQL();
                sql.OpenDB(mmbh.this);
                String strupdata =  " update " + SQL.MySQL.TABLEUSER + " set userissue= '" + edit_wt.getText().toString() + "' , useranswer= '" + edit_da.getText().toString() +"'  where username= '" + edit_zh.getText().toString() + "'";
                sql.updataDB(strupdata);
                Toast.makeText(mmbh.this,"添加成功",Toast.LENGTH_SHORT);
                Log.d(strupdata, "添加成功");
                sql.CloseDB();

            }
        });

    }
}