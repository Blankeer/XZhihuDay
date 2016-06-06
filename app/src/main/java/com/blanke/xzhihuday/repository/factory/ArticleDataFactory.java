package com.blanke.xzhihuday.repository.factory;

import com.blanke.xzhihuday.bean.ArticleBean;
import com.blanke.xzhihuday.bean.LatestResponse;
import com.blanke.xzhihuday.repository.base.ArticleRepository;
import com.blanke.xzhihuday.repository.impl.network.NetworkArticleRepository;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by blanke on 16-6-6.
 */
public class ArticleDataFactory implements ArticleRepository {
    private NetworkArticleRepository mNetworkArticleRepository;
    private Observable.Transformer mSchedulers;

    @Inject
    @Singleton
    public ArticleDataFactory(NetworkArticleRepository mNetworkArticleRepository, Observable.Transformer mSchedulers) {
        this.mNetworkArticleRepository = mNetworkArticleRepository;
        this.mSchedulers = mSchedulers;
    }

    @Override
    public Observable<LatestResponse> getLatestNowData() {
        return mNetworkArticleRepository.getLatestNowData()
                .compose(mSchedulers);
    }

    public Observable<ArticleBean> getArticle(int id) {
        return mNetworkArticleRepository.getArticle(id)
                .compose(mSchedulers);
    }

    @Override
    public Observable<LatestResponse> getLatestResponse(Date date) {
        return mNetworkArticleRepository.getLatestResponse(date)
                .compose(mSchedulers);
    }
}
