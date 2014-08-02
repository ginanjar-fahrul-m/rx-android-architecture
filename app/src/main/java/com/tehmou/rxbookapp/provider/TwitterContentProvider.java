package com.tehmou.rxbookapp.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.tehmou.rxbookapp.pojo.TwitterStatus;

/**
 * Created by ttuo on 24/06/14.
 */
public class TwitterContentProvider extends ContentProvider {
    static private String TAG = TwitterContentProvider.class.getCanonicalName();

    private DBHelper helper;

    @Override
    public boolean onCreate() {
        helper = OpenHelperManager.getHelper(getContext(), DBHelper.class);
        return false;
    }

    @Override
    public void shutdown() {
        super.shutdown();
        OpenHelperManager.releaseHelper();
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            Dao<TwitterStatus, String> twitterStatusDao = DaoManager.createDao(helper.getConnectionSource(), TwitterStatus.class);
            GenericRowMapper<TwitterStatus> mapper = twitterStatusDao.getSelectStarRowMapper();
            twitterStatusDao.qu
            return helper.getReadableDatabase().query();
        } catch (Exception e) {
            Log.e(TAG, "query(" + uri.toString() + ")", e);
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
