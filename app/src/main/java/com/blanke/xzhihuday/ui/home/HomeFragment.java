package com.blanke.xzhihuday.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blanke.xzhihuday.R;
import com.blanke.xzhihuday.base.BaseFragment;
import com.blanke.xzhihuday.ui.home.persenter.HomePersenterImpl;
import com.blanke.xzhihuday.ui.home.view.HomeFrameLayout;
import com.blanke.xzhihuday.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;


/**
 * Created by blanke on 16-6-6.
 */
public class HomeFragment extends BaseFragment {

    @Inject
    HomePersenterImpl mHomePersenter;

    private MainActivity mainActivity;
    private HomeFrameLayout mHomeFrameLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
        Logger.e("onAttach " + this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e("onDestroyView " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e("onDestroy " + this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        initDagger2();
        mHomeFrameLayout = (HomeFrameLayout) view.findViewById(R.id.home_framelayout);
        mHomeFrameLayout.inject(mHomePersenter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initDagger2() {
        mainActivity.getMainComponent()
                .provideContentComponent().injectHome(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
}
