package com.sunil.rxretrofitapp.event;

import com.sunil.rxretrofitapp.model.Friends;

/**
 * Created by sunil on 26-Sep-16.
 */

public class FriendsEvent {

    private Friends friends;

    public FriendsEvent(Friends friends) {
        this.friends = friends;
    }

    public Friends getFriends() {
        return friends;
    }
}
