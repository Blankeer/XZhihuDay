package com.blanke.xzhihuday.app.di;


import com.blanke.data.api.ZhiHuApi;
import com.blanke.data.module.ApiModule;
import com.blanke.data.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by blanke on 16-6-5.
 */
@Singleton
@Component(modules = {
        AppModule.class, ApiModule.class, HttpModule.class
})
public interface AppComponent {
    ZhiHuApi provideZhiHuApi();

}
