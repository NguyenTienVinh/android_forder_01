package com.framgia.forder.screen.ordermanagement.checkorder;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CheckOrderContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetOrderManagementError(BaseException exception);

        void onOrderManagementSuccess(List<Order> orders);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getOrderManagement();
    }
}
