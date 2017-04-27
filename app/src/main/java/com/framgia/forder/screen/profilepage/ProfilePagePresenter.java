package com.framgia.forder.screen.profilepage;

import com.framgia.forder.data.source.UserRepository;

/**
 * Listens to user actions from the UI ({@link ProfilePageFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProfilePagePresenter implements ProfilePageContract.Presenter {
    private static final String TAG = ProfilePagePresenter.class.getName();

    private final ProfilePageContract.ViewModel mViewModel;
    private UserRepository mUserRepository;

    ProfilePagePresenter(ProfilePageContract.ViewModel viewModel, UserRepository userRepository) {
        mViewModel = viewModel;
        mUserRepository = userRepository;
    }

    @Override
    public void onStart() {
        mViewModel.onGetProfileUserSuccess(mUserRepository.getUser());
    }

    @Override
    public void onStop() {
    }

    @Override
    public void clearData() {
        mUserRepository.clearData();
    }
}
