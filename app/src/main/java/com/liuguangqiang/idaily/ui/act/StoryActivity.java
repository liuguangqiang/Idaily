package com.liuguangqiang.idaily.ui.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ActivityStoryBinding;
import com.liuguangqiang.idaily.di.components.DaggerStoryComponent;
import com.liuguangqiang.idaily.di.modules.StoryModule;
import com.liuguangqiang.idaily.ui.viewmodel.StoryViewModel;

import javax.inject.Inject;

public class StoryActivity extends BaseActivity {

    public static final String ARG_STORY = "ARG_STORY";

    @Inject
    StoryViewModel viewModel;

    private ActivityStoryBinding binding;

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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_story);
        binding.setStoryViewModel(viewModel);
        viewModel.pushArguments(getIntent().getExtras());
    }

    private void initViews() {
        setSupportActionBar(binding.toolbar);
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
