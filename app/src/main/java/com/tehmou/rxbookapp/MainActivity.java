package com.tehmou.rxbookapp;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.tehmou.rxbookapp.pojo.TwitterStatus;
import com.tehmou.rxbookapp.provider.DBHelper;
import com.tehmou.rxbookapp.utils.TwitterClient;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import rx.Subscriber;
import twitter4j.Status;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();

    private DBHelper helper;

    public DBHelper getHelper() {
        return helper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new BookFragment())
                    .commit();
        }

        AssetManager assetManager = getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open("twitter.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            TwitterClient twitterClient = new TwitterClient(properties);
            twitterClient.getTweets()
                    .subscribe(new Subscriber<List<Status>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "Error gettings tweets", e);
                        }

                        @Override
                        public void onNext(List<Status> statuses) {
                            Log.d(TAG, "Found " + statuses.size() + " tweets");
                            try {
                                ConnectionSource connectionSource = helper.getConnectionSource();
                                Dao<TwitterStatus, Long> statusDao = DaoManager.createDao(connectionSource, TwitterStatus.class);
                                for (Status status : statuses) {
                                    TwitterStatus twitterStatus = new TwitterStatus(status);
                                    statusDao.create(twitterStatus);
                                }
                                connectionSource.close();
                            } catch (SQLException e) {
                                Log.e(TAG, "Error writing statuses", e);
                            }
                        }
                    });
        } catch (IOException e) {
            Log.e(TAG, "Unable to read assets/twitter.properties", e);
        }

        helper = OpenHelperManager.getHelper(this, DBHelper.class);
        helper.getWritableDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
