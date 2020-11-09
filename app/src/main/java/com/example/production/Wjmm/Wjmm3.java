package com.example.production.Wjmm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.production.Login.Login;
import com.example.production.R;
import com.example.production.SQL.SQL;

public class Wjmm3 extends AppCompatActivity {
    EditText edit_xmm,edit_zxmm;
    Button btn_czmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wjmm3);
        edit_xmm = findViewById(R.id.edit_xmm);
        edit_zxmm = findViewById(R.id.edit_zxmm);
        btn_czmm = findViewById(R.id.btn_czmm);
        Intent intent = getIntent();
        final String userzh = intent.getStringExtra("userzh");
        Log.d(userzh, userzh);
        btn_czmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQL sql = new SQL();
                sql.OpenDB(Wjmm3.this);
                if (!edit_xmm.getText().toString().equals(edit_zxmm.getText().toString())){
                    new AlertDialog.Builder(Wjmm3.this)
                            .setTitle("两次密码不一致")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    edit_xmm.setText("");
                                }
                            })
                            .show();
                }else {
                    String strupdata =  " update " + SQL.MySQL.TABLEUSER + " set password= '" + edit_zxmm.getText().toString() + "'  where username= '" + userzh + "'";
                    sql.updataDB(strupdata);
                    startActivity(new Intent(Wjmm3.this, Login.class));

                }

            }
        });
    }
}