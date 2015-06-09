package com.liuguangqiang.idaily;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.idaily.adapter.BaseRecyclerAdapter;
import com.liuguangqiang.idaily.adapter.Bookends;
import com.liuguangqiang.idaily.adapter.StoryAdapter;
import com.liuguangqiang.idaily.entity.BaseEntity;
import com.liuguangqiang.idaily.entity.Daily;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.entity.StorySection;
import com.liuguangqiang.idaily.uitls.ApiUtils;
import com.liuguangqiang.idaily.uitls.DailyUtils;
import com.liuguangqiang.idaily.widget.LinearRecyclerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private NavigationView navigationView;

    private LinearRecyclerView recylerView;
    private StoryAdapter adapter;
    private Bookends<StoryAdapter> bookends;
    private List<BaseEntity> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initViews();
        getDaily();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("IDaily");
    }

    private void initViews() {
        recylerView = (LinearRecyclerView) findViewById(R.id.rv_news);
        adapter = new StoryAdapter(getApplicationContext(), data);
        bookends = new Bookends<>(adapter);
        addFooter();
        recylerView.setAdapter(bookends);

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BaseEntity entity = data.get(position);
                if (entity instanceof Story) {
                    Intent intent = new Intent(getApplicationContext(), StoryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(StoryActivity.EXTRA_STORY, (Story) entity);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        recylerView.setOnScrollPositionListener(new LinearRecyclerView.OnScrollPositionListener() {
            @Override
            public void onScrollToTop() {

            }

            @Override
            public void onScrollToBottom() {
                getDaily();
            }
        });
    }

    private void addFooter() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_loading_footer, null);
        bookends.addFooter(view);
    }

    private boolean isLoading = false;
    private int lastDatetime = 0;

    private void getDaily() {
        if (!isLoading) {
            getDaily(lastDatetime);
            isLoading = true;
        }
    }

    private void getDaily(int datetime) {
        String url = datetime > 0 ? ApiUtils.getNewsBefore(datetime) : ApiUtils.getLatest();

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(getApplicationContext(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Daily daily = new Gson().fromJson(responseString, Daily.class);
                if (daily != null) {
                    StorySection section = new StorySection(daily.getDate());
                    lastDatetime = daily.getDate();
                    data.add(section);
                    data.addAll(daily.getStories());
                    bookends.notifyDataSetChanged();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                isLoading = false;
                bookends.setFooterVisibility(false);
            }

            @Override
            public void onStart() {
                super.onStart();
                bookends.setFooterVisibility(true);
            }
        });
    }

}
