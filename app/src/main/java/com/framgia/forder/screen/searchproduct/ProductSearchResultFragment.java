package com.framgia.forder.screen.searchproduct;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.FragmentProductSearchResultBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Searchproduct Screen.
 */
public class ProductSearchResultFragment extends Fragment {

    private ProductSearchResultContract.ViewModel mViewModel;

    public static ProductSearchResultFragment newInstance() {
        return new ProductSearchResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        List<Product> products = new ArrayList<>();
        ProductSearchResultAdapter adapter = new ProductSearchResultAdapter(getContext(), products);
        mViewModel = new ProductSearchResultViewModel(adapter);
        ProductSearchResultContract.Presenter presenter =
                new ProductSearchResultPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentProductSearchResultBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_product_search_result,
                        container, false);
        binding.setViewModel((ProductSearchResultViewModel) mViewModel);
        return binding.getRoot();
    }

    public void setProducts(List<Product> products) {
        mViewModel.onSearchSuccess(products);
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
