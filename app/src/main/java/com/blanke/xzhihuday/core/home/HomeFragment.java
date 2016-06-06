package com.blanke.xzhihuday.core.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blanke.xzhihuday.R;
import com.blanke.xzhihuday.base.BaseContentFragment;
import com.blanke.xzhihuday.bean.ArticleBean;
import com.blanke.xzhihuday.bean.LatestResponse;
import com.blanke.xzhihuday.core.home.persenter.HomePersenter;
import com.blanke.xzhihuday.core.home.persenter.HomePersenterImpl;
import com.blanke.xzhihuday.core.home.view.HomeView;
import com.neu.refresh.NeuSwipeRefreshLayout;
import com.socks.library.KLog;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by blanke on 16-6-6.
 */
public class HomeFragment extends
        BaseContentFragment<LinearLayout, LatestResponse, HomeView, HomePersenter>
        implements HomeView {
    @Bind(R.id.home_recyclerview)
    RecyclerView mHomeRecyclerview;
    @Bind(R.id.home_swipelayout)
    NeuSwipeRefreshLayout mHomeSwipelayout;
    @Bind(R.id.contentView)
    LinearLayout mContentView;
    @Bind(R.id.loadingView)
    MaterialProgressBar mLoadingView;
    @Bind(R.id.errorView)
    TextView mErrorView;

    private Date currentDate;
    private SuperAdapter<ArticleBean> mAdapter;
    @Inject
    HomePersenterImpl mHomePersenter;
    private LatestResponse mLatestResponse;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger2();
    }

    @Override
    protected void initView() {
        mHomeRecyclerview.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false));
        mHomeRecyclerview.setItemAnimator(new FadeInAnimator());
    }

    @Override
    protected void initData() {
        currentDate = new Date();
        mAdapter = new SuperAdapter<ArticleBean>(mainActivity, null, R.layout.item_article) {
            @Override
            public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ArticleBean item) {
                holder.setText(R.id.item_article_title, item.getTitle());
            }
        };
        mHomeRecyclerview.setAdapter(new SlideInBottomAnimationAdapter(mAdapter));
    }

    private void nextDate() {
    }

    private void initDagger2() {
        mainActivity.getMainComponent()
                .provideContentComponent().injectHome(this);
//        KLog.d("mHomePersenter  " + (mHomePersenter == null));
    }

    @Override
    protected void lazyLoad() {
        KLog.d();
        loadData(false);
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


    @Override
    public HomePersenter createPresenter() {
        return mHomePersenter;
    }

    @Override
    public void setData(LatestResponse data) {
        mLatestResponse = data;
        mAdapter.addAll(data.getStories());
        setFirstFinish();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mHomePersenter.getLatestData(pullToRefresh, currentDate);
    }
}
