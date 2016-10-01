package com.blanke.data.repository;


import com.blanke.data.api.ZhiHuApi;
import com.blanke.data.bean.ArticleBean;
import com.blanke.data.bean.http.LatestResponse;
import com.blanke.data.utils.DateUtils;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by blanke on 16-6-6.
 */
class NetworkArticleRepository implements ArticleRepository {
    private ZhiHuApi zhiHuApi;

    @Inject
    @Singleton
    public NetworkArticleRepository(ZhiHuApi zhiHuApi) {
        this.zhiHuApi = zhiHuApi;
    }

    @Override
    public Observable<LatestResponse> getLatestNowData() {
        return zhiHuApi.getLatestNowData();
    }

    @Override
    public Observable<ArticleBean> getArticle(int id) {
        return zhiHuApi.getArticle(id);
    }

    @Override
    public Observable<LatestResponse> getLatestResponse(Date date) {
        return zhiHuApi.getLatestData(DateUtils.date2yyyyMMdd2(date));
    }
}
