package com.framgia.forder.screen.cart;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;

/**
 * Created by tri on 19/04/2017.
 */

public interface OrderItemListener {
    void onOrderOneShop(Cart cart);

    void onUpQuantity(CartItem cartItem);

    void onDownQuantity(CartItem cartItem);

    void onDeleteProduct(CartItem cartItem);
}
