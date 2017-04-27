package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.SearchDataSource;
import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 4/20/2017.
 */

public class SearchRemoteDataSource extends BaseRemoteDataSource
        implements SearchDataSource.RemoteDataSource {
    public SearchRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<SearchResponse> search(int domainId, String keyWord) {
        //TODO remove later in here
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(1, 1, 1, "Cơm Tấm", "Cơm Tấm", "active", null, 5, 1,
                new User(117, "Nguyễn Văn Ngọc", "nguyen.van.ngocb@framgia.com"),
                "2000-01-01T11:00:00.000Z"));
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Cơm Sườn", 22000, "Cơm Sườn", null,
                "2000-01-01T11:00:00.000Z", "2000-01-01T11:00:00.000Z", "active",
                new Shop(1, 1, 1, "Cơm Tấm", "Cơm Tấm", "active", null, 5, 1,
                        new User(117, "Nguyễn Văn Ngọc", "nguyen.van.ngocb@framgia.com"),
                        "2000-01-01T11:00:00.000Z"), 1));
        SearchResponse response = new SearchResponse();
        response.setMessage("OK");
        response.setStatus(200);
        response.setListShop(shops);
        response.setListProduct(products);
        return Observable.just(response);
    }
}
