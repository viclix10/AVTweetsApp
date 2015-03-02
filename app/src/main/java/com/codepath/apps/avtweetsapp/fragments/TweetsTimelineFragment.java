package com.codepath.apps.avtweetsapp.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.avtweetsapp.Activity.TimelineActivity;
import com.codepath.apps.avtweetsapp.TwitterApplication;
import com.codepath.apps.avtweetsapp.TwitterClient;
import com.codepath.apps.avtweetsapp.models.Tweet;
import com.codepath.apps.avtweetsapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class TweetsTimelineFragment extends TweetsListFragment {
    private static final String TAG = TimelineActivity.class.getSimpleName();
    private TwitterClient client;
    private User currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        getCurrentUser();
    }

    private void getCurrentUser(){
        //TwitterHelpers.checkForInternetConnectivity(this);
        client.getUserDetails(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                currentUser = User.fromJSON(response);
                Log.d(TAG, "user created: " + currentUser);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    void populateTimeLine(int offset) {

        //if (!checkForInternetConnectivity()) {
        //    return;
        //}
        client.getUserTimeline(currentUser.getScreenName(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.i("TweetsTimelineFragment", "onSuccess");
                Log.i("TweetsTimelineFragment", json.toString());
                addAll(Tweet.fromJsonArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i("TweetsTimelineFragment", "onFailure");
                Log.i("TweetsTimelineFragment", errorRespnose.toString());
            }
        });
    }
}
