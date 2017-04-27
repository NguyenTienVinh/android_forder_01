package com.framgia.forder.screen.notification;

import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * Created by ASUS on 24-04-2017.
 */

public interface NotificationPageContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<NotificationPageContract.Presenter> {
        void onGetListAllNotificationError(BaseException exception);

        void onGetListAllNotificationSuccess(List<Notification> notifications);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
