package com.example.production.Login;

import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.production.MainHome;
import com.example.production.R;
import com.example.production.Register.Register;
import com.example.production.SQL.SQL;
import com.example.production.Wjmm.Wjmm1;
import com.example.production.appContext.applicationContext;

import static java.nio.file.Paths.get;

public class Login extends Activity {
    private Button dlbtn;
    EditText editLgzh, editLgmm;
    TextView text_zc, text_wjmm;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();  //初始化组件
        cilck();    //点击事件
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        //
        if (sp.getBoolean("user",false)) {
            editLgzh.setText(sp.getString("userZh",null));
            editLgmm.setText(sp.getString("userPwd",null));
        }
        login();
    }
    //记住密码
    private void login() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USERNAME", editLgzh.getText().toString());
        editor.putString("PASSWORD", editLgmm.getText().toString());
        editor.commit();
    }

    private void initialize() {
        dlbtn = findViewById(R.id.Btn_Dl);
        editLgzh = findViewById(R.id.editLgzh);
        editLgmm = findViewById(R.id.editLgmm);
        text_wjmm = findViewById(R.id.Check_wjmm);
        text_zc = findViewById(R.id.text_zc);
    }

    private void cilck() {
        dlbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //获取文本框信息
                String strname = editLgzh.getText().toString();
                String strpassword = editLgmm.getText().toString();
                SQL sql = new SQL();//获得数据库对象
                sql.OpenDB(Login.this);//打开数据库
                //判断文本框是否为空
                if (strname.equals("") || strpassword.equals("")) {
                    getDataDir("账号和密码不能为空");
                    return;
                }
                Cursor cursor = sql.selectDB("select * from " + SQL.MySQL.TABLEUSER + " where username='" + strname + "'");
                if (!cursor.moveToNext()) {
                    getDataDir("账号不存在");
                    return;
                }
                //查询数据库
                String selectstr = " select * from " + SQL.MySQL.TABLEUSER + " where username = '" + strname + "' and password = '" + strpassword + "'";
                cursor = sql.selectDB(selectstr);


                if (cursor.moveToNext()) {
                    //记住密码
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("userZh",strname);
                    edit.putString("userPwd",strpassword);
                    edit.putBoolean("user",true);
                    edit.commit();
                    //得到当前用户账号
                    applicationContext.strname = strname;
                    Intent intent = new Intent(Login.this, MainHome.class);
                    intent.putExtra("strname", strname);
                    startActivity(intent);

                } else {// 账号错误
                    getDataDir("账号或密码错误");
                }
                cursor.close();
                sql.CloseDB();
            }

            //提示错误方法
            private void getDataDir(String s) {

                new AlertDialog.Builder(Login.this)
                        .setTitle("错误")
                        .setMessage(s)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //清空对话框
                                editLgmm.setText("");
                            }
                        })
                        .setNegativeButton("前往注册", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Login.this, Register.class));
                            }
                        })
                        .show();
            }
        });
        //禁止回车换行
        editLgmm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEND||(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER)){
                    return true;
                }
                return false;
            }
        });
        editLgzh.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEND||(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER)){
                    return true;
                }
                return false;
            }
        });

    }

    public void zc(View view) {
        startActivity(new Intent(Login.this, Register.class));
    }

    public void wjmm(View view) {
        startActivity(new Intent(Login.this, Wjmm1.class));
    }
}