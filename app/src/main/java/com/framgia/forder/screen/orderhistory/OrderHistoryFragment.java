package com.framgia.forder.screen.orderhistory;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.databinding.FragmentOrderHistoryBinding;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 25-04-2017.
 */

public class OrderHistoryFragment extends Fragment {

    private OrderHistoryContract.ViewModel mViewModel;

    public static OrderHistoryFragment newInstance() {
        return new OrderHistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentOrderHistoryBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_order_history, container,
                        false);
        List<Order> orderHistories = new ArrayList<>();
        OrderHistoryAdapter orderHistoryAdapter =
                new OrderHistoryAdapter(getActivity(), orderHistories);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new OrderHistoryViewModel(orderHistoryAdapter, navigator);
        OrderHistoryContract.Presenter presenter = new OrderHistoryPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        binding.setViewModel((OrderHistoryViewModel) mViewModel);
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
