package com.framgia.forder.data.source;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.data.source.remote.api.response.UpdateProfileResponse;
import rx.Observable;

/**
 * Created by levutantuan on 4/13/17.
 */

public class UserRepository {
    private UserDataSource.RemoteDataSource mUserRemoteDataSource;
    private UserDataSource.LocalDataSource mUserLocalDataSource;

    public UserRepository(UserDataSource.RemoteDataSource userRemoteDataSource,
            UserDataSource.LocalDataSource userLocalDataSource) {
        mUserRemoteDataSource = userRemoteDataSource;
        mUserLocalDataSource = userLocalDataSource;
    }

    public Observable<User> login(String userName, String passWord) {
        return mUserRemoteDataSource.login(userName, passWord);
    }

    public Observable<UpdateProfileResponse> updateProfile(
            UpdateProfileRequest updateProfileRequest) {
        return mUserRemoteDataSource.updateProfile(updateProfileRequest);
    }

    public void saveUser(User user) {
        mUserLocalDataSource.saveUser(user);
    }

    public User getUser() {
        return mUserLocalDataSource.getUser();
    }

    public void clearData() {
        mUserLocalDataSource.clearData();
    }
}
