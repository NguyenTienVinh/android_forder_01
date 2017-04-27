package com.framgia.forder.data.source.remote.api.service;

import android.app.Application;
import android.support.annotation.NonNull;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsKey;
import com.framgia.forder.data.source.remote.api.middleware.RetrofitInterceptor;
import com.framgia.forder.utils.Constant;
import com.google.gson.Gson;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public class FOrderServiceClient extends ServiceClient {

    private static FOrderApi mFOrderApiInstance;

    public static void initialize(@NonNull Application application) {
        SharedPrefsApi prefsApi = new SharedPrefsImpl(application);
        User user = new Gson().fromJson(prefsApi.get(SharedPrefsKey.KEY_USER, String.class),
                User.class);
        RetrofitInterceptor interceptor = null;
        if (user != null) {
            interceptor = new RetrofitInterceptor(user);
        }
        mFOrderApiInstance =
                createService(application, Constant.END_POINT_URL, FOrderApi.class, interceptor);
    }

    public static FOrderApi getInstance() {
        if (mFOrderApiInstance == null) {
            throw new RuntimeException("Need call method FOrderServiceClient#initialize() first");
        }
        return mFOrderApiInstance;
    }
}
