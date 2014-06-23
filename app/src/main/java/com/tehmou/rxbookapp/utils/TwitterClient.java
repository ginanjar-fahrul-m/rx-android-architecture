package com.tehmou.rxbookapp.utils;

import android.util.Log;

import java.util.List;
import java.util.Properties;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by ttuo on 22/06/14.
 */
public class TwitterClient {
    private static final String TAG = TwitterClient.class.getCanonicalName();

    final private Twitter twitter;

    public TwitterClient(Properties properties) {
        final ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(properties.getProperty("oauth_consumer_key"))
                .setOAuthConsumerSecret(properties.getProperty("oauth_consumer_secret"))
                .setOAuthAccessToken(properties.getProperty("oauth_access_token"))
                .setOAuthAccessTokenSecret(properties.getProperty("oauth_access_token_secret"));
        final TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public Observable<List<Status>> getTweets() {
        return Observable
                .create(new Observable.OnSubscribe<List<Status>>() {
                    @Override
                    public void call(Subscriber<? super List<Status>> subscriber) {
                        try {
                            List<Status> statuses = twitter.getHomeTimeline();
                            for (Status status : statuses) {
                                Log.d(TAG, status.getText());
                            }
                            subscriber.onNext(statuses);
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread());
    }
}
