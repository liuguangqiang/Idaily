package com.liuguangqiang.idaily.ui.adapter.page;

import android.app.FragmentManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.fragment.TopStoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 15/7/8.
 */
public class TopStoryAdapter extends FragmentStatePagerAdapter {

    public List<Story> stories = new ArrayList<>();

    public TopStoryAdapter(FragmentManager fragmentManager, List<Story> storyList) {
        super(fragmentManager);
        this.stories = storyList;
    }

    @Override
    public Fragment getItem(int position) {
        return TopStoryFragment.newInstance(stories.get(position));
    }

    @Override
    public int getCount() {
        return stories.size();
    }
}
