package com.blanke.xzhihuday.api;

import com.blanke.xzhihuday.bean.ArticleBean;
import com.blanke.xzhihuday.bean.LatestResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by blanke on 16-6-5.
 */
public interface ZhiHuApi {

    @GET("news/latest")
    Observable<LatestResponse> getLatestNowData();

    @GET("news/before/{date}")
    Observable<LatestResponse> getLatestData(@Path("date") String date);

    @GET("news/{id}")
    Observable<ArticleBean> getArticle(@Path("id") int id);
}
