package com.framgia.forder.screen.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 13/04/2017.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int TAB_NUMBER = 5;

    private Fragment mCurrentFragment;
    private List<Fragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case MainViewModel.Tab.TAB_HOME:
                fragment = MainContainerFragment.newInstance(position);
                mFragments.add(MainViewModel.Tab.TAB_HOME, fragment);
                return fragment;
            case MainViewModel.Tab.TAB_SEARCH:
                fragment = MainContainerFragment.newInstance(position);
                mFragments.add(MainViewModel.Tab.TAB_SEARCH, fragment);
                return fragment;
            case MainViewModel.Tab.TAB_CART:
                fragment = MainContainerFragment.newInstance(position);
                mFragments.add(MainViewModel.Tab.TAB_CART, fragment);
                return fragment;
            case MainViewModel.Tab.TAB_NOTIFICATION:
                fragment = MainContainerFragment.newInstance(position);
                mFragments.add(MainViewModel.Tab.TAB_NOTIFICATION, fragment);
                return fragment;
            case MainViewModel.Tab.TAB_PROFILE:
                fragment = MainContainerFragment.newInstance(position);
                mFragments.add(MainViewModel.Tab.TAB_PROFILE, fragment);
                return fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = ((Fragment) object);
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    public Fragment getFragment(@MainViewModel.Tab int position) {
        return mFragments.get(position);
    }
}
