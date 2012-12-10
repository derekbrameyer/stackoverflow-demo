package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 12/10/12 Time: 6:06 PM
 */
public class RelatedSite {

    @SerializedName("api_site_paramter") public String apiSiteParameter;
    @SerializedName("name") public String name;
    @SerializedName("relation") public String relation;
    @SerializedName("site_url") public String siteUrl;
}
