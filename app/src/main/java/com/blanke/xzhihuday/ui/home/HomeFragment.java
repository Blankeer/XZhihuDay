package com.blanke.xzhihuday.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blanke.xzhihuday.R;
import com.blanke.xzhihuday.base.BaseContentFragment;
import com.blanke.xzhihuday.ui.home.persenter.HomePersenterImpl;
import com.blanke.xzhihuday.ui.home.view.HomeFrameLayout;

import javax.inject.Inject;

/**
 * Created by blanke on 16-6-6.
 */
public class HomeFragment extends BaseContentFragment {
    @Inject
    HomePersenterImpl mHomePersenter;
    private HomeFrameLayout mHomeFrameLayout;
    private ViewGroup rootViewGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootViewGroup = (ViewGroup) view;
        initDagger2();
    }

    private void initDagger2() {
        if (getMainActivity() != null) {
            getMainActivity().getMainComponent()
                    .provideContentComponent().injectHome(this);
        }
    }

    @Override
    protected void lazyLoad() {
        if (mHomeFrameLayout == null) {
            mHomeFrameLayout = (HomeFrameLayout) View
                    .inflate(getMainActivity(), R.layout.view_lce_datalist, null);
            mHomeFrameLayout.inject(mHomePersenter);
            rootViewGroup.addView(mHomeFrameLayout);
        }
    }

    @Override
    public void doubleClickTab() {

    }

    @Override
    public void clickFab(FloatingActionButton fab) {

    }

    @Override
    public void initFab(FloatingActionButton fab) {

    }
}
