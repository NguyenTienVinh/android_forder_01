package com.framgia.forder.screen.orderhistory;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Created by ASUS on 25-04-2017.
 */

public class OrderHistoryViewModel extends BaseObservable
        implements OrderHistoryContract.ViewModel {
    private OrderHistoryContract.Presenter mPresenter;
    private OrderHistoryAdapter mOrderHistoryAdapter;
    private Navigator mNavigator;
    private String mStartDate;
    private String mEndDate;

    OrderHistoryViewModel(OrderHistoryAdapter orderHistoryAdapter, Navigator navigator) {
        mOrderHistoryAdapter = orderHistoryAdapter;
        mNavigator = navigator;
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
    public void setPresenter(OrderHistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListAllOrderHistoryError(BaseException e) {
        mNavigator.showToast(e.getMessage());
    }

    @Override
    public void onGetListAllOrderHistorySuccess(List<Order> orderHistories) {
        mOrderHistoryAdapter.updateData(orderHistories);
    }

    public OrderHistoryAdapter getOrderHistoryAdapter() {
        return mOrderHistoryAdapter;
    }

    public void onClickStartDate() {
        //TODO: Pick date in DatePickerDialog
    }

    public void onClickEndDate() {
        //TODO: Pick date in DatePickerDialog
    }

    public void onChangeFilter() {
        //TODO : Change Condition Filter in DialogInterface
    }

    public void onClickHiddenFilter() {
        //TODO: Hidden filter
    }

    public void onFilter() {
        //TODO : Filter
    }

    @Bindable
    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    @Bindable
    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }
}
