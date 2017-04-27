package com.framgia.forder.screen.ordermanagement.checkorder;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Order;

import static com.framgia.forder.utils.Constant.StatusCode.ACCEPT_CODE;
import static com.framgia.forder.utils.Constant.StatusCode.PENDING_CODE;
import static com.framgia.forder.utils.Constant.StatusCode.REJECT_CODE;

/**
 * Created by Duong on 4/13/2017.
 */

public class ItemParentCheckOrderViewModel extends BaseObservable {
    private Order mOrder;

    ItemParentCheckOrderViewModel(Order order) {
        mOrder = order;
    }

    public String getUserName() {
        return mOrder.getUser().getName();
    }

    public String getTotalPrice() {
        return mOrder.getFormatTotalPrice();
    }

    public String getOrderDate() {
        return mOrder.getEndDate();
    }

    public boolean isPendingStatus() {
        return mOrder.getStatus() == PENDING_CODE;
    }

    public boolean isAcceptStatus() {
        return mOrder.getStatus() == ACCEPT_CODE;
    }

    public boolean isRejectStatus() {
        return mOrder.getStatus() == REJECT_CODE;
    }
}
