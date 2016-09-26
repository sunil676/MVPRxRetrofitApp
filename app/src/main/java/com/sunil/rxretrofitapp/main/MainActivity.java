package com.sunil.rxretrofitapp.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunil.rxretrofitapp.R;
import com.sunil.rxretrofitapp.adapter.FriendsAdapter;
import com.sunil.rxretrofitapp.base.BaseActivity;
import com.sunil.rxretrofitapp.model.Friends;
import com.sunil.rxretrofitapp.util.DialogFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView{

    @BindView(R.id.recyclerView1)
    RecyclerView mRecyclerView;

    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, frameLayoutBase);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        setActionBarWithBackButton();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        mMainPresenter.loadFriends();

    }

    protected void setActionBarWithBackButton() {
        toolbar.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.navigate_back));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void showLoader() {
       DialogFactory.displayProgressDialog(this, "Loading");
    }

    @Override
    public void cancelLoader() {
        DialogFactory.cancelProgressDialog();
    }

    @Override
    public void showFriendsEmpty() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_no_friends)).show();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_firnds)).show();
    }

    @Override
    public void showFriends(Friends friends) {
        FriendsAdapter adapter = new FriendsAdapter(friends.getUser());
        mRecyclerView.setAdapter(adapter);
    }
}
