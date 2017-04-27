package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Created by tri on 20/04/2017.
 */

public class Cart {
    @SerializedName("domain_id")
    @Expose
    private int mDomainId;
    @SerializedName("total_pay")
    @Expose
    private int mTotal;
    @SerializedName("shop_id")
    @Expose
    private int mShopId;
    @SerializedName("user_id")
    @Expose
    private int mUserId;
    @SerializedName("mStatus")
    @Expose
    private int mStatus;
    @SerializedName("orders")
    @Expose
    private List<CartItem> mCartItemList;
    private String mShopName;

    public Cart() {
    }

    public Cart(int domainId, int shopId, String shopName) {
        mDomainId = domainId;
        mShopId = shopId;
        mShopName = shopName;
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

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        mTotal = total;
    }

    public List<CartItem> getCartItemList() {
        return mCartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        mCartItemList = cartItemList;
    }

    public String getFormatTotal() {
        return String.valueOf(mTotal) + UNIT_MONEY;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }
}
