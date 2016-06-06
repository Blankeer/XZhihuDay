package com.blanke.xzhihuday.repository.base;

import com.blanke.xzhihuday.bean.ArticleBean;

import rx.Observable;

/**
 * Created by blanke on 16-6-6.
 */
public interface ArticleRepository {
    Observable<ArticleBean> getArticle(int id);


}
