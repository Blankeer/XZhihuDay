package com.blanke.xzhihuday.ui.home.di;

import com.blanke.xzhihuday.di.scopes.PerActivity;
import com.blanke.xzhihuday.ui.home.HomeFragment;
import com.blanke.xzhihuday.ui.like.LikeFragment;

import dagger.Subcomponent;

/**
 * Created by blanke on 16-6-6.
 */
@Subcomponent
@PerActivity
public interface ContentComponent {
    void injectHome(HomeFragment homeFragment);
    void injectLike(LikeFragment likeFragment);

}
