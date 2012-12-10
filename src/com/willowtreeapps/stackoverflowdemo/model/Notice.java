package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 12/10/12 Time: 5:53 PM
 */
public class Notice {

    @SerializedName("body") public String body;
    @SerializedName("creation_date") public long creationDate;
    @SerializedName("owner_user_id") public int ownerUserId;

}
