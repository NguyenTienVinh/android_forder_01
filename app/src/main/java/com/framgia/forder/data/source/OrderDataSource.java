package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Order;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/27/2017.
 */

public interface OrderDataSource {
    interface RemoteDataSource {
        Observable<List<Order>> getOrderManagement();
        Observable<List<Order>> getOrderHistory(int userId, int domainId);
    }
}
