package com.example.production.Wjmm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.production.R;
import com.example.production.SQL.SQL;

public class Wjmm2 extends AppCompatActivity {
    String userda = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wjmm2);

        final EditText edit_da = findViewById(R.id.edit_da2);
        Button btn_xyb2 = findViewById(R.id.btn_xyb2);
        final TextView text_wt = findViewById(R.id.text_wt);
        Intent intent = getIntent();
        final String userzh = intent.getStringExtra("userzh");


        final SQL sql = new SQL();
        sql.OpenDB(Wjmm2.this);
        Cursor cursor = sql.selectDB("select * from "+ SQL.MySQL.TABLEUSER +" where username='"+ userzh + "'");
        while (cursor.moveToNext()){
            text_wt.setText(cursor.getString(cursor.getColumnIndex("userissue")));
        }

        btn_xyb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userda = edit_da.getText().toString();
                Cursor cursor1 = sql.selectDB(" select * from "+ SQL.MySQL.TABLEUSER + " where useranswer= '"+ userda + "' and username='"+ userzh +"'" );
                if (cursor1.moveToNext()){
                    Intent intent1 = new Intent(Wjmm2.this,Wjmm3.class);
                    intent1.putExtra("userzh",userzh);
                    startActivity(intent1);
                }else {
                    new AlertDialog.Builder(Wjmm2.this)
                            .setTitle("答案错误!")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    edit_da.setText("");
                                }
                            })
                            .show();

                }



            }
        });
    }
}