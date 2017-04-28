package com.framgia.forder.screen.orderhistory;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.DatePicker;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.Calendar;
import java.util.List;

import static com.framgia.forder.utils.Utils.DateTimeUntils.convertDateToString;

/**
 * Created by ASUS on 25-04-2017.
 */

public class OrderHistoryViewModel extends BaseObservable
        implements OrderHistoryContract.ViewModel, DatePickerDialog.OnDateSetListener {
    private static final int FLAG_START_DATE = 1;
    private static final int FLAG_END_DATE = 2;

    private Context mContext;
    private OrderHistoryContract.Presenter mPresenter;
    private OrderHistoryAdapter mOrderHistoryAdapter;
    private Navigator mNavigator;
    private DialogManager mDialogManager;
    private Calendar mCalendar;
    private String mStartDate;
    private String mEndDate;
    private boolean mIsHidden;
    private int mFlag;

    OrderHistoryViewModel(Context context, OrderHistoryAdapter orderHistoryAdapter,
            Navigator navigator, DialogManager dialogManager) {
        mContext = context;
        mOrderHistoryAdapter = orderHistoryAdapter;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mCalendar = Calendar.getInstance();
        mDialogManager.dialogDatePicker(this);
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
    public void setPresenter(OrderHistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListAllOrderHistoryError(BaseException e) {
        mNavigator.showToast(e.getMessage());
    }

    @Override
    public void onGetListAllOrderHistorySuccess(List<Order> orderHistories) {
        mOrderHistoryAdapter.updateData(orderHistories);
    }

    public OrderHistoryAdapter getOrderHistoryAdapter() {
        return mOrderHistoryAdapter;
    }

    public void onClickStartDate() {
        mFlag = FLAG_START_DATE;
        mDialogManager.showDatePickerDialog();
    }

    public void onClickEndDate() {
        mFlag = FLAG_END_DATE;
        mDialogManager.showDatePickerDialog();
    }

    public void onChangeFilter() {
        //TODO : Change Condition Filter in DialogInterface
    }

    public void onClickHiddenFilter() {
        mIsHidden = !mIsHidden;
        notifyPropertyChanged(BR.hidden);
    }

    public void onFilter() {
        //TODO : Filter
    }

    @Bindable
    public boolean isHidden() {
        return mIsHidden;
    }

    @Bindable
    public String getStartDate() {
        if (mStartDate != null) {
            return mStartDate;
        }
        return "";
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    @Bindable
    public String getEndDate() {
        if (mEndDate != null) {
            return mEndDate;
        }
        return "";
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (mFlag == FLAG_START_DATE) {
            mStartDate = convertDateToString(mCalendar.getTime());
            notifyPropertyChanged(BR.startDate);
        } else {
            mEndDate = convertDateToString(mCalendar.getTime());
            notifyPropertyChanged(BR.endDate);
        }
    }
}
