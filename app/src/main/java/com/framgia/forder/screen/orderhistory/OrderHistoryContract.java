package com.framgia.forder.screen.orderhistory;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * Created by ASUS on 25-04-2017.
 */

public interface OrderHistoryContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<OrderHistoryContract.Presenter> {
        void onGetListAllOrderHistoryError(BaseException exception);

        void onGetListAllOrderHistorySuccess(List<Order> orderHistories);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
