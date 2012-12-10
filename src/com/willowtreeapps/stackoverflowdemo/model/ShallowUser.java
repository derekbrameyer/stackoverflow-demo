package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 12/10/12 Time: 5:55 PM
 */
public class ShallowUser {

    @SerializedName("accept_rate") public int acceptRate;
    @SerializedName("display_name") public String displayName;
    @SerializedName("link") public String link;
    @SerializedName("profile_image") public String profileImage;
    @SerializedName("reputation") public int reputation;
    @SerializedName("user_id") public int userId;
    @SerializedName("user_type") public String userType;
}
