package com.framgia.forder.data.model;

import com.framgia.forder.utils.OrderStatusCode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

public class Order {
    @Expose
    @SerializedName("id")
    private int mId;
    @OrderStatusCode
    private int mStatus;
    @Expose
    @SerializedName("end_date")
    private String mEndDate;
    @Expose
    @SerializedName("total_pay")
    private double mTotalPay;
    @Expose
    @SerializedName("user")
    private User mUser;
    @Expose
    @SerializedName("shop")
    private Shop mShop;
    @Expose
    @SerializedName("order_detail")
    private List<OrderDetail> mOrderDetails;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @OrderStatusCode
    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public double getTotalPay() {
        return mTotalPay;
    }

    public void setTotalPay(double totalPay) {
        mTotalPay = totalPay;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }

    public List<OrderDetail> getOrderDetails() {
        return mOrderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        mOrderDetails = orderDetails;
    }

    public String getFormatTotalPrice() {
        return String.format(FORMAT_PRICE, mTotalPay) + UNIT_MONEY;
    }
}
