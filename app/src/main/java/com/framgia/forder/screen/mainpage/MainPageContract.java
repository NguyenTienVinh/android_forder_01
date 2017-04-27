package com.framgia.forder.screen.mainpage;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainPageContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListProductError(BaseException exception);

        void onGetListProductSuccess(List<Product> products);

        void onGetListShopError(BaseException exception);

        void onGetListShopSuccess(List<Shop> shops);

        void onAddToCartError(BaseException exception);

        void onAddToCartSuccess();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void getListProduct();

        void getListShop();
    }
}
