package com.example.production;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.production.Tab.Tab01fragment;
import com.example.production.Tab.Tab02fragment;
import com.example.production.Tab.Tab03fragment;

public class MainHome extends FragmentActivity implements View.OnClickListener {
    private LinearLayout mTab01fragment;
    private LinearLayout mTab02fragment;
    private LinearLayout mTab03fragment;
//
    private ImageButton img_sy;
    private ImageButton img_hy;
    private ImageButton img_wd;

    private Tab01fragment mTab01;
    private Tab02fragment mTab02;
    private Tab03fragment mTab03;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        init();//初始化

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mTab01 = new Tab01fragment();
        transaction.add(R.id.id_fragment,mTab01);
        mTab02 = new Tab02fragment();
        transaction.add(R.id.id_fragment,mTab02);
        mTab03 = new Tab03fragment();
        transaction.add(R.id.id_fragment,mTab03);
        hideFragment(transaction);
        transaction.show(mTab01);
        transaction.commitAllowingStateLoss();


        Intent intert=getIntent();
        int id = intert.getIntExtra("id",-1);
        if(id==2)
        {
            setSelect(2);
        }else if (id==1){
            setSelect(1);
        } else if (id==0) {
            setSelect(0);

        }
    }
    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i){
            case 0:
                    hideFragment(transaction);
                    transaction.show(mTab01);
                break;
            case 1:
                    hideFragment(transaction);
                    transaction.show(mTab02);
                break;
            case 2:
                    hideFragment(transaction);
                    transaction.show(mTab03);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
    }

    private void init() {
        //imagebutton
        img_sy = findViewById(R.id.img_sy);
        img_hy = findViewById(R.id.img_hy);
        img_wd = findViewById(R.id.img_wd);
        //tab
        mTab01fragment = findViewById(R.id.lay_sy);
        mTab02fragment = findViewById(R.id.lay_hy);
        mTab03fragment = findViewById(R.id.lay_wd);

        mTab01fragment.setOnClickListener(this);
        mTab02fragment.setOnClickListener(this);
        mTab03fragment.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
//        resetImg();
        switch (v.getId()){
            case R.id.lay_sy:
                setSelect(0);
                break;
            case R.id.lay_hy:
                setSelect(1);
                break;
            case R.id.lay_wd:
                setSelect(2);
                break;
            default:
                break;
        }
    }


}