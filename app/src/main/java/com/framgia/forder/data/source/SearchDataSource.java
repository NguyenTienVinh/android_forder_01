package com.framgia.forder.data.source;

import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import rx.Observable;

/**
 * Created by Age on 4/20/2017.
 */

public interface SearchDataSource {
    interface RemoteDataSource {
        Observable<SearchResponse> search(int domainId, String keyWord);
    }
}
