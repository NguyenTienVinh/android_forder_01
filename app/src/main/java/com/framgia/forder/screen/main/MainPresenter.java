package com.framgia.forder.screen.main;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getName();
    private final CompositeSubscription mCompositeSubscription;
    private final MainContract.ViewModel mViewModel;
    protected DomainRepository mDomainRepository;

    MainPresenter(MainContract.ViewModel viewModel, DomainRepository domainRepository) {
        mViewModel = viewModel;
        mDomainRepository = domainRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void getCurrentDomain() {
        mViewModel.showCurrentDomain(mDomainRepository.getCurrentDomain().getName());
    }

    @Override
    public void getListDomain() {
        Subscription subscription = mDomainRepository.getListDomain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Domain>>() {
                    @Override
                    public void call(List<Domain> domains) {
                        mViewModel.onGetListDomainSuccess(domains);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListDomainError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public int getCurrentDomainPosition(List<Domain> domains) {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (domain == null) {
            return 0;
        }
        for (int i = 0; i < domains.size(); i++) {
            if (domain.getId() == domains.get(i).getId()) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void saveCurrentDomain(Domain domain) {
        mDomainRepository.saveCurrentDomain(domain);
    }
}
