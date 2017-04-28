package com.framgia.forder.screen.productdetail;

import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProductDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListProductShopError(BaseException exception);

        void onGetListProductShopSuccess(List<Product> products);

        void onGetListCommentInProductError(BaseException exception);

        void onGetListCommentInProductSusscess(List<Comment> commentsList);

        void onAddToCartError(BaseException exception);

        void onAddToCartSuccess();

        void onAddToCart();

        void onCommentSuccess();

        void onCommentError();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void getListProductInShop(int shopId);

        void getListCommentInProduct(int productId);

        void sendComment(CommentRequest request);
    }
}
