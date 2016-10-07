package com.blanke.xzhihuday.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.blanke.data.bean.ArticleBean;
import com.blanke.data.bean.http.LatestResponse;
import com.blanke.data.utils.DateUtils;
import com.blanke.xzhihuday.R;
import com.blanke.xzhihuday.base.BaseContentFragment;
import com.blanke.xzhihuday.ui.home.persenter.HomePersenter;
import com.blanke.xzhihuday.ui.home.persenter.HomePersenterImpl;
import com.blanke.xzhihuday.ui.home.view.HomeView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.paginate.Paginate;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by blanke on 16-6-6.
 */
public class HomeFragment extends
        BaseContentFragment<SwipeRefreshLayout, LatestResponse, HomeView, HomePersenter>
        implements HomeView {
    @Bind(R.id.home_recyclerview)
    RecyclerView mHomeRecyclerview;
    @Bind(R.id.contentView)
    SwipeRefreshLayout mHomeSwipelayout;
    @Bind(R.id.loadingView)
    MaterialProgressBar mLoadingView;
    @Bind(R.id.errorView)
    TextView mErrorView;
    ConvenientBanner mConvenientBanner;

    private Date currentDate, startDate;
    private BaseQuickAdapter<ArticleBean> mAdapter;
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
//        mHomeRecyclerview.setLayoutManager(new ScrollLinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false));
        mHomeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mHomeRecyclerview.setItemAnimator(new FadeInAnimator());
        mHomeRecyclerview.setNestedScrollingEnabled(false);
        mHomeRecyclerview.setHasFixedSize(false);
//        mHomeSwipelayout.setDirection(SwipeRefreshLayoutDirection.BOTH);
//        mHomeSwipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh(SwipeRefreshLayoutDirection SwipeRefreshLayoutDirection) {
//                if (SwipeRefreshLayoutDirection == SwipeRefreshLayoutDirection.TOP) {
//                    currentDate = startDate;
//                }
//                loadData(true);
//            }
//        });

        mConvenientBanner = new ConvenientBanner(getContext());
        mConvenientBanner.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, 300));
        mConvenientBanner.setCanLoop(true);
    }

    private void initBanner() {
        mConvenientBanner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, mLatestResponse.getTop_stories())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }

    public static class NetworkImageHolderView implements Holder<ArticleBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, ArticleBean item) {
            Glide.with(context).load(item.getImage()).into(imageView);
        }
    }

    @Override
    protected void initData() {
        startDate = currentDate = DateUtils.nextDate(new Date());
        mAdapter = new BaseQuickAdapter<ArticleBean>(R.layout.item_article, null) {
            @Override
            protected void convert(BaseViewHolder holder, ArticleBean item) {
                holder.setText(R.id.item_article_title, item.getTitle());
                ImageView iv = holder.getView(R.id.item_img);
                Glide.with(mainActivity)
                        .load(item.getImages().get(0))
                        .into(iv);
            }
        };
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.addHeaderView(mConvenientBanner);
        mHomeRecyclerview.setAdapter(new SlideInBottomAnimationAdapter(mAdapter));
        Paginate.Callbacks callbacks=new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public boolean isLoading() {
                return false;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        };
//        Paginate.with(mHomeRecyclerview, callbacks)
//                .setLoadingTriggerThreshold(2)
//                .addLoadingListItem(true)
//                .build();
    }

    private void lastDate() {
        currentDate = DateUtils.lastDate(currentDate);
    }

    private void initDagger2() {
        mainActivity.getMainComponent()
                .provideContentComponent().injectHome(this);
//        KLog.d("mHomePersenter  " + (mHomePersenter == null));
    }

    @Override
    protected void lazyLoad() {
//        Logger.d("");
        loadData(false);
    }

    @Override
    public void doubleClickTab() {

    }

    @Override
    public void clickFab(FloatingActionButton fab) {
        mHomeRecyclerview.smoothScrollToPosition(0);
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
        if (currentDate == startDate) {
            mAdapter.setNewData(data.getStories());
            initBanner();
        } else {
            mAdapter.addData(data.getStories());
        }
        setFirstFinish();
        lastDate();
        stopRefre();
    }

    private void stopRefre() {
//        mHomeSwipelayout.setRefreshing(false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mHomePersenter.getLatestData(pullToRefresh, currentDate);
    }
}