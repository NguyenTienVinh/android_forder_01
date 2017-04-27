package com.framgia.forder.data.source;

import com.framgia.forder.data.source.remote.SearchRemoteDataSource;
import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import rx.Observable;

/**
 * Created by Age on 4/20/2017.
 */

public class SearchRepository {
    private SearchDataSource.RemoteDataSource mRemoteDataSource;

    public SearchRepository(SearchRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<SearchResponse> search(int doMain, String keyWord) {
        return mRemoteDataSource.search(doMain, keyWord);
    }
}
