package com.popularmovies.mcondle.popularmovies;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.Stetho;
import com.popularmovies.mcondle.popularmovies.db.FavoritesDbHelper;

/**
 * Created by mandeep.condle on 4/9/16.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final Context context = this;

        // test db load
        FavoritesDbHelper dbHelper = new FavoritesDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // initialize Stetho
        Stetho.initialize(
                Stetho.newInitializerBuilder(context)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                        .build());
    }

}
