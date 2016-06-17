package mtn.popularmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    private MovieArrayAdapter mMovieAdapter;

    public static final String FRAGMENT_KEY = "FRAGMENT_KEY";
    public static final String FRAGMENT_POPULAR = "FRAGMENT_POPULAR";
    public static final String FRAGMENT_TOP_RATED = "FRAGMENT_TOP_RATED";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mMovieAdapter = new MovieArrayAdapter(getActivity(), new ArrayList<Movie>());

        Bundle args = getArguments();
        String fragmentType = args.getString(FRAGMENT_KEY);
        FetchMoviesAsyncTask t = new FetchMoviesAsyncTask(mMovieAdapter);
        t.execute(fragmentType);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(mMovieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Intent intent = new Intent(getActivity(), DetailsActivity.class)
                        .putExtra(getString(R.string.details_intent), mMovieAdapter.getItem(position));

                startActivity(intent);
            }
        });

        return rootView;
    }
}
