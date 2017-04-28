package com.framgia.forder.data.source;

import com.framgia.forder.data.model.CollectionAvatar;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.remote.NotificationRemoteDataSource;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationRepository {
    private NotificationDataSource.RemoteDataSource mRemoteDataSource;

    public NotificationRepository(NotificationRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<List<Notification>> getListNotification() {
        //TODO: Fake data, remove later
        List<Notification> notifications = new ArrayList<>();
        String title = "Bạn vừa hủy đơn hàng của shop Nếp Cẩm";
        String time = "Khoảng 30 phút trước";
        String url = "http://www.papawestray.co.uk/images/shop-interior.jpg";
        for (int i = 0; i < 10; i++) {
            Notification notification =
                    new Notification(title, time, new CollectionAvatar(new Image(url)));
            notifications.add(notification);
        }
        return Observable.just(notifications);
    }
}
