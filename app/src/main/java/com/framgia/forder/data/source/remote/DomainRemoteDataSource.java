package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.DomainDataSource;
import com.framgia.forder.data.source.remote.api.response.DomainResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Age on 4/12/2017.
 */

public class DomainRemoteDataSource implements DomainDataSource.RemoteDataSource {
    private FOrderApi mFOrderApi;

    public DomainRemoteDataSource(FOrderApi api) {
        mFOrderApi = api;
    }

    @Override
    public Observable<List<Domain>> getListDomain(int userId, String userEmail, String userToken) {
        return mFOrderApi.getDomains(userId, userEmail, userToken)
                .flatMap(new Func1<DomainResponse, Observable<List<Domain>>>() {
                    @Override
                    public Observable<List<Domain>> call(DomainResponse domainResponse) {
                        if (domainResponse == null || domainResponse.getDomainList() == null) {
                            return Observable.just(null);
                        }
                        List<Domain> list = new ArrayList<>();
                        for (DomainResponse.ChooseDomain chooseDomain : domainResponse
                                .getDomainList()) {
                            list.add(chooseDomain.getDomain());
                        }
                        return Observable.just(list);
                    }
                });
    }
}
