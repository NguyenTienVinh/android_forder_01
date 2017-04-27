package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.NotificationDataSource;
import com.framgia.forder.data.source.remote.api.response.NotificationResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationRemoteDataSource extends BaseRemoteDataSource
        implements NotificationDataSource.RemoteDataSource {
    public NotificationRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<List<Notification>> getListNotification() {
        return mFOrderApi.getListNotification()
                .flatMap(new Func1<NotificationResponse, Observable<List<Notification>>>() {

                    @Override
                    public Observable<List<Notification>> call(
                            NotificationResponse notificationResponse) {
                        if (notificationResponse != null) {
                            return Observable.just(notificationResponse.getNotifications());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }
}
