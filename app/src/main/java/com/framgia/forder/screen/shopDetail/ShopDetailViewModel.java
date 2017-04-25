package com.framgia.forder.screen.shopDetail;

/**
 * Exposes the data to be used in the DetailShop screen.
 */

public class ShopDetailViewModel implements ShopDetailContract.ViewModel {

    private ShopDetailContract.Presenter mPresenter;

    public ShopDetailViewModel() {
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
}
