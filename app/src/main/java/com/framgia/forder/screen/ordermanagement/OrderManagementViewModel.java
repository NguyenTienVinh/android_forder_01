package com.framgia.forder.screen.ordermanagement;

/**
 * Exposes the data to be used in the OrderManagement screen.
 */

public class OrderManagementViewModel implements OrderManagementContract.ViewModel {

    private OrderManagementContract.Presenter mPresenter;

    public OrderManagementViewModel() {
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
    public void setPresenter(OrderManagementContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
