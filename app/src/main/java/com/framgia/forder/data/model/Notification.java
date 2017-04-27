package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 24-04-2017.
 */

public class Notification implements Parcelable {

    @Expose
    @SerializedName("title")
    private String mTitle;
    @Expose
    @SerializedName("time")
    private String mTime;
    @Expose
    @SerializedName("avatar")
    private CollectionAvatar mCollectionAvatar;

    public Notification(String title, String time, CollectionAvatar collectionAvatar) {
        mTitle = title;
        mTime = time;
        mCollectionAvatar = collectionAvatar;
    }

    protected Notification(Parcel in) {
        mTitle = in.readString();
        mTime = in.readString();
        mCollectionAvatar = in.readParcelable(CollectionAvatar.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mTime);
        dest.writeParcelable(mCollectionAvatar, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public CollectionAvatar getCollectionAvatar() {
        return mCollectionAvatar;
    }

    public void setCollectionAvatar(CollectionAvatar collectionAvatar) {
        mCollectionAvatar = collectionAvatar;
    }
}
