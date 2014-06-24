package com.tehmou.rxbookapp.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import twitter4j.Status;

/**
 * Created by ttuo on 23/06/14.
 */
@DatabaseTable(tableName = "statuses")
public class TwitterStatus {
    @DatabaseField
    Date createdAt;

    @DatabaseField(id = true)
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

    public TwitterStatus() { }

    public TwitterStatus(Status status) {
        id = status.getId();
        createdAt = status.getCreatedAt();
        text = status.getText();
        source = status.getSource();
        isTruncated = status.isTruncated();
        inReplyToStatusId = status.getInReplyToStatusId();
        inReplyToUserId = status.getInReplyToUserId();
        inReplyToScreenName = status.getInReplyToScreenName();
        geoLocation = status.getGeoLocation() != null ? status.getGeoLocation().toString() : null;
        place = status.getPlace() != null ? status.getPlace().toString() : null;
        isFavorited = status.isFavorited();
        isRetweeted = status.isRetweeted();
        favoriteCount = status.getFavoriteCount();
        user = status.getUser() != null ? status.getUser().getId() : 0;
        isRetweet = status.isRetweet();
        retweetedStatus = status.getRetweetedStatus() != null ? status.getRetweetedStatus().getId() : 0;
        getRetweetCount = status.getRetweetCount();
        isRetweetedByMe = status.isRetweetedByMe();
        getCurrentUserRetweetId = status.getCurrentUserRetweetId();
        isPossiblySensitive = status.isPossiblySensitive();
        getIsoLanguageCode = status.getIsoLanguageCode();

    }
}
