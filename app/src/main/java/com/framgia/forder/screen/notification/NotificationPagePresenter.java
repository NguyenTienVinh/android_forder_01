package com.framgia.forder.screen.notification;

import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.NotificationRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationPagePresenter implements NotificationPageContract.Presenter {
    private static final String TAG = NotificationPagePresenter.class.getName();

    private final NotificationPageContract.ViewModel mViewModel;
    private NotificationRepository mNotificationRepository;
    private final CompositeSubscription mCompositeSubscription;

    public NotificationPagePresenter(NotificationPageContract.ViewModel viewModel,
            NotificationRepository notificationRepository) {
        mViewModel = viewModel;
        mNotificationRepository = notificationRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getListNotification();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    private void getListNotification() {
        Subscription subscription = mNotificationRepository.getListNotification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Notification>>() {
                    @Override
                    public void call(List<Notification> notifications) {
                        mViewModel.onGetListAllNotificationSuccess(notifications);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListAllNotificationError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
