package com.liuguangqiang.idaily.ui.act;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ActivityStoryBinding;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.viewmodel.StoryViewModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryActivity extends BaseActivity {

    public static final String ARG_STORY = "ARG_STORY";

    private Story mStory;

    @Bind(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbar;

    private ActivityStoryBinding binding;
    private StoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public void onCreateBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_story);
        viewModel = new StoryViewModel();
        binding.setStoryViewModel(viewModel);
        ButterKnife.bind(this);
        viewModel.pushArguments(getIntent().getExtras());
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
        if (bundle != null && bundle.containsKey(ARG_STORY)) {
            mStory = bundle.getParcelable(ARG_STORY);
            if (mStory != null) {
                collapsingToolbar.setExpandedTitleColor(Color.WHITE);
                collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
                collapsingToolbar.setExpandedTitleTextAppearance(R.style.CollapsingToolbarTitle);
                viewModel.getStory(mStory.getId());
            }
        }
    }

}
