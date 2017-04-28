package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Shop;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/18/2017.
 */

public interface ShopDataSource {
    interface RemoteDataSource {
        Observable<List<Shop>> getListShop(int domainId);

        Observable<List<Shop>> getRelativeShops(int domainId);
    }
}
