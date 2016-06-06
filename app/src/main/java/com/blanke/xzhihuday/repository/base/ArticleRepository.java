package com.blanke.xzhihuday.repository.base;

import com.blanke.xzhihuday.bean.ArticleBean;
import com.blanke.xzhihuday.bean.LatestResponse;

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
