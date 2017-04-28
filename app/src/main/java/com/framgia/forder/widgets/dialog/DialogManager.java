package com.framgia.forder.widgets.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.framgia.forder.R;
import java.util.Calendar;

/**
 * Created by tri on 27/04/2017.
 */

public class DialogManager {
    private Context mContext;
    private AlertDialog mDialog;
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;

    public DialogManager(Context context) {
        mContext = context;
        mCalendar = Calendar.getInstance();
    }

    public DialogManager dialogwithNoTitleTwoButton(int message,
            DialogInterface.OnClickListener positiveButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(message)
                .setPositiveButton(R.string.action_yes, positiveButtonListener)
                .setNegativeButton(R.string.action_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        mDialog = builder.create();
        return this;
    }

    public DialogManager dialogDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener) {
        mDatePickerDialog =
                new DatePickerDialog(mContext, onDateSetListener, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        return this;
    }

    public void showDatePickerDialog() {
        if (mDatePickerDialog == null) {
            return;
        }
        mDatePickerDialog.show();
    }

    public void show() {
        if (mDialog == null) {
            return;
        }
        mDialog.show();
    }

    public void dismiss() {
        if (mDialog == null) {
            return;
        }
        mDialog.dismiss();
    }
}
