package com.framgia.forder.screen.searchproduct;

/**
 * Listens to user actions from the UI ({@link ProductSearchResultFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProductSearchResultPresenter implements ProductSearchResultContract.Presenter {
    private static final String TAG = ProductSearchResultPresenter.class.getName();

    private final ProductSearchResultContract.ViewModel mViewModel;

    ProductSearchResultPresenter(ProductSearchResultContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
