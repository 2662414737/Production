package com.example.production.uitl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.production.Amend.Tjhy;
import com.example.production.R;
import com.example.production.Tab.Tab02fragment;
import com.example.production.Tab.Tab03fragment;

public class Mypw02 {
    private Context context;
    private View view,item;


    public Mypw02(Context context,View item){
        this.context = context;
        this.item = item;
    }
    public void show(){
        view = LayoutInflater.from(context).inflate(R.layout.pop_item02,null);
        LinearLayout lin_tjhy = view.findViewById(R.id.lin_tjhy);
        lin_tjhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Tjhy.class);
                intent.putExtra("strname", Tab03fragment.strname);
                context.startActivity(intent);
            }
        });
        PopupWindow pop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.showAsDropDown(item,0,0);
    }
    public View getView(){
        return view;
    }
}
