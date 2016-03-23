package com.liuguangqiang.idaily.ui.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ActivityStoryBinding;
import com.liuguangqiang.idaily.ui.viewmodel.StoryViewModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoryActivity extends BaseActivity {

    public static final String ARG_STORY = "ARG_STORY";

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

}
