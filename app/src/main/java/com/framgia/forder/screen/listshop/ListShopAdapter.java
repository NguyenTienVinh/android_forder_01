package com.framgia.forder.screen.listshop;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.databinding.ItemHeaderShopBinding;
import com.framgia.forder.databinding.ItemListShopBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.shop.ItemShopViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 20-04-2017.
 */

public class ListShopAdapter extends BaseRecyclerViewAdapter<RecyclerView.ViewHolder> {
    private static final int HEADER = 0;
    private static final int CONTENT = 1;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private List<Shop> mShops;

    ListShopAdapter(@NonNull Context context, List<Shop> shops) {
        super(context);
        mShops = new ArrayList<>();
        mShops.addAll(shops);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                ItemHeaderShopBinding itemHeaderShopBinding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                R.layout.item_header_shop, parent, false);
                return new HeaderViewHolder(itemHeaderShopBinding, mItemClickListener);
            case CONTENT:
                ItemListShopBinding itemListShopBinding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                R.layout.item_list_shop, parent, false);
                return new ContentViewHolder(itemListShopBinding, mItemClickListener);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.bind(mShops.get(position));
                break;
            case CONTENT:
                ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
                contentViewHolder.bind(mShops.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : CONTENT;
    }

    void updateData(List<Shop> shops) {
        if (shops == null) {
            return;
        }
        mShops.clear();
        mShops.addAll(shops);
        notifyDataSetChanged();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ItemHeaderShopBinding mBinding;
        private OnRecyclerViewItemClickListener<Object> mItemClickListener;

        HeaderViewHolder(ItemHeaderShopBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Shop shop) {
            mBinding.setViewModel(new ItemShopViewModel(shop, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        private ItemListShopBinding mBinding;
        private OnRecyclerViewItemClickListener<Object> mItemClickListener;

        ContentViewHolder(ItemListShopBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Shop shop) {
            mBinding.setViewModel(new ItemShopViewModel(shop, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
