package com.framgia.forder.screen.mainpage.shop;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by Duong on 4/13/2017.
 */

public class ItemShopViewModel extends BaseObservable {
    private Shop mShop;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    public ItemShopViewModel(Shop shop,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mShop = shop;
        mItemClickListener = listener;
    }

    public String getShopImage() {
        if (mShop != null
                && mShop.getCollectionAvatar() != null
                && mShop.getCollectionAvatar().getImage() != null) {
            return mShop.getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getShopName() {
        return mShop.getName();
    }

    public String getOwner() {
        if (mShop != null && mShop.getUser() != null && mShop.getUser().getName() != null) {
            return mShop.getUser().getName();
        }
        return "";
    }

    public String getDescription() {
        return mShop.getDescription();
    }

    public float getRating() {
        return mShop.getAverageRating();
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mShop);
    }
}
