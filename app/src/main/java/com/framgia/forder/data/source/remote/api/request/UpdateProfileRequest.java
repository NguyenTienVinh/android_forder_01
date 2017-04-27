package com.framgia.forder.data.source.remote.api.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.data.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 19-04-2017.
 */

public class UpdateProfileRequest implements Parcelable {
    @SerializedName("user")
    @Expose
    private User mUser;
    @SerializedName("new_password")
    @Expose
    private String mNewPassword;
    @SerializedName("current_password")
    @Expose
    private String mCurrentPassword;

    public UpdateProfileRequest() {
    }

    protected UpdateProfileRequest(Parcel in) {
        mUser = in.readParcelable(User.class.getClassLoader());
        mNewPassword = in.readString();
        mCurrentPassword = in.readString();
    }

    public static final Creator<UpdateProfileRequest> CREATOR =
            new Creator<UpdateProfileRequest>() {
                @Override
                public UpdateProfileRequest createFromParcel(Parcel in) {
                    return new UpdateProfileRequest(in);
                }

                @Override
                public UpdateProfileRequest[] newArray(int size) {
                    return new UpdateProfileRequest[size];
                }
            };

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getNewPassword() {
        return mNewPassword;
    }

    public void setNewPassword(String newPassword) {
        mNewPassword = newPassword;
    }

    public String getCurrentPassword() {
        return mCurrentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        mCurrentPassword = currentPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mUser, flags);
        dest.writeString(mNewPassword);
        dest.writeString(mCurrentPassword);
    }
}
