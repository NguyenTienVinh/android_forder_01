package com.framgia.forder.screen.shopDetail;

/**
 * Listens to user actions from the UI ({@link ShopDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ShopDetailPresenter implements ShopDetailContract.Presenter {
    private static final String TAG = ShopDetailPresenter.class.getName();

    private final ShopDetailContract.ViewModel mViewModel;

    ShopDetailPresenter(ShopDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
