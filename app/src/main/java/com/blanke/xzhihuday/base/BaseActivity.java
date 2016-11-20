package com.blanke.xzhihuday.base;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class BaseActivity extends SupportActivity {


    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
