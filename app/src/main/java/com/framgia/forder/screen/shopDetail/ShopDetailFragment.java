package com.framgia.forder.screen.shopDetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentShopDetailBinding;

/**
 * DetailShop Screen.
 */
public class ShopDetailFragment extends Fragment {

    private ShopDetailContract.ViewModel mViewModel;

    public static ShopDetailFragment newInstance() {
        return new ShopDetailFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ShopDetailViewModel();
        ShopDetailContract.Presenter presenter = new ShopDetailPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentShopDetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shop_detail, container, false);
        binding.setViewModel((ShopDetailViewModel) mViewModel);
        return binding.getRoot();
    }
}
