package com.framgia.forder.screen.searchshop;

/**
 * Listens to user actions from the UI ({@link ShopSearchResultFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ShopSearchResultPresenter implements ShopSearchResultContract.Presenter {
    private static final String TAG = ShopSearchResultPresenter.class.getName();

    private final ShopSearchResultContract.ViewModel mViewModel;

    ShopSearchResultPresenter(ShopSearchResultContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
