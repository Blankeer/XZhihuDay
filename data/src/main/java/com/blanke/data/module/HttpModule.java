package com.blanke.data.module;

import android.app.Application;
import android.text.TextUtils;

import com.blanke.data.utils.NetWorkUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    public static final String TAG_CACHE_OFFLINE_AGE = "TAG_CACHE_OFFLINE_AGE";//添加到head的缓存超时时间
    public static final String TAG_CACHE_ONLINE_AGE = "TAG_CACHE_ONLINE_AGE";//网络离线和在线清空下的超时
    public static final String CACHE_DIR_NAME = "http-cache";//缓存目录名称
    private int httpTimeOut = 10 * 1000;//毫秒
    private int httpCacheSize = 10 * 1024 * 1024;//缓存文件最大值
    private HttpUrl mApiUrl;
    private List<Interceptor> mInterceptors;
    private List<Interceptor> mNetWorkInterceptors;
    private String httpCacheDirName = CACHE_DIR_NAME;//缓存目录名称
    private Gson gson;
    private boolean isShowLog = false;
    private Application application;
    private Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.checkNet(application)) {//没网强制从缓存读取(必须得写，不然断网状态下，退出应用，或者等待一分钟后，就获取不到缓存）
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            //通过cache head设置相应的缓存设置,设置完后删除相应的head
            String offAgeStr = request.header(TAG_CACHE_OFFLINE_AGE);
            String onAgeStr = request.header(TAG_CACHE_ONLINE_AGE);
            int offAge = 0, onAge = 0;
            if (!TextUtils.isEmpty(offAgeStr)) {
                try {
                    offAge = Integer.parseInt(offAgeStr);
                } catch (Exception e) {
                }
                request = request.newBuilder()
                        .removeHeader(TAG_CACHE_OFFLINE_AGE)
                        .build();
            }
            if (!TextUtils.isEmpty(onAgeStr)) {
                try {
                    onAge = Integer.parseInt(onAgeStr);
                } catch (Exception e) {
                }
                request = request.newBuilder()
                        .removeHeader(TAG_CACHE_ONLINE_AGE)
                        .build();
            }
            Response response = chain.proceed(request);
            response = response.newBuilder().removeHeader("Etag").build();
            boolean isNetwork = NetWorkUtils.checkNet(application);
            Logger.e("cache :" + offAgeStr + "," + onAgeStr + "," + isNetwork);
            if (isNetwork) {
                if (onAge > 0) {//单位是秒
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, max-age=" + onAge)
                            .build();
                }
            } else {
                if (offAge > 0) {
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + offAge)
                            .build();
                }
            }
            return response;
        }
    };

    private HttpModule() {
        mInterceptors = new ArrayList<>();
        mNetWorkInterceptors = new ArrayList<>();
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
        this.application = application;
        String cachePath = application.getCacheDir().getAbsolutePath();
        cachePath += File.separator + httpCacheDirName;
        return new Cache(new File(cachePath), httpCacheSize);//设置缓存路径和大小
    }

    private Retrofit configureRetrofit(Retrofit.Builder builder, OkHttpClient client, final HttpUrl httpUrl) {
        return builder.baseUrl(httpUrl)
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
        builder.addNetworkInterceptor(new StethoInterceptor());//okhttp听诊器
        builder.addNetworkInterceptor(cacheInterceptor);//cache
        builder.addInterceptor(cacheInterceptor);
        //显示http log
        if (isShowLog) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(logging);
        }
        //自定义interceptor
        if (mInterceptors != null && mInterceptors.size() > 0) {
            for (Interceptor interceptor : mInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        //自定义networkInterceptor
        if (mNetWorkInterceptors != null && mNetWorkInterceptors.size() > 0) {
            for (Interceptor interceptor : mNetWorkInterceptors) {
                builder.addNetworkInterceptor(interceptor);
            }
        }
        return builder.build();
    }


    public static final class Buidler {
        HttpModule httpModule;

        public Buidler() {
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

        public Buidler showLog(boolean isShowLog) {
            httpModule.isShowLog = isShowLog;
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

        public Buidler addNetworkInterceptor(Interceptor... interceptors) {
            for (Interceptor in : interceptors) {
                httpModule.mNetWorkInterceptors.add(in);
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