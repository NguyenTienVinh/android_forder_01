package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Created by tri on 20/04/2017.
 */

public class CartItem {

    @SerializedName("status")
    @Expose
    private int mStatus;
    @SerializedName("product_id")
    @Expose
    private int mProductId;
    @SerializedName("user_id")
    @Expose
    private int mUserId;
    @SerializedName("quantity")
    @Expose
    private int mQuantity;
    private int mDomainId;
    private int mShopId;
    private String mProductName;
    private String mProductImage;
    private double mPrice;
    private String mStartHour;
    private String mEndHour;
    private Double mTotal;

    public CartItem() {
    }

    public CartItem(int domainId, int shopId, int quantity, int productId, String productName,
            String productImage, double price, String startHour, String endHour, Double total) {
        mDomainId = domainId;
        mShopId = shopId;
        mQuantity = quantity;
        mProductId = productId;
        mProductName = productName;
        mProductImage = productImage;
        mPrice = price;
        mStartHour = startHour;
        mEndHour = endHour;
        mTotal = total;
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public void setProductImage(String productImage) {
        mProductImage = productImage;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getStartHour() {
        return mStartHour;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    public String getEndHour() {
        return mEndHour;
    }

    public void setEndHour(String endHour) {
        mEndHour = endHour;
    }

    public String getFormatPrice() {
        return String.format(FORMAT_PRICE, mTotal) + UNIT_MONEY;
    }

    public Double getTotal() {
        return mQuantity * mPrice;
    }

    public void setTotal(Double total) {
        mTotal = total;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }
}
