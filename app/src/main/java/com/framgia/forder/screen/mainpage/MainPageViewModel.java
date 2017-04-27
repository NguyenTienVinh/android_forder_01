package com.framgia.forder.screen.mainpage;

import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.ListProductFragment;
import com.framgia.forder.screen.listshop.ListShopFragment;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.shopDetail.ShopDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;
import java.util.Observable;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainPageViewModel extends Observable implements MainPageContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener {
    private static final String TAG = "ListProductFragment";

    private Navigator mNavigator;
    private MainPageContract.Presenter mPresenter;
    private ProductAdapter mProductAdapter;
    private ShopAdapter mShopAdapter;

    public MainPageViewModel(ProductAdapter productAdapter, ShopAdapter shopAdapter,
            Navigator navigator) {
        mProductAdapter = productAdapter;
        mProductAdapter.setOrderListener(this);
        mProductAdapter.setItemClickListener(this);
        mShopAdapter = shopAdapter;
        mShopAdapter.setItemClickListener(this);
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListProduct();
        mPresenter.getListShop();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(MainPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (item instanceof Product) {
            Product product = (Product) item;
            mNavigator.goNextChildFragment(R.id.layout_content,
                    ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT,
                    "ProductDetailFragment");
        }
        if (item instanceof Shop) {
            Shop shop = (Shop) item;
            mNavigator.goNextChildFragment(R.id.layout_content,
                    ShopDetailFragment.newInstance(shop), true, Navigator.RIGHT_LEFT,
                    "ShopDetailFragment");
        }
    }

    @Override
    public void onAddToCart(Product product) {
        if (product == null) {
            return;
        }
        mPresenter.addToCart(product);
    }

    @Override
    public void onGetListProductError(BaseException exception) {
        // Todo show dialog message
    }

    @Override
    public void onGetListProductSuccess(List<Product> products) {
        mProductAdapter.updateData(products);
    }

    @Override
    public void onGetListShopError(BaseException exception) {
        // Todo show dialog message
    }

    @Override
    public void onGetListShopSuccess(List<Shop> shops) {
        mShopAdapter.updateData(shops);
    }

    @Override
    public void onAddToCartError(BaseException exception) {
        // Todo show dialog message
    }

    @Override
    public void onAddToCartSuccess() {
        // Todo show dialog message
    }

    public void onSeeMoreShopClick() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListShopFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    public void onSeeMoreProductClick() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListProductFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    public ProductAdapter getProductAdapter() {
        return mProductAdapter;
    }

    public ShopAdapter getShopAdapter() {
        return mShopAdapter;
    }
}
