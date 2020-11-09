package com.example.production.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.production.R;
import com.example.production.uitl.PublicData;

import java.util.List;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.ViewHolder> {

     List<recyclerblog> mrecyclerList;

    public BlogsAdapter(List<recyclerblog> mrecyclerList) {
        this.mrecyclerList = mrecyclerList;
    }

    @NonNull
    @Override
    public BlogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_lite,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BlogsAdapter.ViewHolder holder, final int position) {
        recyclerblog recyblog = mrecyclerList.get(position);
        holder.text_mc.setText(recyblog.getName());
        holder.text_fl.setText(recyblog.getFl());
        holder.text_blog.setText(recyblog.getBlog());
        holder.text_author.setText(recyblog.getAuthor());
        holder.text_blogviews.setText(String.valueOf(recyblog.getViews()));

        recyclerblog r = mrecyclerList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                    PublicData.setRecyclerblog(mrecyclerList.get(position));
                }
            }
        });
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_mc,text_blog,text_fl,text_author,text_blogviews;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_mc = itemView.findViewById(R.id.text_mc);
            text_blog = itemView.findViewById(R.id.text_blog);
            text_fl = itemView.findViewById(R.id.text_fl);
            text_author = itemView.findViewById(R.id.text_author);
            text_blogviews = itemView.findViewById(R.id.text_lll);

        }
    }
    @Override
    public int getItemCount() {
        return mrecyclerList.size();
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
    private OnItemClickListener listener;
    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
