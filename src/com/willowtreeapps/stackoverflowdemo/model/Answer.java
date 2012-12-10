package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * User: derek Date: 12/10/12 Time: 5:49 PM
 */
public class Answer {

    @SerializedName("answer_id") public int answerId;
    @SerializedName("body") public String body;
    @SerializedName("comments") public ArrayList<Comment> comments;
    @SerializedName("community_owned_date") public long communityOwnedDate;
    @SerializedName("creation_date") public long creationDate;
    @SerializedName("down_vote_count") public int downVoteCount;
    @SerializedName("is_accepted") public boolean isAccepted;
    @SerializedName("last_activity_date") public long lastActivityDate;
    @SerializedName("last_edit_date") public long lastEditDate;
    @SerializedName("link") public String link;
    @SerializedName("locked_date") public long lockedDate;
    @SerializedName("owner") public ShallowUser owner;
    @SerializedName("question_id") public int questionId;
    @SerializedName("score") public int score;
    @SerializedName("tags") public ArrayList<String> tags;
    @SerializedName("title") public String title;
    @SerializedName("up_vote_count") public int upVoteCount;
}
