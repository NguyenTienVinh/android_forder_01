package com.framgia.forder.screen.mainpage;

import com.framgia.forder.screen.main.MainActivity;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPageContainerPresenter implements MainPageContainerContract.Presenter {
    private static final String TAG = MainPageContainerPresenter.class.getName();

    private final MainPageContainerContract.ViewModel mViewModel;

    MainPageContainerPresenter(MainPageContainerContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
