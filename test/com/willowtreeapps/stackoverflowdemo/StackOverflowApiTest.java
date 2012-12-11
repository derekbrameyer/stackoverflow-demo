package com.willowtreeapps.stackoverflowdemo;

import com.google.inject.Injector;

import com.willowtreeapps.stackoverflowdemo.model.Question;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import roboguice.RoboGuice;

/**
 * User: derek Date: 12/11/12 Time: 9:37 AM
 */
@RunWith(RobolectricTestRunner.class)
public class StackOverflowApiTest {

    StackOverflowApi api;

    @Before
    public void setUp() {
        if (api == null) {
            Injector i = RoboGuice.getBaseApplicationInjector(Robolectric.application);
            api = i.getInstance(StackOverflowApi.class);
        }
    }

    @Test
    public void testGetQuestions() {
        try {
            Question.Response response = api.getQuestions("android actionbar", 1);
            Assert.assertNotNull(response);
            System.out.print(api.getGson().toJson(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
