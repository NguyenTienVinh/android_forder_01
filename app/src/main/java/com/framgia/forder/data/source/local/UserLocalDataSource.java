package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsKey;
import com.google.gson.Gson;

/**
 * Created by levutantuan on 4/14/17.
 */

public class UserLocalDataSource implements UserDataSource.LocalDataSource {
    private SharedPrefsApi mSharedPrefsApi;

    public UserLocalDataSource(@NonNull SharedPrefsApi prefsApi) {
        mSharedPrefsApi = prefsApi;
    }

    public void saveUser(User user) {
        String data = new Gson().toJson(user);
        mSharedPrefsApi.put(SharedPrefsKey.KEY_USER, data);
    }

    public User getUser() {
        String data = mSharedPrefsApi.get(SharedPrefsKey.KEY_USER, String.class);
        return new Gson().fromJson(data, User.class);
    }

    @Override
    public void clearData() {
        mSharedPrefsApi.clear();
    }
}
