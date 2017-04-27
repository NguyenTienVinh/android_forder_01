package com.framgia.forder.screen.orderhistory;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by ASUS on 25-04-2017.
 */

public class OrderHistoryPresenter implements OrderHistoryContract.Presenter {
    private static final String TAG = OrderHistoryPresenter.class.getName();

    private final OrderHistoryContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;

    public OrderHistoryPresenter(OrderHistoryContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getListOrderHistory();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    private void getListOrderHistory() {
        //TODO: Bind list order history to viewModel
    }
}
