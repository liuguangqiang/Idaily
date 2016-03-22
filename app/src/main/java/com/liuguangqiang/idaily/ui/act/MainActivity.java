package com.liuguangqiang.idaily.ui.act;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ActivityMainBinding;
import com.liuguangqiang.idaily.di.components.DaggerMainComponent;
import com.liuguangqiang.idaily.di.modules.MainModule;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.adapter.page.TopStoryAdapter;
import com.liuguangqiang.idaily.ui.viewmodel.MainViewModel;
import com.liuguangqiang.idaily.utils.events.TopStoriesEvent;
import com.liuguangqiang.support.utils.Logs;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private CollapsingToolbarLayout collapsingToolbar;
    private CirclePageIndicator pageIndicator;

    private ViewPager viewPager;
    private TopStoryAdapter topStoryAdapter;
    private List<Story> topStories = new ArrayList<>();

    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initViews();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onCreateBinding() {
        DaggerMainComponent
                .builder()
                .mainModule(new MainModule(getApplicationContext()))
                .build().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mainViewModel);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.CollapsingToolbarTitle);
    }

    private void initViews() {
        pageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.vp_top_stories);
        topStoryAdapter = new TopStoryAdapter(getSupportFragmentManager(), topStories);

        viewPager.setAdapter(topStoryAdapter);
        pageIndicator.setViewPager(viewPager);
        pageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Logs.i(topStories.get(position).getTitle());
                collapsingToolbar.setTitle(topStories.get(position).getTitle());
                setTitle(topStories.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Subscribe
    public void onEvent(TopStoriesEvent event) {
        if (event.stories != null) {
            topStories.addAll(event.stories);
            topStoryAdapter.notifyDataSetChanged();
            collapsingToolbar.setTitle(topStories.get(0).getTitle());
        }
    }

}
