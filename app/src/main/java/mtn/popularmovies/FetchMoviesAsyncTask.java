package mtn.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matan on 08.06.2016.
 */
public class FetchMoviesAsyncTask extends AsyncTask<String, Void, List<Movie> > {

    private final String LOG_TAG = FetchMoviesAsyncTask.class.getSimpleName();

    private final String API_KEY = BuildConfig.THE_MOVIE_DB_API_KEY;

    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private final String POPULAR_BASE_URL = "http://api.themoviedb.org/3/movie/popular?";
    private final String TOP_RATED_BASE_URL = "http://api.themoviedb.org/3/movie/top_rated?";

    private ArrayAdapter<Movie> mMovieAdapter;
    private String mCurrentUrl;

    public FetchMoviesAsyncTask(ArrayAdapter<Movie> response) {
        mMovieAdapter = response;
    }

    @Override
    protected List<Movie> doInBackground(String... tabUrl) {
        if (tabUrl[0].equals(MainActivityFragment.FRAGMENT_POPULAR)) {
            mCurrentUrl = POPULAR_BASE_URL;
        }
        else {
            mCurrentUrl = TOP_RATED_BASE_URL;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String responseJsonStr = null;

        final int PAGE = 1;
        String page = Integer.toString(PAGE);

        try {
            final String PAGE_KEY_PARAM = "page";
            final String API_KEY_PARAM = "api_key";

            Uri builtUri = Uri.parse(mCurrentUrl).buildUpon()
                    .appendQueryParameter(PAGE_KEY_PARAM, page)
                    .appendQueryParameter(API_KEY_PARAM, API_KEY).build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ( (line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            responseJsonStr = buffer.toString();

        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                }
            }
        }

        return new MovieParser().parseResponse(responseJsonStr);
    }

    @Override
    protected void onPostExecute(List<Movie> result) {
        if (result != null) {
            mMovieAdapter.addAll(result);
        }
    }
}
