package com.framgia.forder.data.source;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.data.source.remote.api.response.UpdateProfileResponse;
import rx.Observable;

/**
 * Created by levutantuan on 4/13/17.
 */

public class UserDataSource {

    public interface LocalDataSource {
        void saveUser(User user);

        User getUser();

        void clearData();
    }

    public interface RemoteDataSource {
        Observable<User> login(String userName, String passWord);

        Observable<UpdateProfileResponse> updateProfile(UpdateProfileRequest updateProfileRequest);
    }
}
