package com.blanke.xzhihuday.app.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by blanke on 16-6-6.
 */
@Module
@Singleton
public class ConfigModule {

    @Provides
    @Singleton
    public Observable.Transformer provideSchedulers() {
        return new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable) observable).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
