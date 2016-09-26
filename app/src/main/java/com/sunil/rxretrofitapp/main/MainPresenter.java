package com.sunil.rxretrofitapp.main;

import com.sunil.rxretrofitapp.api.DataManager;
import com.sunil.rxretrofitapp.base.BasePresenter;
import com.sunil.rxretrofitapp.event.FriendsEvent;
import com.sunil.rxretrofitapp.injection.ConfigPersistent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by sunil on 26-Sep-16.
 */
@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
        EventBus.getDefault().unregister(this);
    }

    public void loadFriends() {
        checkViewAttached();
        getMvpView().showLoader();
        mDataManager.getFriends();
    }

    @Subscribe
    public void OnEvent(FriendsEvent response) {
        if(response.getFriends()!=null) {
            getMvpView().cancelLoader();
            if (response.getFriends().getUser().size() < 1) {
                getMvpView().showFriendsEmpty();
            } else {
                getMvpView().showFriends(response.getFriends());
            }
        }
    }

}

