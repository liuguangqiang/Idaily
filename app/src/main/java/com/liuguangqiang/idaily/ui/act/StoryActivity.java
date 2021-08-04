package com.liuguangqiang.idaily.ui.act;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.liuguangqiang.idaily.databinding.ActivityStoryBinding;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.viewmodel.StoryViewModel;

public class StoryActivity extends BaseActivity {

    public static final String ARG_STORY = "ARG_STORY";

    private StoryViewModel viewModel;

    private ActivityStoryBinding binding;

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
        if (viewModel != null) {
//            viewModel.onDestroy();
        }
    }

    @Override
    public void onCreateBinding() {
    }

    private void initViews() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.webContent.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        binding.webContent.getSettings().setJavaScriptEnabled(true);
        binding.webContent.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.myapp.resize(document.body.getBoundingClientRect().bottom);") ;
                //This call invokes an injected js method to override the webview height, which resolves the initial loading of the page. Problem, 1
            }
        });

        viewModel.storyLiveData.observe(this, new Observer<Story>() {
            @Override
            public void onChanged(Story story) {
                binding.collapsingToolbar.setTitle(story.title);
                Glide.with(StoryActivity.this).load(story.getImage()).into(binding.ivPic);
                binding.webContent.loadData(viewModel.getBody(), "text/html; charset=UTF-8", null);
            }
        });
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
