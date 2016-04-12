package com.popularmovies.mcondle.popularmovies;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;

import java.util.ArrayList;

/**
 * Created by mandeep.condle on 4/9/16.
 */
public class ThisApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final Context context = this;

//        todo - stetho init
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(context)
//                .enableDumpapp(new )
//        );
    }

    private static class ThisDumperPluginsProvider implements DumperPluginsProvider {
        private final Context context;

        public ThisDumperPluginsProvider(Context context) {
            this.context = context;
        }

        @Override
        public Iterable<DumperPlugin> get() {
            ArrayList<DumperPlugin> dumperPlugins = new ArrayList<>();


            return null;
        }
    }

}
