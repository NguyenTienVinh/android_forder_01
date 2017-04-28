package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

public class ProductRepository {
    private ProductDataSource.RemoteDataSource mRemoteDataSource;
    private ProductDataSource.LocalDataSource mLocalProductDataSource;
    private DomainRepository mDomainRepository;
    private int mCurrentDomainId;

    public ProductRepository(ProductRemoteDataSource remoteDataSource,
            ProductLocalDataSource productLocalDataSource, DomainRepository domainRepository) {
        mRemoteDataSource = remoteDataSource;
        mLocalProductDataSource = productLocalDataSource;
        mDomainRepository = domainRepository;
        mCurrentDomainId = mDomainRepository.getCurrentDomain().getId();
    }

    public Observable<List<Product>> getListProduct() {
        return mRemoteDataSource.getListProduct(mCurrentDomainId);
    }

    public Observable<Void> orderOneShop(OrderRequest orderRequest, final int shopId) {
        return mRemoteDataSource.orderProduct(orderRequest)
                .flatMap(new Func1<Void, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(Void aVoid) {
                        return removeOrderOneShop(shopId);
                    }
                });
    }

    public Observable<Void> orderAllShop(OrderRequest orderRequest) {
        return mRemoteDataSource.orderProduct(orderRequest)
                .flatMap(new Func1<Void, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(Void aVoid) {
                        return removeAllOrder();
                    }
                });
    }

    public Observable<Void> addToCart(Product product) {
        return mLocalProductDataSource.addShoppingCard(product, mCurrentDomainId);
    }

    public Observable<Void> deleteCart(int productId) {
        return mLocalProductDataSource.deleteShoppingCard(productId, mCurrentDomainId);
    }

    public Observable<Void> upQuantity(int productId) {
        return mLocalProductDataSource.upQuantity(productId, mCurrentDomainId);
    }

    public Observable<Void> downQuantity(int productId) {
        return mLocalProductDataSource.downQuantity(productId, mCurrentDomainId);
    }

    public Observable<Integer> getNumberItem() {
        return mLocalProductDataSource.getNumberItem(mCurrentDomainId);
    }

    public Observable<Double> getTotalPrice() {
        return mLocalProductDataSource.getTotalPrice(mCurrentDomainId);
    }

    public Observable<List<Cart>> getAllShoppingCart() {
        return mLocalProductDataSource.getAllShoppingCart(mCurrentDomainId);
    }

    private Observable<Void> removeOrderOneShop(int shopId) {
        return mLocalProductDataSource.removeOrderOneShop(shopId, mCurrentDomainId);
    }

    private Observable<Void> removeAllOrder() {
        return mLocalProductDataSource.removeAllOrder(mCurrentDomainId);
    }

    public void openTransaction() {
        mLocalProductDataSource.openTransaction();
    }

    public void closeTransaction() {
        mLocalProductDataSource.closeTransaction();
    }

    public Observable<List<Product>> getListProductInShop(int shopId) {
        return mRemoteDataSource.getListProductInShop(shopId, mCurrentDomainId);
    }

    public Observable<List<Comment>> getListCommentInProduct(int productId) {
        return mRemoteDataSource.getListCommentInProduct(productId, mCurrentDomainId);
    }

    public Observable<Comment> sendComment(CommentRequest request) {
        return mRemoteDataSource.sendComment(request);
    }
}
