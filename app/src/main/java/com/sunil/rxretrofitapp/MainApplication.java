package com.sunil.rxretrofitapp;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.sunil.rxretrofitapp.injection.component.ApplicationComponent;
import com.sunil.rxretrofitapp.injection.component.DaggerApplicationComponent;
import com.sunil.rxretrofitapp.injection.module.ApplicationModule;

import io.fabric.sdk.android.Fabric;

/**
 * Created by sunil on 25-Sep-16.
 */
public class MainApplication extends Application{

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
          //  Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    public static MainApplication getContext(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
