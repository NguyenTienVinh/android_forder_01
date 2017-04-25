package com.framgia.forder.screen.cart;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ShoppingCartFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ShoppingCartPresenter implements ShoppingCartContract.Presenter {
    private static final String TAG = ShoppingCartPresenter.class.getName();
    private final CompositeSubscription mCompositeSubscription;
    private final ShoppingCartContract.ViewModel mViewModel;
    private ProductRepository mProductRepository;

    ShoppingCartPresenter(ShoppingCartContract.ViewModel viewModel,
            ProductRepository productRepository) {
        mViewModel = viewModel;
        mProductRepository = productRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        mProductRepository.openTransaction();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
        mProductRepository.closeTransaction();
    }

    @Override
    public void getListCart() {
        Subscription subscriptions =
                mProductRepository.getAllShoppingCart().subscribe(new Action1<List<Cart>>() {
                    @Override
                    public void call(List<Cart> carts) {
                        mViewModel.onGetListCartSuccess(carts);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListCartError(error);
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void orderItem(Cart cart) {
        if (cart == null) {
            return;
        }
        //TODO order item in cart
    }

    @Override
    public void upQuantity(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        Subscription subscriptions = mProductRepository.upQuantity(cartItem.getProductId())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onUpQuantitySuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onUpQuantityError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void downQuantity(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        Subscription subscriptions = mProductRepository.downQuantity(cartItem.getProductId())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onDownQuantitySuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onDownQuantityError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void deleteProduct(CartItem cartItem) {
        if (cartItem == null) {
            return;
        }
        Subscription subscriptions = mProductRepository.deleteCart(cartItem.getProductId())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.onDeleteProductSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onDeleteProductError(e);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        // No-Op
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }

    @Override
    public void getTotalPrice() {
        Subscription subscriptions =
                mProductRepository.getTotalPrice().subscribe(new Action1<Double>() {
                    @Override
                    public void call(Double totalPrice) {
                        mViewModel.onGetTotalPriceSuccess(totalPrice);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetTotalPriceError(error);
                    }
                });
        mCompositeSubscription.add(subscriptions);
    }
}
