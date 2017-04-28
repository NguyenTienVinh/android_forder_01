package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Notification;
import java.util.List;
import rx.Observable;

/**
 * Created by ASUS on 24-04-2017.
 */

public interface NotificationDataSource {
    interface RemoteDataSource {
        Observable<List<Notification>> getListNotification();
    }
}
