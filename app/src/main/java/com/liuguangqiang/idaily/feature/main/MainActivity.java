package com.liuguangqiang.idaily.feature.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ActivityMainBinding;
import com.liuguangqiang.idaily.api.entity.BaseEntity;
import com.liuguangqiang.idaily.api.entity.Story;
import com.liuguangqiang.idaily.adapter.BannerStoryAdapter;
import com.liuguangqiang.idaily.adapter.StoriesAdapter;
import com.liuguangqiang.idaily.adapter.page.TopStoryAdapter;

import com.liuguangqiang.idaily.utils.DisplayUtils;
import com.liuguangqiang.idaily.utils.events.TopStoriesEvent;
import com.liuguangqiang.idaily.utils.navigator.Navigator;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private StoriesAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbar;
    private TopStoryAdapter topStoryAdapter;
    private BannerStoryAdapter bannerStoryAdapter;
    private List<Story> topStories = new ArrayList<>();

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new MainViewModel(getApplication());
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        initViews();
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.toolbar.getLayoutParams();
        layoutParams.topMargin = DisplayUtils.getStatusBarHeight(this);

        collapsingToolbar = binding.collapsingToolbar;
        collapsingToolbar.setTitle(getString(R.string.app_name));
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, R.color.gray_dark));
        collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.gray_dark));
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.CollapsingToolbarTitle);
        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float maxOffset = (appBarLayout.getHeight() - binding.toolbar.getHeight());
                float ratio = (verticalOffset + maxOffset) / maxOffset;
                int color = ColorUtils.blendARGB(Color.WHITE, Color.BLACK, 1 - ratio);
                binding.toolbar.getNavigationIcon().setTint(color);
            }
        });
    }

    private void initViews() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        adapter = new StoriesAdapter(new ArrayList<>());
        adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mainViewModel.getStories();
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                BaseEntity entity = (BaseEntity) adapter.getItem(position);
                if (entity instanceof Story) {
                    Navigator.getInstance().openStory(MainActivity.this, (Story) entity);
                }
            }
        });

        binding.rvNews.setLayoutManager(new LinearLayoutManager(this));
        binding.rvNews.setAdapter(adapter);
        mainViewModel.getLiveData().observe(this, new Observer<List<BaseEntity>>() {
            @Override
            public void onChanged(List<BaseEntity> baseEntities) {
                Timber.d("list onChanged:" + baseEntities.size());
                adapter.addData(baseEntities);
                adapter.getLoadMoreModule().loadMoreComplete();
            }
        });

        binding.banner.setIndicator(new CircleIndicator(this));
        binding.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {

            }
        });
        binding.banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                collapsingToolbar.setTitle(topStories.get(position).getTitle());
                setTitle(topStories.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mainViewModel.getTopLiveData().observe(this, new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                topStories.addAll(stories);
                bannerStoryAdapter = new BannerStoryAdapter(stories);
                binding.banner.setAdapter(bannerStoryAdapter);
                collapsingToolbar.setTitle(topStories.get(0).getTitle());
                setTitle(topStories.get(0).getTitle());
            }
        });
        mainViewModel.getStories();
    }

    @Subscribe
    public void onEvent(TopStoriesEvent event) {
//        if (event.stories != null) {
//            topStories.addAll(event.stories);
//            topStoryAdapter.notifyDataSetChanged();
//            collapsingToolbar.setTitle(topStories.get(0).getTitle());
//        }
    }

}
