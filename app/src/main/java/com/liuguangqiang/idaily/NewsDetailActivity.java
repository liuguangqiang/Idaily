package com.liuguangqiang.idaily;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liuguangqiang.idaily.entity.News;

public class NewsDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NEWS = "EXTRA_NEWS";

    private CollapsingToolbarLayout collapsingToolbar;

    private News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        initToolbar();
        getExtraData();
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
        if (bundle != null && bundle.containsKey(EXTRA_NEWS)) {
            mNews = bundle.getParcelable(EXTRA_NEWS);
            collapsingToolbar.setTitle(mNews.getTitle());
        }
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("ScrollViewActivity");
    }

}
