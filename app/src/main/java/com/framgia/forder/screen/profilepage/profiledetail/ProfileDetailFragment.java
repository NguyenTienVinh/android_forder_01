package com.framgia.forder.screen.profilepage.profiledetail;

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
import com.framgia.forder.databinding.FragmentProfileDetailBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Created by ASUS on 4/17/2017.
 */

public class ProfileDetailFragment extends Fragment {

    private ProfileDetailContract.ViewModel mViewModel;

    public static ProfileDetailFragment newInstance() {
        return new ProfileDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new ProfileDetailViewModel(navigator);
        SharedPrefsApi sharedPrefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        UserRepository userRepository =
                new UserRepository(null, new UserLocalDataSource(sharedPrefsApi));
        ProfileDetailContract.Presenter presenter =
                new ProfileDetailPresenter(mViewModel, userRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentProfileDetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_profile_detail, container,
                        false);
        binding.setViewModel((ProfileDetailViewModel) mViewModel);
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
