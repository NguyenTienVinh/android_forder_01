package com.framgia.forder.screen.orderhistory;

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
 * Created by ASUS on 25-04-2017.
 */

public class OrderHistoryPresenter implements OrderHistoryContract.Presenter {
    private static final String TAG = OrderHistoryPresenter.class.getName();

    private final OrderHistoryContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private OrderRepository mOrderRepository;

    OrderHistoryPresenter(OrderHistoryContract.ViewModel viewModel,
            OrderRepository orderRepository) {
        mViewModel = viewModel;
        mOrderRepository = orderRepository;
        mCompositeSubscription = new CompositeSubscription();
        getListOrderHistory();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void getListOrderHistory() {
        Subscription subscription = mOrderRepository.getOrderHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Order>>() {
                    @Override
                    public void call(List<Order> orders) {
                        mViewModel.onGetListAllOrderHistorySuccess(orders);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListAllOrderHistoryError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onGetListOrderHistoryByDate(String startDate, String endDate) {
        Subscription subscription = mOrderRepository.getOrderHistoryByDate(startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Order>>() {
                    @Override
                    public void call(List<Order> orders) {
                        mViewModel.onGetListAllOrderHistorySuccess(orders);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListAllOrderHistoryError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
