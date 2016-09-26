package com.blanke.xzhihuday.common.api.di;

import com.blanke.xzhihuday.common.api.ZhiHuApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by blanke on 16-5-30.
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    public ZhiHuApi provideAPI(Retrofit retrofit) {
        return retrofit.create(ZhiHuApi.class);
    }
}
