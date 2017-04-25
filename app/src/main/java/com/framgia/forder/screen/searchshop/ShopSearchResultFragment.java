package com.framgia.forder.screen.searchshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.databinding.FragmentShopSearchResultBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Searchshop Screen.
 */
public class ShopSearchResultFragment extends Fragment {

    private ShopSearchResultContract.ViewModel mViewModel;

    public static ShopSearchResultFragment newInstance() {
        return new ShopSearchResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        List<Shop> shops = new ArrayList<>();
        ShopSearchResultAdapter adapter = new ShopSearchResultAdapter(getContext(), shops);
        mViewModel = new ShopSearchResultViewModel(adapter);

        ShopSearchResultContract.Presenter presenter = new ShopSearchResultPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentShopSearchResultBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_search_result, container,
                        false);
        binding.setViewModel((ShopSearchResultViewModel) mViewModel);
        return binding.getRoot();
    }

    public void setShops(List<Shop> shops) {
        mViewModel.onSearchSuccess(shops);
    }
}
