package com.framgia.forder.screen.ordermanagement.checkorder;

/**
 * Listens to user actions from the UI ({@link CheckOrderFragment}), retrieves the data and updates
 * the UI as required.
 */
final class CheckOrderPresenter implements CheckOrderContract.Presenter {
    private static final String TAG = CheckOrderPresenter.class.getName();

    private final CheckOrderContract.ViewModel mViewModel;

    CheckOrderPresenter(CheckOrderContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
