package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.DomainDataSource;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsKey;
import com.google.gson.Gson;

/**
 * Created by Age on 4/13/2017.
 */

public class DomainLocalDataSource implements DomainDataSource.LocalDataSource {

    private SharedPrefsApi mSharedPrefsApi;
    private UserDataSource.LocalDataSource mUserLocalDataSource;

    public DomainLocalDataSource(@NonNull SharedPrefsApi prefsApi,
            UserDataSource.LocalDataSource userLocalDataSource) {
        mSharedPrefsApi = prefsApi;
        mUserLocalDataSource = userLocalDataSource;
    }

    @Override
    public User getUser() {
        return mUserLocalDataSource.getUser();
    }

    @Override
    public void saveCurrentDomain(Domain domain) {
        mSharedPrefsApi.put(SharedPrefsKey.KEY_DOMAIN, new Gson().toJson(domain));
    }

    @Override
    public Domain getCurrentDomain() {
        return new Gson().fromJson(mSharedPrefsApi.get(SharedPrefsKey.KEY_DOMAIN, String.class),
                Domain.class);
    }
}
