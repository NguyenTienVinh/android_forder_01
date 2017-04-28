package com.framgia.forder.utils;

import android.support.annotation.IntDef;

import static com.framgia.forder.utils.OrderStatusCode.ACCEPT_CODE;
import static com.framgia.forder.utils.OrderStatusCode.PENDING_CODE;
import static com.framgia.forder.utils.OrderStatusCode.REJECT_CODE;

/**
 * Created by ASUS on 28-04-2017.
 */
@IntDef({ PENDING_CODE, ACCEPT_CODE, REJECT_CODE })
public @interface OrderStatusCode {
    int PENDING_CODE = 0;
    int ACCEPT_CODE = 1;
    int REJECT_CODE = 2;
}
