package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.ShopDataSource;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.CheckFollowShopResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteShopInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.ManagerResponse;
import com.framgia.forder.data.source.remote.api.response.ProductResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import com.framgia.forder.data.source.remote.api.response.ShopInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.ShopManagementResponse;
import com.framgia.forder.data.source.remote.api.response.ShopRequestResponse;
import com.framgia.forder.data.source.remote.api.response.ShopResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Duong on 4/19/2017.
 */

public class ShopRemoteDataSource extends BaseRemoteDataSource
        implements ShopDataSource.RemoteDataSource {

    public ShopRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<List<Shop>> getListShop(int domainId) {
        return mFOrderApi.getListShop(domainId)
                .flatMap(new Func1<ShopResponse, Observable<List<Shop>>>() {
                    @Override
                    public Observable<List<Shop>> call(ShopResponse shopResponse) {
                        if (shopResponse != null) {
                            return Observable.just(shopResponse.getListShop());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Shop>> getRelativeShops(int domainId) {
        // todo edit later
        return mFOrderApi.getRelativeShops(domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Shop>>>() {
                    @Override
                    public Observable<List<Shop>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            List<Shop> shops = new ArrayList<>();
                            for (Product product : productResponse.getListProduct()) {
                                //TODO remove when server update API
                                product.getShop()
                                        .setUser(new User(1, "Trần Đức Quốc",
                                                "tran.duc.quoc@framgia.com"));
                                shops.add(product.getShop());
                            }
                            return Observable.just(shops);
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<ShopManagement>> getListShopManagement(int userId) {
        return mFOrderApi.getLitShopManagement(userId)
                .flatMap(new Func1<ShopManagementResponse, Observable<List<ShopManagement>>>() {
                    @Override
                    public Observable<List<ShopManagement>> call(
                            ShopManagementResponse shopResponse) {
                        if (shopResponse != null) {
                            return Observable.just(shopResponse.getListShopManagement());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> requestApplyShopToDomain(
            ApplyShopToDomainRequest applyShopToDomainRequest) {
        return mFOrderApi.requestApplyShopToDomain(applyShopToDomainRequest);
    }

    @Override
    public Observable<BaseResponse> requestLeaveShopFromDomain(int domainId, int shopId,
            boolean leaveDomain) {
        return mFOrderApi.requestLeaveShopFromDomain(domainId, shopId, leaveDomain);
    }

    @Override
    public Observable<RegisterShopResponse> requestRegisterShop(
            RegisterShopRequest registerShopRequest) {
        return mFOrderApi.requestRegisterShop(registerShopRequest);
    }

    @Override
    public Observable<List<ManagerResponse.ManagerDetail>> getListManagerOfShop(int shopId) {
        return mFOrderApi.getListManagerOfShop(shopId)
                .flatMap(
                        new Func1<ManagerResponse, Observable<List<ManagerResponse
                                .ManagerDetail>>>() {
                            @Override
                            public Observable<List<ManagerResponse.ManagerDetail>> call(
                                    ManagerResponse managerResponse) {
                                if (managerResponse != null) {
                                    return Observable.just(managerResponse.getManagerDetails());
                                }
                                return Observable.error(new NullPointerException());
                            }
                        });
    }

    @Override
    public Observable<RegisterShopResponse> updateShop(int shopId,
            UpdateShopRequest updateShopRequest) {
        return mFOrderApi.updateShop(shopId, updateShopRequest);
    }

    @Override
    public Observable<List<ShopInDomain>> getListShopInDomain(int domainId) {
        return mFOrderApi.getListShopInDomain(domainId)
                .flatMap(new Func1<ShopInDomainResponse, Observable<List<ShopInDomain>>>() {
                    @Override
                    public Observable<List<ShopInDomain>> call(ShopInDomainResponse shopResponse) {
                        if (shopResponse != null) {
                            return Observable.just(shopResponse.getListShop());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<DeleteShopInDomainResponse> requestDeleteShopInDomain(int domainId,
            int shopId) {
        return mFOrderApi.requestDeleteShopInDomain(domainId, shopId);
    }

    @Override
    public Observable<BaseResponse> requestChangeStatusShopManagement(final int shopId,
            final String status) {
        return mFOrderApi.requestChangeStatusShopManagement(shopId, status);
    }

    @Override
    public Observable<List<ShopRequestResponse.ShopContain>> getListShopRequest(int domainId) {
        return mFOrderApi.getListShopRequest(domainId)
                .flatMap(
                        new Func1<ShopRequestResponse, Observable<List<ShopRequestResponse
                                .ShopContain>>>() {
                            @Override
                            public Observable<List<ShopRequestResponse.ShopContain>> call(
                                    ShopRequestResponse shopResponse) {
                                if (shopResponse != null) {
                                    return Observable.just(shopResponse.getShopContains());
                                }
                                return Observable.error(new NullPointerException());
                            }
                        });
    }

    @Override
    public Observable<BaseResponse> requestFollowShop(int shopId, String type) {
        return mFOrderApi.requestFollowShop(shopId, type);
    }

    @Override
    public Observable<BaseResponse> requestRateShop(int shopId, float ratePoint) {
        return mFOrderApi.requestRateShop(shopId, ratePoint);
    }

    @Override
    public Observable<CheckFollowShopResponse> checkFollowShop(int shopId) {
        return mFOrderApi.checkFollowShop(shopId);
    }

    @Override
    public Observable<BaseResponse> requestToAcceptRejectShopToDomain(int domainId, int shopId,
            String status) {
        return mFOrderApi.requestToAcceptRejectShopToDomain(domainId, shopId, status);
    }
}
