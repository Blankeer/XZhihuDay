package com.blanke.xzhihuday.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by blanke on 16-5-30.
 */
public class BaseFragment extends SupportFragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        setRetainInstance(true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
