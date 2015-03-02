package com.codepath.apps.avtweetsapp.fragments;


import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.avtweetsapp.TwitterApplication;
import com.codepath.apps.avtweetsapp.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class SuggestionsTimelineFragment extends TweetsListFragment {
    private static final String TAG = SuggestionsTimelineFragment.class.getSimpleName();

    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
    }

    void populateTimeLine(int offset) {
        client.getSuggestionsTimeLine(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.i(TAG, "onSuccess");
                Log.i(TAG, json.toString());
                //addAll(Tweet.fromJsonArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i(TAG, "onFailure");
                Log.i(TAG, errorRespnose.toString());
            }
        });
    }
}
