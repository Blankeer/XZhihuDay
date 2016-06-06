package com.blanke.xzhihuday.config;

/**
 * Created by blanke on 16-5-27.
 */
public class ProjectConfig {
    private final static String BASE_URL = "http://news-at.zhihu.com/";
    private final static String BASE_API_URL = BASE_URL + "api/4/";
    public static final int PAGE_SIZE = 15;
    private static String TEST_BASE_API_URL = null;
    public static final String DATE_FORMAT = "yyyyMMdd";
    private static boolean isTestJunit = false;//测试是否在Junit
    private static boolean isTestApp = false;//测试是否是真机APP
    private static boolean isDebug = false;
    public final static int TAB_SIZE = 4;

    public static boolean isTest() {
        return isTestApp || isTestApp;
    }

    public static boolean isTestApp() {
        return isTestApp;
    }

    public static void setIsTestApp(boolean isTestApp) {
        ProjectConfig.isTestApp = isTestApp;
        ProjectConfig.isTestJunit = !isTestApp;
    }

    public static boolean isTestJunit() {
        return isTestJunit;
    }

    public static void setIsTestJunit(boolean isTestJunit) {
        ProjectConfig.isTestJunit = isTestJunit;
        ProjectConfig.isTestApp = !isTestJunit;
    }

    public static String getTestBaseApiUrl() {
        return TEST_BASE_API_URL;
    }

    public static void setTestBaseApiUrl(String testBaseApiUrl) {
        TEST_BASE_API_URL = testBaseApiUrl;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        ProjectConfig.isDebug = isDebug;
    }

    public static String getBaseApiUrl() {
        return isTest() ? TEST_BASE_API_URL : BASE_API_URL;
    }
}
