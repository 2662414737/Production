package com.example.production.Amend;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.production.MainHome;
import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.appContext.applicationContext;

public class Xgmyxx extends AppCompatActivity {
    EditText editnc;
    TextView textsr;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    String ah;
    Button btn_xg;
    int day,year,month;
    String strname;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xgmyxx);
        editnc = findViewById(R.id.editnc);
        textsr = findViewById(R.id.textsr);
        checkBox1 = findViewById(R.id.Check_1);
        checkBox2 = findViewById(R.id.Check_2);
        checkBox3 = findViewById(R.id.Check_3);
        checkBox4 = findViewById(R.id.check_4);
        strname = applicationContext.strname;
        Log.d(TAG, strname);
        btn_xg = findViewById(R.id.btn_xg);
            //生日选择
        textsr.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                        Calendar c= Calendar.getInstance();
                        year=c.get(Calendar.YEAR);
                        month=c.get(Calendar.MONTH);
                        day=c.get(Calendar.DAY_OF_MONTH);

                        new DatePickerDialog(Xgmyxx.this,2, new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker arg0, int nyear, int nmonth, int nday) {
                                textsr.setText(nyear+"-"+(nmonth+1)+"-"+nday);

                            }
                        },year,month,day).show();
            }
        });

        btn_xg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = getIntent();
//                String strname = intent.getStringExtra("strname");

                SQL sql = new SQL();
                sql.OpenDB(Xgmyxx.this);
                if (checkBox1.isChecked()){
                    ah=checkBox1.getText().toString();
                }
                if (checkBox2.isChecked()){
                    ah=checkBox2.getText().toString();
                }
                if (checkBox3.isChecked()){
                    ah=checkBox3.getText().toString();
                }
                if (checkBox4.isChecked()){
                    ah=checkBox4.getText().toString();
                }

                String strupdata =  " update " + SQL.MySQL.TABLEUSER + " set usernick= '" + editnc.getText().toString() +
                        "' , gamehobby= '" + ah +"' , userbirthday= '" + textsr.getText().toString() + "'where username =  '" + strname +"'";
                sql.updataDB(strupdata);
                Intent intent1 = new Intent(Xgmyxx.this, MainHome.class);
                intent1.putExtra("id",2);
                startActivity(intent1);


            }
        });




    }
}