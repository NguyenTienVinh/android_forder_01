package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.source.remote.api.service.FOrderApi;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public abstract class BaseRemoteDataSource {

    FOrderApi mFOrderApi;

    public BaseRemoteDataSource(FOrderApi fOrderApi) {
        mFOrderApi = fOrderApi;
    }
}
