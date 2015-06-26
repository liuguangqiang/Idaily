package com.liuguangqiang.idaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuguangqiang.idaily.widget.PageableRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 15/3/19.
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public Context mContext;
    public List<T> data = new ArrayList<>();
    public final LayoutInflater layoutInflater;

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        if (onItemClickListener != null && holder.itemView != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void add(List<T> list) {
        data.addAll(list);
    }

    PageableRecyclerView recyclerView;

    public void setRecylerView(PageableRecyclerView recylerView) {
        this.recyclerView = recylerView;
    }

    public PageableRecyclerView getRecyclerView() {
        return recyclerView;
    }

}
