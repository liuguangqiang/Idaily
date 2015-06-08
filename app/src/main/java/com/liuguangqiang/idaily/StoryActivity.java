package com.liuguangqiang.idaily;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.uitls.ApiUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;

public class StoryActivity extends AppCompatActivity {

    public static final String EXTRA_STORY = "EXTRA_STORY";

    private CollapsingToolbarLayout collapsingToolbar;

    private Story mStory;

    private ImageView ivPic;
    private WebView webViewContent;
    private NestedScrollView mScrollView;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initToolbar();
        initViews();
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
        if (bundle != null && bundle.containsKey(EXTRA_STORY)) {
            mStory = bundle.getParcelable(EXTRA_STORY);
            collapsingToolbar.setTitle(mStory.getTitle());
            getStory(mStory.getId());
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

    private void initViews() {
        tvEmpty=(TextView)findViewById(R.id.tv_empty);
        mScrollView = (NestedScrollView) findViewById(R.id.scrollView);
        ivPic = (ImageView) findViewById(R.id.iv_pic);
        webViewContent = (WebView) findViewById(R.id.web_content);

        WebSettings settings = webViewContent.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

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

    private void bindData(Story story) {
        Picasso.with(getApplicationContext()).load(story.getImage()).into(ivPic);
        webViewContent.loadData(loadDataWithCSS(story.getBody(), story.getCss().get(0)), "text/html; charset=UTF-8", null);
    }

    private String loadDataWithCSS(String loadData, String cssPath) {
        String header = "<html><head><link href=\"%s\" type=\"text/css\" rel=\"stylesheet\"/></head><body>";
        String footer = "</body></html>";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(header, cssPath));
        sb.append(loadData);
        sb.append(footer);
        return sb.toString();
    }

    private void getStory(int id) {
        String url = ApiUtils.getStory(id);
        Logs.i("daily", url);
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(getApplicationContext(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Story story = new Gson().fromJson(responseString, Story.class);
                if (story != null) {
                    bindData(story);
                }
            }
        });
    }

}
