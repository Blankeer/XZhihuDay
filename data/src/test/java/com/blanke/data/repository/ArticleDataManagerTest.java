package com.blanke.data.repository;

import com.blanke.data.bean.http.LatestResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;

/**
 * Created by blanke on 16-10-2.
 */
public class ArticleDataManagerTest {
    @Mock
    NetworkArticleRepository networkArticleRepository;
    @Mock
    CacheArticleRepository cacheArticleRepository;

    ArticleDataManager articleDataManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        articleDataManager = new ArticleDataManager
                (new ArticleDataFactory(
                        networkArticleRepository, cacheArticleRepository));


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getLatestNowData() throws Exception {
        LatestResponse mockData = new LatestResponse();
        Mockito.when(networkArticleRepository.getLatestNowData())
                .thenReturn(Observable.just(mockData));
        articleDataManager.getLatestNowData();
        Mockito.verify(networkArticleRepository).getLatestNowData();
    }

    @Test
    public void getArticle() throws Exception {


    }

    @Test
    public void getLatestResponse() throws Exception {

    }

}