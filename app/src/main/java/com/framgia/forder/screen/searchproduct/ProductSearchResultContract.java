package com.framgia.forder.screen.searchproduct;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProductSearchResultContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchSuccess(List<Product> products);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
