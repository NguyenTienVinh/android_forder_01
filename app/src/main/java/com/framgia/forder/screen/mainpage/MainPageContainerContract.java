package com.framgia.forder.screen.mainpage;

import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainPageContainerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void reloadData();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
