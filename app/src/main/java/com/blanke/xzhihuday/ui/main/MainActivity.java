package com.blanke.xzhihuday.ui.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.blanke.xzhihuday.R;
import com.blanke.xzhihuday.app.XApplication;
import com.blanke.xzhihuday.base.BaseActivity;
import com.blanke.xzhihuday.base.BaseContentFragment;
import com.blanke.xzhihuday.config.ProjectConfig;
import com.blanke.xzhihuday.ui.home.HomeFragment;
import com.blanke.xzhihuday.ui.main.di.DaggerMainComponent;
import com.blanke.xzhihuday.ui.main.di.MainComponent;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

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
    BottomBar mBottomBar;

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
                mBottomBar.selectTabAtPosition(position);
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
//        initBottomBar(savedInstanceState);
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
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int position) {
                mViewPager.setCurrentItem(position);
            }

//            @Override
//            public void onTabReSelected(int position) {
//                baseContentFragments[position].doubleClickTab();
//            }
        });
//        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
//        mBottomBar.mapColorForTab(1, 0xFF5D4037);
//        mBottomBar.mapColorForTab(2, "#7B1FA2");
//        mBottomBar.mapColorForTab(3, "#FF9800");
//        mBottomBar.getBarSize(new OnSizeDeterminedListener() {
//            @Override
//            public void onSizeReady(int size) {
//                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) fab.getLayoutParams();
//                lp.setMargins(lp.leftMargin, lp.topMargin, lp.rightMargin, size + 16);
//            }
//        });
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
        mBottomBar.onSaveInstanceState();
//        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return false;
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
//            mBottomBar.show
        }
    }

    public void hideTab() {
        if (mBottomBar != null) {
//            mBottomBar.hide();
        }
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
