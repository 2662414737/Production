package com.example.production.Amend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.production.Login.Login;
import com.example.production.R;
import com.example.production.SQL.SQL;

public class Xgmymm extends AppCompatActivity {
    EditText edit_xgmm,edit_xgzcmm;
    Button btn_xgmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xgmymm);
        edit_xgmm = findViewById(R.id.edit_xgmm);
        edit_xgzcmm = findViewById(R.id.edit_xgzcmm);
        btn_xgmm = findViewById(R.id.btn_xgmm);


        btn_xgmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xgmm = edit_xgmm.getText().toString();
                String xgzcmm = edit_xgzcmm.getText().toString();
                Intent intent = getIntent();
                String strname = intent.getStringExtra("strname");
                if (!xgmm.equals(xgzcmm)) {
                    new AlertDialog.Builder(Xgmymm.this)
                            .setTitle("错误")
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }

                            })
                            .show();
                    return;
                } else {
                    SQL sql = new SQL();
                    sql.OpenDB(Xgmymm.this);
                    String strupdata = " update " + SQL.MySQL.TABLEUSER + " set password = '" + xgzcmm + "'  where username =  '" + strname + "'";
                    sql.updataDB(strupdata);
                    startActivity(new Intent(Xgmymm.this, Login.class));
                }
            }
        });
    }
}