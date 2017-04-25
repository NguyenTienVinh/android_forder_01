package com.framgia.forder.screen.cart;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShoppingCartContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListCartError(BaseException exception);

        void onGetListCartSuccess(List<Cart> carts);

        void onUpQuantityError(Throwable throwable);

        void onUpQuantitySuccess();

        void onDownQuantityError(Throwable throwable);

        void onDownQuantitySuccess();

        void onDeleteProductError(Throwable throwable);

        void onDeleteProductSuccess();

        void reloadData();

        void onGetTotalPriceSuccess(double totalPrice);

        void onGetTotalPriceError(Throwable throwable);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void orderItem(Cart cart);

        void getListCart();

        void upQuantity(CartItem cartItem);

        void downQuantity(CartItem cartItem);

        void deleteProduct(CartItem cartItem);

        void getTotalPrice();
    }
}
