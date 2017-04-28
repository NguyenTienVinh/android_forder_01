package com.framgia.forder.screen.ordermanagement.checkorder;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import java.util.List;

/**
 * Exposes the data to be used in the CheckOrder screen.
 */

public class CheckOrderViewModel implements CheckOrderContract.ViewModel {

    private CheckOrderContract.Presenter mPresenter;
    private CheckOrderAdapter mAdapter;

    public CheckOrderViewModel(CheckOrderAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getOrderManagement();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(CheckOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetOrderManagementError(BaseException exception) {

    }

    @Override
    public void onOrderManagementSuccess(List<Order> orders) {
        mAdapter.updateData(orders);
    }

    public CheckOrderAdapter getAdapter() {
        return mAdapter;
    }
}
