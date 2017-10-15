package com.sania.demosqlite;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sania on 31/10/2016.
 */

public class LocalDatabase extends Application {

    public Context context;
    public static SQLiteDatabase db;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        db = openOrCreateDatabase("app", MODE_PRIVATE, null);
        context = getApplicationContext();
        SQLHelper.SetupDB();
    }

}
