package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 12/10/12 Time: 6:06 PM
 */
public class Styling {

    @SerializedName("link_color") public String linkColor;
    @SerializedName("tag_background_color") public String tagBackgroundColor;
    @SerializedName("tag_foreground_color") public String tagForegroundColor;
}
