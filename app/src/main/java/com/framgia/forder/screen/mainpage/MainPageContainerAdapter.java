package com.framgia.forder.screen.mainpage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.forder.R;

public class MainPageContainerAdapter extends FragmentPagerAdapter {
    private static final int TAB_NUMBER = 3;
    private static final int TAB_FEATURED_POSITION = 0;
    private static final int TAB_NEW_POSITION = 1;
    private static final int TAB_SALE_POSITION = 2;

    private Context mContext;

    public MainPageContainerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_FEATURED_POSITION:
                return MainPageFragment.newInstance();
            case TAB_NEW_POSITION:
                return MainPageFragment.newInstance();
            case TAB_SALE_POSITION:
                return MainPageFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_FEATURED_POSITION:
                return mContext.getString(R.string.featured);
            case TAB_NEW_POSITION:
                return mContext.getString(R.string.new_);
            case TAB_SALE_POSITION:
                return mContext.getString(R.string.sale_off);
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

