package com.blanke.xzhihuday.core.home.persenter;

import com.blanke.xzhihuday.bean.LatestResponse;
import com.blanke.xzhihuday.repository.factory.ArticleDataFactory;
import com.blanke.xzhihuday.utils.DateUtils;
import com.socks.library.KLog;

import java.util.Date;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by blanke on 16-6-6.
 */
public class HomePersenterImpl extends HomePersenter {

    private ArticleDataFactory mArticleDataFactory;
    private Observable<LatestResponse> observer;

    @Inject
    public HomePersenterImpl(ArticleDataFactory mArticleDataFactory) {
        this.mArticleDataFactory = mArticleDataFactory;
    }

    @Override
    public void getLatestData(boolean isPull, Date date) {
        if (getView() != null) {
            getView().showLoading(isPull);
            if (DateUtils.isSameDay(date, DateUtils.nextDate(new Date()))) {
                observer = mArticleDataFactory.getLatestNowData();
            } else {
                observer = mArticleDataFactory.getLatestResponse(date);
            }
            observer.filter(latestResponse -> getView() != null)
                    .subscribe(new Action1<LatestResponse>() {
                        @Override
                        public void call(LatestResponse latestResponse) {
                            KLog.d(latestResponse);
                            getView().setData(latestResponse);
                            getView().showContent();
                        }
                    }, throwable -> {
                        throwable.printStackTrace();
                        getView().showError(throwable, isPull);
                    });
        }
    }
}
