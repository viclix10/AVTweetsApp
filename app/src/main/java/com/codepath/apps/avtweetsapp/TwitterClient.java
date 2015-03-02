package com.codepath.apps.avtweetsapp;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

public class TwitterClient extends OAuthBaseClient {
    private static final String TAG = TwitterClient.class.getSimpleName();

    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "mk5hZKpCHzu0Oa4Q2wST28Ebg";       // Change this
	public static final String REST_CONSUMER_SECRET = "XvHVvOEcPL2HkbDu9aS5dMg6VJK04jWeoc9g7kc7b8Rrr11LiB"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://avtweetsapp"; // Change this (here and in manifest)

    private static final String[] apiUri = {"statuses/home_timeline.json",
                                            "statuses/mentions_timeline.json",
                                            "statuses/user_timeline.json",
                                            "statuses/update.json",
                                            "account/verify_credentials.json",
                                            "users/suggestions/twitter.json"
                                            };


	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

    public void getHomeTimeLine(int offset, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl(apiUri[0]);
        RequestParams params = new RequestParams();
        params.put("since_id", (offset*10)+1);
        params.put("count", (offset*10)+10);
        getClient().get(apiUrl, params, handler);
    }

    public void getMetionsTimeLine(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl(apiUri[1]);
        RequestParams params = new RequestParams();
        params.put("count", 25);
        getClient().get(apiUrl, params, handler);
    }

    public void getUserTimeline(String screenName, AsyncHttpResponseHandler handler){
        String apiUrl = getApiUrl(apiUri[2]);
        RequestParams params = new RequestParams();
        params.put("count", 25);
        params.put("screen_name", screenName);
        getClient().get(apiUrl, params, handler);
    }

    public void postTweet(String body, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl(apiUri[3]);
        RequestParams params = new RequestParams();
        params.put("status", body);
        getClient().post(apiUrl, params, handler);
    }

    public void getUserDetails(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl(apiUri[4]);
        getClient().get(apiUrl, handler);
    }

    public void getSuggestionsTimeLine(AsyncHttpResponseHandler handler){
        String apiUrl = getApiUrl(apiUri[5]);
        getClient().get(apiUrl, null, handler);
    }
}