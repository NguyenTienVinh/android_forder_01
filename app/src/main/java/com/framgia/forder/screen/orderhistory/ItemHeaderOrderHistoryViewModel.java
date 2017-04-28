package com.framgia.forder.screen.orderhistory;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.utils.OrderStatusCode;
import com.framgia.forder.utils.Utils;

/**
 * Created by ASUS on 28-04-2017.
 */

public class ItemHeaderOrderHistoryViewModel extends BaseObservable {
    private Order mOrder;

    public ItemHeaderOrderHistoryViewModel(Order order) {
        mOrder = order;
    }

    public String getFormatOrderDate() {
        if (mOrder != null) {
            return Utils.DateTimeUntils.convertUiFormatToDataFormat(mOrder.getEndDate(),
                    Utils.INPUT_TIME_FORMAT, Utils.OUTPUT_DATE_FORMAT);
        }
        return "";
    }

    public String getTotalPrice() {
        return mOrder != null ? mOrder.getFormatTotalPrice() : "";
    }

    public String getShopName() {
        if (mOrder != null && mOrder.getShop() != null) {
            return mOrder.getShop().getName();
        }
        return "";
    }

    public boolean isPendingStatus() {
        return mOrder.getStatus() == OrderStatusCode.PENDING_CODE;
    }

    public boolean isAcceptStatus() {
        return mOrder.getStatus() == OrderStatusCode.ACCEPT_CODE;
    }

    public boolean isRejectStatus() {
        return mOrder.getStatus() == OrderStatusCode.REJECT_CODE;
    }
}
