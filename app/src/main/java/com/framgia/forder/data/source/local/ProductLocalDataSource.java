package com.framgia.forder.data.source.local;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShoppingCart;
import com.framgia.forder.data.source.ProductDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;

import static com.framgia.forder.utils.Constant.DEFAULT_QUANTITY;

public class ProductLocalDataSource implements ProductDataSource.LocalDataSource {
    private RealmApi mRealmApi;

    public ProductLocalDataSource(RealmApi realmApi) {
        mRealmApi = realmApi;
    }

    @Override
    public void openTransaction() {
        if (mRealmApi == null) {
            mRealmApi = new RealmApi();
        }
    }

    @Override
    public void closeTransaction() {
        mRealmApi.closeRealmOnMainThread();
    }

    @Override
    public Observable<Void> addShoppingCard(@NonNull final Product product,
            @NonNull final int domainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                ShoppingCart shoppingCart = realm.where(ShoppingCart.class)
                        .equalTo("mDomainId", domainId)
                        .equalTo("mProductId", product.getId())
                        .findFirst();
                if (shoppingCart == null) {
                    ShoppingCart cart = new ShoppingCart();
                    if (realm.where(ShoppingCart.class).findAll().size() == 0) {
                        cart.setShoppingCartId(1);
                    } else {
                        cart.setShoppingCartId(
                                realm.where(ShoppingCart.class).max("mShoppingCartId").intValue()
                                        + 1);
                    }
                    cart.setDomainId(domainId);
                    cart.setShopId(product.getShopId());
                    cart.setQuantity(DEFAULT_QUANTITY);
                    cart.setProductId(product.getId());
                    cart.setPrice(product.getPrice());
                    cart.setShopName(product.getShop().getName());
                    cart.setProductName(product.getName());
                    cart.setProductImage(product.getCollectionImage().getImage().getUrl());
                    cart.setStartHour(product.getStartHour());
                    cart.setEndHour(product.getEndHour());
                    cart.setTotal(cart.getTotal());
                    try {
                        realm.insertOrUpdate(cart);
                    } catch (IllegalStateException e) {
                        subscriber.onError(e);
                    }
                } else {
                    try {
                        shoppingCart.setQuantity(shoppingCart.getQuantity() + 1);
                        realm.insertOrUpdate(shoppingCart);
                    } catch (IllegalStateException e) {
                        subscriber.onError(e);
                    }
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Void> deleteShoppingCard(@NonNull final int productId,
            @NonNull final int domainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                ShoppingCart cart = realm.where(ShoppingCart.class)
                        .equalTo("mDomainId", domainId)
                        .equalTo("mProductId", productId)
                        .findFirst();
                try {
                    cart.deleteFromRealm();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Void> upQuantity(@NonNull final int productId, @NonNull final int domainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                ShoppingCart cart = realm.where(ShoppingCart.class)
                        .equalTo("mProductId", productId)
                        .equalTo("mDomainId", domainId)
                        .findFirst();
                try {
                    cart.setQuantity(cart.getQuantity() + 1);
                    realm.insertOrUpdate(cart);
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Void> downQuantity(@NonNull final int productId,
            @NonNull final int domainId) {
        return mRealmApi.realmTransactionAsync(new Action2<Subscriber<? super Void>, Realm>() {
            @Override
            public void call(Subscriber<? super Void> subscriber, Realm realm) {
                ShoppingCart cart = realm.where(ShoppingCart.class)
                        .equalTo("mProductId", productId)
                        .equalTo("mDomainId", domainId)
                        .findFirst();
                if (cart.getQuantity() == DEFAULT_QUANTITY) {
                    try {
                        cart.deleteFromRealm();
                    } catch (IllegalStateException e) {
                        subscriber.onError(e);
                    }
                } else {
                    try {
                        cart.setQuantity(cart.getQuantity() - 1);
                        realm.insertOrUpdate(cart);
                    } catch (IllegalStateException e) {
                        subscriber.onError(e);
                    }
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Integer> getNumberItem(@NonNull final int domainId) {
        return mRealmApi.realmGet(new Action2<Subscriber<? super Integer>, Realm>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber, Realm realm) {
                try {
                    RealmResults<ShoppingCart> carts = realm.where(ShoppingCart.class)
                            .equalTo("mDomainId", domainId)
                            .findAll();
                    subscriber.onNext(carts.size());
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Double> getTotalPrice(@NonNull final int domainId) {
        return mRealmApi.realmGet(new Action2<Subscriber<? super Double>, Realm>() {
            @Override
            public void call(Subscriber<? super Double> subscriber, Realm realm) {
                try {
                    double totalPrice = 0;
                    RealmResults<ShoppingCart> carts = realm.where(ShoppingCart.class)
                            .equalTo("mDomainId", domainId)
                            .findAll();
                    for (ShoppingCart shoppingCart : carts) {
                        totalPrice += shoppingCart.getTotal();
                    }
                    subscriber.onNext(totalPrice);
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Cart>> getAllShoppingCart(final int domainId) {
        return mRealmApi.realmGet(new Action2<Subscriber<? super List<Cart>>, Realm>() {
            @Override
            public void call(Subscriber<? super List<Cart>> subscriber, Realm realm) {
                List<Cart> cartList = new ArrayList<>();

                RealmResults<ShoppingCart> shops = realm.where(ShoppingCart.class)
                        .equalTo("mDomainId", domainId)
                        .distinct("mShopId");

                for (ShoppingCart shoppingCart : shops) {
                    Cart cart = new Cart(shoppingCart.getDomainId(), shoppingCart.getShopId(),
                            shoppingCart.getShopName(), shoppingCart.getTotal());
                    cartList.add(cart);
                }
                for (Cart cart : cartList) {
                    double chargeForOrderInShop = 0;
                    RealmResults<ShoppingCart> orderInShops = realm.where(ShoppingCart.class)
                            .equalTo("mShopId", cart.getShopId())
                            .equalTo("mDomainId", domainId)
                            .findAll();
                    List<CartItem> cartItemList = new ArrayList<>();
                    for (ShoppingCart shoppingCart : orderInShops) {
                        chargeForOrderInShop += shoppingCart.getTotal();
                        CartItem cartItem =
                                new CartItem(shoppingCart.getDomainId(), shoppingCart.getShopId(),
                                        shoppingCart.getQuantity(), shoppingCart.getProductId(),
                                        shoppingCart.getProductName(),
                                        shoppingCart.getProductImage(), shoppingCart.getPrice(),
                                        shoppingCart.getStartHour(), shoppingCart.getEndHour(),
                                        shoppingCart.getTotal());
                        cartItemList.add(cartItem);
                    }
                    cart.setCartItemList(cartItemList);
                    cart.setTotal(chargeForOrderInShop);
                }
                try {
                    subscriber.onNext(cartList);
                    subscriber.onCompleted();
                } catch (IllegalStateException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }
}
