package mtn.popularmovies;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by matan on 09.06.2016.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }
}
