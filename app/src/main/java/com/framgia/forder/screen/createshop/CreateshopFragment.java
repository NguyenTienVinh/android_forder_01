package com.framgia.forder.screen.createshop;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.ShopRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentCreateShopBinding;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Createshop Screen.
 */
public class CreateshopFragment extends Fragment {

    private CreateshopContract.ViewModel mViewModel;

    public static CreateshopFragment newInstance() {
        return new CreateshopFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogManager dialogManager = new DialogManager(getActivity());
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new CreateshopViewModel(getActivity(), dialogManager, navigator);
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        UserRepository userRepository = new UserRepository(null, new UserLocalDataSource(prefsApi));
        ShopRepository shopRepository =
                new ShopRepository(new ShopRemoteDataSource(FOrderServiceClient.getInstance()));
        CreateshopContract.Presenter presenter =
                new CreateshopPresenter(mViewModel, userRepository, shopRepository);
        mViewModel.setPresenter(presenter);

        FragmentCreateShopBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_create_shop, container, false);
        binding.setViewModel((CreateshopViewModel) mViewModel);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Uri u = data.getData();
            //Todo dev later
        }
    }
}