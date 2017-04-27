package com.framgia.forder.screen.listProduct;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListProductContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListAllProductError(BaseException exception);

        void onGetListAllProductSuccess(List<Product> products);

        void onAddToCartError(Throwable throwable);

        void onAddToCartSuccess();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void getListAllProduct();
    }
}
