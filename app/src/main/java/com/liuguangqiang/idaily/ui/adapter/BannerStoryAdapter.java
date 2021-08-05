package com.liuguangqiang.idaily.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.liuguangqiang.idaily.databinding.ItemBannerStoryBinding;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class BannerStoryAdapter extends BannerAdapter<Story, BannerStoryAdapter.BannerViewHolder> {

    public BannerStoryAdapter(List<Story> data) {
        super(data);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ItemBannerStoryBinding binding = ItemBannerStoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BannerViewHolder(binding);
    }

    @Override
    public void onBindView(BannerViewHolder holder, Story data, int position, int size) {
        Glide.with(holder.itemView.getContext()).load(data.getImage()).into(holder.binding.image);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        ItemBannerStoryBinding binding;

        public BannerViewHolder(@NonNull ItemBannerStoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
