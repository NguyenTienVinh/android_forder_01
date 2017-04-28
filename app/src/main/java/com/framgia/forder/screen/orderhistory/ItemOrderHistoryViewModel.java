package com.framgia.forder.screen.orderhistory;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.OrderDetail;
import com.framgia.forder.utils.OrderStatusCode;

/**
 * Created by ASUS on 28-04-2017.
 */

public class ItemOrderHistoryViewModel extends BaseObservable {
    private OrderDetail mOrderDetail;

    public ItemOrderHistoryViewModel(OrderDetail orderDetail) {
        mOrderDetail = orderDetail;
    }

    public String getProductImage() {

        if (mOrderDetail != null
                && mOrderDetail.getProduct() != null
                && mOrderDetail.getProduct().getCollectionImage() != null
                && mOrderDetail.getProduct().getCollectionImage().getImage() != null) {
            return mOrderDetail.getProduct().getCollectionImage().getImage().getUrl();
        }
        return "";
    }

    public String getProductQuantity() {
        return mOrderDetail != null ? Integer.toString(mOrderDetail.getQuantity()) : "";
    }

    public String getProductName() {
        if (mOrderDetail != null && mOrderDetail.getProduct() != null) {
            return mOrderDetail.getProduct().getName();
        }
        return "";
    }

    public String getProductPrice() {
        if (mOrderDetail != null && mOrderDetail.getProduct() != null) {
            return mOrderDetail.getProduct().getFormatPrice();
        }
        return "";
    }

    public boolean isAcceptStatus() {
        return mOrderDetail.getStatus() == OrderStatusCode.ACCEPT_CODE;
    }

    public boolean isPendingStatus() {
        return mOrderDetail.getStatus() == OrderStatusCode.PENDING_CODE;
    }

    public boolean isRejectStatus() {
        return mOrderDetail.getStatus() == OrderStatusCode.REJECT_CODE;
    }
}
