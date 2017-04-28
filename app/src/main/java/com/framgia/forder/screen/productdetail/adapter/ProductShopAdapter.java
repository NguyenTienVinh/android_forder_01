package com.framgia.forder.screen.productdetail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.ItemProductShopBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.product.ItemProductViewModel;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 4/25/17.
 */

public class ProductShopAdapter extends BaseRecyclerViewAdapter<ProductShopAdapter.ItemViewHolder> {

    private final List<Product> mProducts;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private OrderListener mOrderListener;

    public ProductShopAdapter(@NonNull Context context, List<Product> products) {
        super(context);
        mProducts = new ArrayList<>();
        if (products == null) {
            return;
        }
        mProducts.addAll(products);
    }

    @Override
    public ProductShopAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductShopBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_product_shop, parent, false);
        return new ProductShopAdapter.ItemViewHolder(binding, mItemClickListener, mOrderListener);
    }

    @Override
    public void onBindViewHolder(ProductShopAdapter.ItemViewHolder holder, int position) {
        holder.bind(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateData(List<Product> products) {
        if (products == null) {
            return;
        }
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductShopBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;
        private final OrderListener mOrderListener;

        ItemViewHolder(ItemProductShopBinding binding,
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
