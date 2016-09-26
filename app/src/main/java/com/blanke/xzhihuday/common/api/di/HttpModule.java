package com.blanke.xzhihuday.common.api.di;

import android.app.Application;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    public static final String CACHE_DIR_NAME = "http-cache";
    private int httpTimeOut = 10 * 1000;//毫秒
    private int httpCacheSize = 10 * 1024 * 1024;//缓存文件最大值
    private HttpUrl mApiUrl;
    private List<Interceptor> mInterceptors;
    private String httpCacheDirName = CACHE_DIR_NAME;//缓存目录名称
    private Gson gson;

    private HttpModule() {
        mInterceptors = new ArrayList<>();
        gson = new Gson();
    }

    public static Buidler buidler() {
        return new Buidler();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(Cache cache) {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        return configureClient(okHttpClient, cache);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        return configureRetrofit(builder, client, mApiUrl);
    }

    @Singleton
    @Provides
    Cache provideCache(Application application) {
        String cachePath = application.getCacheDir().getAbsolutePath();
        cachePath += File.separator + httpCacheDirName;
        return new Cache(new File(cachePath), httpCacheSize);//设置缓存路径和大小
    }

    private Retrofit configureRetrofit(Retrofit.Builder builder, OkHttpClient client, final HttpUrl httpUrl) {
        return builder
                .baseUrl(httpUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private OkHttpClient configureClient(OkHttpClient.Builder okHttpClient, Cache cache) {
        OkHttpClient.Builder builder = okHttpClient
                .connectTimeout(httpTimeOut, TimeUnit.MILLISECONDS)
                .readTimeout(httpTimeOut, TimeUnit.MILLISECONDS)
                .cache(cache);
        if (mInterceptors != null && mInterceptors.size() > 0) {
            for (Interceptor interceptor : mInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }


    public static final class Buidler {
        HttpModule httpModule;

        private Buidler() {
            httpModule = new HttpModule();
        }

        public Buidler baseurl(String baseurl) {
            httpModule.mApiUrl = HttpUrl.parse(baseurl);
            return this;
        }

        public Buidler timeout(int timeout) {
            httpModule.httpTimeOut = timeout;
            return this;
        }

        public Buidler cacheDirName(String httpCacheDirName) {
            httpModule.httpCacheDirName = httpCacheDirName;
            return this;
        }

        public Buidler cacheSize(int cacheSize) {
            httpModule.httpCacheSize = cacheSize;
            return this;
        }

        public Buidler gson(Gson gson) {
            httpModule.gson = gson;
            return this;
        }

        public Buidler addInterceptor(Interceptor... interceptors) {
            for (Interceptor in : interceptors) {
                httpModule.mInterceptors.add(in);
            }
            return this;
        }

        public HttpModule build() {
            if (httpModule.mApiUrl == null) {
                throw new IllegalStateException("baseurl == null");
            }
            return httpModule;
        }
    }
}
