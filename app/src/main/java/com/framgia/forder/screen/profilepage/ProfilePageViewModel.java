package com.framgia.forder.screen.profilepage;

import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.login.LoginActivity;
import com.framgia.forder.screen.orderhistory.OrderHistoryFragment;
import com.framgia.forder.screen.profilepage.profiledetail.ProfileDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ProfileFragment screen.
 */

public class ProfilePageViewModel implements ProfilePageContract.ViewModel {
    private static final String TAG = "ProfileDetailFragment";

    private Navigator mNavigator;
    private ProfilePageContract.Presenter mPresenter;
    private User mUser;

    public ProfilePageViewModel(Navigator navigator) {
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
    public void setPresenter(ProfilePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetProfileUserSuccess(User user) {
        if (user == null) {
            return;
        }
        mUser = user;
    }

    public String getUsername() {
        return mUser != null ? mUser.getName() : "";
    }

    public String getEmail() {
        return mUser != null ? mUser.getEmail() : "";
    }

    public void onClickProfileDetail() {
        mNavigator.goNextChildFragment(R.id.layout_content, ProfileDetailFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, TAG);
    }

    public void onClickHistoryOrder() {
        mNavigator.goNextChildFragment(R.id.layout_content, OrderHistoryFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, TAG);
    }

    public void onClickYourOrder() {
        //TODO: open Your Order
    }

    public void onClickShopManagement() {
        //TODO: open Shop Management
    }

    public void onClickDomainManagement() {
        //TODO: open Domain Management
    }

    public void onLogoutClicked() {
        mPresenter.clearData();
        mNavigator.startActivity(LoginActivity.class);
        mNavigator.finishActivity();
    }
}
