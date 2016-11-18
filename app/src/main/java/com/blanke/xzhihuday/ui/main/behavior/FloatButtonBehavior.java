package com.blanke.xzhihuday.ui.main.behavior;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

/**
 * Created by blanke on 16-11-16.
 */

public class FloatButtonBehavior extends BaseDependenToolBarBehavior<FloatingActionButton> {

    public FloatButtonBehavior() {
    }

    public FloatButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    void childChange(FloatingActionButton child, float fraction) {
        child.setScaleX(child.getScaleX() - fraction);
        child.setScaleY(child.getScaleY() - fraction);
    }
}
