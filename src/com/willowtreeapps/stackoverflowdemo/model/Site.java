package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * User: derek Date: 12/10/12 Time: 6:01 PM
 */
public class Site {

    @SerializedName("aliases") public ArrayList<String> aliases;
    @SerializedName("api_site_parameter") public String apiSiteParameter;
    @SerializedName("audience") public String audience;
    @SerializedName("closed_beta_date") public long closedBetaDate;
    @SerializedName("favicon_url") public String faviconUrl;
    @SerializedName("high_resolution_icon_url") public String highResolutionIconUrl;
    @SerializedName("icon_url") public String iconUrl;
    @SerializedName("launch_date") public long launchDate;
    @SerializedName("logo_url") public String logoUrl;
    // @SerializedName("markdown_extensions") public ArrayList<String> markdownExtensions;
    @SerializedName("name") public String name;
    @SerializedName("open_beta_date") public long openBetaDate;
    @SerializedName("related_sites") public ArrayList<RelatedSite> relatedSites;
    @SerializedName("site_state") public String siteState;
    @SerializedName("site_type") public String siteType;
    @SerializedName("site_url") public String siteUrl;
    @SerializedName("styling") public Styling styling;
    @SerializedName("twitter_account") public String twitterAccount;
}
