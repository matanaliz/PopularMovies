package mtn.popularmovies;

/**
 * Created by matan on 6/9/2016.
 */
public interface AsyncResponse<T> {
    void onResponse(T response);
}
