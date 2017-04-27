package com.framgia.forder.screen.chooseDomain;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.ArrayAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.main.MainActivity;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the ChooseDomain screen.
 */

public class ChooseDomainViewModel extends BaseObservable
        implements ChooseDomainContract.ViewModel {

    private Context mContext;
    private ChooseDomainContract.Presenter mPresenter;
    private ArrayAdapter<String> mAdapter;
    private List<Domain> mDomains;
    private int mSelectedTypePosition;
    private Navigator mNavigator;

    public ChooseDomainViewModel(Context context, ArrayAdapter<String> adapter,
            Navigator navigator) {
        mContext = context;
        mAdapter = adapter;
        mNavigator = navigator;
        mDomains = new ArrayList<>();
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ChooseDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetDomainSuccess(List<Domain> domains) {
        if (domains == null) {
            return;
        }
        mDomains.clear();
        mDomains.addAll(domains);
        mAdapter.add(mContext.getString(R.string.none));
        for (Domain domain : domains) {
            mAdapter.add(domain.getName());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDomainError(BaseException e) {
        // TODO show dialog error later
    }

    public ArrayAdapter<String> getAdapter() {
        return mAdapter;
    }

    public void onClickNext(View view) {
        if (mSelectedTypePosition == 0) {
            mNavigator.showToast(R.string.you_need_to_choose_a_domain_to_continue);
        } else {
            Domain domain = mDomains.get(mSelectedTypePosition - 1);
            mPresenter.saveCurrentDomain(domain);
            mNavigator.startActivity(MainActivity.class);
            mNavigator.finishActivity();
        }
    }

    @Bindable
    public Integer getSelectedTypePosition() {
        return mSelectedTypePosition;
    }

    public void setSelectedTypePosition(Integer selectedTypePosition) {
        this.mSelectedTypePosition = selectedTypePosition;
    }
}
