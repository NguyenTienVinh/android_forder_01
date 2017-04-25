package com.framgia.forder.screen.productdetail;

/**
 * Listens to user actions from the UI ({@link ProductDetailFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProductDetailPresenter implements ProductDetailContract.Presenter {
    private static final String TAG = ProductDetailPresenter.class.getName();

    private final ProductDetailContract.ViewModel mViewModel;

    ProductDetailPresenter(ProductDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
