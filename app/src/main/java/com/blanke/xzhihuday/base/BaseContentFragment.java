package com.blanke.xzhihuday.base;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.blanke.xzhihuday.ui.main.MainActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by blanke on 16-6-6.
 */
public abstract class BaseContentFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends BaseMvpLceFragment<CV, M, V, P> {
    protected MainActivity mainActivity;
    protected boolean isFirstFinish = false;
    private boolean isStart = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        isStart = true;
        onLazyLoad();
    }

    private void onLazyLoad() {
        if (getUserVisibleHint() && !isFirstFinish && isStart) {
            lazyLoad();
        }
    }

    /**
     * 设置fragment的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onLazyLoad();
    }

    /**
     * 可见懒加载，第一次完成，之后再切换不会触发事件
     */
    public void setFirstFinish() {
        isFirstFinish = true;
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
