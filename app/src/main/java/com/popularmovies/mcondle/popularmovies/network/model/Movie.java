package com.popularmovies.mcondle.popularmovies.network.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Model retrieved from themoviedb.org
 * This will be used by the adapter
 *
 * Created by mandeep.condle on 1/2/16.
 */
public class Movie implements Parcelable {

    private static final String TAG = Movie.class.getSimpleName();

    private long id;
    private String title;
    private int[] genre_ids;
    private boolean adult;
    private String backdrop_path;
    private String overview;
    private String original_title;
    private String original_language;
    private String poster_path;
    private String release_date;
    private double popularity;
    private long vote_count;
    private boolean video;
    private double vote_average;

    // these fields are grabbed in the MovieDetailsActivity
    private long budget;
    private String homepage;
    private String imdbId;
    private long revenue;
    private int runtime;

    public Movie() {
        // empty constructor if Gson is used later
    }

    public Movie(long id,
                 String title,
                 int[] genre_ids,
                 boolean adult,
                 String backdrop_path,
                 String overview,
                 String original_title,
                 String original_language,
                 String poster_path,
                 String release_date,
                 double popularity,
                 long vote_count,
                 boolean video,
                 double vote_average,
                 long budget,
                 String homepage,
                 String imdbId,
                 long revenue,
                 int runtime) {

        this.id = id;
        this.title = title;
        this.genre_ids = genre_ids;
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.original_title = original_title;
        this.original_language = original_language;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.vote_average = vote_average;

        this.budget = budget;
        this.homepage = homepage;
        this.imdbId = imdbId;
        this.revenue = revenue;
        this.runtime = runtime;
    }

    /**
     * Converts the expected jsonObject into a Movie objects
     * @param jsonObject    returned from the api call
     * @return  converted Movie object
     */
    public static Movie fromJson(JSONObject jsonObject) {
        Movie movie = new Movie();

        try {
            movie.id = jsonObject.getLong("id");
            movie.title = jsonObject.getString("title");
            movie.adult = jsonObject.getBoolean("adult");
            movie.backdrop_path = jsonObject.getString("backdrop_path");
            movie.overview = jsonObject.getString("overview");
            movie.original_title = jsonObject.getString("original_title");
            movie.original_language = jsonObject.getString("original_language");
            movie.poster_path = jsonObject.getString("poster_path");
            movie.release_date = jsonObject.getString("release_date");
            movie.popularity = jsonObject.getDouble("popularity");
            movie.vote_count = jsonObject.getLong("vote_count");
            movie.video = jsonObject.getBoolean("video");
            movie.vote_average = jsonObject.getDouble("vote_average");

//            JSONArray jsonArray = jsonObject.getJSONArray("genre_ids");
//            int[] genre_ids = new int[jsonArray.length()];

//            for (int i = 0; i < jsonArray.length(); i++) {  // jsonArray = [28, 12, 878, 53]
////                genre_ids[i] = jsonArray.getJSONObject(i).getInt("");
//                genre_ids[i] = (Integer) jsonArray.get(i);
//            }
//            movie.genre_ids = genre_ids;

            movie.budget = jsonObject.getLong("budget");
            movie.homepage = jsonObject.getString("homepage");
            movie.imdbId = jsonObject.getString("imdb_id");
            movie.revenue = jsonObject.getLong("revenue");
            movie.runtime = jsonObject.getInt("runtime");

        } catch (JSONException jse) {
            Log.d(TAG, "JSONException in Movie.fromJson(...) " + jse.toString());
            return null;
        }

        return movie;
    }

    /**
     * Converts the expected jsonArray into a list of Movie objects
     * @param jsonArray returned from the api call
     * @return  List of Movie objects
     */
    public static List<Movie> fromJson(JSONArray jsonArray) {
        List<Movie> movies = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Movie m = Movie.fromJson(jsonArray.getJSONObject(i));
                movies.add(m);
            } catch (JSONException jse) {
                Log.d(TAG, "JSONException in Movie.fromJson(...) " + jse.toString());
                // don't exist if there's an issue with a particular json movie object. continue...
            }
        }

        return movies;
    }

    /***********************
     * getters and setters *
     ***********************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getGenreIds() {
        return genre_ids;
    }

    public void setGenreIds(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String original_language) {
        this.original_language = original_language;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public long getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(long vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    /********************************
     * implement parcelable methods *
     ********************************/

    @Override
    public int describeContents() {
        return 0;   //TODO - complete this
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {

    }

}

