package com.framgia.forder.data.model;

import java.util.List;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Created by tri on 20/04/2017.
 */

public class Cart {
    private int mDomainId;
    private int mShopId;
    private String mShopName;
    private double mTotal;
    private List<CartItem> mCartItemList;

    public Cart() {
    }

    public Cart(int domainId, int shopId, String shopName, double total) {
        mDomainId = domainId;
        mShopId = shopId;
        mShopName = shopName;
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

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

    public double getTotal() {
        return mTotal;
    }

    public void setTotal(double total) {
        mTotal = total;
    }

    public List<CartItem> getCartItemList() {
        return mCartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        mCartItemList = cartItemList;
    }

    public String getFormatTotal() {
        return String.format(FORMAT_PRICE, mTotal) + UNIT_MONEY;
    }
}
