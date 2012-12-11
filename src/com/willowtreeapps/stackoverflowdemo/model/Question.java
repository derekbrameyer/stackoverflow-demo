package com.willowtreeapps.stackoverflowdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * User: derek Date: 12/10/12 Time: 5:49 PM
 */
public class Question {

    @SerializedName("accepted_answer_id") public int acceptedAnswerId;
    @SerializedName("answer_count") public int answerCount;
    @SerializedName("answers") public ArrayList<Answer> answers;
    @SerializedName("body") public String body;
    @SerializedName("bounty_amount") public int bountyAmount;
    @SerializedName("bounty_closes_date") public long bountyClosesDate;
    @SerializedName("close_vote_count") public int closeVoteCount;
    @SerializedName("closed_date") public long closedDate;
    @SerializedName("closed_reason") public String closedReason;
    @SerializedName("comments") public ArrayList<Comment> comments;
    @SerializedName("community_owned_date") public long communityOwnedDate;
    @SerializedName("creation_date") public long creationDate;
    @SerializedName("delete_vote_count") public int deleteVoteCount;
    @SerializedName("down_vote_count") public int downVoteCount;
    @SerializedName("favorite_count") public int favoriteCount;
    @SerializedName("is_answered") public boolean isAnswered;
    @SerializedName("last_activity_date") public long lastActivityDate;
    @SerializedName("last_edit_date") public long lastEditDate;
    @SerializedName("link") public String link;
    @SerializedName("locked_date") public long lockedDate;
    @SerializedName("migrated_from") public MigrationInfo migratedFrom;
    @SerializedName("migrated_to") public MigrationInfo migratedTo;
    @SerializedName("notice") public Notice notice;
    @SerializedName("owner") public ShallowUser owner;
    @SerializedName("protected_date") public long protectedDate;
    @SerializedName("question_id") public int questionId;
    @SerializedName("reopen_vote_count") public int reopenVoteCount;
    @SerializedName("score") public int score;
    @SerializedName("tags") public ArrayList<String> tags;
    @SerializedName("title") public String title;
    @SerializedName("up_vote_count") public int upVoteCount;
    @SerializedName("view_count") public int viewCount;

    public static class Response {

        @SerializedName("items") public ArrayList<Question> items;
        @SerializedName("quota_remaining") public int quotaRemaining;
        @SerializedName("quota_max") public int quotaMax;
        @SerializedName("has_more") public boolean hasMore;

    }

}
