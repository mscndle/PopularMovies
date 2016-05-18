package com.popularmovies.mcondle.popularmovies.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines tables and column names for the favorites database
 *
 * Created by mandeep.condle on 4/16/16.
 */
public class FavoritesContract {

    // using the app's package name as the content authority string
    public static final String CONTENT_AUTHORITY = "com.popularmovies.mcondle.popularmovies";

    // using CONTENT_AUTHORITY to create the base of all URIs
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // possible paths, for now all favorites, we'll just use this path
    public static final String PATH_FAVORITES = "favorites";

    public static final class FavoritesEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorites";

        // movieDetail id, primary key
        public static final String COLUMN_MOVIE_ID = "movie_id";

        // movieDetail title, string
        public static final String COLUMN_MOVIE_TITLE = "movie_title";

        // movieDetail poster path to retrieve the poster for the grid
        public static final String COLUMN_MOVIE_POSTER_PATH = "movie_poster_path";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITES;

        public static Uri buildFavoritesUri() {
            return CONTENT_URI;
        }

    }

}
