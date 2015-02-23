package com.codepath.apps.avtweetsapp.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.avtweetsapp.Adapter.TweetsArrayAdapter;
import com.codepath.apps.avtweetsapp.R;
import com.codepath.apps.avtweetsapp.TwitterApplication;
import com.codepath.apps.avtweetsapp.TwitterClient;
import com.codepath.apps.avtweetsapp.models.CurrentUser;
import com.codepath.apps.avtweetsapp.models.Tweet;
import com.codepath.apps.avtweetsapp.utils.EndlessScrollListener;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {

    private SwipeRefreshLayout swipeContainer;
    private TwitterClient client;
    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;
    CurrentUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(this, tweets);
        lvTweets.setAdapter(aTweets);

        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
            }
        });

        client = TwitterApplication.getRestClient();
        getUserDetails();

        customLoadMoreDataFromApi(0);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                //fetchTimelineAsync(0);
                customLoadMoreDataFromApi(0);
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private static final int numberOfResults = 1200;

    // Append more data into the adapter
    private void customLoadMoreDataFromApi(int offset) {

        if (offset == 0 ) {
            aTweets.clear();
        }
        offset += 1;

        Toast.makeText(this, "Loading Tweets #"+offset, Toast.LENGTH_SHORT).show();

        if (offset > numberOfResults) return;
        populateTimeLine(offset);
    }

    private void getUserDetails() {
        if (!checkForInternetConnectivity()) {
            return;
        }

        client.getUserDetails(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                currentUser = CurrentUser.fromJSON(json);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i("getUserDetails", errorRespnose.toString());
            }
        });
    }
    private void populateTimeLine(int offset) {
        if (!checkForInternetConnectivity()) {
            return;
        }

        client.getHomeTimeLine(offset, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                ArrayList<Tweet> tweets = Tweet.fromJsonArray(json);
                aTweets.addAll(tweets);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i("Timeline.onFailure", errorRespnose.toString());
            }
        });
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
            if (currentUser != null) {
                Intent i = new Intent(this, PostTweetActivity.class);
                i.putExtra("CurrentUser", currentUser);
                Toast.makeText(this, "currentUser #" + currentUser.getName(), Toast.LENGTH_SHORT).show();
                startActivity(i);
                //startActivityForResult(i, REQUEST_CODE);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting());
    }

    private boolean checkForInternetConnectivity() {
        boolean networkAval = isNetworkAvailable();

        if (!networkAval) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Unable to connect to the internet.  Please try again later.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        return networkAval;
    }
}
