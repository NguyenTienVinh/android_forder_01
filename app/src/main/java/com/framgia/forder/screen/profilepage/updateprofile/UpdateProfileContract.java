package com.framgia.forder.screen.profilepage.updateprofile;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface UpdateProfileContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetUserProfile(User user);

        void onUpdateProfileSuccess();

        void onUpdateProfileError(BaseException throwable);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void updateProfile(UpdateProfileRequest updateProfileRequest);

        void getUserProfile();

        boolean validateDataInputChange(String userName, String chatwordId, String description);
    }
}
