package com.liuguangqiang.idaily.ui.act;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.helper.TestFragment;
import com.liuguangqiang.idaily.ui.adapter.page.TopStoryAdapter;
import com.liuguangqiang.idaily.databinding.ActivityMainBinding;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.ui.viewmodel.MainViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

public class MainActivity extends AppCompatActivity implements MainViewModel.OnDisplayTopStoryListener {

    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    private CollapsingToolbarLayout collapsingToolbar;

    private ViewPager viewPager;
    private TopStoryAdapter topStoryAdapter;
    private List<Story> topStories = new ArrayList<>();

    @InjectView(R.id.iv_avatar)
    ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.initialize(this);
        initBinding();
        initToolbar();
        initViews();
        TestFragment.attach(this);
    }

    private void initBinding() {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new MainViewModel(this, this);
        activityMainBinding.setViewModel(mainViewModel);
        ButterKnife.inject(this);
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

        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("IDaily");
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void initViews() {
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_menu);
        viewPager = (ViewPager) findViewById(R.id.vp_top_stories);
        topStoryAdapter = new TopStoryAdapter(getSupportFragmentManager(), topStories);
        viewPager.setAdapter(topStoryAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                collapsingToolbar.setTitle(topStories.get(position).getTitle());
                Logs.i("onPageSelected:" + topStories.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDisplayTopStories(List<Story> stories) {
        topStories.addAll(stories);
        topStoryAdapter.notifyDataSetChanged();
    }

    private MediaPlayer mediaPlayer;

    private void createMediaPlayer() {
        Logs.i("createMediaPlayer");

        if (mediaPlayer == null) {

            try {
                mediaPlayer = new MediaPlayer(this);
                mediaPlayer.setBufferSize(1 * 1024);
                mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("http://jpg.cpmok.net/test/1.m4a"));
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        Logs.i("onBufferingUpdate:" + percent);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.iv_avatar)
    public void onClickAvatar() {
//        ivAvatar.animate().alpha(0f).rotationX(360).setDuration(1000);
        int[] start = new int[2];
        ivAvatar.getLocationOnScreen(start);
        Logs.i("start.x:" + start[0]);
        Logs.i("start.y:" + start[1]);
        OtherActivity.startUserProfileFromLocation(start, this);
        overridePendingTransition(0, 0);
    }

}
