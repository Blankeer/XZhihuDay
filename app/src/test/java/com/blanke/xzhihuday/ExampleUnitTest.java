package com.blanke.xzhihuday;

import com.blanke.data.utils.DateUtils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Date now = new Date();
        String s = DateUtils.date2yyyyMMdd2(now);
        System.out.println(s);
    }
}