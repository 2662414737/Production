package com.example.production.Tab;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.production.Amend.Seek;
import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.adapter.BlogsAdapter;
import com.example.production.adapter.recyclerblog;
import com.example.production.uitl.MyPW;

import java.util.ArrayList;
import java.util.List;

//整理代码ctrl+alt+l
public class Tab01fragment extends Fragment {
    private List<recyclerblog> mrecyclerList = new ArrayList<>();
    RecyclerView blogs;
    private BlogsAdapter myAdapter;
    String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("-------------------------onCreateView-------------------");
        return inflater.inflate(R.layout.tab01, container, false);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        System.out.println("-------------------------onActivityCreated-------------------");
        super.onActivityCreated(savedInstanceState);
        itemsql();
    }

    @Override
    public void onResume() {
        super.onResume();
        //刷新界面
        System.out.println("-------------------------onResume-------------------");
        if (myAdapter != null) {
            itemsql();
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //
        blogs = view.findViewById(R.id.reblogs);
        blogs.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        myAdapter = new BlogsAdapter(mrecyclerList);
        blogs.setAdapter(myAdapter);
        // 菜单按钮
        Button menu = view.findViewById(R.id.menu);
        Button btn_ss = view.findViewById(R.id.btn_ss);
        // 自定义PopupWindow
        final MyPW myPW = new MyPW(this.getActivity(), menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPW.show();
            }
        });
        //搜索
        btn_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Seek.class));
            }
        });

    }

    public void itemsql() {

        mrecyclerList.clear();
        //将数据库内容放在recyclerview的子项上
        final SQL sql = new SQL();
        sql.OpenDB(this.getContext());
        String strselect = " select * from " + SQL.MySQL.TABLEBLOG + "," + SQL.MySQL.TABLEUSER + " where blog.author = user.username ";
        final Cursor cursor = sql.selectDB(strselect);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("blogname"));
            String fl = cursor.getString(cursor.getColumnIndex("blogclassify"));
            String blog = cursor.getString(cursor.getColumnIndex("blogcontent"));
            String author = cursor.getString(cursor.getColumnIndex("usernick"));
            int blogviews = cursor.getInt(cursor.getColumnIndex("blogviews"));
            recyclerblog recyclerblog = new recyclerblog(name, blog, fl, author, blogviews);
            mrecyclerList.add(recyclerblog);
        }
        cursor.close();
        sql.CloseDB();
        //recyclerview的点击事件
        myAdapter.setOnItemClickListener(new BlogsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), tab01_content.class);
                startActivity(intent);
            }
        });
    }
}
