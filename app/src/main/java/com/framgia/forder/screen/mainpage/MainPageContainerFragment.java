package com.framgia.forder.screen.mainpage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentMainPageContainerBinding;

/**
 * Created by Duong on 4/13/2017.
 */

public class MainPageContainerFragment extends Fragment {
    private MainPageContainerContract.ViewModel mViewModel;

    public static MainPageContainerFragment newInstance() {
        return new MainPageContainerFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        MainPageContainerAdapter adapter =
                new MainPageContainerAdapter(getChildFragmentManager(), getActivity());
        mViewModel = new MainPageContainerViewModel(adapter);
        MainPageContainerContract.Presenter presenter = new MainPageContainerPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        FragmentMainPageContainerBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_main_page_container, container,
                        false);
        binding.setViewModel((MainPageContainerViewModel) mViewModel);
        return binding.getRoot();
    }

    public void reloadData() {
        mViewModel.reloadData();
    }
}
