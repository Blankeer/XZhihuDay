package com.blanke.data.module;

import com.blanke.data.api.ZhiHuApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {
    @Provides
    @Singleton
    public ZhiHuApi provideAPI(Retrofit retrofit) {
        return retrofit.create(ZhiHuApi.class);
    }
}