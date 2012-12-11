package com.willowtreeapps.stackoverflowdemo;

import com.google.gson.Gson;
import com.google.inject.Inject;

import com.integralblue.httpresponsecache.HttpResponseCache;
import com.willowtreeapps.stackoverflowdemo.model.Question;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import oak.http.OakConnection;
import oak.http.OakHttpTool;

/**
 * User: derek Date: 12/11/12 Time: 9:37 AM
 */
public class StackOverflowApi {

    private static final String SEARCH_URL
            = "https://api.stackexchange.com/2.1/search?page=%s&order=desc&sort=activity&intitle=%s&site=stackoverflow";

    private OakHttpTool mOakHttpTool;
    private static Gson mGson;

    @Inject
    public StackOverflowApi(OakHttpTool oakHttpTool, Context context) {
        final long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
        final File httpCacheDir = new File(context.getCacheDir(), "http");
        try {
            HttpResponseCache.install(httpCacheDir, httpCacheSize);
        } catch (IOException e) {
            Log.e(getClass().getCanonicalName(), "Failed to set up ResponseCache");
        }
        mOakHttpTool = oakHttpTool;
        Map<String, String> defaultHeaders = new HashMap<String, String>();
        defaultHeaders.put("Accept-Encoding", "gzip");
        mOakHttpTool.setDefaultHeaders(defaultHeaders);
        mOakHttpTool.setCertValidationDisabled(true);
    }

    public Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    private <T> T readJsonToObject(String url, Type type) throws IOException {
        OakConnection oakConnection = mOakHttpTool.get(url).withCache(30);
        BufferedReader bufferedReader = oakConnection.getBufferedResponseReader();
        T response = getGson().fromJson(bufferedReader, type);
        oakConnection.disconnect();
        return response;
    }

    public Question.Response getQuestions(String intitle, int page) throws IOException {
        Question.Response response = readJsonToObject(
                String.format(SEARCH_URL, URLEncoder.encode(Integer.toString(page), "UTF-8"),
                        URLEncoder.encode(intitle, "UTF-8")),
                Question.Response.class);
        return response;
    }

}
