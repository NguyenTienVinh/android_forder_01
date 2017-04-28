package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.Cart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by tri on 26/04/2017.
 */

public class OrderRequest {
    @SerializedName("cart")
    @Expose
    private List<Cart> mCartList;

    public List<Cart> getCartList() {
        return mCartList;
    }

    public void setCartList(List<Cart> cartList) {
        mCartList = cartList;
    }
}
