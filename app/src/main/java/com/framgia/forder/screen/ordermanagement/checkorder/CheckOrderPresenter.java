package com.framgia.forder.screen.ordermanagement.checkorder;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link CheckOrderFragment}), retrieves the data and updates
 * the UI as required.
 */
final class CheckOrderPresenter implements CheckOrderContract.Presenter {
    private static final String TAG = CheckOrderPresenter.class.getName();
    private final CompositeSubscription mCompositeSubscription;
    private final CheckOrderContract.ViewModel mViewModel;
    private OrderRepository mOrderRepository;

    CheckOrderPresenter(CheckOrderContract.ViewModel viewModel, OrderRepository orderRepository) {
        mViewModel = viewModel;
        mCompositeSubscription = new CompositeSubscription();
        mOrderRepository = orderRepository;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void getOrderManagement() {
        Subscription subscription = mOrderRepository.getOrderManagement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Order>>() {
                    @Override
                    public void call(List<Order> orders) {
                        mViewModel.onOrderManagementSuccess(orders);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetOrderManagementError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
