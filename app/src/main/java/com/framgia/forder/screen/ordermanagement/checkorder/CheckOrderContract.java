package com.framgia.forder.screen.ordermanagement.checkorder;

import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CheckOrderContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
