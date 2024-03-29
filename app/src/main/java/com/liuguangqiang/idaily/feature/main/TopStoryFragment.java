package com.liuguangqiang.idaily.feature.main;

import android.os.Bundle;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.liuguangqiang.idaily.databinding.FragmentTopStoryBinding;
import com.liuguangqiang.idaily.entity.Story;

public class TopStoryFragment extends Fragment {

    public static final String ARG_STORY = "ARG_STORY";

    private Story story;

    private TopStoryViewModel viewModel;

    public static TopStoryFragment newInstance(Story story) {
        TopStoryFragment fragment = new TopStoryFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_STORY, story);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ARG_STORY)) {
            story = bundle.getParcelable(ARG_STORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTopStoryBinding binding = FragmentTopStoryBinding.inflate(inflater);
        viewModel = new TopStoryViewModel();
        viewModel.setStory(story);
        binding.setViewModel(viewModel);
        return binding.getRoot();

    }

}
