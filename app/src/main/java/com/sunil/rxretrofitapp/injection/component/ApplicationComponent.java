package com.sunil.rxretrofitapp.injection.component;

import android.app.Application;
import android.content.Context;

import com.sunil.rxretrofitapp.api.APIService;
import com.sunil.rxretrofitapp.api.DataManager;
import com.sunil.rxretrofitapp.injection.ApplicationContext;
import com.sunil.rxretrofitapp.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    APIService apiService();
    DataManager dataManager();


}
