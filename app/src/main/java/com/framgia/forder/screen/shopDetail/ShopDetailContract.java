package com.framgia.forder.screen.shopDetail;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShopDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListShopSuccess(List<Shop> shops);

        void onGetListShopError(BaseException error);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
