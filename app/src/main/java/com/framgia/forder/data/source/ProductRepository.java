package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import java.util.List;
import rx.Observable;

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

    public void openTransaction() {
        mLocalProductDataSource.openTransaction();
    }

    public void closeTransaction() {
        mLocalProductDataSource.closeTransaction();
    }
}
