package com.codepath.apps.avtweetsapp.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.avtweetsapp.R;
import com.codepath.apps.avtweetsapp.fragments.HomeTimelineFragment;
import com.codepath.apps.avtweetsapp.fragments.MetionsTimelineFragment;

public class TimelineActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // ActionBar configuration
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bird_small);
        actionBar.setDisplayShowTitleEnabled(false);

        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        vpPager.setAdapter(new TweetsPagerAdapater(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(vpPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_create) {
            /*if (currentUser != null) {
                Intent i = new Intent(this, PostTweetActivity.class);
                i.putExtra("CurrentUser", currentUser);
                Toast.makeText(this, "currentUser #" + currentUser.getName(), Toast.LENGTH_SHORT).show();
                startActivity(i);
                //startActivityForResult(i, REQUEST_CODE);
            }*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TweetsPagerAdapater extends FragmentPagerAdapter {
        private String tabTitles[] = {"Home", "Mentions"};

        public TweetsPagerAdapater(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            if  (position == 0) {
                return new HomeTimelineFragment();
            } else if (position == 1) {
                return new MetionsTimelineFragment();
            } else {
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle (int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }

}
