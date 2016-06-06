package com.blanke.xzhihuday.app.di;

import com.blanke.xzhihuday.api.ZhiHuApi;
import com.blanke.xzhihuday.api.di.ApiModule;

import javax.inject.Singleton;

import dagger.Component;
import rx.Observable;

/**
 * Created by blanke on 16-6-5.
 */
@Singleton
@Component(modules = {
        AppModule.class, ApiModule.class, ConfigModule.class
})
public interface AppComponent {

    ZhiHuApi provideZhiHuApi();

    Observable.Transformer provideSchedulers();
}
