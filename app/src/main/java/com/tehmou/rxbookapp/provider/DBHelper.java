package com.tehmou.rxbookapp.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tehmou.rxbookapp.pojo.TwitterStatus;

import java.sql.SQLException;

/**
 * Created by ttuo on 24/06/14.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DBHelper.class.getCanonicalName();

    public DBHelper(Context context) {
        super(context, "rx_android_architecture.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.d(TAG, "onCreate");
        try {
            TableUtils.dropTable(connectionSource, TwitterStatus.class, true);
            TableUtils.createTable(connectionSource, TwitterStatus.class);
        } catch (SQLException e) {
            Log.e(TAG, "TableUtils.createTable", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade from " + oldVersion + " to " + newVersion);
    }
}
