package com.framgia.forder.screen.searchshop;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShopSearchResultContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchSuccess(List<Shop> shops);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
