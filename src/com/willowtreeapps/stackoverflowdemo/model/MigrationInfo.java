package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

import android.test.SingleLaunchActivityTestCase;

/**
 * User: derek Date: 12/10/12 Time: 5:53 PM
 */
public class MigrationInfo {

    @SerializedName("on_date") public long onDate;
    @SerializedName("other_site") public Site otherSite;
    @SerializedName("question_id") public int questionId;

}
