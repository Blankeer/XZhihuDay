package com.blanke.data.repository;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by blanke on 16-6-6.
 */
class ArticleDataFactory {
    private NetworkArticleRepository mNetworkArticleRepository;
    private CacheArticleRepository mCacheArticleRepository;

    @Inject
    @Singleton
    public ArticleDataFactory(NetworkArticleRepository mNetworkArticleRepository, CacheArticleRepository cacheArticleRepository) {
        this.mNetworkArticleRepository = mNetworkArticleRepository;
        mCacheArticleRepository = cacheArticleRepository;
    }

    public ArticleRepository createLatestNowData() {
        return mNetworkArticleRepository;
    }

    public ArticleRepository createArticle(int id) {
        return mNetworkArticleRepository;
    }

    public ArticleRepository createLatestResponse(Date date) {
        return mNetworkArticleRepository;
    }
}
