package com.framgia.forder.screen.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.screen.cart.ShoppingCartFragment;
import com.framgia.forder.screen.mainpage.MainPageContainerFragment;
import com.framgia.forder.screen.notification.NotificationPageFragment;
import com.framgia.forder.screen.profilepage.ProfilePageFragment;
import com.framgia.forder.screen.searchpage.SearchContainerFragment;
import com.framgia.forder.utils.navigator.Navigator;

import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_CART;
import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_HOME;
import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_NOTIFICATION;
import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_PROFILE;
import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_SEARCH;

/**
 * Created by tri on 13/04/2017.
 */

public class MainContainerFragment extends Fragment {

    private static final String EXTRA_TAB_FOOTER = "EXTRA_TAB_FOOTER";

    private Navigator mNavigator;

    public static MainContainerFragment newInstance(@MainViewModel.Tab int tab) {
        MainContainerFragment fragment = new MainContainerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_TAB_FOOTER, tab);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        mNavigator = new Navigator(this);
        @MainViewModel.Tab int tab = getArguments().getInt(EXTRA_TAB_FOOTER);
        switch (tab) {
            case TAB_HOME:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        MainPageContainerFragment.newInstance(), true, Navigator.NONE,
                        "MainPageContainerFragment");
                break;
            case TAB_SEARCH:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        SearchContainerFragment.newInstance(), true, Navigator.NONE,
                        "SearchContainerFragment");
                break;
            case TAB_CART:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        ShoppingCartFragment.newInstance(), true, Navigator.BOTTOM_UP,
                        "ShoppingCartFragment");
                break;
            case TAB_NOTIFICATION:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        NotificationPageFragment.newInstance(), true, Navigator.NONE,
                        "NotificationPageFragment");
                break;
            case TAB_PROFILE:
                mNavigator.goNextChildFragment(R.id.layout_content,
                        ProfilePageFragment.newInstance(), true, Navigator.NONE,
                        "ProfilePageFragment");
                break;
            default:
                break;
        }
        return view;
    }

    public boolean onBackPressed() {
        return mNavigator.goBackChildFragment();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!isAdded()) {
            return;
        }
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.layout_content);
        if (fragment == null) {
            return;
        }
        fragment.setUserVisibleHint(isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }
}
