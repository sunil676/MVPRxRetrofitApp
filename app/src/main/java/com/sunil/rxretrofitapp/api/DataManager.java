package com.sunil.rxretrofitapp.api;

import com.sunil.rxretrofitapp.event.FriendsEvent;
import com.sunil.rxretrofitapp.model.Friends;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sunil on 26-Sep-16.
 */

public class DataManager {

    private final APIService mAPIService;

    @Inject
    public DataManager(APIService apiService) {
        mAPIService = apiService;

    }


    public void getFriends() {

        Observable<Friends> friendResponseObservable = mAPIService.getMyFriends()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        friendResponseObservable.subscribe(new Observer<Friends>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //handle error
                if (e instanceof HttpException) {
                    // We had non-2XX http error
                }
                if (e instanceof IOException) {
                    // A network or conversion error happened
                }

                // We don't know what happened. We need to simply convert to an unknown error
                // ...
            }

            @Override
            public void onNext(Friends response) {
                //handle response
                EventBus.getDefault().post(new FriendsEvent(response));
            }
        });


    }

}
