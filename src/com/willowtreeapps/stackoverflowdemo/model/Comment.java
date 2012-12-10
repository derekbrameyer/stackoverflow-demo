package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 12/10/12 Time: 5:51 PM
 */
public class Comment {

    @SerializedName("body") public String body;
    @SerializedName("body_markdown") public String bodyMarkdown;
    @SerializedName("comment_id") public int commentId;
    @SerializedName("creation_date") public long creationDate;
    @SerializedName("edited") public boolean edited;
    @SerializedName("link") public String link;
    @SerializedName("owner") public ShallowUser owner;
    @SerializedName("post_id") public int postId;
    //@SerializedName("post_type") public String postType;
    @SerializedName("reply_to_user") public ShallowUser replyToUser;
    @SerializedName("score") public int score;
}
