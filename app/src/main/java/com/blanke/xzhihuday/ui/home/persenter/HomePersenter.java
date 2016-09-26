package com.blanke.xzhihuday.ui.home.persenter;

import com.blanke.xzhihuday.ui.home.view.HomeView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.Date;

/**
 * Created by blanke on 16-6-6.
 */
public abstract class HomePersenter extends MvpBasePresenter<HomeView> {

    public abstract void getLatestData(boolean isPull, Date date);
}
