package com.blanke.data.repository;


import com.blanke.data.bean.ArticleBean;
import com.blanke.data.bean.http.LatestResponse;

import java.util.Date;

import rx.Observable;

/**
 * Created by blanke on 16-6-6.
 */
interface ArticleRepository {
    Observable<LatestResponse> getLatestNowData();

    Observable<ArticleBean> getArticle(int id);

    Observable<LatestResponse> getLatestResponse(Date date);
}
