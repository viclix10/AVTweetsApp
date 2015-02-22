package com.codepath.apps.avtweetsapp.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.avtweetsapp.R;
import com.codepath.apps.avtweetsapp.TwitterApplication;
import com.codepath.apps.avtweetsapp.TwitterClient;
import com.codepath.apps.avtweetsapp.models.CurrentUser;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


public class PostTweetActivity extends ActionBarActivity {

    private TwitterClient client;
    CurrentUser currentUser;

    ImageView profileImg;
    TextView username;
    TextView userHandle;
    TextView tvFollowers;
    TextView tvFollowing;
    TextView tvTweets;
    EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_tweet);

        currentUser = (CurrentUser)getIntent().getParcelableExtra("CurrentUser");

        profileImg = (ImageView) findViewById(R.id.ivProfileImg);
        username = (TextView) findViewById(R.id.tvUsername);
        userHandle = (TextView) findViewById(R.id.tvUserHandle);
        tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        tvTweets = (TextView) findViewById(R.id.tvTweets);
        body = (EditText) findViewById(R.id.etBody);

        profileImg.setImageResource(android.R.color.transparent);
        Picasso.with(this).load(currentUser.getProfile_image_url()).into(profileImg);

        username.setText(currentUser.getScreen_name());
        userHandle.setText(currentUser.getName());
        tvFollowers.setText("Followers: "+ currentUser.getFollowers_count());
        tvFollowing.setText("Favourites: " + currentUser.getFavourites_count());
        tvTweets.setText("Tweets: " + currentUser.getStatuses_count());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_tweet, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            this.finish();
            return true;
        } else if (id == R.id.action_create) {
            //checkForInternetConnectivity();

            client = TwitterApplication.getRestClient();
            String tweetBody = body.getText().toString();

            client.postTweet(tweetBody, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                    Log.i("postTweet.onSuccess", "onSuccess");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                    Log.i("postTweet.onFailure", errorRespnose.toString());
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
