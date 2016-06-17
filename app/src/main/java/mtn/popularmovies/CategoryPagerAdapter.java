package mtn.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;

import java.util.List;

/**
 * Created by matan on 17.06.2016.
 */

/*
 * https://developer.android.com/reference/android/support/v13/app/FragmentPagerAdapter.html
 */
public class CategoryPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Pair<String, String>> mFragmentsData;

    public CategoryPagerAdapter(FragmentManager fm, Context context, List<Pair<String, String>> fragmentData) {
        super(fm);
        mContext = context;
        mFragmentsData = fragmentData;
    }

    @Override
    public int getCount() {
        return mFragmentsData.size();
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putString(MainActivityFragment.FRAGMENT_KEY, mFragmentsData.get(position).second);

        return Fragment.instantiate(mContext, MainActivityFragment.class.getName(), bundle);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsData.get(position).first;
    }
}
