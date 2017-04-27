package com.framgia.forder.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.data.model.Notification;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationResponse implements Parcelable {
    @Expose
    @SerializedName("content")
    private List<Notification> mNotifications;
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;

    protected NotificationResponse(Parcel in) {
        mNotifications = in.createTypedArrayList(Notification.CREATOR);
        mStatus = in.readInt();
        mMessage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mNotifications);
        dest.writeInt(mStatus);
        dest.writeString(mMessage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NotificationResponse> CREATOR =
            new Creator<NotificationResponse>() {
                @Override
                public NotificationResponse createFromParcel(Parcel in) {
                    return new NotificationResponse(in);
                }

                @Override
                public NotificationResponse[] newArray(int size) {
                    return new NotificationResponse[size];
                }
            };

    public List<Notification> getNotifications() {
        return mNotifications;
    }

    public void setNotifications(List<Notification> notifications) {
        mNotifications = notifications;
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
