package com.framgia.forder.screen.listshop;

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
 * Listens to user actions from the UI ({@link ListShopFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ListShopPresenter implements ListShopContract.Presenter {
    private static final String TAG = ListShopPresenter.class.getName();

    private final ListShopContract.ViewModel mViewModel;
    private final CompositeSubscription mCompositeSubscription;
    private ShopRepository mShopRepository;
    private DomainRepository mDomainRepository;

    ListShopPresenter(ListShopContract.ViewModel viewModel, ShopRepository shopRepository,
            DomainRepository domainRepository) {
        mViewModel = viewModel;
        mShopRepository = shopRepository;
        mDomainRepository = domainRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getListAllShop();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    private void getListAllShop() {
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
                        mViewModel.onGetListAllShopSuccess(shops);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListAllShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
