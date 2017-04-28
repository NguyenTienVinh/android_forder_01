package com.framgia.forder.screen.chooseDomain;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ChooseDomainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetDomainSuccess(List<Domain> domains);

        void onGetDomainError(BaseException e);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void saveCurrentDomain(Domain domain);
    }
}
