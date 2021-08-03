package com.liuguangqiang.idaily.ui.adapter;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ItemStoryBinding;
import com.liuguangqiang.idaily.databinding.ItemStoryHeaderBinding;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.domain.entity.StorySection;
import com.liuguangqiang.support.widgets.recyclerview.adapter.AbsRVAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Eric on 15/6/6.
 */
public class StoryAdapter extends BaseQuickAdapter<Story, BaseViewHolder> {


    public StoryAdapter(int layoutResId, @Nullable List<Story> data) {
        super(layoutResId, data);
    }

    /**
     * 构造方法，此示例中，在实例化Adapter时就传入了一个List。
     * 如果后期设置数据，不需要传入初始List，直接调用 super(layoutResId); 即可
     */
//    public StoryAdapter() {
//        super(R.layout.item_story);
//    }



    /**
     * 在此方法中设置item数据
     */
    @Override
    protected void convert(BaseViewHolder helper, Story item) {
        helper.setText(R.id.item_tv_title, "This is an Item, pos: " + (helper.getAdapterPosition() - getHeaderLayoutCount()));
    }
}