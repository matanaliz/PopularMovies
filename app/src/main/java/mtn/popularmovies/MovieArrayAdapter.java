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

    private static class ViewHolder {
        public ImageView mImageView;

        public ViewHolder(ImageView image) {
            this.mImageView = image;
        }
    }

    private final String LOG_TAG = MovieArrayAdapter.class.getSimpleName();

    private Context mContext;

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView posterImage;
        Movie movie = getItem(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.poster_item, parent, false);
            posterImage = (ImageView) convertView.findViewById(R.id.poster_view);
            convertView.setTag(new ViewHolder(posterImage));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            posterImage = viewHolder.mImageView;
        }

        // TODO check if url was well formed
        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w325/" + movie.getPosterUrl())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_error_placeholder)
                .into(posterImage);

        return convertView;
    }
}
