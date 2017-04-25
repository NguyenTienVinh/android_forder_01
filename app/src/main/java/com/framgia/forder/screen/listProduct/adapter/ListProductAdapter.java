package com.framgia.forder.screen.listProduct.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.ItemHeaderProductBinding;
import com.framgia.forder.databinding.ItemListProductBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.product.ItemProductViewModel;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 4/21/17.
 */

public class ListProductAdapter extends BaseRecyclerViewAdapter<RecyclerView.ViewHolder> {

    public static final int HEADER = 0;
    public static final int CONTENT = 1;

    private List<Product> mProducts;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private OrderListener mOrderListener;

    public ListProductAdapter(@NonNull Context context, List<Product> products) {
        super(context);
        mProducts = new ArrayList<>();
        mProducts.addAll(products);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {

            case HEADER:
                ItemHeaderProductBinding itemHeaderProductBinding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                R.layout.item_header_product, parent, false);
                return new HeaderViewHolder(itemHeaderProductBinding, mItemClickListener,
                        mOrderListener);
            case CONTENT:
                ItemListProductBinding itemListProductBinding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                R.layout.item_list_product, parent, false);
                return new ContentViewHolder(itemListProductBinding, mItemClickListener,
                        mOrderListener);
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
                headerViewHolder.bind(mProducts.get(position));
                break;
            case CONTENT:
                ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
                contentViewHolder.bind(mProducts.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setOrderListener(OrderListener listener) {
        mOrderListener = listener;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else {
            return CONTENT;
        }
    }

    public void updateData(List<Product> products) {
        if (products == null) {
            return;
        }
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final OnRecyclerViewItemClickListener<Object> mItemClickListener;
        private final OrderListener mOrderListener;
        private ItemHeaderProductBinding mBinding;

        HeaderViewHolder(ItemHeaderProductBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener,
                OrderListener orderListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
            mOrderListener = orderListener;
        }

        void bind(Product product) {
            mBinding.setViewModel(
                    new ItemProductViewModel(product, mItemClickListener, mOrderListener));
            mBinding.executePendingBindings();
        }
    }

    private static class ContentViewHolder extends RecyclerView.ViewHolder {
        private ItemListProductBinding mBinding;
        private final OnRecyclerViewItemClickListener<Object> mItemClickListener;
        private final OrderListener mOrderListener;

        ContentViewHolder(ItemListProductBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener,
                OrderListener orderListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
            mOrderListener = orderListener;
        }

        void bind(Product product) {
            mBinding.setViewModel(
                    new ItemProductViewModel(product, mItemClickListener, mOrderListener));
            mBinding.executePendingBindings();
        }
    }
}
