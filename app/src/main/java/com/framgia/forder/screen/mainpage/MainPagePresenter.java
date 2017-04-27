package com.framgia.forder.screen.mainpage;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

final class MainPagePresenter implements MainPageContract.Presenter {
    private static final String TAG = MainPagePresenter.class.getName();
    private final MainPageContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private ProductRepository mProductRepository;
    private ShopRepository mShopRepository;
    private DomainRepository mDomainRepository;
    private static final int START_SUB_LIST = 0;
    private static final int END_SUB_LIST = 6;

    MainPagePresenter(MainPageContract.ViewModel viewModel,
            ProductRepository productRepository, DomainRepository domainRepository,
            ShopRepository shopRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
        mDomainRepository = domainRepository;
        mShopRepository = shopRepository;
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
    public void getListProduct() {
        Subscription subscription = mProductRepository.getListProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Product>>() {
                    @Override
                    public void call(List<Product> products) {
                        mViewModel.onGetListProductSuccess(
                                products.subList(START_SUB_LIST, END_SUB_LIST));
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListProductError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void getListShop() {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (domain == null) {
            return;
        }
        Subscription subscription = mShopRepository.getListShop(domain.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Shop>>() {
                    @Override
                    public void call(List<Shop> shops) {
                        mViewModel.onGetListShopSuccess(
                                shops.subList(START_SUB_LIST, END_SUB_LIST));
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListShopError(error);
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
        mCompositeSubscription.clear();
        mProductRepository.closeTransaction();
    }
}
