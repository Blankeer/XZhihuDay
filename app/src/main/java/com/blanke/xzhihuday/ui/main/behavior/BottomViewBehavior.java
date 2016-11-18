package com.blanke.xzhihuday.ui.main.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by blanke on 16-11-16.
 */

public class BottomViewBehavior extends BaseDependenToolBarBehavior<View> {
    public BottomViewBehavior() {
    }

    public BottomViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    void childChange(View child, float fraction) {
        child.setY(child.getY() - fraction * child.getHeight());
    }
}
