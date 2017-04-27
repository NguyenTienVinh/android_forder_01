package com.framgia.forder.screen.orderhistory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.framgia.forder.data.model.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 25-04-2017.
 */

public class OrderHistoryAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<Order> mOrderHistories;

    public OrderHistoryAdapter(@NonNull Context context, List<Order> orderHistories) {
        mContext = context;
        mOrderHistories = new ArrayList<>();
        if (orderHistories == null) {
            return;
        }
        mOrderHistories.addAll(orderHistories);
    }

    void updateData(List<Order> orderHistories) {
        if (orderHistories == null) {
            return;
        }
        mOrderHistories.clear();
        mOrderHistories.addAll(orderHistories);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mOrderHistories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mOrderHistories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
