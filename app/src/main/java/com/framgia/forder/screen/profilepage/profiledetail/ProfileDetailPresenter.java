package com.framgia.forder.screen.profilepage.profiledetail;

import com.framgia.forder.data.source.UserRepository;

/**
 * Listens to user actions from the UI ({@link ProfileDetailFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProfileDetailPresenter implements ProfileDetailContract.Presenter {
    private static final String TAG = ProfileDetailPresenter.class.getName();

    private final ProfileDetailContract.ViewModel mViewModel;
    private UserRepository mUserRepository;

    ProfileDetailPresenter(ProfileDetailContract.ViewModel viewModel,
            UserRepository userRepository) {
        mViewModel = viewModel;
        mUserRepository = userRepository;
    }

    @Override
    public void onStart() {
        mViewModel.onGetProfileDetail(mUserRepository.getUser());
    }

    @Override
    public void onStop() {
    }
}
