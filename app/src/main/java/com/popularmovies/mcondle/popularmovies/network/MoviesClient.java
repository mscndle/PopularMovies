package com.popularmovies.mcondle.popularmovies.network;

import com.popularmovies.mcondle.popularmovies.network.service.MovieDetailsService;
import com.popularmovies.mcondle.popularmovies.network.service.MoviesListService;

import retrofit.RestAdapter;

/**
 * Handles network requests to themoviedb.org and gets movie objects and poster images
 *
 * Created by mandeep.condle on 1/7/16.
 */
public class MoviesClient {

    private static final String TAG = MoviesClient.class.getSimpleName();

    // PLEASE INSERT API_KEY HERE
    private static final String API_KEY = "39759d3e11a3b8d6194c19814150629c";

    private static final String BASE_URL = "";

    MoviesListService moviesListService;
    MovieDetailsService movieDetailsService;

    RestAdapter restAdapter;
    private MoviesClient moviesClient;

    private MoviesClient() {
        // singleton
    }

    public MoviesClient getInstance() {
        if (moviesClient == null) {
            moviesClient = new MoviesClient();
        }
        if (restAdapter == null) {
            initRestAdapter();
        }

        return moviesClient;
    }

    private void initRestAdapter() {
        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();
    }

    public MoviesListService getMoviesListService() {
        if (moviesListService == null) {
            moviesListService = restAdapter.create(MoviesListService.class);
        }
        return moviesListService;
    }

    public MovieDetailsService getMovieDetailsService() {
        if (movieDetailsService == null) {
            movieDetailsService = restAdapter.create(MovieDetailsService.class);
        }
        return movieDetailsService;
    }


//    public static final String API_POPULAR_MOVIES = "api.themoviedb.org/3/movie/popular";
//    public static final String API_TOP_RATED_MOVIES = "api.themoviedb.org/3/movie/top_rated";
//
//    public static final String API_BASE_MOVIE_DETAILS = "api.themoviedb.org/3/movie";
//    public static final String API_BASE_POSTER = "http://image.tmdb.org/t/p/w185"; //using size w185 for now
//
//    private URL getDiscoverMovieUrl(SortOrder sortOrder) {
//        Uri.Builder builder = new Uri.Builder();
//        URL url = null;
//        Uri uri;
//
//        String apiPath = API_POPULAR_MOVIES;
//        if (sortOrder == SortOrder.TOP_RATED) {
//            apiPath = API_TOP_RATED_MOVIES;
//        }
//
//        try {
//            uri = builder.scheme("http")
//                    .encodedAuthority(apiPath)
//                    .appendQueryParameter("api_key", API_KEY)
//                    .build();
//
//            url = new URL(uri.toString());
//
//        } catch (MalformedURLException mue) {
//            Log.d(TAG, mue.toString());
//        }
//
//        return url;
//    }
//
//    private URL getMovieDetailsUrl(long movieId) {
//        Uri.Builder builder = new Uri.Builder();
//        URL url = null;
//        Uri uri;
//
//        try {
//            uri = builder.scheme("http")
//                    .encodedAuthority(API_BASE_MOVIE_DETAILS)
//                    .appendPath(String.valueOf(movieId))
//                    .appendQueryParameter("api_key", API_KEY)
//                    .build();
//
//            url = new URL(uri.toString());
//
//        } catch (IOException ioe) {
//            Log.d(TAG, ioe.toString());
//        }
//
//        return url;
//    }
//
//    /**
//     * @return  Most popular list of movies returned by the discover movie endpoint
//     * this is also the default list loaded when
//     */
//    public List<MovieLite> getMoviesList(SortOrder sortOrder) {
//        final String JSON_RESULTS = "results";
//        JSONArray moviesJsonArray = new JSONArray();
//        List<MovieLite> defaultMoviesList;
//
//        try {
//            JSONObject moviesJsonObject = new JSONObject(getMoviesListJson(sortOrder));
//            moviesJsonArray = moviesJsonObject.getJSONArray(JSON_RESULTS);
//
//        } catch (JSONException jse) {
//            Log.d(TAG, "-----> getDefaultMoviesList()" + jse.toString());
//
//        } finally {
//            if (moviesJsonArray != null) {
//                defaultMoviesList = MovieLite.fromJson(moviesJsonArray);
//            } else {
//                defaultMoviesList = new ArrayList<>();
//            }
//        }
//
//        return defaultMoviesList;
//    }
//
//    /**
//     * @return  Json string of the movies list
//     */
//    @Nullable
//    private String getMoviesListJson(SortOrder sortOrder) {
//        String moviesJson = null;
//        HttpURLConnection urlConnection = null;
//        BufferedReader br = null;
//
//        try {
//            URL url = getDiscoverMovieUrl(sortOrder);
//            urlConnection = (HttpURLConnection) url.openConnection();
//
//            InputStream inputStream = urlConnection.getInputStream();
//            br = new BufferedReader(new InputStreamReader(inputStream));
//
//            String line;
//            StringBuilder sb = new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//                sb.append("\n");
//            }
//
//            if (sb.length() == 0) {
//                return null;
//            } else {
//                moviesJson = sb.toString();
//            }
//
//        } catch (IOException ioe) {
//            Log.d(TAG, ioe.toString());
//
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException ioe) {
//                    Log.d(TAG, "error closing stream " + ioe.toString());
//                }
//            }
//        }
//
//        return moviesJson;
//    }
//
//    public Movie getMovieDetails(long movieId) {
//        Movie movie = new Movie();
//
//        try {
//            JSONObject jsonObject = new JSONObject(getMovieDetailsJson(movieId));
//            movie = Movie.fromJson(jsonObject);
//
//        } catch (JSONException jse) {
//            Log.d(TAG, jse.toString());
//        }
//
//        return movie;
//    }
//
//    private String getMovieDetailsJson(long movieId) {
//        HttpURLConnection urlConnection = null;
//        BufferedReader br;
//        String movie = null;
//
//        try {
//            URL url = getMovieDetailsUrl(movieId);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());
//
//            br = new BufferedReader(reader);
//            StringBuilder sb = new StringBuilder();
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//            movie = sb.toString();
//            br.close();
//
//        } catch (IOException ioe) {
//            Log.d(TAG, ioe.toString());
//        }
//
//        if (urlConnection != null) {
//            urlConnection.disconnect();
//        }
//
//        return movie;
//    }
//
//
//




}
