package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

public class OrderDetail {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("product")
    private Product mProduct;
    @Expose
    @SerializedName("quantity")
    private int mQuantity;
    @Expose
    @SerializedName("status")
    private int mStatus;
    private boolean mCheckBoxStatus;

    public OrderDetail() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public boolean isCheckBoxStatus() {
        return mCheckBoxStatus;
    }

    public void setCheckBoxStatus(boolean checkBoxStatus) {
        mCheckBoxStatus = checkBoxStatus;
    }

    public String getTotalPriceFormat() {
        return String.format(FORMAT_PRICE, mProduct.getPrice() * mQuantity) + UNIT_MONEY;
    }
}
