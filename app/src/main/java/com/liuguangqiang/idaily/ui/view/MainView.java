package com.liuguangqiang.idaily.ui.view;

import com.liuguangqiang.idaily.domain.entity.Story;

import java.util.List;

/**
 * Created by Eric on 16/3/21.
 */
public interface MainView {

    void bindTopStories(List<Story> stories);

}
