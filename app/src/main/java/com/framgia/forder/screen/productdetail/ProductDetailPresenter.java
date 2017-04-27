package com.framgia.forder.screen.productdetail;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ProductDetailFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProductDetailPresenter implements ProductDetailContract.Presenter {
    private static final String TAG = ProductDetailPresenter.class.getName();

    private final ProductDetailContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private ProductRepository mProductRepository;
    private DomainRepository mDomainRepository;

    ProductDetailPresenter(ProductDetailContract.ViewModel viewModel,
            ProductRepository productRepository, DomainRepository domainRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
        mDomainRepository = domainRepository;
    }

    @Override
    public void addToCart(Product product) {
        if (product == null) {
            return;
        }
        Subscription subscription =
                mProductRepository.addToCart(product).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mViewModel.onAddToCartSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onAddToCartError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListProductInShop(int shopId) {
        Subscription subscription = mProductRepository.getListProductInShop(shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Product>>() {
                    @Override
                    public void call(List<Product> products) {
                        mViewModel.onGetListProductShopSuccess(products);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListProductShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onStart() {
        mProductRepository.openTransaction();
    }

    @Override
    public void onStop() {
        mProductRepository.closeTransaction();
    }
}
