package com.example.production.uitl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.production.Tab.Tab03fragment;
import com.example.production.addblog.Addblog;
import com.example.production.Login.Login;
import com.example.production.R;

public class MyPW {
    private Context context;
    private View view,item;


    public MyPW(Context context,View item){
        this.context = context;
        this.item = item;
    }

    public void show(){
        //实例化一个布局，作为之后的菜单样式
        view= LayoutInflater.from(context).inflate(R.layout.fun_popup,null);
        LinearLayout addblog = view.findViewById(R.id.text_addblogs);
        LinearLayout qhzh = view.findViewById(R.id.text_qhzh);
        addblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Addblog.class);
                intent.putExtra("strname", Tab03fragment.strname);
                context.startActivity(intent);
            }
        });
        qhzh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Login.class));
            }
        });
        //初始化，参1为具体菜单样式的实例化对象，参23为菜单的宽高
        PopupWindow pop=new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //想PopupWindow点击外侧时消失需要设置一个背景，才能成功
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);//获取焦点
        pop.setOutsideTouchable(true);//点击外侧消失
        //设置位置，参1表示显示在哪个组件下，参23表示偏移值
        pop.showAsDropDown(item,20,13);
    }

    public View getView(){
        return view;
    }


}
