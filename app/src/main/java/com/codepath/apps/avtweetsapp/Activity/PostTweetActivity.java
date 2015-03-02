package com.codepath.apps.avtweetsapp.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.avtweetsapp.R;
import com.codepath.apps.avtweetsapp.TwitterApplication;
import com.codepath.apps.avtweetsapp.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


public class PostTweetActivity extends ActionBarActivity {

    private TwitterClient client;
    TextView tvTweets;
    EditText body;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_tweet);

        tvTweets = (TextView) findViewById(R.id.tvTweets);
        mTextView = (TextView)findViewById(R.id.tvCharCount);

        body = (EditText) findViewById(R.id.etBody);
        body.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int charLeft = 140;
                if (s.length() >= 140) { charLeft = 0;}
                else { charLeft -= s.length();}

                mTextView.setText("MaxMessageSpace: "+String.valueOf(charLeft));
            }

            public void afterTextChanged(Editable s) {
            }
        });

        final Button button = (Button) findViewById(R.id.bntPostTweet);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onPostTweetClick();
                Toast.makeText(v.getContext(), "Tweet Posted.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_tweet, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_back) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void onPostTweetClick()
    {
        if (!checkForInternetConnectivity()) {
            return;
        }

        client = TwitterApplication.getRestClient();
        String tweetBody = body.getText().toString();

        client.postTweet(tweetBody, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.i("onPostTweetClick", "onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorRespnose) {
                Log.i("onPostTweetClick", "onFailure" + errorRespnose.toString());
            }
        });
        return;
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
