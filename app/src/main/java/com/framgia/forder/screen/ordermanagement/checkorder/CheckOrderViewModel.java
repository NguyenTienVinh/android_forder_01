package com.framgia.forder.screen.ordermanagement.checkorder;

/**
 * Exposes the data to be used in the CheckOrder screen.
 */

public class CheckOrderViewModel implements CheckOrderContract.ViewModel {

    private CheckOrderContract.Presenter mPresenter;

    public CheckOrderViewModel() {
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
    public void setPresenter(CheckOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
