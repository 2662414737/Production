package com.example.production.Register;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.production.Login.Login;
import com.example.production.R;
import com.example.production.SQL.SQL;

public class Register extends AppCompatActivity {
    EditText editrezh,editremm,editqrmm;
    RadioGroup rgsex ;
    RadioButton radio_nan,radio_nv;
    CheckBox check_1,check_2,check_3,check_4;
    private String xb = "保密";
    private String ih = "保密";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();//初始化
        cilck();    //点击事件

    }
    private void initialize() {
        editrezh = findViewById(R.id.editRezh);
        editremm = findViewById(R.id.editRemm);
        editqrmm = findViewById(R.id.editReqrmm);
        radio_nan = findViewById(R.id.radio_nan);
        radio_nv = findViewById(R.id.radio_nv);
        check_1 = findViewById(R.id.Check_1);
        check_2 = findViewById(R.id.Check_2);
        check_3 = findViewById((R.id.Check_3));
        check_4 = findViewById(R.id.check_4);
    }
    private void cilck() {
    }
    public void dl(View view) {
        //获得文本框信息
        String strname = editrezh.getText().toString();
        String strpaw = editremm.getText().toString();
        String strqrpaw = editqrmm.getText().toString();
        // 数据库连接初始化
        SQL sql = new  SQL();
        sql.OpenDB(Register.this);

        //判断两次密码输入是否一致
        if (!strpaw.equals(strqrpaw)){
            getDataDir("两次密码不一致");
            return;
        }
        // 判断账号是否已存在
        Cursor cursor= sql.selectDB("select * from "+ SQL.MySQL.TABLEUSER +" where username='"+strname+"'");
        if (cursor.moveToNext()){
            getDataDir("账号已存在");
            return;
        }
        if (radio_nan.isChecked()){
            xb="男";
        }
        if (radio_nv.isChecked()){
            xb="女";
        }
        if (check_1.isChecked()){
           ih=check_1.getText().toString();
        }
        if (check_2.isChecked()){
            ih=check_2.getText().toString();
        }
        if (check_3.isChecked()){
            ih=check_3.getText().toString();
        }
        if (check_4.isChecked()){
            ih=check_4.getText().toString();
        }
        String ah="无";
        String sr="无";

        String strinsert = " insert into " + SQL.MySQL.TABLEUSER + "(username,password,gender,gamehobby,userbirthday,gamehobby) values('"+ strname+ "', '"
                + strpaw +"','"+ xb +"','"+ ih +"','" + sr + "','" + ah + "') ";
        sql.insertDB(strinsert);
        new AlertDialog.Builder(Register.this)
                .setMessage(editrezh.getText().toString())
                .setPositiveButton("确认",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    }
                })
                .show();
        cursor.close();
        sql.CloseDB();
    }
    private void getDataDir(String s) {

        new AlertDialog.Builder(Register.this)
                .setTitle("错误")
                .setMessage(s)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //清空对话框
                        editrezh.setText("");
                        editremm.setText("");
                        editqrmm.setText("");
                    }
                })
                .show();
    }
}