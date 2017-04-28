package com.framgia.forder.screen.searchpage;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.forder.R;
import com.framgia.forder.screen.searchproduct.ProductSearchResultFragment;
import com.framgia.forder.screen.searchshop.ShopSearchResultFragment;
import java.util.ArrayList;
import java.util.List;

public class SearchContainerAdapter extends FragmentPagerAdapter {
    private static final int TAB_NUMBER = 2;

    private Context mContext;
    private List<Fragment> mFragments;

    public SearchContainerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case SearchResultsTab.TAB_PRODUCT:
                fragment = ProductSearchResultFragment.newInstance();
                mFragments.add(SearchResultsTab.TAB_PRODUCT, fragment);
                return fragment;
            case SearchResultsTab.TAB_SHOP:
                fragment = ShopSearchResultFragment.newInstance();
                mFragments.add(SearchResultsTab.TAB_SHOP, fragment);
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
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case SearchResultsTab.TAB_PRODUCT:
                return mContext.getString(R.string.product);
            case SearchResultsTab.TAB_SHOP:
                return mContext.getString(R.string.shop);
            default:
                return null;
        }
    }

    public Fragment getFragment(@SearchResultsTab int position) {
        return mFragments.get(position);
    }

    @IntDef({ SearchResultsTab.TAB_PRODUCT, SearchResultsTab.TAB_SHOP })
    public @interface SearchResultsTab {
        int TAB_PRODUCT = 0;
        int TAB_SHOP = 1;
    }
}
