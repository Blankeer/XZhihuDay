package com.blanke.xzhihuday.config;

import android.content.Context;

import io.appflate.restmock.MatchableCall;
import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.RESTMockServerStarter;
import io.appflate.restmock.android.AndroidAssetsFileParser;
import io.appflate.restmock.android.AndroidLogger;
import io.appflate.restmock.utils.RequestMatchers;

/**
 * Created by blanke on 16-5-31.
 */
public class RESTMockConfig {
    public static void start(Context context){
        RESTMockServerStarter.startSync(
                new AndroidAssetsFileParser(context),
                new AndroidLogger());//mock rest
        MatchableCall mockServer = RESTMockServer.whenGET(
                RequestMatchers.pathContains("Android"));
        mockServer.thenReturnFile(200, ".json");
        ProjectConfig.setTestBaseApiUrl(RESTMockServer.getUrl());
        ProjectConfig.setIsTestApp(true);
    }
}
