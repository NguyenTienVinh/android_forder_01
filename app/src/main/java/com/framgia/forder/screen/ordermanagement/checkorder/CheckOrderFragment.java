package com.framgia.forder.screen.ordermanagement.checkorder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentCheckOrderBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * CheckOrder Screen.
 */
public class CheckOrderFragment extends Fragment {

    private CheckOrderContract.ViewModel mViewModel;

    public static CheckOrderFragment newInstance() {
        return new CheckOrderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Order> orders = new ArrayList<>();
        CheckOrderAdapter adapter = new CheckOrderAdapter(getActivity(), orders);
        mViewModel = new CheckOrderViewModel(adapter);

        OrderRepository orderRepository =
                new OrderRepository(new OrderRemoteDataSource(FOrderServiceClient.getInstance()));
        CheckOrderContract.Presenter presenter =
                new CheckOrderPresenter(mViewModel, orderRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentCheckOrderBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_check_order, container, false);
        binding.setViewModel((CheckOrderViewModel) mViewModel);

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
