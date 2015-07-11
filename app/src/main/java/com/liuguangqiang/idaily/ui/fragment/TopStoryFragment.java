package com.liuguangqiang.idaily.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.entity.Story;


public class TopStoryFragment extends Fragment {

    public static final String EXTRA_STORY = "EXTRA_STORY";

    private Story story;

    public static TopStoryFragment newInstance(Story story) {
        TopStoryFragment fragment = new TopStoryFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_STORY, story);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(EXTRA_STORY)) {
            story = bundle.getParcelable(EXTRA_STORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_story, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        if (story != null) {
            ImageView ivPic = (ImageView) getView().findViewById(R.id.iv_pic);
            Glide.with(getActivity()).load(story.getImage()).into(ivPic);
        }
    }

}
