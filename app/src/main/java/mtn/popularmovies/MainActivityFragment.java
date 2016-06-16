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
public class MainActivityFragment extends Fragment implements AsyncResponse<List<Movie>> {

    private MovieArrayAdapter mMovieAdapter;

    @Override
    public void onResponse(List<Movie> movies){
        mMovieAdapter.clear();
        mMovieAdapter.addAll(movies);
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        int tab = args.getInt("key");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mMovieAdapter = new MovieArrayAdapter(getActivity(), new ArrayList<Movie>());

        FetchMoviesAsyncTask t = new FetchMoviesAsyncTask(this);
        t.execute(Integer.toString(tab));

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
