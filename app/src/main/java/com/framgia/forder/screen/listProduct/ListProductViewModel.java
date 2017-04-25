package com.framgia.forder.screen.listProduct;

import android.content.Context;
import android.util.Log;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.adapter.ListProductAdapter;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;
import java.util.Observable;

/**
 * Exposes the data to be used in the Productpage screen.
 */

public class ListProductViewModel extends Observable implements ListProductContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener {
    private static final String TAG = "ListProductFragment";

    private Context mContext;
    private Navigator mNavigator;
    private ListProductContract.Presenter mPresenter;
    private ListProductAdapter mListProductAdapter;

    public ListProductViewModel(Context context, ListProductAdapter listProductAdapter,
            Navigator navigator) {

        mContext = context;
        mListProductAdapter = listProductAdapter;
        mNavigator = navigator;
        mListProductAdapter.setItemClickListener(this);
        mListProductAdapter.setOrderListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListAllProduct();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ListProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListAllProductError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onGetListAllProductSuccess(List<Product> products) {
        mListProductAdapter.updateData(products);
    }

    @Override
    public void onAddToCartError(Throwable throwable) {
        Log.d(TAG, "onAddToCartError", throwable);
    }

    @Override
    public void onAddToCartSuccess() {
        // Todo show dialog message
    }

    @Override
    public void onAddToCart(Product product) {
        if (product == null) {
            return;
        }
        mPresenter.addToCart(product);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        mNavigator.goNextChildFragment(R.id.layout_content, ProductDetailFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, "ProductDetailFragment");
    }

    public ListProductAdapter getListProductAdapter() {
        return mListProductAdapter;
    }
}
