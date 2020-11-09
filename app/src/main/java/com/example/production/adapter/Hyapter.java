package com.example.production.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.production.R;
import com.example.production.uitl.ViewHyInformation;

import java.util.List;

public class Hyapter extends RecyclerView.Adapter<Hyapter.ViewHolder> {
    List<recyclerhy> recyclerList;

    public Hyapter(List<recyclerhy> recyclerList){
        this.recyclerList = recyclerList;
    }
    @NonNull
    @Override
    public Hyapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Hyapter.ViewHolder holder, final int position) {
        final recyclerhy recyclerhy = recyclerList.get(position);
        holder.text_nc.setText(recyclerhy.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                    ViewHyInformation.setHy(recyclerList.get(position));
                }
            }
        });


    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_nc;
        public  ViewHolder(@NonNull View itemView){
            super(itemView);
            text_nc = itemView.findViewById(R.id.text_nc);


        }

    }
    @Override
    public int getItemCount() {
        return recyclerList.size();
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
    private BlogsAdapter.OnItemClickListener listener;
    //第二步， 写一个公共的方法
    public void setOnItemClickListener(BlogsAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}

