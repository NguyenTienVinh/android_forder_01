package com.framgia.forder.screen.ordermanagement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.forder.R;
import com.framgia.forder.databinding.ActivityOrderManagementBinding;
import com.framgia.forder.screen.BaseActivity;

/**
 * OrderManagement Screen.
 */
public class OrderManagementActivity extends BaseActivity {

    private OrderManagementContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new OrderManagementViewModel();

        OrderManagementContract.Presenter presenter = new OrderManagementPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityOrderManagementBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_order_management);
        binding.setViewModel((OrderManagementViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
