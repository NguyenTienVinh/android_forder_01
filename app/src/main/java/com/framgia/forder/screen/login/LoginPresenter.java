package com.framgia.forder.screen.login;

import android.text.TextUtils;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link LoginActivity}), retrieves the data and updates
 * the UI as required.
 */
final class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getName();

    private final LoginContract.ViewModel mViewModel;
    private CompositeSubscription mCompositeSubscription;
    private UserRepository mUserRepository;

    LoginPresenter(LoginContract.ViewModel viewModel, UserRepository repository) {
        mViewModel = viewModel;
        mUserRepository = repository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void login(final String userName, final String passWord) {
        if (!validateDataInput(userName, passWord)) {
            return;
        }
        Subscription subscription = mUserRepository.login(userName, passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        mUserRepository.saveUser(user);
                        mViewModel.onLoginSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onLoginError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public boolean validateDataInput(String userName, String passWord) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(userName)) {
            isValidate = false;
            mViewModel.onInputUserNameError();
        }
        if (TextUtils.isEmpty(passWord)) {
            isValidate = false;
            mViewModel.onInputPasswordError();
        }
        return isValidate;
    }
}
