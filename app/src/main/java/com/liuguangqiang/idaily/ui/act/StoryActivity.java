package com.liuguangqiang.idaily.ui.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ActivityStoryBinding;
import com.liuguangqiang.idaily.di.components.DaggerStoryComponent;
import com.liuguangqiang.idaily.di.modules.StoryModule;
import com.liuguangqiang.idaily.ui.viewmodel.StoryViewModel;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryActivity extends BaseActivity {

    public static final String ARG_STORY = "ARG_STORY";

    @Inject
    StoryViewModel viewModel;

    @Bind(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public void onCreateBinding() {
        DaggerStoryComponent.builder()
                .storyModule(new StoryModule())
                .build()
                .inject(this);

        ActivityStoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_story);
        ButterKnife.bind(this);
        binding.setStoryViewModel(viewModel);
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

}
