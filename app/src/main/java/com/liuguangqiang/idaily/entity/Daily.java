package com.liuguangqiang.idaily.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 15/6/6.
 */
public class Daily implements Parcelable {

    private int date;

    private boolean is_today;

    private String display_date;

    private List<News> news = new ArrayList<>();

    public boolean is_today() {
        return is_today;
    }

    public void setIs_today(boolean is_today) {
        this.is_today = is_today;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDisplay_date() {
        return display_date;
    }

    public void setDisplay_date(String display_date) {
        this.display_date = display_date;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.date);
        dest.writeByte(is_today ? (byte) 1 : (byte) 0);
        dest.writeString(this.display_date);
        dest.writeList(this.news);
    }

    public Daily() {
    }

    protected Daily(Parcel in) {
        this.date = in.readInt();
        this.is_today = in.readByte() != 0;
        this.display_date = in.readString();
        this.news = new ArrayList<News>();
        in.readList(this.news, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<Daily> CREATOR = new Parcelable.Creator<Daily>() {
        public Daily createFromParcel(Parcel source) {
            return new Daily(source);
        }

        public Daily[] newArray(int size) {
            return new Daily[size];
        }
    };
}
