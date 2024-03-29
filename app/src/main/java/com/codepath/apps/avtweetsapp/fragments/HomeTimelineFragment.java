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

public class HomeTimelineFragment extends TweetsListFragment {

    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
    }

    void populateTimeLine(int offset) {

        //if (!checkForInternetConnectivity()) {
        //    return;
        //}

        client.getHomeTimeLine(offset, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.i("HomeTimelineFragment", "onSuccess");
                Log.i("HomeTimelineFragment", json.toString());
                addAll(Tweet.fromJsonArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i("HomeTimelineFragment", "onFailure");
                Log.i("HomeTimelineFragment", errorRespnose.toString());
            }
        });
    }
}
