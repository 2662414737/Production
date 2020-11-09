package com.example.production.Tab;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.appContext.applicationContext;
import com.example.production.uitl.MyPw03_2;
import com.example.production.uitl.Mypw03;

import de.hdodenhof.circleimageview.CircleImageView;

public class Tab03fragment extends Fragment {
    TextView text_nc,text_zh,text_sr,text_ah,text_xb;
    LinearLayout lin_tx;
    CircleImageView list_imagetx;
    public static String strname="";





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab03,container,false);
        return view;

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        myxx();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        strname = applicationContext.strname;
        text_nc = view.findViewById(R.id.text_nc);
        text_zh = view.findViewById(R.id.text_zh);
        text_sr = view.findViewById(R.id.text_sr);
        text_ah = view.findViewById(R.id.text_ah);
        lin_tx = view.findViewById(R.id.lin_tx);
        text_xb = view.findViewById(R.id.text_xb);
        list_imagetx = view.findViewById(R.id.list_imagetx);
        Button menu = view.findViewById(R.id.btn_xg);
        // 自定义PopupWindow
        final Mypw03 myPW = new Mypw03(this.getActivity(),menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPW.show();
            }
        });
        final MyPw03_2 myPw03_2 = new MyPw03_2(this.getActivity(),lin_tx);
        lin_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPw03_2.show();
            }
        });

    }
    public void myxx() {
        final SQL sql = new SQL();
        sql.OpenDB(this.getContext());
        Cursor cursor= sql.selectDB("select * from "+ SQL.MySQL.TABLEUSER +" where username='"+strname+"'");
        while (cursor.moveToNext()){
            text_nc.setText(cursor.getString(cursor.getColumnIndex("usernick")));
            text_zh.setText(cursor.getString(cursor.getColumnIndex("username")));
            text_sr.setText(cursor.getString(cursor.getColumnIndex("userbirthday")));
            text_ah.setText(cursor.getString(cursor.getColumnIndex("gamehobby")));
            text_xb.setText(cursor.getString(cursor.getColumnIndex("gender")));
        }
        cursor.close();
        sql.CloseDB();
    }
}