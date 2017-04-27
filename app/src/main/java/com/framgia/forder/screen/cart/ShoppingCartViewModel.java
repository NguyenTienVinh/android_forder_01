package com.framgia.forder.screen.cart;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import java.util.List;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Exposes the data to be used in the ShoppingCart screen.
 */

public class ShoppingCartViewModel extends BaseObservable
        implements ShoppingCartContract.ViewModel, OrderItemListener {
    private static final String TAG = "ShoppingCartFragment";
    private ShoppingCartAdapter mShoppingCartAdapter;
    private ShoppingCartContract.Presenter mPresenter;
    private double mTotalPrice;
    private List<Cart> mCartList;

    public ShoppingCartViewModel(ShoppingCartAdapter shoppingCartAdapter) {
        mShoppingCartAdapter = shoppingCartAdapter;
        shoppingCartAdapter.setOrderItemListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListCart();
        mPresenter.getTotalPrice();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShoppingCartContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListCartSuccess(List<Cart> cartList) {
        mShoppingCartAdapter.updateData(cartList);
        mCartList = cartList;
    }

    @Override
    public void onOrderOneShop(Cart cart) {
        if (cart == null) {
            return;
        }
        mPresenter.orderOneShop(cart);
    }

    @Override
    public void onUpQuantity(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        mPresenter.upQuantity(cartItem);
    }

    @Override
    public void onDownQuantity(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        mPresenter.downQuantity(cartItem);
    }

    @Override
    public void onDeleteProduct(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        mPresenter.deleteProduct(cartItem);
    }

    public ShoppingCartAdapter getShoppingCartAdapter() {
        return mShoppingCartAdapter;
    }

    @Override
    public void onGetListCartError(BaseException exception) {
        Log.e(TAG, "onGetListCartError", exception);
    }

    @Override
    public void onUpQuantityError(Throwable throwable) {
        Log.e(TAG, "onUpQuantityError", throwable);
    }

    @Override
    public void onUpQuantitySuccess() {
        reloadData();
    }

    @Override
    public void onDownQuantityError(Throwable throwable) {
        Log.e(TAG, "onDownQuantityError", throwable);
    }

    @Override
    public void onDownQuantitySuccess() {
        reloadData();
    }

    @Override
    public void onDeleteProductError(Throwable throwable) {
        Log.e(TAG, "onDeleteProductError", throwable);
    }

    @Override
    public void onDeleteProductSuccess() {
        reloadData();
    }

    @Override
    public void reloadData() {
        mPresenter.getListCart();
        mPresenter.getTotalPrice();
    }

    @Override
    public void onGetTotalPriceSuccess(double totalPrice) {
        mTotalPrice = totalPrice;
        notifyPropertyChanged(BR.totalPrice);
        mPresenter.getListCart();
    }

    @Override
    public void onGetTotalPriceError(Throwable throwable) {
        Log.e(TAG, "onGetTotalPriceError", throwable);
    }

    @Override
    public void onOrderAllShop() {
        mPresenter.orderAllShop(mCartList);
    }

    @Override
    public void onOrderAllShopError(BaseException exception) {
        Log.e(TAG, "onOrderAllShopError", exception);
    }

    @Override
    public void onOrderAllShopSuccess() {
        reloadData();
    }

    @Override
    public void onOrderOneShopError(BaseException exception) {
        Log.e(TAG, "onOrderOneShopError", exception);
    }

    @Override
    public void onOrderOneShopSuccess() {
        reloadData();
    }

    @Bindable
    public String getTotalPrice() {
        return String.format(FORMAT_PRICE, mTotalPrice) + UNIT_MONEY;
    }
}
