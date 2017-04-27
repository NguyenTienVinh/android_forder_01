package com.framgia.forder.screen.productdetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.ListProductFragment;
import com.framgia.forder.screen.productdetail.adapter.ProductShopAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the Detailproduct screen.
 */

public class ProductDetailViewModel extends BaseObservable
        implements ProductDetailContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = "ListProductFragment";

    private ProductDetailContract.Presenter mPresenter;
    private Product mProduct;
    private Navigator mNavigator;
    private ProductShopAdapter mProductShopAdapter;

    public ProductDetailViewModel(Product product, ProductShopAdapter productShopAdapter,
            Navigator navigator) {
        mProduct = product;
        mNavigator = navigator;
        mProductShopAdapter = productShopAdapter;
        mProductShopAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListProductInShop(mProduct.getShopId());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ProductDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getNameProduct() {
        return mProduct.getName();
    }

    @Bindable
    public String getDescription() {
        return mProduct.getDescription();
    }

    @Bindable
    public String getProductPrice() {
        return mProduct.getFormatPrice();
    }

    public String getStatus() {
        return mProduct.getStatus();
    }

    @Bindable
    public String getOrderTime() {
        return mProduct.getFormatStartHour() + "-" + mProduct.getFormatEndHour();
    }

    @Bindable
    public String getProductImage() {
        if (mProduct != null
                && mProduct.getCollectionImage() != null
                && mProduct.getCollectionImage().getImage() != null) {
            return mProduct.getCollectionImage().getImage().getUrl();
        }
        return "";
    }

    @Bindable
    public String getShopImage() {
        if (mProduct != null
                && mProduct.getShop() != null
                && mProduct.getShop().getCollectionAvatar() != null
                && mProduct.getShop().getCollectionAvatar().getImage() != null) {
            return mProduct.getShop().getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    @Bindable
    public String getShopName() {
        return mProduct.getShop().getName();
    }

    @Bindable
    public String getNameUserShop() {
        return mProduct.getShop().getDescription();
    }

    @Bindable
    public float getRatingShop() {
        return mProduct.getShop().getAverageRating();
    }

    public void onClickSeeAllProduct() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListProductFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, "ListProductFragment");
    }

    @Bindable
    public String getUserImage() {
        if (mProduct != null
                && mProduct.getShop().getUser() != null
                && mProduct.getShop().getCollectionAvatar() != null
                && mProduct.getShop().getCollectionAvatar().getImage() != null) {
            return mProduct.getShop().getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    @Override
    public void onGetListProductShopError(BaseException exception) {
        // Todo show dialog message
    }

    @Override
    public void onGetListProductShopSuccess(List<Product> products) {
        mProductShopAdapter.updateData(products);
    }

    @Override
    public void onAddToCartError(BaseException exception) {
        // Todo show dialog message

    }

    @Override
    public void onAddToCartSuccess() {
        //TODO update order quantity of product in here
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (!(item instanceof Product)) {
            return;
        }
        Product product = (Product) item;
        mNavigator.goNextChildFragment(R.id.layout_content,
                ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT,
                "ProductDetailFragment");
    }

    @Override
    public void onAddToCart() {
        mPresenter.addToCart(mProduct);
    }

    public void onClickSendComment() {
        //Todo send Comment
    }

    public ProductShopAdapter getProductShopAdapter() {
        return mProductShopAdapter;
    }
}
