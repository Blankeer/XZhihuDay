package com.blanke.data.repository.base;


import com.blanke.data.bean.ArticleBean;
import com.blanke.data.bean.http.LatestResponse;

import java.util.Date;

import rx.Observable;

/**
 * Created by blanke on 16-6-6.
 */
public interface ArticleRepository {
    Observable<LatestResponse> getLatestNowData();

    Observable<ArticleBean> getArticle(int id);

    Observable<LatestResponse> getLatestResponse(Date date);
}
