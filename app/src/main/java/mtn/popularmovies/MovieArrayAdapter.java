package mtn.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by matan on 09.06.2016.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private String LOG_TAG = MovieArrayAdapter.class.getSimpleName();

    private Context mContext;

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Movie movie = getItem(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.poster_item, parent, false);
        }

        ImageView posterImage = (ImageView) convertView.findViewById(R.id.poster_view);

        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w500/" + movie.getPosterUrl())
                .into(posterImage);

        return convertView;
    }
}
