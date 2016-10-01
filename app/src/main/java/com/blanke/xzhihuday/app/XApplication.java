package com.blanke.xzhihuday.app;

import android.app.Application;

import com.blanke.data.module.ApiModule;
import com.blanke.data.module.HttpModule;
import com.blanke.xzhihuday.app.di.AppComponent;
import com.blanke.xzhihuday.app.di.AppModule;
import com.blanke.xzhihuday.app.di.DaggerAppComponent;
import com.blanke.xzhihuday.config.ProjectConfig;

/**
 * Created by blanke on 16-6-5.
 */
public class XApplication extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule.Buidler()
                        .baseurl(ProjectConfig.BASE_API_URL).build())
                .apiModule(new ApiModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
