package com.framgia.forder.screen.listProduct;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentListproductBinding;
import com.framgia.forder.screen.listProduct.adapter.ListProductAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductList Screen.
 */
public class ListProductFragment extends Fragment {

    private ListProductContract.ViewModel mViewModel;

    public static ListProductFragment newInstance() {
        return new ListProductFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Product> products = new ArrayList<>();
        ListProductAdapter productAdapter = new ListProductAdapter(getActivity(), products);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new ListProductViewModel(getActivity(), productAdapter, navigator);
        RealmApi realmApi = new RealmApi();

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity());
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()),
                new ProductLocalDataSource(realmApi), domainRepository);
        ListProductContract.Presenter presenter =
                new ListProductPresenter(mViewModel, productRepository);
        mViewModel.setPresenter(presenter);

        FragmentListproductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_listproduct, container, false);
        binding.setViewModel((ListProductViewModel) mViewModel);
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
