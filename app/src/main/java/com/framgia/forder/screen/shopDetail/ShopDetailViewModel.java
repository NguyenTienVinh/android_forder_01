package com.framgia.forder.screen.shopDetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listshop.ListShopFragment;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the DetailShop screen.
 */

public class ShopDetailViewModel extends BaseObservable implements ShopDetailContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private ShopDetailContract.Presenter mPresenter;
    private Shop mShop;
    private ShopAdapter mAdapter;
    private Navigator mNavigator;

    public ShopDetailViewModel(Shop shop, ShopAdapter adapter, Navigator navigator) {
        mShop = shop;
        mAdapter = adapter;
        mNavigator = navigator;
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShopDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getName() {
        return mShop.getName();
    }

    @Bindable
    public String getDescription() {
        return mShop.getDescription();
    }

    @Bindable
    public float getAverageRating() {
        return mShop.getAverageRating();
    }

    @Bindable
    public String getShopImage() {
        if (mShop != null
                && mShop.getCollectionAvatar() != null
                && mShop.getCollectionAvatar().getImage() != null) {
            return mShop.getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    @Bindable
    public String getStatus() {
        return mShop.getStatus();
    }

    @Override
    public void onGetListShopSuccess(List<Shop> shops) {
        mAdapter.updateData(shops);
    }

    @Override
    public void onGetListShopError(BaseException error) {
        //TODO dev later
    }

    @Bindable
    public String getShopOwnerName() {
        return mShop.getUser().getName();
    }

    @Bindable
    public String getShopOwnerEmail() {
        return mShop.getUser().getEmail();
    }

    public void onSeeMoreShopClick() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListShopFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, "ListProductFragment");
    }

    public ShopAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (!(item instanceof Shop)) {
            return;
        }
        Shop shop = (Shop) item;
        mNavigator.goNextChildFragment(R.id.layout_content, ShopDetailFragment.newInstance(shop),
                true, Navigator.RIGHT_LEFT, "ShopDetailFragment");
    }
}
