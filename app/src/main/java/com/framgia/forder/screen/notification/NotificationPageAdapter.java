package com.framgia.forder.screen.notification;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.databinding.ItemListNotificationBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationPageAdapter
        extends BaseRecyclerViewAdapter<NotificationPageAdapter.ItemViewHolder> {

    private OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private List<Notification> mNotifications;

    NotificationPageAdapter(@NonNull Context context, List<Notification> notifications) {
        super(context);
        mNotifications = new ArrayList<>();
        if (notifications == null) {
            return;
        }
        mNotifications.addAll(notifications);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListNotificationBinding itemListNotificationBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list_notification, parent, false);
        return new NotificationPageAdapter.ItemViewHolder(itemListNotificationBinding,
                mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mNotifications.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    void updateData(List<Notification> notifications) {
        if (notifications == null) {
            return;
        }
        mNotifications.clear();
        mNotifications.addAll(notifications);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemListNotificationBinding mBinding;
        private OnRecyclerViewItemClickListener<Object> mItemClickListener;

        ItemViewHolder(ItemListNotificationBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Notification notification) {
            mBinding.setViewModel(new NotificationPageViewModel(notification, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
