package com.liuguangqiang.idaily.ui.viewmodel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.view.View;

import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.act.StoryActivity;

/**
 * Created by Eric on 15/10/11.
 */
public class TopStoryViewModel extends BaseObservable {

    private Story story;

    public void setStory(Story s) {
        this.story = s;
    }

    public View.OnClickListener getPicClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(StoryActivity.ARG_STORY, story);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        };
    }

    public void onClickPic(View view) {
        Intent intent = new Intent(view.getContext(), StoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(StoryActivity.ARG_STORY, story);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }

    public String getImage() {
        if (story == null) return "";

        return story.getImage();
    }

}
