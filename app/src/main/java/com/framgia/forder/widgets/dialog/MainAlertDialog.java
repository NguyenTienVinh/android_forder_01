package com.framgia.forder.widgets.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.framgia.forder.R;

/**
 * Created by le.quang.dao on 15/03/2017.
 */

public class MainAlertDialog extends AppCompatDialog implements DialogInterface {

    @StyleRes
    private int mTheme;
    private AlertParams mParams;

    MainAlertDialog(AlertParams alertParams, @StyleRes int theme) {
        super(alertParams.mContext);
        mParams = alertParams;
        mTheme = theme;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getWindow();
        if (window != null) {
            // dim the screen to match the design
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = 0.85f;
            windowParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(windowParams);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            setTheme(mTheme);
            window.getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
            window.getAttributes().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        TextView textTitle = (TextView) findViewById(R.id.text_title);
        TextView textMessage = (TextView) findViewById(R.id.text_message);
        Button buttonNegative = (Button) findViewById(R.id.btn_negative);
        Button buttonPositive = (Button) findViewById(R.id.btn_positive);

        textTitle.setVisibility(mParams.mInvisibleTitle ? View.GONE : View.VISIBLE);
        textTitle.setText(mParams.mTitle);
        textMessage.setText(mParams.mMessage);
        assert buttonNegative != null;
        buttonNegative.setVisibility(mParams.mInvisibleButtonCancle ? View.GONE : View.VISIBLE);
        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert mParams.mNegativeListener != null;
                mParams.mNegativeListener.onClick(MainAlertDialog.this,
                        DialogInterface.BUTTON_NEGATIVE);
            }
        });
        assert buttonPositive != null;
        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert mParams.mPositiveListener != null;
                mParams.mPositiveListener.onClick(MainAlertDialog.this,
                        DialogInterface.BUTTON_POSITIVE);
            }
        });
    }

    void setTheme(int theme) {
        final TypedArray typedArray =
                getContext().obtainStyledAttributes(theme, R.styleable.StyleMainAlertDialog);
        int layoutId = typedArray.getResourceId(R.styleable.StyleMainAlertDialog_layout,
                R.layout.dialog_main_alert);
        setContentView(layoutId);
        typedArray.recycle();
    }

    /**
     * Builder for params of Dialog
     */
    static class Builder {
        private AlertParams mParams;
        @StyleRes
        private int mTheme;

        Builder(Context context) {
            this(context, R.style.StyleMainAlertDialog);
        }

        Builder(Context context, int theme) {
            mParams = new AlertParams(context);
            mTheme = theme;
        }

        Builder setMessage(String message) {
            mParams.mMessage = message;
            return this;
        }

        Builder setTitle(String title) {
            mParams.mTitle = title;
            return this;
        }

        Builder setVisibleTitle(boolean invisibleTitle) {
            mParams.mInvisibleTitle = invisibleTitle;
            return this;
        }

        Builder setVisibleButtonCancle(boolean visibleButtonCancle) {
            mParams.mInvisibleButtonCancle = visibleButtonCancle;
            return this;
        }

        Builder setPositiveListener(@Nullable OnClickListener positiveListener) {
            mParams.mPositiveListener = positiveListener;
            return this;
        }

        Builder setNegativeListener(@Nullable OnClickListener negativeListener) {
            mParams.mNegativeListener = negativeListener;
            return this;
        }

        Builder setPositiveBtnText(String positiveBtnText) {
            mParams.mPositiveBtnText = positiveBtnText;
            return this;
        }

        Builder setNegativeBtnText(String negativeBtnText) {
            mParams.mNegativeBtnText = negativeBtnText;
            return this;
        }

        Builder setIsCancelable(boolean isCancelable) {
            mParams.isCancelable = isCancelable;
            return this;
        }

        MainAlertDialog create() {
            return new MainAlertDialog(mParams, mTheme);
        }

        MainAlertDialog show() {
            MainAlertDialog dialog = this.create();
            dialog.setCancelable(mParams.isCancelable);
            dialog.show();
            return dialog;
        }
    }

    /**
     * AlertParams for Dialog
     */
    static class AlertParams {
        private final Context mContext;
        String mMessage;
        String mTitle;
        boolean mInvisibleTitle;
        boolean mInvisibleButtonCancle = true;
        @Nullable
        OnClickListener mPositiveListener;
        @Nullable
        OnClickListener mNegativeListener;
        String mPositiveBtnText;
        String mNegativeBtnText;
        boolean isCancelable;

        AlertParams(Context context) {
            mContext = context;
        }
    }
}
