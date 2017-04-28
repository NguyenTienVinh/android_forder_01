package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.OrderDataSource;
import com.framgia.forder.data.source.remote.api.response.OrderManagementResponse;
import com.framgia.forder.data.source.remote.api.response.OrderResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Duong on 4/27/2017.
 */

public class OrderRemoteDataSource extends BaseRemoteDataSource
        implements OrderDataSource.RemoteDataSource {

    public OrderRemoteDataSource(FOrderApi api) {
        super(api);
    }

    @Override
    public Observable<List<Order>> getOrderManagement() {
        return mFOrderApi.getOrderManagement()
                .flatMap(new Func1<OrderManagementResponse, Observable<List<Order>>>() {
                    @Override
                    public Observable<List<Order>> call(
                            OrderManagementResponse orderManagementResponse) {
                        if (orderManagementResponse != null) {
                            return Observable.just(orderManagementResponse.getOrders());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Order>> getOrderHistory(int userId, int domainId) {
        return mFOrderApi.getListOrder(userId, domainId)
                .flatMap(new Func1<OrderResponse, Observable<List<Order>>>() {

                    @Override
                    public Observable<List<Order>> call(OrderResponse orderResponse) {
                        if (orderResponse != null) {
                            return Observable.just(orderResponse.getOrders());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }
}
