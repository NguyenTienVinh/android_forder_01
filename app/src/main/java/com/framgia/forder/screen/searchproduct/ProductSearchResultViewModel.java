package com.framgia.forder.screen.searchproduct;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import java.util.List;

/**
 * Exposes the data to be used in the Searchproduct screen.
 */

public class ProductSearchResultViewModel extends BaseObservable
        implements ProductSearchResultContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener {
    private ProductSearchResultContract.Presenter mPresenter;
    private ProductSearchResultAdapter mAdapter;

    ProductSearchResultViewModel(ProductSearchResultAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setOrderListener(this);
        mAdapter.setItemClickListener(this);
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
    public void setPresenter(ProductSearchResultContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ProductSearchResultAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onSearchSuccess(List<Product> products) {
        mAdapter.updateData(products);
    }

    @Override
    public void onAddToCart(Product product) {
        // TODO add to cart
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        // TODO view detail product
    }
}
