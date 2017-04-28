package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.Order;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

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
    @SerializedName("content")
    @Expose
    private List<Order> mOrders;

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

    public List<Order> getOrders() {
        return mOrders;
    }

    public void setOrders(List<Order> orders) {
        mOrders = orders;
    }
}
