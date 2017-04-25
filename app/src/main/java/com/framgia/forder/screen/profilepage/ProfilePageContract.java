package com.framgia.forder.screen.profilepage;

import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProfilePageContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetProfileUserSuccess(User user);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void clearData();
    }
}
