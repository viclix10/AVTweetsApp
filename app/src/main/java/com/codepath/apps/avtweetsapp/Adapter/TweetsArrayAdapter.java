package com.codepath.apps.avtweetsapp.Adapter;


import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.avtweetsapp.R;
import com.codepath.apps.avtweetsapp.models.Tweet;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    private static class ViewHolder {
        TextView username;
        TextView body;
        TextView userHandle;
        TextView createdAt;
        ImageView profileImg;
    }

    public TweetsArrayAdapter(Context context, List<Tweet> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.profileImg = (ImageView) convertView.findViewById(R.id.ivProfileImg);
            viewHolder.username = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.userHandle = (TextView) convertView.findViewById(R.id.tvUserHandle);
            viewHolder.createdAt = (TextView) convertView.findViewById(R.id.tvCreatedat);
            viewHolder.body = (TextView) convertView.findViewById(R.id.tvBody);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.username.setText(tweet.getUser().getScreenName());
        viewHolder.userHandle.setText("@"+tweet.getUser().getNameHandle());
        viewHolder.body.setText(tweet.getBody());
        viewHolder.createdAt.setText(getRelativeTimeAgo(tweet.getCreatedAt()));
        viewHolder.profileImg.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(viewHolder.profileImg);

        return convertView;
    }



    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
