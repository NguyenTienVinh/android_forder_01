package com.framgia.forder.screen.searchpage;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SearchContainerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchProductsSuccess(List<Product> products);

        void onSearchShopsSuccess(List<Shop> shops);

        void onSearchProductsError(BaseException error);

        void onClickSearch(String keyWord);

        void onSearchShopsError(BaseException error);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void search(String keyWord);
    }
}
