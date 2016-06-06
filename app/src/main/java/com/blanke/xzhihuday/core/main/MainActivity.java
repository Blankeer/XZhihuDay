package com.blanke.xzhihuday.core.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.blanke.xzhihuday.R;
import com.blanke.xzhihuday.app.XApplication;
import com.blanke.xzhihuday.base.BaseActivity;
import com.blanke.xzhihuday.base.BaseContentFragment;
import com.blanke.xzhihuday.bean.ArticleBean;
import com.blanke.xzhihuday.config.ProjectConfig;
import com.blanke.xzhihuday.core.home.HomeFragment;
import com.blanke.xzhihuday.core.main.di.DaggerMainComponent;
import com.blanke.xzhihuday.core.main.di.MainComponent;
import com.blanke.xzhihuday.repository.factory.ArticleDataFactory;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.socks.library.KLog;

import javax.inject.Inject;

import butterknife.Bind;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    @Inject
    ArticleDataFactory mArticleDataFactory;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.main_coordinatorlayout)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private BottomBar mBottomBar;
    private int tabSize = ProjectConfig.TAB_SIZE;
    private BaseContentFragment[] baseContentFragments;
    private MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
        initDagger2();
    }

    private void test() {
        mArticleDataFactory.getArticle(8387524)
                .subscribe(new Action1<ArticleBean>() {
                    @Override
                    public void call(ArticleBean articleBean) {
                        KLog.d(articleBean);
                    }
                });
    }

    private void initDagger2() {
        mainComponent = DaggerMainComponent.builder()
                .appComponent(XApplication.getAppComponent())
                .build();
        mainComponent.inject(this);
//        KLog.d("mArticleDataFactory  " + (mArticleDataFactory == null));
    }

    private void initView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        baseContentFragments = new BaseContentFragment[tabSize];
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
                mBottomBar.selectTabAtPosition(position, true);
                BaseContentFragment fragment = baseContentFragments[position];
                fragment.initFab(fab);

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentFragment().clickFab(fab);
            }
        });
        initBottomBar(savedInstanceState);
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

    private void initBottomBar(Bundle savedInstanceState) {
        mBottomBar = BottomBar.attachShy(mCoordinatorLayout, mViewPager, savedInstanceState);
        mBottomBar.setItems(R.menu.main_bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                mViewPager.setCurrentItem(getTabIndex(menuItemId));
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                getCurrentFragment().doubleClickTab();
            }
        });
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, 0xFF5D4037);
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF9800");
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showTab() {
        if (mBottomBar != null) {
            mBottomBar.show();
        }
    }

    public void hideTab() {
        if (mBottomBar != null) {
            mBottomBar.hide();
        }
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
