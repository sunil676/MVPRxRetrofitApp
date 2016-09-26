package com.sunil.rxretrofitapp.injection.component;

import com.sunil.rxretrofitapp.injection.PerActivity;
import com.sunil.rxretrofitapp.injection.module.ActivityModule;
import com.sunil.rxretrofitapp.main.MainActivity;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
