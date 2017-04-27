package com.framgia.forder.screen.profilepage.profiledetail;

import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProfileDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetProfileDetail(User user);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
