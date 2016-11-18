package com.blanke.xzhihuday.ui.home.persenter;

import com.blanke.data.bean.http.LatestResponse;
import com.blanke.data.repository.ArticleDataManager;
import com.blanke.data.utils.DateUtils;

import java.util.Date;

import javax.inject.Inject;

import rx.Observable;

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
            if (DateUtils.isSameDay(date, DateUtils.nextDate(new Date()))) {
                observer = mArticleDataManager.getLatestNowData();
            } else {
                observer = mArticleDataManager.getLatestResponse(date);
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
