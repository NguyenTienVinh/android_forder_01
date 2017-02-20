package com.example.duong.android_forder_01.ui.shoppingcard;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.ShoppingCard;
import com.example.duong.android_forder_01.data.model.ShoppingCardDetail;
import com.example.duong.android_forder_01.databinding.ActivityShoppingCardBinding;
import com.example.duong.android_forder_01.ui.adapter.ShoppingCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCardActivity extends AppCompatActivity implements ShoppingCardContract.View {
    private ActivityShoppingCardBinding mBinding;
    private ShoppingCardContract.Presenter mPresenter;
    private ShoppingCard mShoppingCard;
    private ObservableField<ShoppingCardAdapter> mShoppingCardAdapter = new ObservableField<>();
    private Toolbar mToolbar;
    private List<ShoppingCardDetail> mShoppingCardDetailList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout
            .activity_shopping_card);
        setPresenter(new ShoppingCardPresenter(this));
        mPresenter.start();
    }

    @Override
    public void start() {
        mShoppingCard = new ShoppingCard();
        mBinding.setShoppingCardActivity(this);
        mBinding.setActionHandler(new ShoppingCardActionHandler
            (mPresenter));
        initRecyclerView();
        initToolbar();
    }

    @Override
    public void setPresenter(ShoppingCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initRecyclerView() {
        mShoppingCardAdapter
            .set(new ShoppingCardAdapter(mShoppingCardDetailList, this, mPresenter));
    }

    @Override
    public void initToolbar() {
        mToolbar = mBinding.toolbarShoppingCard;
        mToolbar.setTitle(R.string.title_shopping_card);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public ShoppingCard getShoppingCard() {
        return mShoppingCard;
    }

    public ObservableField<ShoppingCardAdapter> getShoppingCardAdapter() {
        return mShoppingCardAdapter;
    }
}