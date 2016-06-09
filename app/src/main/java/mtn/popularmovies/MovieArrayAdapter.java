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
    private List<Movie> mMovies;

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        mMovies = movies;
        mContext = context;
    }

    public int getCount() {
        return mMovies.size();
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(mContext);

            convertView = inflater.inflate(R.layout.poster_item, parent, false);
            ImageView img = (ImageView) convertView.findViewById(R.id.poster_view);

            Movie m = mMovies.get(position);
            String url = m.getPosterUrl();

            Log.d(LOG_TAG, "http://image.tmdb.org/t/p/w185" + url);
            Picasso.with(mContext).load("http://image.tmdb.org/t/p/w185/" + url).into(img);
            //Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png").into(img);
        }

        return convertView;
    }
}
