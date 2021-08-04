package com.liuguangqiang.idaily.ui.act;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.liuguangqiang.idaily.databinding.ActivityStoryBinding;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.adapter.WebViewAdapter;
import com.liuguangqiang.idaily.ui.viewmodel.StoryViewModel;

import java.util.ArrayList;

import timber.log.Timber;

public class StoryActivity extends BaseActivity {

    public static final String ARG_STORY = "ARG_STORY";

    private StoryViewModel viewModel;

    private ActivityStoryBinding binding;
    private WebViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new StoryViewModel();
        binding = ActivityStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel.pushArguments(getIntent().getExtras());
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreateBinding() {
    }

    private void initViews() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.collapsingToolbarLayout.post(new Runnable() {
            @Override
            public void run() {
                binding.collapsingToolbarLayout.requestLayout();
            }
        });

        adapter = new WebViewAdapter(new ArrayList<>());
        binding.rvWebView.setLayoutManager(new LinearLayoutManager(this));
        binding.rvWebView.setAdapter(adapter);

        viewModel.storyLiveData.observe(this, new Observer<Story>() {
            @Override
            public void onChanged(Story story) {
                binding.collapsingToolbarLayout.setTitle(story.title);
                Glide.with(StoryActivity.this).load(story.getImage()).into(binding.ivPic);
                adapter.addData(story);
            }
        });
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.toolbar.getLayoutParams();
        layoutParams.topMargin = getStatusBarHeight();
        Timber.d("status bar height:" + getStatusBarHeight());
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
