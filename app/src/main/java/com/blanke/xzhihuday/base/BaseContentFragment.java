package com.blanke.xzhihuday.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import com.blanke.xzhihuday.ui.main.MainActivity;

/**
 * Created by blanke on 16-6-6.
 * 加入viewpager fragment的懒加载
 */
public abstract class BaseContentFragment extends BaseFragment {
    private boolean isCreate = false;

    protected MainActivity getMainActivity() {
        if (_mActivity != null) {
            return (MainActivity) _mActivity;
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isCreate = true;
        onLazyLoad();

    }

    private void onLazyLoad() {
        if (getUserVisibleHint() && isCreate) {
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

    //  可见才加载
    protected abstract void lazyLoad();

    //双击tab标签的事件
    public abstract void doubleClickTab();

    //点击fab的事件
    public abstract void clickFab(FloatingActionButton fab);

    //初始化fab
    public abstract void initFab(FloatingActionButton fab);

}
