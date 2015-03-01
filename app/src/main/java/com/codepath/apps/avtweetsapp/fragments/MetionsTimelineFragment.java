package com.codepath.apps.avtweetsapp.fragments;


import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.avtweetsapp.TwitterApplication;
import com.codepath.apps.avtweetsapp.TwitterClient;
import com.codepath.apps.avtweetsapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class MetionsTimelineFragment extends TweetsListFragment {


    private TwitterClient client;
    private static final int numberOfResults = 1200;

    public MetionsTimelineFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        customLoadMoreDataFromApi(0);
    }

    // Append more data into the adapter
    private void customLoadMoreDataFromApi(int offset) {

        if (offset == 0 ) {
            clear();
        }
        offset += 1;

        if (offset > numberOfResults) return;
        populateTimeLine(offset);
    }

    void populateTimeLine(int offset) {

        Log.i("MetionsTimelineFragment","populateTimeLine");

        client.getMetionsTimeLine(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.i(".onSuccess", json.toString());
                addAll(Tweet.fromJsonArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i(".onFailure", errorRespnose.toString());
            }
        });
    }

    boolean checkForInternetConnectivity()
    {
        return false;
    }
}
