package com.framgia.forder.screen.notification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.NotificationRepository;
import com.framgia.forder.data.source.remote.NotificationRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentListNotificationBinding;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationPageFragment extends Fragment {

    private NotificationPageContract.ViewModel mViewModel;

    public static NotificationPageFragment newInstance() {
        return new NotificationPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Notification> notifications = new ArrayList<>();
        NotificationPageAdapter notificationPageAdapter =
                new NotificationPageAdapter(getActivity(), notifications);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new NotificationPageViewModel(notificationPageAdapter, navigator);
        NotificationRepository notificationRepository = new NotificationRepository(
                new NotificationRemoteDataSource(FOrderServiceClient.getInstance()));
        NotificationPageContract.Presenter presenter =
                new NotificationPagePresenter(mViewModel, notificationRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentListNotificationBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_notification, container,
                        false);
        binding.setViewModel((NotificationPageViewModel) mViewModel);
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
