package com.liuguangqiang.idaily.ui.act;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ActivityMainBinding;
import com.liuguangqiang.idaily.databinding.ActivityStoryBinding;
import com.liuguangqiang.idaily.ui.model.StoryModel;
import com.liuguangqiang.idaily.ui.viewmodel.StoryViewModel;

public class StoryActivity extends BaseActivity {

    public static final String ARG_STORY = "ARG_STORY";

    private StoryViewModel viewModel = new StoryViewModel(new StoryModel());

    private ActivityStoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_story);
        binding = ActivityStoryBinding.inflate(getLayoutInflater());
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
