package mtn.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by matan on 08.06.2016.
 */
public class MovieParser {
    private final String LOG_TAG = MovieParser.class.getSimpleName();

    public ArrayList<Movie> parseResponse(String response) {

        if (null == response) return null;

        ArrayList<Movie> movieList = new ArrayList<>();

        try {
            final String MOVIE_ARRAY = "results";
            final String MOVIE_ID = "id";
            final String MOVIE_POSTER_URL = "poster_path";
            final String MOVIE_OVERVIEW = "overview";
            final String MOVIE_RELEASE_DATE = "release_date";
            final String MOVIE_TITLE = "title";
            final String MOVIE_ORIGINAL_TITLE = "original_title";
            final String MOVIE_VOTE_RATING = "vote_average";

            JSONObject res = new JSONObject(response);
            JSONArray jMovies = res.getJSONArray(MOVIE_ARRAY);

            for (int i=0; i < jMovies.length(); i++) {
                try {
                    JSONObject jMovie = jMovies.getJSONObject(i);

                    movieList.add(new Movie()
                            .setId(jMovie.getInt(MOVIE_ID))
                            .setOriginalTitle(jMovie.getString(MOVIE_ORIGINAL_TITLE))
                            .setTitle(jMovie.getString(MOVIE_TITLE))
                            .setOverview(jMovie.getString(MOVIE_OVERVIEW))
                            .setPosterUrl(jMovie.getString(MOVIE_POSTER_URL))
                            .setReleaseDate(jMovie.getString(MOVIE_RELEASE_DATE))
                            .setVoteRating(jMovie.getDouble(MOVIE_VOTE_RATING))
                    );

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return movieList;
    }
}
