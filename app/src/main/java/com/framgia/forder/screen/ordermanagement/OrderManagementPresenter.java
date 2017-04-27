package com.framgia.forder.screen.ordermanagement;

/**
 * Listens to user actions from the UI ({@link OrderManagementActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class OrderManagementPresenter implements OrderManagementContract.Presenter {
    private static final String TAG = OrderManagementPresenter.class.getName();

    private final OrderManagementContract.ViewModel mViewModel;

    OrderManagementPresenter(OrderManagementContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
