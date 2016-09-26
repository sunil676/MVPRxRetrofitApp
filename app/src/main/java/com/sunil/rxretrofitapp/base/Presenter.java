package com.sunil.rxretrofitapp.base;

/**
 * Created by sunil on 26-Sep-16.
 */
/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}

