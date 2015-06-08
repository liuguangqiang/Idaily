package com.liuguangqiang.idaily;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.idaily.adapter.BaseRecyclerAdapter;
import com.liuguangqiang.idaily.adapter.NewsAdapter;
import com.liuguangqiang.idaily.entity.Daily;
import com.liuguangqiang.idaily.entity.News;
import com.liuguangqiang.idaily.uitls.ApiUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private NavigationView navigationView;

    private RecyclerView recylerView;
    private NewsAdapter adapter;
    private List<News> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initViews();
        getDailay();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
        setTitle("IDaily");
    }

    private void initViews() {
        recylerView = (RecyclerView) findViewById(R.id.rv_news);
        adapter = new NewsAdapter(getApplicationContext(), data);
        recylerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recylerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), NewsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(NewsDetailActivity.EXTRA_NEWS, data.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getDailay() {
        String url = ApiUtils.getLatest();
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(getApplicationContext(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Daily daily = new Gson().fromJson(responseString, Daily.class);
                if (daily != null) {
                    data.addAll(daily.getNews());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}
