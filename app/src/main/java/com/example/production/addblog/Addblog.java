package com.example.production.addblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.production.MainHome;
import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.appContext.applicationContext;


public class Addblog extends AppCompatActivity {
    private static final String TAG = "";
    EditText edit_gl, edit_mc;
    CheckBox check_bh, check_wz, check_zd, check_gz;
    private String fl;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addblog);
        initlaiz();//初始化
        setSupportActionBar(toolbar);
    }

    private void initlaiz() {
        edit_gl = findViewById(R.id.edit_gl);
        edit_mc = findViewById(R.id.edit_mc);
        check_bh = findViewById(R.id.check_bh);
        check_wz = findViewById(R.id.check_wz);
        check_zd = findViewById(R.id.check_zd);
        check_gz = findViewById(R.id.check_gz);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, 1, Menu.NONE, "发布");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case Menu.FIRST:
                String strname = applicationContext.strname;
                String strmc = edit_mc.getText().toString();
                String strgl = edit_gl.getText().toString();

                if (check_bh.isChecked()) {
                    fl = check_bh.getText().toString();
                }
                if (check_gz.isChecked()) {
                    fl = check_gz.getText().toString();
                }
                if (check_wz.isChecked()) {
                    fl = check_wz.getText().toString();
                }
                if (check_zd.isChecked()) {
                    fl = check_zd.getText().toString();
                }
                SQL sql = new SQL();
                sql.OpenDB(Addblog.this);
                String strinsert = " insert into " + SQL.MySQL.TABLEBLOG + "( blogname , blogclassify , blogcontent, author) values " +
                        " ('" + strmc + "', '" + fl + "' ,'" + strgl + "','" + strname + "')";
                sql.insertDB(strinsert);
                sql.CloseDB();
                startActivity(new Intent(this, MainHome.class));
        }
        return super.onOptionsItemSelected(item);
    }
}