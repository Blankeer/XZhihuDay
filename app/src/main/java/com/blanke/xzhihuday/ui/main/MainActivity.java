package com.blanke.xzhihuday.ui.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.blanke.xzhihuday.R;
import com.blanke.xzhihuday.app.XApplication;
import com.blanke.xzhihuday.base.BaseActivity;
import com.blanke.xzhihuday.base.BaseContentFragment;
import com.blanke.xzhihuday.config.ProjectConfig;
import com.blanke.xzhihuday.ui.home.HomeFragment;
import com.blanke.xzhihuday.ui.main.di.DaggerMainComponent;
import com.blanke.xzhihuday.ui.main.di.MainComponent;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.main_coordinatorlayout)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_bottomBar)
    BottomNavigationView mBottomBar;

    private int tabSize = ProjectConfig.TAB_SIZE;
    private BaseContentFragment[] baseContentFragments;
    private MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDagger2();
    }

    private void initDagger2() {
        mainComponent = DaggerMainComponent.builder()
                .appComponent(XApplication.getAppComponent())
                .build();
        mainComponent.inject(this);
//        KLog.d("mArticleDataFactory  " + (mArticleDataFactory == null));
    }

    private void initView() {
        setSupportActionBar(toolbar);
        baseContentFragments = new BaseContentFragment[tabSize];
        mViewPager.setOffscreenPageLimit(tabSize);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (baseContentFragments[position] == null) {
                    baseContentFragments[position] = newFragment(position);
                }
                return baseContentFragments[position];
            }

            @Override
            public int getCount() {
                return tabSize;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < baseContentFragments.length; i++) {
                    if (i != position) {
                        mBottomBar.getMenu().getItem(i).setChecked(false);
                    }
                }
                mBottomBar.getMenu().getItem(position).setChecked(true);
                BaseContentFragment fragment = baseContentFragments[position];
                fragment.initFab(fab);
            }
        });
//        mViewPager.setOnTouchListener((v, event) -> true);
        fab.setOnClickListener(v -> getCurrentFragment().clickFab(fab));
        initBottomBar();
    }

    //获得当前选中的fragment
    private BaseContentFragment getCurrentFragment() {
        return baseContentFragments[mViewPager.getCurrentItem()];
    }

    /**
     * new一个对应位置的fragment
     *
     * @param position
     * @return
     */
    private BaseContentFragment newFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
        }
        return new HomeFragment();
    }

    private void initBottomBar() {
        mBottomBar.setOnNavigationItemSelectedListener(item -> {
            mViewPager.setCurrentItem(getTabIndex(item.getItemId()));
            return false;
        });
    }

    /**
     * 根据tab item ID获得index
     *
     * @param id
     * @return
     */
    private int getTabIndex(int id) {
        int position = 0;
        switch (id) {
            case R.id.main_bottombar_home:
                position = 0;
                break;
            case R.id.main_bottombar_favorite:
                position = 2;
                break;
            case R.id.main_bottombar_column:
                position = 1;
                break;
            case R.id.main_bottombar_date:
                position = 3;
                break;
        }
        return position;
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
