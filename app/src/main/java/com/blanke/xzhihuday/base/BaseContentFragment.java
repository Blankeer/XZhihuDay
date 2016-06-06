package com.blanke.xzhihuday.base;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.blanke.xzhihuday.core.main.MainActivity;
import com.blanke.xzhihuday.core.main.di.MainComponent;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by blanke on 16-6-6.
 */
public abstract class BaseContentFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends BaseMvpLceViewStateFragment<CV, M, V, P> {
    protected MainActivity mainActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    /**
     * 设置fragment的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            lazyLoad();
        }
    }

    //  可见才加载
    protected abstract void lazyLoad();

    //双击tab标签的事件
    public abstract void doubleClickTab();

    //点击fab的事件
    public abstract void clickFab(FloatingActionButton fab);

    //初始化fab
    public abstract void initFab(FloatingActionButton fab);

}
