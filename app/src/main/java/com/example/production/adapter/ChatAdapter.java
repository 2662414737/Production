package com.example.production.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.production.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    List<recyclerchat> recyclerchatList;

    public ChatAdapter(List<recyclerchat> recyclerchatList) {
        this.recyclerchatList = recyclerchatList;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_chat, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recyclerchat recychat = recyclerchatList.get(position);
        Log.e("-------------TAG", "onBindViewHolder: "+recychat.getType());
        if (recychat.getType() == recyclerchat.TYPE_RECEIVED) {                                         //增加对消息类的判断，如果这条消息是收到的，显示左边布局，是发出的，显示右边布局
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.textchat.setText(recychat.getChat());
        } else if (recychat.getType() == recyclerchat.TYPE_SENT) {
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.textchat2.setText(recychat.getChat());
        }

    }


    @Override
    public int getItemCount() {
        return recyclerchatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textchat, textchat2;
        LinearLayout leftLayout, rightLayout;

        public ViewHolder(View itemview) {
            super(itemview);
            textchat = itemview.findViewById(R.id.text_chat);
            textchat2 = itemview.findViewById(R.id.text_chat2);
            leftLayout = itemview.findViewById(R.id.left_layout);
            rightLayout = itemview.findViewById(R.id.right_layout);
        }
    }
}
