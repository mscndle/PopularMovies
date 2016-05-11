package com.popularmovies.mcondle.popularmovies.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by mandeep.condle on 4/17/16.
 */
public class FavoritesProvider extends ContentProvider {

    private static final String TAG = FavoritesProvider.class.getSimpleName();

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private FavoritesDbHelper favoritesDbHelper;

    static final int FAVORITES = 100;

    // we are only going to be using this to query all the items in the favorites table
    private static final SQLiteQueryBuilder favoritesQueryBuilder;
    static {
        favoritesQueryBuilder = new SQLiteQueryBuilder();
        favoritesQueryBuilder.setTables(FavoritesContract.FavoritesEntry.TABLE_NAME);
    }

    private Cursor getAllFavorites(Uri uri, String sortOrder) {
        return favoritesQueryBuilder.query(
                favoritesDbHelper.getReadableDatabase(),
                null,
                null,
                null,
                null,
                null,
                sortOrder);
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FavoritesContract.CONTENT_AUTHORITY;
        final String path = FavoritesContract.PATH_FAVORITES;

        matcher.addURI(authority, path, FAVORITES);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        favoritesDbHelper = new FavoritesDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        final int match  = uriMatcher.match(uri);

        // added a switch to add more queries in the future
        switch (match) {
            case FAVORITES:
            default:
                return FavoritesContract.FavoritesEntry.CONTENT_TYPE;
        }
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case FAVORITES:
                cursor = getAllFavorites(uri, sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = favoritesDbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case FAVORITES: {
                long _id = db.insert(FavoritesContract.FavoritesEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = FavoritesContract.FavoritesEntry.buildFavoritesUri();
                else
                    throw new SQLException("Failed to insert row into " + uri);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = favoritesDbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case FAVORITES: {
                rowsDeleted = db.delete(FavoritesContract.FavoritesEntry.TABLE_NAME, selection,
                        selectionArgs);
                if (selection == null || rowsDeleted != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = favoritesDbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case FAVORITES: {
                rowsUpdated = db.update(FavoritesContract.FavoritesEntry.TABLE_NAME, values,
                        selection, selectionArgs);
                if (selection == null || rowsUpdated != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return rowsUpdated;
    }

}
