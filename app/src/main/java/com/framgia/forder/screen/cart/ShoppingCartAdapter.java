package com.framgia.forder.screen.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.databinding.ItemContentCartBinding;
import com.framgia.forder.databinding.ItemHeaderCartBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 19/04/2017.
 */

public class ShoppingCartAdapter extends BaseExpandableListAdapter {
    private static OrderItemListener mOrderListener;
    private Context mContext;
    private List<Cart> mCartList;

    protected ShoppingCartAdapter(@NonNull Context context, List<Cart> cart) {
        mContext = context;
        mCartList = new ArrayList<>();
        if (cart == null) {
            return;
        }
        mCartList.addAll(cart);
    }

    public void updateData(List<Cart> carts) {
        if (carts == null) {
            return;
        }
        mCartList.clear();
        mCartList.addAll(carts);
        notifyDataSetChanged();
    }

    public void setOrderItemListener(OrderItemListener listener) {
        mOrderListener = listener;
    }

    @Override
    public int getGroupCount() {
        return mCartList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCartList.get(groupPosition).getCartItemList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mCartList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mCartList.get(groupPosition).getCartItemList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mCartList.get(groupPosition).getShopId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mCartList.get(groupPosition).getCartItemList().get(childPosition).getProductId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        ItemHeaderCartBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_header_cart, parent, false);
        binding.setViewModel(
                new ItemShoppingCartViewModel((Cart) getGroup(groupPosition), mOrderListener));
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        ItemContentCartBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_content_cart, parent, false);
        binding.setViewModel(
                new ItemShoppingCartViewModel((CartItem) getChild(groupPosition, childPosition),
                        mOrderListener));
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
