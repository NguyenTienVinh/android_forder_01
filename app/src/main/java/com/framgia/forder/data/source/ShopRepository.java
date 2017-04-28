package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/18/2017.
 */

public class ShopRepository {
    private ShopDataSource.RemoteDataSource mRemoteDataSource;

    public ShopRepository(ShopRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<List<Shop>> getListShop(int domainId) {
        return mRemoteDataSource.getListShop(domainId);
    }

    public Observable<List<Shop>> getRelativeShops(int domainId) {
        return mRemoteDataSource.getRelativeShops(domainId);
    }
}
