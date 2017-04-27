package com.framgia.forder.screen.profilepage.profiledetail;

import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.profilepage.updateprofile.UpdateProfileFragment;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ProfileDetail screen.
 */

public class ProfileDetailViewModel implements ProfileDetailContract.ViewModel {
    private static final String TAG = "UpdateProfileFragment";

    private ProfileDetailContract.Presenter mPresenter;
    private Navigator mNavigator;
    private User mUser;

    public ProfileDetailViewModel(Navigator navigator) {
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
    public void setPresenter(ProfileDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetProfileDetail(User user) {
        mUser = user;
    }

    public String getName() {
        return mUser != null ? mUser.getName() : "";
    }

    public String getEmail() {
        return mUser != null ? mUser.getEmail() : "";
    }

    public String getCreatedAt() {
        return mUser != null ? mUser.getFormatDate() : "";
    }

    public void onClickUpdateProfile() {
        mNavigator.goNextChildFragment(R.id.layout_content, UpdateProfileFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, TAG);
    }

    public void onClickBack() {
        mNavigator.goBackChildFragment();
    }
}
