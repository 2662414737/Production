package com.example.production.uitl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.production.Amend.Xgmymm;
import com.example.production.Amend.Xgmyxx;
import com.example.production.Amend.mmbh;
import com.example.production.MainHome;
import com.example.production.R;
import com.example.production.Tab.Tab03fragment;

public class MyPw03_2  extends PopupWindow{
    private Context context;
    private View view,item;


    public MyPw03_2(Context context, View item){
        this.context = context;
        this.item = item;
    }
    public void show(){
        //实例化一个布局，作为之后的菜单样式
        view= LayoutInflater.from(context).inflate(R.layout.pop_item,null);
        Button icon_btn_camera = view.findViewById(R.id.icon_btn_camera);
        Button icon_btn_select = view.findViewById(R.id.icon_btn_select);
        Button icon_btn_cancel = view.findViewById(R.id.icon_btn_cancel);
        icon_btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, Xgmyxx.class);
//                context.startActivity(intent);
            }
        });
        icon_btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, Xgmymm.class));
            }
        });
        icon_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();


            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.ll_pop).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        //初始化，参1为具体菜单样式的实例化对象，参23为菜单的宽高
        PopupWindow pop=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
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
