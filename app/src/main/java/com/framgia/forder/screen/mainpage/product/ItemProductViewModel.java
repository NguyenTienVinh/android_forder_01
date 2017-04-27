package com.framgia.forder.screen.mainpage.product;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by Duong on 4/13/2017.
 */

public class ItemProductViewModel extends BaseObservable {
    private Product mProduct;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private OrderListener mOrderListener;

    public ItemProductViewModel(Product product,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener,
            OrderListener orderListener) {
        mProduct = product;
        mItemClickListener = itemClickListener;
        mOrderListener = orderListener;
    }

    public String getProductImage() {
        if (mProduct != null
                && mProduct.getCollectionImage() != null
                && mProduct.getCollectionImage().getImage() != null) {
            return mProduct.getCollectionImage().getImage().getUrl();
        }
        return "";
    }

    public String getProductName() {
        return mProduct.getName();
    }

    public String getOrderTime() {
        return mProduct.getFormatStartHour() + "-" + mProduct.getFormatEndHour();
    }

    public String getProductPrice() {
        return mProduct.getFormatPrice();
    }

    public String getShopName() {
        return mProduct.getShop().getName();
    }

    public String getShopImage() {
        if (mProduct != null
                && mProduct.getShop() != null
                && mProduct.getShop().getCollectionAvatar() != null
                && mProduct.getShop().getCollectionAvatar().getImage() != null) {
            return mProduct.getShop().getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mProduct);
    }

    public void addCart() {
        if (mOrderListener == null) {
            return;
        }
        mOrderListener.onAddToCart(mProduct);
    }
}
