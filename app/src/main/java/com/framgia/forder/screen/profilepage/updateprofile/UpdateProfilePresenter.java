package com.framgia.forder.screen.profilepage.updateprofile;

import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.data.source.remote.api.response.UpdateProfileResponse;
import java.util.Objects;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link UpdateProfileFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class UpdateProfilePresenter implements UpdateProfileContract.Presenter {
    private static final String TAG = UpdateProfilePresenter.class.getName();

    private final UpdateProfileContract.ViewModel mViewModel;
    private CompositeSubscription mCompositeSubscription;
    private UserRepository mUserRepository;

    UpdateProfilePresenter(UpdateProfileContract.ViewModel viewModel,
            UserRepository userRepository) {
        mViewModel = viewModel;
        mUserRepository = userRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getUserProfile();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void updateProfile(UpdateProfileRequest updateProfileRequest) {
        Subscription subscription = mUserRepository.updateProfile(updateProfileRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UpdateProfileResponse>() {
                    @Override
                    public void call(UpdateProfileResponse updateProfileRespone) {
                        mUserRepository.clearData();
                        mUserRepository.saveUser(
                                updateProfileRespone.getUpdateProfileRequest().getUser());
                        mViewModel.onUpdateProfileSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onUpdateProfileError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void getUserProfile() {
        mViewModel.onGetUserProfile(mUserRepository.getUser());
    }

    @Override
    public boolean validateDataInputChange(String userName, String chatwordId, String description) {
        boolean isDataChange = false;
        String oldUsername = mUserRepository.getUser().getName();
        String oldChatworkId = mUserRepository.getUser().getChatworkId();
        String oldDescription = mUserRepository.getUser().getDescription();
        if (!Objects.equals(userName, oldUsername)) {
            isDataChange = true;
        }
        if (!Objects.equals(chatwordId, oldChatworkId)) {
            isDataChange = true;
        }
        if (!Objects.equals(description, oldDescription)) {
            isDataChange = true;
        }
        return isDataChange;
    }
}
