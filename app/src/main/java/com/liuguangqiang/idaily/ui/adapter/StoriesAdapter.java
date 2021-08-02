package com.liuguangqiang.idaily.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ItemStoryBinding;
import com.liuguangqiang.idaily.databinding.ItemStoryHeaderBinding;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.domain.entity.StorySection;
import com.liuguangqiang.idaily.ui.adapter.ItemProvider.SectionItemProvider;
import com.liuguangqiang.idaily.ui.adapter.ItemProvider.StoryItemProvider;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Eric at 2021/7/31
 */
public class StoriesAdapter extends BaseProviderMultiAdapter<BaseEntity> {

    public static final int ITEM_STORY = 0;

    public static final int ITEM_SECTION = 1;
//

//    public StoriesAdapter() {
//        super();
////        addItemType(ITEM_STORY, R.layout.item_story);
////        addItemType(ITEM_SECTION, R.layout.item_story_header);
//        addItemProvider(new StoryItemProvider());
//        addItemProvider(new SectionItemProvider());
//    }

//    @Override
//    protected void convert(BaseViewHolder helper, BaseEntity baseEntity) {
//        switch (helper.getItemViewType()) {
//            case ITEM_STORY:
//                Timber.d("StoriesAdapter ITEM_STORY");
//                ItemStoryBinding binding = (ItemStoryBinding) helper.itemView.getTag();
//                binding.setStory((Story) baseEntity);
//                binding.executePendingBindings();
//                break;
//            case ITEM_SECTION:
//                Timber.d("StoriesAdapter ITEM_SECTION");
//                ItemStoryHeaderBinding sectionBinding = (ItemStoryHeaderBinding) helper.itemView.getTag();
//                sectionBinding.setSection((StorySection) baseEntity);
//                sectionBinding.executePendingBindings();
//                break;
//            default:
//                break;
//        }
//    }


    @Override
    protected int getItemType(List<? extends BaseEntity> list, int i) {
        if (getItem(i) instanceof StorySection) {
            return ITEM_SECTION;
        }
        return ITEM_STORY;
    }
}
