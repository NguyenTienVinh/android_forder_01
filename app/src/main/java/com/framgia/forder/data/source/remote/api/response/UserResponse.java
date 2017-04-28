package com.framgia.forder.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.data.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 4/14/17.
 */

public class UserResponse implements Parcelable {
    @Expose
    @SerializedName("content")
    private User mUser;
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;

    protected UserResponse(Parcel in) {
        mUser = in.readParcelable(User.class.getClassLoader());
        mStatus = in.readInt();
        mMessage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mUser, flags);
        dest.writeInt(mStatus);
        dest.writeString(mMessage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
