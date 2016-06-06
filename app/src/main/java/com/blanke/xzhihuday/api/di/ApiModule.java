package com.blanke.xzhihuday.api.di;

import com.blanke.xzhihuday.api.ZhiHuApi;
import com.blanke.xzhihuday.config.ProjectConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by blanke on 16-5-30.
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat(ProjectConfig.DATE_FORMAT)
                .create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ProjectConfig.getBaseApiUrl())
                .addConverterFactory(
                        GsonConverterFactory.create(gson)
                )
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ZhiHuApi provideGanKAPI(Retrofit retrofit) {
        return retrofit.create(ZhiHuApi.class);
    }
}
