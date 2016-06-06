package com.blanke.xzhihuday.core.home.di;

import com.blanke.xzhihuday.app.di.scopes.PerActivity;
import com.blanke.xzhihuday.core.home.HomeFragment;
import com.blanke.xzhihuday.core.like.LikeFragment;

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
