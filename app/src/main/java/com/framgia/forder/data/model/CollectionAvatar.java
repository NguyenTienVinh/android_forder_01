package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Duong on 4/17/2017.
 */

public class CollectionAvatar implements Parcelable {

    public static final Creator<CollectionAvatar> CREATOR = new Creator<CollectionAvatar>() {
        @Override
        public CollectionAvatar createFromParcel(Parcel in) {
            return new CollectionAvatar(in);
        }

        @Override
        public CollectionAvatar[] newArray(int size) {
            return new CollectionAvatar[size];
        }
    };

    public CollectionAvatar(Image image) {
        mImage = image;
    }

    @Expose
    @SerializedName("avatar")
    private Image mImage;

    protected CollectionAvatar(Parcel in) {
        mImage = in.readParcelable(Image.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mImage, flags);
    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }
}
