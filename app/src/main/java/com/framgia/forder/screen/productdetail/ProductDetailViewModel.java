package com.framgia.forder.screen.productdetail;

/**
 * Exposes the data to be used in the Detailproduct screen.
 */

public class ProductDetailViewModel implements ProductDetailContract.ViewModel {

    private ProductDetailContract.Presenter mPresenter;

    public ProductDetailViewModel() {
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
    public void setPresenter(ProductDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
