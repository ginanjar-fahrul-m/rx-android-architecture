package com.tehmou.rxbookapp.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by ttuo on 23/06/14.
 */
@DatabaseTable(tableName = "statuses")
public class TwitterStatus {
    @DatabaseField
    Date createdAt;

    @DatabaseField
    long id;

    @DatabaseField
    String text;

    @DatabaseField
    String source;

    @DatabaseField
    boolean isTruncated;

    @DatabaseField
    long inReplyToStatusId;

    @DatabaseField
    long inReplyToUserId;

    @DatabaseField
    String inReplyToScreenName;

    @DatabaseField
    String geoLocation;

    @DatabaseField
    String place;

    @DatabaseField
    boolean isFavorited;

    @DatabaseField
    boolean isRetweeted;

    @DatabaseField
    int favoriteCount;

    @DatabaseField
    long user;

    @DatabaseField
    boolean isRetweet;

    @DatabaseField
    long retweetedStatus;

    @DatabaseField
    int getRetweetCount;

    @DatabaseField
    boolean isRetweetedByMe;

    @DatabaseField
    long getCurrentUserRetweetId;

    @DatabaseField
    boolean isPossiblySensitive;

    @DatabaseField
    String getIsoLanguageCode;
}
