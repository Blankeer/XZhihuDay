package com.blanke.xzhihuday.app;

import android.app.Application;

import com.blanke.xzhihuday.app.di.AppComponent;
import com.blanke.xzhihuday.app.di.AppModule;
import com.blanke.xzhihuday.app.di.DaggerAppComponent;

/**
 * Created by blanke on 16-6-5.
 */
public class XApplication extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        // test
    }

    private void initAppComponent() {
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
