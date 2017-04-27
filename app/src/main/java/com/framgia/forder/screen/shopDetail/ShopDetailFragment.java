package com.framgia.forder.screen.shopDetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentShopDetailBinding;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * DetailShop Screen.
 */
public class ShopDetailFragment extends Fragment {
    private static final String EXTRA_SHOP = "EXTRA_SHOP";
    private ShopDetailContract.ViewModel mViewModel;

    public static ShopDetailFragment newInstance(Shop shop) {
        ShopDetailFragment shopDetailFragment = new ShopDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SHOP, shop);
        shopDetailFragment.setArguments(bundle);
        return shopDetailFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Shop shop = (Shop) getArguments().get(EXTRA_SHOP);
        List<Shop> shops = new ArrayList<>();
        ShopAdapter adapter = new ShopAdapter(getContext(), shops);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new ShopDetailViewModel(shop, adapter, navigator);
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getContext());

        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        ShopRepository shopRepository =
                new ShopRepository(new ShopRemoteDataSource(FOrderServiceClient.getInstance()));
        ShopDetailContract.Presenter presenter =
                new ShopDetailPresenter(mViewModel, domainRepository, shopRepository);

        mViewModel.setPresenter(presenter);
        FragmentShopDetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_detail, container, false);
        binding.setViewModel((ShopDetailViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
