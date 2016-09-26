package com.sunil.rxretrofitapp.main;

import com.sunil.rxretrofitapp.base.MvpView;
import com.sunil.rxretrofitapp.model.Friends;

/**
 * Created by sunil on 26-Sep-16.
 */

public interface MainMvpView extends MvpView{

    void showLoader();
    void cancelLoader();
    void showFriendsEmpty();
    void showError();
    void showFriends(Friends friends);
}
