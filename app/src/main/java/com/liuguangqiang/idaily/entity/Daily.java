package com.liuguangqiang.idaily.entity;

import java.util.List;

/**
 * Created by Eric on 15/6/6.
 */
public class Daily {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int date;

    private List<Story> stories;

    private List<Story> top_stories;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<Story> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<Story> top_stories) {
        this.top_stories = top_stories;
    }

}
