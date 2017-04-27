package com.framgia.forder.screen.shopDetail;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ShopDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ShopDetailPresenter implements ShopDetailContract.Presenter {
    private static final String TAG = ShopDetailPresenter.class.getName();

    private final ShopDetailContract.ViewModel mViewModel;
    private CompositeSubscription mCompositeSubscriptions;
    private ShopRepository mShopRepository;
    private DomainRepository mDomainRepository;

    ShopDetailPresenter(ShopDetailContract.ViewModel viewModel, DomainRepository domainRepository,
            ShopRepository shopRepository) {
        mViewModel = viewModel;
        mDomainRepository = domainRepository;
        mShopRepository = shopRepository;
        mCompositeSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getRelativeShops();
    }

    @Override
    public void onStop() {
        mCompositeSubscriptions.clear();
    }

    public void getRelativeShops() {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (domain == null) {
            return;
        }
        Subscription subscription = mShopRepository.getRelativeShops(domain.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Shop>>() {
                    @Override
                    public void call(List<Shop> shops) {
                        mViewModel.onGetListShopSuccess(shops);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListShopError(error);
                    }
                });
        mCompositeSubscriptions.add(subscription);
    }
}
