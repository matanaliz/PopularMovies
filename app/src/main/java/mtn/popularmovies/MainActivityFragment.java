package mtn.popularmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AsyncResponse<ArrayList<Movie>> {

    private MovieArrayAdapter mMovieAdapter;

    @Override
    public void onResponse(ArrayList<Movie> movies){
        //mMovieAdapter = new MovieArrayAdapter(getActivity(), movies);
        mMovieAdapter.clear();
        mMovieAdapter.addAll(movies);
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mMovieAdapter = new MovieArrayAdapter(getActivity(), new ArrayList<Movie>());

        FetchMoviesAsyncTask t = new FetchMoviesAsyncTask(this);
        t.execute("test");

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(mMovieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
            }
        });



        return rootView;
    }
}
