package com.blanke.xzhihuday.ui.home.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.blanke.data.bean.ArticleBean;
import com.blanke.data.bean.http.LatestResponse;
import com.blanke.data.utils.DateUtils;
import com.blanke.xzhihuday.R;
import com.blanke.xzhihuday.base.MvpLceViewStateFrameLayout;
import com.blanke.xzhihuday.ui.home.persenter.HomePersenter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.SerializeableLceViewState;
import com.orhanobut.logger.Logger;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by blanke on 16-11-17.
 */

public class HomeFrameLayout
        extends MvpLceViewStateFrameLayout<SwipeRefreshLayout, LatestResponse, HomeView, HomePersenter>
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
    private BaseQuickAdapter mAdapter;
    HomePersenter mHomePersenter;
    private LatestResponse mLatestResponse;
    private int pageSize = 10;

    public HomeFrameLayout(Context context) {
        super(context);
    }

    public HomeFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HomeFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 手动注入,注入需要的实例之后再开始加载
     *
     * @param homePersenter
     */
    public void inject(HomePersenter homePersenter) {
        this.mHomePersenter = homePersenter;
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void initView() {
        initBanner();
        initRecyclerView();
        mHomeSwipelayout.setOnRefreshListener(() -> {
            currentDate = startDate;
            loadData(true);
        });
    }

    private void initRecyclerView() {
        mHomeRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_article, null) {
            @Override
            protected void convert(BaseViewHolder holder, ArticleBean item) {
                holder.setText(R.id.item_article_title, item.getTitle());
                ImageView iv = holder.getView(R.id.item_img);
                Glide.with(getContext()).load(item.getImages().get(0)).into(iv);
            }
        };
        mHomeRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Logger.d("");
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.addHeaderView(mConvenientBanner);
        mAdapter.isFirstOnly(true);
        mAdapter.openLoadMore(pageSize);
        mAdapter.setOnLoadMoreListener(() -> loadData(true));
        FrameLayout layout = new FrameLayout(getContext());
        layout.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setPadding(10, 10, 10, 10);
        View.inflate(getContext(), R.layout.view_loading, layout);
        mAdapter.setLoadingView(layout);
        mHomeRecyclerview.setAdapter(mAdapter);
        mHomeRecyclerview.setHasFixedSize(true);
    }

    private void initBanner() {
        mConvenientBanner = new ConvenientBanner(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, 500);
//        params.setMargins(5, 5, 5, 5);
        mConvenientBanner.setLayoutParams(params);
        mConvenientBanner.setCanLoop(true);
    }

    private void showBanner() {
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

    protected void initData() {
        startDate = currentDate = DateUtils.nextDate(new Date());
    }

    //切换到昨天的日期
    private void lastDate() {
        currentDate = DateUtils.lastDate(currentDate);
    }


    @Override
    public LceViewState<LatestResponse, HomeView> createViewState() {
        return new SerializeableLceViewState<>();
    }

    @Override
    public LatestResponse getData() {
        return mLatestResponse;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "error";
    }

    @Override
    public HomePersenter createPresenter() {
        return mHomePersenter;
    }


    private void stopRefre() {
        mHomeSwipelayout.setRefreshing(false);
    }


    @Override
    public void setData(LatestResponse data) {
        mLatestResponse = data;
        if (currentDate == startDate) {
            mAdapter.setNewData(data.getStories());
            showBanner();
        } else {
            mAdapter.addData(data.getStories());
        }
        lastDate();
        stopRefre();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mHomePersenter.getLatestData(pullToRefresh, currentDate);
    }

    //轮播viewholder
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
}
