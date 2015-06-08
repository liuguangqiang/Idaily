package com.liuguangqiang.idaily.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eric on 15/6/6.
 */
public class News implements Parcelable {

    private int id;

    private int ga_prefix;

    private String title;

    private String url;

    private String image;

    private String share_url;

    private String thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(int ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.ga_prefix);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.image);
        dest.writeString(this.share_url);
        dest.writeString(this.thumbnail);
    }

    public News() {
    }

    protected News(Parcel in) {
        this.id = in.readInt();
        this.ga_prefix = in.readInt();
        this.title = in.readString();
        this.url = in.readString();
        this.image = in.readString();
        this.share_url = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
