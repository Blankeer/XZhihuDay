package com.blanke.data.api;

import com.blanke.data.bean.ArticleBean;
import com.blanke.data.bean.http.LatestResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by blanke on 16-6-5.
 */
public interface ZhiHuApi {

    @GET("news/latest")
//    @Headers({HttpModule.TAG_CACHE_ONLINE_AGE + ":" + 10})
    Observable<LatestResponse> getLatestNowData();

    @GET("news/before/{date}")
    Observable<LatestResponse> getLatestData(@Path("date") String date);

    @GET("news/{id}")
    Observable<ArticleBean> getArticle(@Path("id") int id);
}
