package com.codepath.apps.avtweetsapp.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
    }

    private static final int numberOfResults = 64;

    // Append more data into the adapter
    private void customLoadMoreDataFromApi(int offset) {
        offset += 1;

        Toast.makeText(this, "Loading Tweets #"+offset, Toast.LENGTH_SHORT).show();

        if (offset > numberOfResults) return;
        populateTimeLine(offset);
    }

    private void getUserDetails() {
        client.getUserDetails(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                Log.i("getUserDetails", json.toString());

                currentUser = CurrentUser.fromJSON(json);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i("getUserDetails", errorRespnose.toString());
            }
        });
    }
    private void populateTimeLine(int offset) {
        checkForInternetConnectivity();

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

            Intent i = new Intent(this, PostTweetActivity.class);
            i.putExtra("CurrentUser", currentUser);
            Toast.makeText(this, "currentUser #"+currentUser.getName(), Toast.LENGTH_SHORT).show();
            startActivity(i);
            //startActivityForResult(i, REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void checkForInternetConnectivity() {
        if (!isNetworkAvailable()) {
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
    }
}
