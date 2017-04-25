package com.framgia.forder.screen.productdetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentProductDetailBinding;

/**
 * Detailproduct Screen.
 */
public class ProductDetailFragment extends Fragment {

    private ProductDetailContract.ViewModel mViewModel;

    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ProductDetailViewModel();

        ProductDetailContract.Presenter presenter = new ProductDetailPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentProductDetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container,
                        false);
        binding.setViewModel((ProductDetailViewModel) mViewModel);
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
