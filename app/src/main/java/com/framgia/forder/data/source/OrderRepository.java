package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/27/2017.
 */

public class OrderRepository {
    private OrderDataSource.RemoteDataSource mRemoteDataSource;

    public OrderRepository(OrderRemoteDataSource orderRemoteDataSource) {
        mRemoteDataSource = orderRemoteDataSource;
    }

    public Observable<List<Order>> getOrderManagement() {
        return mRemoteDataSource.getOrderManagement();
    }
}
