package com.framgia.forder.screen.notification;

import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;
import java.util.Observable;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationPageViewModel extends Observable
        implements NotificationPageContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private NotificationPageContract.Presenter mPresenter;
    private NotificationPageAdapter mNotificationPageAdapter;
    private Navigator mNavigator;
    private Notification mNotification;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    NotificationPageViewModel(NotificationPageAdapter notificationPageAdapter,
            Navigator navigator) {
        mNotificationPageAdapter = notificationPageAdapter;
        mNavigator = navigator;
        mNotificationPageAdapter.setItemClickListener(this);
    }

    NotificationPageViewModel(Notification notification,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mNotification = notification;
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(NotificationPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListAllNotificationError(BaseException e) {
        mNavigator.showToast(e.getMessage());
    }

    @Override
    public void onGetListAllNotificationSuccess(List<Notification> notifications) {
        mNotificationPageAdapter.updateData(notifications);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        //TODO: Item click
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mNotification);
    }

    public String getNotificationImage() {
        if (mNotification != null
                && mNotification.getCollectionAvatar() != null
                && mNotification.getCollectionAvatar().getImage() != null) {
            return mNotification.getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getNotificationTitle() {
        return mNotification != null ? mNotification.getTitle() : "";
    }

    public String getNotificationTime() {
        return mNotification != null ? mNotification.getTime() : "";
    }

    public NotificationPageAdapter getNotificationPageAdapter() {
        return mNotificationPageAdapter;
    }
}
