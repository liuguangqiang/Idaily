package com.liuguangqiang.idaily;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.idaily.base.BaseActivity;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.presenter.StoryPresenter;
import com.liuguangqiang.idaily.view.StoryView;
import com.liuguangqiang.idaily.view.callback.StoryViewCallback;
import com.squareup.picasso.Picasso;

public class StoryActivity extends BaseActivity implements StoryView {

    public static final String EXTRA_STORY = "EXTRA_STORY";

    private StoryViewCallback mCallback;

    private Story mStory;

    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView ivPic;
    private WebView webViewContent;

    //这个View是为了解决CoordinatorLayout的一个bug
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initToolbar();
        initViews();
    }

    @Override
    public Presenter createPresenter() {
        return new StoryPresenter(getApplicationContext(), this);
    }

    @Override
    public void setUiCallback(StoryViewCallback storyViewCallback) {
        mCallback = storyViewCallback;
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

    @Override
    protected void onAttachedUi() {
        super.onAttachedUi();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_STORY)) {
            mStory = bundle.getParcelable(EXTRA_STORY);
            collapsingToolbar.setTitle(mStory.getTitle());
            mCallback.getStory(mStory.getId());
        }
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    }

    private void initViews() {
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
        ivPic = (ImageView) findViewById(R.id.iv_pic);

        webViewContent = (WebView) findViewById(R.id.web_content);
        webViewContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    tvEmpty.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
            }
        });
    }

    @Override
    public void bindStory(Story story) {
        Picasso.with(getApplicationContext()).load(story.getImage()).into(ivPic);
    }

    @Override
    public void bindContent(String content) {
        webViewContent.loadData(content, "text/html; charset=UTF-8", null);
    }

}
