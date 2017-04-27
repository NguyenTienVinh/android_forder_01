package com.framgia.forder.screen.main;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.framgia.forder.R;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.ActivityMainBinding;
import com.framgia.forder.screen.BaseActivity;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity {

    private static final int DELAY_TIME_TWO_TAP_BACK_BUTTON = 2000;

    private MainContract.ViewModel mViewModel;
    private Handler mHandler;
    private Runnable mRunnable;
    private boolean mIsDoubleTapBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        mViewModel = new MainViewModel(adapter, alertDialog);

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getApplicationContext());
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));

        MainContract.Presenter presenter = new MainPresenter(mViewModel, domainRepository);
        mViewModel.setPresenter(presenter);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mIsDoubleTapBack = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        mHandler.removeCallbacks(mRunnable);
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (mViewModel.onBackPressed()) {
            return;
        }
        if (mIsDoubleTapBack) {
            super.onBackPressed();
            return;
        }
        mIsDoubleTapBack = true;
        Toast.makeText(this, getString(R.string.please_click_back_again_to_exit),
                Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, DELAY_TIME_TWO_TAP_BACK_BUTTON);
    }
}
