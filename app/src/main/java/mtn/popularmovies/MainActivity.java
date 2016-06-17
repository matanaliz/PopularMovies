package mtn.popularmovies;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fill up tabs data
        List<Pair<String, String>> fragmentData = new ArrayList<>();
        fragmentData.add(Pair.create(getString(R.string.popular_tab_item),
                MainActivityFragment.FRAGMENT_POPULAR));
        fragmentData.add(Pair.create(getString(R.string.top_rated_tab_item),
                MainActivityFragment.FRAGMENT_TOP_RATED));

        CategoryPagerAdapter adapter =
                new CategoryPagerAdapter(getSupportFragmentManager(), getApplicationContext(), fragmentData);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);

        Picasso picasso = new Picasso.Builder(getApplicationContext()).build();

        if (BuildConfig.DEBUG) {
            picasso.setIndicatorsEnabled(true);
        }

        try {
            Picasso.setSingletonInstance(picasso);
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //TODO Save activity data
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        //TODO restore activity state here
}
}
