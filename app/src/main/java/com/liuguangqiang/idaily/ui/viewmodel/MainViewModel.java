package com.liuguangqiang.idaily.ui.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.act.MainActivity;
import com.liuguangqiang.idaily.ui.adapter.StoriesAdapter;
import com.liuguangqiang.idaily.ui.adapter.StoryAdapter;
import com.liuguangqiang.idaily.ui.model.MainModel;
import com.liuguangqiang.idaily.ui.view.MainView;
import com.liuguangqiang.idaily.ui.view.RequestView;
import com.liuguangqiang.idaily.utils.events.TopStoriesEvent;
import com.liuguangqiang.idaily.utils.navigator.Navigator;
import com.liuguangqiang.support.utils.IntentUtils;
import com.liuguangqiang.support.widgets.recyclerview.OnPageListener;
import com.liuguangqiang.support.widgets.recyclerview.adapter.AbsRVAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import timber.log.Timber;


/**
 * Created by Eric on 15/6/26.
 */
public class MainViewModel extends AndroidViewModel {

    private MainModel mainModel;

    public MainViewModel(Application application) {
        super(application);
        mainModel = new MainModel();
    }


    public MutableLiveData<List<BaseEntity>> getLiveData() {
        return mainModel.getLiveData();
    }

    public void getStories(){
        mainModel.getDaily();
    }

//    public StoriesAdapter getAdapter() {
//        return adapter;
//    }

//    @Override
//    public void onItemClick(View view, int position) {
//        BaseEntity entity = data.get(position);
//        if (entity instanceof Story) {
//            Navigator.getInstance().openStory(context, (Story) entity);
//        }
//    }
//
//    @Override
//    public void requestData() {
//        super.requestData();
//        mainModel.getDaily();
//    }
//
//    @Override
//    public void onRequestSuccess(List<BaseEntity> list) {
//        super.onRequestSuccess(list);
//        Timber.d("get daily onRequestSuccess:" + list.size());
//    }
//
//    @Override
//    public void bindTopStories(List<Story> stories) {
//        TopStoriesEvent event = new TopStoriesEvent(stories);
//        EventBus.getDefault().post(event);
//    }


//    public View.OnClickListener getOnClickListener() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.tv_github:
//                        IntentUtils.skipToBrowser(v.getContext(), "https://github.com/liuguangqiang/Idaily");
//                        break;
//                }
//            }
//        };
//    }

}
