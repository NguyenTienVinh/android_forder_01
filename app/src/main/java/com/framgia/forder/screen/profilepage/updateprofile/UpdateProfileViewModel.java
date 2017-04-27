package com.framgia.forder.screen.profilepage.updateprofile;

import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ProfileUpdate screen.
 */

public class UpdateProfileViewModel implements UpdateProfileContract.ViewModel {
    private static final String TAG = "ProfileDetailFragment";

    private UpdateProfileContract.Presenter mPresenter;
    private Navigator mNavigator;
    private User mUser;
    private String mEmail;
    private String mUsername;
    private String mChatworkId;
    private String mDescription;

    public UpdateProfileViewModel(Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(UpdateProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetUserProfile(User user) {
        if (user == null) {
            return;
        }
        mUser = user;
    }

    public void onUpdateProfileClick() {
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
        mUser.setName(mUsername);
        mUser.setChatworkId(mChatworkId);
        mUser.setDescription(mDescription);
        updateProfileRequest.setUser(mUser);
        if (!mPresenter.validateDataInputChange(mUsername, mChatworkId, mDescription)) {
            onNothingChange();
        } else {
            mPresenter.updateProfile(updateProfileRequest);
        }
    }

    public void onClickBack() {
        mNavigator.goBackChildFragment();
    }

    public void onChangePassword() {
        //TODO: navigate to change password fragment
    }

    public void onNothingChange() {
        mNavigator.showToast(R.string.nothing_change);
    }

    @Override
    public void onUpdateProfileSuccess() {
        mNavigator.showToast(R.string.update_profile_success);
        mPresenter.getUserProfile();
    }

    @Override
    public void onUpdateProfileError(BaseException e) {
        mNavigator.showToast(e.getMessage());
    }

    public String getEmail() {
        return mUser != null ? mUser.getEmail() : "";
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getUsername() {
        return mUser != null ? mUser.getName() : "";
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getChatworkId() {
        return mUser != null ? mUser.getChatworkId() : "";
    }

    public void setChatworkId(String chatworkId) {
        mChatworkId = chatworkId;
    }

    public String getDescription() {
        return mUser != null ? mUser.getDescription() : "";
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
