package com.blanke.data.repository;

import com.blanke.data.bean.ArticleBean;
import com.blanke.data.bean.http.LatestResponse;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by blanke on 16-10-1.
 */
public class ArticleDataManager implements ArticleRepository {
    private ArticleDataFactory mArticleDataFactory;
    private Observable.Transformer mSchedulers;

    @Inject
    @Singleton
    public ArticleDataManager(ArticleDataFactory mArticleDataFactory) {
        this.mArticleDataFactory = mArticleDataFactory;
        this.mSchedulers = new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public Observable<LatestResponse> getLatestNowData() {
        return mArticleDataFactory.createLatestNowData()
                .getLatestNowData().compose(mSchedulers);
    }

    @Override
    public Observable<ArticleBean> getArticle(int id) {
        return mArticleDataFactory.createLatestNowData()
                .getArticle(id).compose(mSchedulers);
    }

    @Override
    public Observable<LatestResponse> getLatestResponse(Date date) {
        return mArticleDataFactory.createLatestNowData()
                .getLatestResponse(date).compose(mSchedulers);
    }
}
