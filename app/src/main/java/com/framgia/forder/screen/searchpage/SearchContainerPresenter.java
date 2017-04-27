package com.framgia.forder.screen.searchpage;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.SearchRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link SearchContainerFragment}), retrieves the data
 * and updates
 * the UI as required.
 */
final class SearchContainerPresenter implements SearchContainerContract.Presenter {
    private static final String TAG = SearchContainerPresenter.class.getName();

    private final SearchContainerContract.ViewModel mViewModel;
    private CompositeSubscription mCompositeSubscriptions;
    private SearchRepository mSearchRepository;
    private DomainRepository mDomainRepository;

    SearchContainerPresenter(SearchContainerContract.ViewModel viewModel,
            SearchRepository repository, DomainRepository domainRepository) {
        mViewModel = viewModel;
        mSearchRepository = repository;
        mDomainRepository = domainRepository;
        mCompositeSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscriptions.clear();
    }

    @Override
    public void search(String keyWord) {
        Domain domain = mDomainRepository.getCurrentDomain();
        if (domain == null) {
            return;
        }
        Subscription subscription = mSearchRepository.search(domain.getId(), keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchResponse>() {
                    @Override
                    public void call(SearchResponse response) {
                        if (response == null) {
                            return;
                        }
                        mViewModel.onSearchProductsSuccess(response.getListProduct());
                        mViewModel.onSearchShopsSuccess(response.getListShop());
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onSearchProductsError(error);
                    }
                });
        mCompositeSubscriptions.add(subscription);
    }
}
