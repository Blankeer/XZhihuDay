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
    private Bundle mSavedInstanceState;

    protected MainActivity getMainActivity() {
        if (_mActivity != null) {
            return (MainActivity) _mActivity;
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        setRetainInstance(true);
        isCreate = true;
        onLazyLoad(savedInstanceState);
    }

    private void onLazyLoad(Bundle savedInstanceState) {
        if (getUserVisibleHint() && isCreate) {
            lazyLoad(savedInstanceState);
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
        onLazyLoad(mSavedInstanceState);
    }

    //  可见才加载
    protected abstract void lazyLoad(Bundle savedInstanceState);

    //双击tab标签的事件
    public abstract void doubleClickTab();

    //点击fab的事件
    public abstract void clickFab(FloatingActionButton fab);

    //初始化fab
    public abstract void initFab(FloatingActionButton fab);

}
