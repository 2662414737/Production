package com.example.production.Tab;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.production.Amend.HyInformation;
import com.example.production.R;
import com.example.production.SQL.SQL;
import com.example.production.adapter.BlogsAdapter;
import com.example.production.adapter.Hyapter;
import com.example.production.adapter.recyclerhy;
import com.example.production.appContext.applicationContext;
import com.example.production.uitl.Mypw02;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Tab02fragment extends Fragment {

    RecyclerView list_hy;
    private List<recyclerhy> recyclerList = new ArrayList<>();
    private View view;
    private Hyapter hyapter;
    Button btn_tjhy;
    String strname = applicationContext.strname;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab02, container, false);
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_hy = view.findViewById(R.id.list_hy);
        btn_tjhy = view.findViewById(R.id.btn_tjhy);
        Hylist();
        list_hy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        hyapter = new Hyapter(recyclerList);
        list_hy.setAdapter(hyapter);
        final Mypw02 mypw02 = new Mypw02(this.getContext(), btn_tjhy);
        btn_tjhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypw02.show();
            }
        });
        hyapter.setOnItemClickListener(new BlogsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                SQL sql = new SQL();
                sql.OpenDB(getContext());
                String friendname = " ";
                String strselectRight = " select * from " + SQL.MySQL.TABLEFRIEND + " as f," + SQL.MySQL.TABLEUSER +
                        " as u where f.username = " + strname + " and f.friendname=u.username";

                String strselectLeft = " select * from " + SQL.MySQL.TABLEFRIEND + " as f," + SQL.MySQL.TABLEUSER +
                        " as u where f.friendname = " + strname + " and f.username=u.username";
                Cursor cursorRight = sql.selectDB(strselectRight);
                while (cursorRight.moveToNext()) {
                    friendname = cursorRight.getString(cursorRight.getColumnIndex("friendname"));

                }
                Cursor cursorLeft = sql.selectDB(strselectLeft);
                while (cursorLeft.moveToNext()) {
                  friendname = cursorLeft.getString(cursorLeft.getColumnIndex("friendname"));
                }
                Log.d(TAG, friendname);
                Intent intent = new Intent(getActivity(), HyInformation.class);
                intent.putExtra("friendname",recyclerList.get(position).getUsername());
                startActivity(intent);
            }
        });

    }

    public void Hylist() {
//        Log.d(TAG, strname);
        recyclerList = new SQL().findFriend(getContext(), strname);
    }

}
