package com.codepath.apps.avtweetsapp.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.avtweetsapp.TwitterApplication;
import com.codepath.apps.avtweetsapp.TwitterClient;
import com.codepath.apps.avtweetsapp.models.CurrentUser;
import com.codepath.apps.avtweetsapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomeTimelineFragment extends TweetsListFragment {

    private TwitterClient client;

    public HomeTimelineFragment() {
        super();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("HomeTimelineFragment", "Done");

        client = TwitterApplication.getRestClient();
        populateTimeLine(0);
        getUserDetails();

    }

    void populateTimeLine(int offset) {

        Log.i("HomeTimelineFragment","populateTimeLine");

        //if (!checkForInternetConnectivity()) {
        //    return;
        //}

        client.getHomeTimeLine(offset, new JsonHttpResponseHandler() {
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

    /*
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
    */

    CurrentUser currentUser;
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

}
