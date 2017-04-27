package com.framgia.forder.data.source.remote.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tri on 26/04/2017.
 */

public class OrderResponse extends BaseRespone {
    @SerializedName("status")
    @Expose
    private int mStatus;
    @SerializedName("message")
    @Expose
    private String mMessage;

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
