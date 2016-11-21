package com.blanke.xzhihuday.ui.home.persenter;

import com.blanke.data.bean.http.LatestResponse;
import com.blanke.data.repository.ArticleDataManager;
import com.blanke.data.utils.DateUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by blanke on 16-6-6.
 */
public class HomePersenterImpl extends HomePersenter {
    private ArticleDataManager mArticleDataManager;
    private Observable<LatestResponse> observer;

    @Inject
    public HomePersenterImpl(ArticleDataManager mArticleDataManager) {
        this.mArticleDataManager = mArticleDataManager;
    }

    @Override
    public void getLatestData(boolean isPull, Date date) {
        if (getView() != null) {
            getView().showLoading(isPull);
            Observable<Long> temp = Observable.timer(5000, TimeUnit.MILLISECONDS);
            if (DateUtils.isSameDay(date, DateUtils.nextDate(new Date()))) {

                observer = temp.flatMap(new Func1<Long, Observable<LatestResponse>>() {
                    @Override
                    public Observable<LatestResponse> call(Long aLong) {
                        return mArticleDataManager.getLatestNowData();
                    }
                });
            } else {
                observer = temp.flatMap(new Func1<Long, Observable<LatestResponse>>() {
                    @Override
                    public Observable<LatestResponse> call(Long aLong) {
                        return mArticleDataManager.getLatestResponse(date);
                    }
                });
            }
            observer.filter(latestResponse -> getView() != null)
                    .subscribe(latestResponse -> {
//                        Logger.d(latestResponse);
                        getView().setData(latestResponse);
                        getView().showContent();
                    }, throwable -> {
                        throwable.printStackTrace();
                        getView().showError(throwable, isPull);
                    });
        }
    }
}
