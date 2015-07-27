package com.liuguangqiang.idaily.ui.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.liuguangqiang.idaily.BR;
import com.liuguangqiang.idaily.ui.act.StoryActivity;
import com.liuguangqiang.idaily.ui.adapter.BaseRecyclerAdapter;
import com.liuguangqiang.idaily.ui.adapter.StoryAdapter;
import com.liuguangqiang.idaily.entity.BaseEntity;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.listener.RequestCallback;
import com.liuguangqiang.idaily.ui.model.MainModel;
import com.liuguangqiang.idaily.ui.widget.PageableRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 15/6/26.
 */
public class MainViewModel extends BaseObservable implements RequestCallback<List<BaseEntity>>, BaseRecyclerAdapter.OnItemClickListener {

    private Activity mContext;

    private MainModel mMainModel;

    private StoryAdapter adapter;

    @Bindable
    private List<BaseEntity> data = new ArrayList<>();

    public MainViewModel(Activity context, OnDisplayTopStoryListener onDisplayTopStoryListener) {
        this.mContext = context;
        adapter = new StoryAdapter(context, data);
        adapter.setOnItemClickListener(this);

        mMainModel = new MainModel(this, onDisplayTopStoryListener);
        mMainModel.getDaily();
    }

    public StoryAdapter getAdapter() {
        return adapter;
    }

    public List<BaseEntity> getData() {
        return data;
    }

    @Override
    public void onItemClick(int position) {
        BaseEntity entity = data.get(position);
        if (entity instanceof Story) {
            Intent intent = new Intent(mContext, StoryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(StoryActivity.EXTRA_STORY, (Story) entity);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
//            mContext.overridePendingTransition(0, 0);
        }
    }

    /**
     * Create a PageListener for Main View.
     *
     * @return
     */
    public PageableRecyclerView.OnPageListener getOnPageListener() {
        return new PageableRecyclerView.OnPageListener() {
            @Override
            public void onPage() {
                mMainModel.getDaily();
            }
        };
    }

    @Override
    public void requestFinished() {
    }

    @Override
    public void requestSuccess(List<BaseEntity> list) {
        data.addAll(list);
        notifyPropertyChanged(BR.data);
    }

    public interface OnDisplayTopStoryListener {
        void onDisplayTopStories(List<Story> stories);
    }

}
