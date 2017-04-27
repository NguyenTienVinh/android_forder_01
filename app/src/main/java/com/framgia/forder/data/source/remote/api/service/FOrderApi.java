package com.framgia.forder.data.source.remote.api.service;

import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.data.source.remote.api.response.DomainResponse;
import com.framgia.forder.data.source.remote.api.response.NotificationResponse;
import com.framgia.forder.data.source.remote.api.response.OrderManagementResponse;
import com.framgia.forder.data.source.remote.api.response.OrderResponse;
import com.framgia.forder.data.source.remote.api.response.ProductResponse;
import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import com.framgia.forder.data.source.remote.api.response.UpdateProfileResponse;
import com.framgia.forder.data.source.remote.api.response.UserResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface FOrderApi {

    // TODO: 11/04/2017 remove later
    @GET("/search/users")
    Observable<Void> searchGithubUsers(@Query("per_page") int limit, @Query("q") String searchTerm);

    @GET("v1/authen_user_tokens")
    Observable<UserResponse> login(@Query("user_email") String userEmail,
            @Query("password") String passWord);

    @GET("v1/domains")
    Observable<DomainResponse> getDomains(@Query("user_id") int id,
            @Query("user_email") String userEmail, @Query("user_token") String userToken);

    @GET("v1/products")
    Observable<ProductResponse> getListProduct(@Query("domain_id") int id);

    // TODO edit later
    @GET("v1/products")
    Observable<ProductResponse> getListShop(@Query("domain_id") int id);

    // TODO edit later
    @GET("v1/products")
    Observable<ProductResponse> getRelativeShops(@Query("domain_id") int id);

    // TODO edit later: There is no api profile link yet
    @POST("v1/profile")
    Observable<UpdateProfileResponse> updateUserInformation(
            @Body UpdateProfileRequest updateProfileRequest);

    @GET("v1/search")
    Observable<SearchResponse> search(@Query("domain_id") int id, @Query("keyWord") String keyWord);

    @GET("v1/notifications")
    Observable<NotificationResponse> getListNotification();

    @GET("v1/products")
    Observable<ProductResponse> getListProductShop(@Query("shop_id") int shopId,
            @Query("domain_id") int id);

    @POST("v1/orders/")
    Observable<OrderResponse> orderRequest(@Body OrderRequest orderRequest);

    @GET("order_managers")
    Observable<OrderManagementResponse> getOrderManagement();

    @GET("v1/orders")
    Observable<OrderResponse> getListOrder(@Query("user_id") int userId,
            @Query("domain_id") int domainId);
}
