package com.framgia.forder.screen.profilepage;

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
import com.framgia.forder.databinding.FragmentProfileBinding;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * ProfileFragment Screen.
 */
public class ProfilePageFragment extends Fragment {

    private ProfilePageContract.ViewModel mViewModel;

    public static ProfilePageFragment newInstance() {
        return new ProfilePageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new ProfilePageViewModel(navigator);
        SharedPrefsApi sharedPrefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        UserRepository userRepository =
                new UserRepository(null, new UserLocalDataSource(sharedPrefsApi));
        ProfilePageContract.Presenter presenter =
                new ProfilePagePresenter(mViewModel, userRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentProfileBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setViewModel((ProfilePageViewModel) mViewModel);
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
