package com.framgia.forder.data.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Created by tri on 17/04/2017.
 */

public class ShoppingCart extends RealmObject {
    @PrimaryKey
    private int mShoppingCartId;
    private int mDomainId;
    @Index
    private int mShopId;
    private int mQuantity;
    private int mProductId;
    private double mPrice;
    private String mShopName;
    private String mProductName;
    private String mProductImage;
    private String mStartHour;
    private String mEndHour;
    private Double mTotal;

    public ShoppingCart() {
    }

    public int getShoppingCartId() {
        return mShoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        mShoppingCartId = shoppingCartId;
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

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
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
        return String.format(FORMAT_PRICE, mPrice) + UNIT_MONEY;
    }

    public String getFormatTotal() {
        return String.format(FORMAT_PRICE, mTotal) + UNIT_MONEY;
    }

    public Double getTotal() {
        return mPrice * mQuantity;
    }

    public void setTotal(Double total) {
        mTotal = total;
    }
}
