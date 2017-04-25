package com.framgia.forder.screen.listshop;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListShopContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListAllShopError(BaseException exception);

        void onGetListAllShopSuccess(List<Shop> shops);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
