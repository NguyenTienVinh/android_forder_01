package com.framgia.forder.screen.ordermanagement.checkorder;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.OrderDetail;

import static com.framgia.forder.utils.Constant.StatusCode.ACCEPT_CODE;
import static com.framgia.forder.utils.Constant.StatusCode.PENDING_CODE;
import static com.framgia.forder.utils.Constant.StatusCode.REJECT_CODE;

/**
 * Created by Duong on 4/25/2017.
 */

public class ItemCheckOrderViewModel extends BaseObservable {
    private OrderDetail mOrderDetail;

    ItemCheckOrderViewModel(OrderDetail orderDetail) {
        mOrderDetail = orderDetail;
    }

    public String getProductName() {
        return mOrderDetail.getProduct().getName();
    }

    public String getProductPrice() {
        return mOrderDetail.getProduct().getFormatPrice();
    }

    public String getQuantity() {
        return String.valueOf(mOrderDetail.getQuantity());
    }

    public String getProductImage() {
        if (mOrderDetail.getProduct() != null
                && mOrderDetail.getProduct().getCollectionImage() != null
                && mOrderDetail.getProduct().getCollectionImage().getImage() != null) {
            return mOrderDetail.getProduct().getCollectionImage().getImage().getUrl();
        }
        return "";
    }

    public boolean isPendingStatus() {
        return mOrderDetail.getStatus() == PENDING_CODE;
    }

    public boolean isAcceptStatus() {
        return mOrderDetail.getStatus() == ACCEPT_CODE;
    }

    public boolean isRejectStatus() {
        return mOrderDetail.getStatus() == REJECT_CODE;
    }

    public boolean isChecked() {
        return mOrderDetail.isCheckBoxStatus();
    }
}
