package com.framgia.forder.screen.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.mainpage.MainPageContainerFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainViewModel extends BaseObservable implements MainContract.ViewModel {

    private static final int POP_BACK_STACK_CLEAR_TASK = 0;
    private MainContract.Presenter mPresenter;
    private MainViewPagerAdapter mViewPagerAdapter;
    private String mCurrentDomain;
    private int mPageLimit = 5;
    private AlertDialog.Builder mDialogChangeDomain;

    @Tab
    private int mCurrentTab;

    public MainViewModel(MainViewPagerAdapter mainViewPagerAdapter,
            AlertDialog.Builder alertDialog) {
        mViewPagerAdapter = mainViewPagerAdapter;
        mDialogChangeDomain = alertDialog;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getCurrentDomain();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public int getCurrentTab() {
        return mCurrentTab;
    }

    public void setCurrentTab(@Tab int tab) {
        mCurrentTab = tab;
        notifyPropertyChanged(BR.currentTab);
    }

    public void onFooterClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_home:
                setCurrentTab(Tab.TAB_HOME);
                break;
            case R.id.layout_seach:
                setCurrentTab(Tab.TAB_SEARCH);
                break;
            case R.id.img_top:
                setCurrentTab(Tab.TAB_CART);
                break;
            case R.id.layout_notify:
                setCurrentTab(Tab.TAB_NOTIFICATION);
                break;
            case R.id.layout_profile:
                setCurrentTab(Tab.TAB_PROFILE);
                break;
            default:
                break;
        }
        setTabSelected(view);
    }

    private void setTabSelected(View view) {
        ViewGroup viewGroup = null;
        if (view instanceof ImageView && !view.isSelected()) {
            view.setSelected(!view.isSelected());
            viewGroup = (ViewGroup) ((ViewGroup) view.getParent()).getChildAt(0);
        } else if (!(view instanceof ImageView)) {
            viewGroup = (ViewGroup) view.getParent();
            ((ViewGroup) viewGroup.getParent()).getChildAt(1).setSelected(false);
        }
        if (viewGroup == null) {
            return;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View tab = viewGroup.getChildAt(i);
            tab.setSelected(tab == view);
        }
    }

    public MainViewPagerAdapter getViewPagerAdapter() {
        return mViewPagerAdapter;
    }

    @Override
    public boolean onBackPressed() {
        Fragment fragment = mViewPagerAdapter.getCurrentFragment();
        if (fragment instanceof MainContainerFragment) {
            MainContainerFragment containerFragment = (MainContainerFragment) fragment;
            return containerFragment.onBackPressed();
        }
        return false;
    }

    @Override
    public void showCurrentDomain(String domainName) {
        mCurrentDomain = domainName;
        notifyPropertyChanged(BR.currentDomain);
    }

    @Override
    public void onGetListDomainSuccess(final List<Domain> domains) {
        List<String> listDomainName = new ArrayList<>();
        for (Domain domain : domains) {
            listDomainName.add(domain.getName());
        }
        mDialogChangeDomain.setTitle(R.string.change_domain);
        mDialogChangeDomain.setSingleChoiceItems(
                listDomainName.toArray(new String[listDomainName.size()]), -1,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        mPresenter.saveCurrentDomain(domains.get(item));
                        mPresenter.getCurrentDomain();
                        reloadData();
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = mDialogChangeDomain.create();
        alertDialog.show();
        alertDialog.getListView()
                .setItemChecked(mPresenter.getCurrentDomainPosition(domains), true);
    }

    @Override
    public void onGetListDomainError(BaseException e) {

    }

    @Bindable
    public String getCurrentDomain() {
        return mCurrentDomain;
    }

    public void onChangeDomainClick() {
        mPresenter.getListDomain();
    }

    public int getPageLimit() {
        return mPageLimit;
    }

    public void reloadData() {
        Navigator navigator = new Navigator(mViewPagerAdapter.getFragment(Tab.TAB_HOME));
        navigator.goBackFragmentByTag("MainPageContainerFragment", POP_BACK_STACK_CLEAR_TASK);
        MainPageContainerFragment fragment =
                (MainPageContainerFragment) mViewPagerAdapter.getFragment(Tab.TAB_HOME)
                        .getChildFragmentManager()
                        .getFragments()
                        .get(Tab.TAB_HOME);
        fragment.reloadData();
    }

    @IntDef({ Tab.TAB_HOME, Tab.TAB_SEARCH, Tab.TAB_CART, Tab.TAB_NOTIFICATION, Tab.TAB_PROFILE })
    public @interface Tab {
        int TAB_HOME = 0;
        int TAB_SEARCH = 1;
        int TAB_CART = 2;
        int TAB_NOTIFICATION = 3;
        int TAB_PROFILE = 4;
    }
}
