package com.framgia.forder.data.source;

import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderDetail;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import java.util.ArrayList;
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

    public Observable<List<Order>> getOrderHistory(int userId, int domainId) {
        // TODO: Fake data, remove late
        String productImage = "http://www.papawestray.co.uk/images/shop-interior.jpg";
        List<Order> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        int status = 1;
        Double totalPay = 120000.0;
        String time = "2017-04-27T00:00:00.000Z";
        for (int i = 0; i < 3; i++) {
            Product product = new Product(1, "Cơm rang", 12000.0, "Ngon",
                    new CollectionImage(new Image(productImage)), null, null, "accepted", null, 1);
            orderDetail.setId(1);
            orderDetail.setStatus(1);
            orderDetail.setQuantity(10);
            orderDetail.setProduct(product);
            orderDetails.add(orderDetail);
            Order order = new Order(1, status, time, totalPay, null, orderDetails);
            orders.add(order);
        }
        return Observable.just(orders);
    }
}
