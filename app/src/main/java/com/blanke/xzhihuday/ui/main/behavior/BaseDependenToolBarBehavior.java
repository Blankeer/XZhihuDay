package com.blanke.xzhihuday.ui.main.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by blanke on 16-11-16.
 */

public abstract class BaseDependenToolBarBehavior<V extends View>
        extends CoordinatorLayout.Behavior<V> {
    private float lastY = Float.MAX_VALUE;

    public BaseDependenToolBarBehavior() {
    }

    public BaseDependenToolBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
        float y = dependency.getY();
        boolean re = false;
        if (lastY == Float.MAX_VALUE) {
            re = false;
        } else {
            float dy = y - lastY;
            float fraction = dy / dependency.getHeight();
            childChange(child, fraction);
            re = true;
        }
        lastY = y;
        return re;
    }

    abstract void childChange(V child, float fraction);
}
