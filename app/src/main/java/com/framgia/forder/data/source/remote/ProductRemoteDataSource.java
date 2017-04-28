package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.ProductDataSource;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.response.OrderResponse;
import com.framgia.forder.data.source.remote.api.response.ProductResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Duong on 4/14/2017.
 */

public class ProductRemoteDataSource extends BaseRemoteDataSource
        implements ProductDataSource.RemoteDataSource {

    public ProductRemoteDataSource(FOrderApi api) {
        super(api);
    }

    @Override
    public Observable<List<Product>> getListProduct(int domainId) {
        return mFOrderApi.getListProduct(domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            return Observable.just(productResponse.getListProduct());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Product>> getListProductInShop(int shopId, int domainId) {
        return mFOrderApi.getListProductShop(shopId, domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            return Observable.just(productResponse.getListProduct());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<Void> orderProduct(OrderRequest orderRequest) {
        return mFOrderApi.orderRequest(orderRequest)
                .flatMap(new Func1<OrderResponse, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(OrderResponse orderResponse) {
                        if (orderResponse != null) {
                            return Observable.just(null);
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Comment>> getListCommentInProduct(int productId, int domainId) {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1, 1, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        comments.add(new Comment(1, 2, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        comments.add(new Comment(1, 3, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        comments.add(new Comment(1, 4, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        comments.add(new Comment(1, 5, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        comments.add(new Comment(1, 6, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        comments.add(new Comment(1, 7, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        comments.add(new Comment(1, 8, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        comments.add(new Comment(1, 9, "Trần Đức Quốc", "Rất ngon", "26/04/2017"));
        return Observable.just(comments);
    }

    @Override
    public Observable<Comment> sendComment(CommentRequest request) {
        Comment comment =
                new Comment(request.getProductId(), request.getUserId(), request.getComment());
        return Observable.just(comment);
    }
}
