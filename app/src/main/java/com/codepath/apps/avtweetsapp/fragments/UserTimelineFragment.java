package com.codepath.apps.avtweetsapp.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.avtweetsapp.Activity.ProfileActivity;
import com.codepath.apps.avtweetsapp.TwitterApplication;
import com.codepath.apps.avtweetsapp.TwitterClient;
import com.codepath.apps.avtweetsapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class displays the tweets from this user.
 */
public class UserTimelineFragment extends TweetsListFragment {
    private static final String TAG = ProfileActivity.class.getSimpleName();
    private static TwitterClient client;
    private static String mScreenname;

    public UserTimelineFragment() {
        client = TwitterApplication.getRestClient();
    }

    public static UserTimelineFragment newInstance(String screenName) {
        UserTimelineFragment userTimelineFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        mScreenname = screenName;
        return userTimelineFragment;
    }

    void populateTimeLine(int offset) {
        client.getUserTimeline(mScreenname, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.i("UserTimelineFragment", "onSuccess");
                Log.i("UserTimelineFragment", json.toString());
                addAll(Tweet.fromJsonArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i("UserTimelineFragment", "onFailure");
                Log.i("UserTimelineFragment", errorRespnose.toString());
            }
        });
    }
}

