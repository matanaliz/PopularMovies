package mtn.popularmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    private Movie mMovie;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        Intent intent = getActivity().getIntent();

        if (null != intent && intent.hasExtra(getString(R.string.details_intent))) {
            mMovie = intent.getParcelableExtra(getString(R.string.details_intent));

            TextView title = (TextView) rootView.findViewById(R.id.details_title);
            TextView overview = (TextView) rootView.findViewById(R.id.details_overview);
            TextView releaseDate = (TextView) rootView.findViewById(R.id.details_release_date);
            TextView voteRate = (TextView) rootView.findViewById(R.id.details_vote_rate);
            ImageView image = (ImageView) rootView.findViewById(R.id.details_poster);

            title.setText(mMovie.getTitle());
            overview.setText(mMovie.getOverview());
            releaseDate.setText(mMovie.getReleaseDate());
            voteRate.setText(Double.toString(mMovie.getVoteRating()));

            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w342/" + mMovie.getPosterUrl())
                    .into(image);
        }

        return rootView;
    }
}
