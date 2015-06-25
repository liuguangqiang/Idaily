package com.liuguangqiang.idaily;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import com.liuguangqiang.idaily.base.BaseActivity;
import com.liuguangqiang.idaily.databinding.ActivityStoryBinding;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.viewmodel.StoryViewModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StoryActivity extends BaseActivity {

    public static final String EXTRA_STORY = "EXTRA_STORY";

    private Story mStory;

    @InjectView(R.id.web_content)
    public WebView webview;

    @InjectView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbar;

    private ActivityStoryBinding binding;
    private StoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding();
        initViews();
        getExtraData();
    }

    private void binding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_story);
        ButterKnife.inject(this);
        viewModel = new StoryViewModel();
        binding.setStoryViewModel(viewModel);
    }

    private void initViews() {
        Toolbar toolbar = findById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_STORY)) {
            mStory = bundle.getParcelable(EXTRA_STORY);
            if (mStory != null) {
                collapsingToolbar.setTitle(mStory.getTitle());
                viewModel.getStory(mStory.getId());
            }
        }
    }

}
