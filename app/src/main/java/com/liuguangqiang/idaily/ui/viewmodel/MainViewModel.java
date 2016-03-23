package com.liuguangqiang.idaily.ui.viewmodel;

import android.content.Context;
import android.view.View;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.adapter.StoryAdapter;
import com.liuguangqiang.idaily.ui.model.MainModel;
import com.liuguangqiang.idaily.ui.view.MainView;
import com.liuguangqiang.idaily.ui.view.RequestView;
import com.liuguangqiang.idaily.utils.events.TopStoriesEvent;
import com.liuguangqiang.idaily.utils.navigator.Navigator;
import com.liuguangqiang.support.utils.IntentUtils;
import com.liuguangqiang.support.widgets.recyclerview.OnPageListener;
import com.liuguangqiang.support.widgets.recyclerview.adapter.AbsRVAdapter;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Eric on 15/6/26.
 */
public class MainViewModel extends AbsRecyclerViewModel<BaseEntity> implements MainView, RequestView<BaseEntity>, AbsRVAdapter.OnItemClickListener {

    private Context context;

    private MainModel mainModel;

    private StoryAdapter adapter;

    @Inject
    public MainViewModel(Context context, MainModel mainModel) {
        this.context = context;
        this.mainModel = mainModel;
        mainModel.setView(this, this);

        adapter = new StoryAdapter(context, getData());
        adapter.setOnItemClickListener(this);

        requestData();
    }

    public StoryAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onItemClick(View view, int position) {
        BaseEntity entity = data.get(position);
        if (entity instanceof Story) {
            Navigator.getInstance().openStory(context, (Story) entity);
        }
    }

    @Override
    public void requestData() {
        super.requestData();
        mainModel.getDaily();
    }

    @Override
    public void bindTopStories(List<Story> stories) {
        TopStoriesEvent event = new TopStoriesEvent(stories);
        EventBus.getDefault().post(event);
    }

    public OnPageListener getOnPageListener() {
        return new OnPageListener() {
            @Override
            public void onPage() {
                requestData();
            }
        };
    }

    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_github:
                        IntentUtils.skipToBrowser(v.getContext(), "https://github.com/liuguangqiang/Idaily");
                        break;
                }
            }
        };
    }

}
