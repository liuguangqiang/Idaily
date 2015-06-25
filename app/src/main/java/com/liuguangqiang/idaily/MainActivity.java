package com.liuguangqiang.idaily;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liuguangqiang.asyncokhttp.AsyncOkHttp;
import com.liuguangqiang.asyncokhttp.JsonResponseHandler;
import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.idaily.adapter.BaseRecyclerAdapter;
import com.liuguangqiang.idaily.adapter.StoryAdapter;
import com.liuguangqiang.idaily.entity.BaseEntity;
import com.liuguangqiang.idaily.entity.Daily;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.entity.StorySection;
import com.liuguangqiang.idaily.uitls.ApiUtils;
import com.liuguangqiang.idaily.widget.PageableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private NavigationView navigationView;

    private PageableRecyclerView recyclerView;
    private StoryAdapter adapter;
    private List<BaseEntity> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initViews();
        getDaily();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerlayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_menu);

        recyclerView = (PageableRecyclerView) findViewById(R.id.rv_news);
        adapter = new StoryAdapter(getApplicationContext(), data);
        recyclerView.setAdapter(adapter);
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

        recyclerView.setOnPageListener(new PageableRecyclerView.OnPageListener() {
            @Override
            public void onPage() {
                getDaily();
            }
        });
    }

    private int lastDatetime = 0;

    private void getDaily() {
        getDaily(lastDatetime);
    }

    private void getDaily(int datetime) {
        String url = datetime > 0 ? ApiUtils.getNewsBefore(datetime) : ApiUtils.getLatest();
        Logs.i(url);
        AsyncOkHttp.getInstance().get(url, new JsonResponseHandler<Daily>(Daily.class) {
            @Override
            public void onSuccess(Daily daily) {
                if (daily != null) {
                    StorySection section = new StorySection(daily.getDate());
                    lastDatetime = daily.getDate();
                    data.add(section);
                    data.addAll(daily.getStories());
                    recyclerView.notifyDataSetChanged();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                recyclerView.onPageFinished();
            }
        });
    }

}
