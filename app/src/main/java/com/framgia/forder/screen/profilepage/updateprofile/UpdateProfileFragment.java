package com.framgia.forder.screen.profilepage.updateprofile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.UserRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentProfileUpdateBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * ProfileUpdate Screen.
 */
public class UpdateProfileFragment extends Fragment {

    private UpdateProfileContract.ViewModel mViewModel;

    public static UpdateProfileFragment newInstance() {
        return new UpdateProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new UpdateProfileViewModel(navigator);
        SharedPrefsApi sharedPrefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        UserRepository userRepository =
                new UserRepository(new UserRemoteDataSource(FOrderServiceClient.getInstance()),
                        new UserLocalDataSource(sharedPrefsApi));
        UpdateProfileContract.Presenter presenter =
                new UpdateProfilePresenter(mViewModel, userRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentProfileUpdateBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_profile_update, container,
                        false);
        binding.setViewModel((UpdateProfileViewModel) mViewModel);
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
