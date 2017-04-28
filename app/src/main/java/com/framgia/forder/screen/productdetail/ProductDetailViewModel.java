package com.framgia.forder.screen.productdetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.ListProductFragment;
import com.framgia.forder.screen.productdetail.adapter.CommentAdapter;
import com.framgia.forder.screen.productdetail.adapter.ProductShopAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the Detailproduct screen.
 */

public class ProductDetailViewModel extends BaseObservable
        implements ProductDetailContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private ProductDetailContract.Presenter mPresenter;
    private final Product mProduct;
    private final Navigator mNavigator;
    private final ProductShopAdapter mProductShopAdapter;
    private final CommentAdapter mCommentInProductAdapter;
    private String mComment;

    public ProductDetailViewModel(Product product, ProductShopAdapter productShopAdapter,
            CommentAdapter commentInProductAdapter, Navigator navigator) {
        mProduct = product;
        mNavigator = navigator;
        mProductShopAdapter = productShopAdapter;
        mCommentInProductAdapter = commentInProductAdapter;
        mProductShopAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListProductInShop(mProduct.getShopId());
        mPresenter.getListCommentInProduct(mProduct.getId());
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
    public void onGetListCommentInProductError(BaseException exception) {
        // Todo show dialog message
    }

    @Override
    public void onGetListCommentInProductSusscess(List<Comment> comments) {
        mCommentInProductAdapter.updateData(comments);
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

    @Override
    public void onCommentSuccess() {
        // Todo show dialog message
    }

    @Override
    public void onCommentError() {
        // Todo show dialog message
    }

    public void onClickSendComment() {
        if (TextUtils.isEmpty(mComment)) {
            return;
        }
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setComment(mComment);
        mPresenter.sendComment(commentRequest);
        mNavigator.showToast(R.string.ok);
    }

    public ProductShopAdapter getProductShopAdapter() {
        return mProductShopAdapter;
    }

    public CommentAdapter getCommentAdapter() {
        return mCommentInProductAdapter;
    }

    @Bindable
    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }
}
