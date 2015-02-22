package com.codepath.apps.avtweetsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Table(name = "Tweets")
public class Tweet extends Model implements Parcelable {

    @Column(name = "body")
    private String body;

    @Column(name = "uid")
    private long uid;

    @Column(name = "User", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private User user;

    @Column(name = "createdAt")
    private String createdAt;

    public Tweet()
    {
        super();
    }

    public static Tweet fromJson(JSONObject jsonObject)
    {
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    public static ArrayList<Tweet> fromJsonArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i< jsonArray.length(); i++) {

            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJson(tweetJson);
                if (tweet != null) {
                    tweet.save();
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return  tweets;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
        dest.writeLong(this.uid);
        dest.writeParcelable(this.user, 0);
        dest.writeString(this.createdAt);
    }

    private Tweet(Parcel in) {
        this.body = in.readString();
        this.uid = in.readLong();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.createdAt = in.readString();
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        public Tweet createFromParcel(Parcel source) {
            return new Tweet(source);
        }

        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };
}
/*
[

  {
    "coordinates": null,
    "favorited": false,
    "truncated": false,
    "created_at": "Wed Aug 29 17:12:58 +0000 2012",
    "id_str": "240859602684612608",
    "entities": {
      "urls": [
        {
          "expanded_url": "https://dev.twitter.com/blog/twitter-certified-products",
          "url": "https://t.co/MjJ8xAnT",
          "indices": [
            52,
            73
          ],
          "display_url": "dev.twitter.com/blog/twitter-c\u2026"
        }
      ],
      "hashtags": [

      ],
      "user_mentions": [

      ]
    },
    "in_reply_to_user_id_str": null,
    "contributors": null,
    "text": "Introducing the Twitter Certified Products Program: https://t.co/MjJ8xAnT",
    "retweet_count": 121,
    "in_reply_to_status_id_str": null,
    "id": 240859602684612608,
    "geo": null,
    "retweeted": false,
    "possibly_sensitive": false,
    "in_reply_to_user_id": null,
    "place": null,
    "user": {
      "profile_sidebar_fill_color": "DDEEF6",
      "profile_sidebar_border_color": "C0DEED",
      "profile_background_tile": false,
      "name": "Twitter API",
      "profile_image_url": "http://a0.twimg.com/profile_images/2284174872/7df3h38zabcvjylnyfe3_normal.png",
      "created_at": "Wed May 23 06:01:13 +0000 2007",
      "location": "San Francisco, CA",
      "follow_request_sent": false,
      "profile_link_color": "0084B4",
      "is_translator": false,
      "id_str": "6253282",
      "entities": {
        "url": {
          "urls": [
            {
              "expanded_url": null,
              "url": "http://dev.twitter.com",
              "indices": [
                0,
                22
              ]
            }
          ]
        },
        "description": {
          "urls": [

          ]
        }
      },
      "default_profile": true,
      "contributors_enabled": true,
      "favourites_count": 24,
      "url": "http://dev.twitter.com",
      "profile_image_url_https": "https://si0.twimg.com/profile_images/2284174872/7df3h38zabcvjylnyfe3_normal.png",
      "utc_offset": -28800,
      "id": 6253282,
      "profile_use_background_image": true,
      "listed_count": 10775,
      "profile_text_color": "333333",
      "lang": "en",
      "followers_count": 1212864,
      "protected": false,
      "notifications": null,
      "profile_background_image_url_https": "https://si0.twimg.com/images/themes/theme1/bg.png",
      "profile_background_color": "C0DEED",
      "verified": true,
      "geo_enabled": true,
      "time_zone": "Pacific Time (US & Canada)",
      "description": "The Real Twitter API. I tweet about API changes, service issues and happily answer questions about Twitter and our API. Don't get an answer? It's on my website.",
      "default_profile_image": false,
      "profile_background_image_url": "http://a0.twimg.com/images/themes/theme1/bg.png",
      "statuses_count": 3333,
      "friends_count": 31,
      "following": null,
      "show_all_inline_media": false,
      "screen_name": "twitterapi"
    },
    "in_reply_to_screen_name": null,
    "source": "<a href="//sites.google.com/site/yorufukurou/\"" rel="\"nofollow\"">YoruFukurou</a>",
    "in_reply_to_status_id": null
  },
  {
    "coordinates": null,
    "favorited": false,
    "truncated": false,
    "created_at": "Sat Aug 25 17:26:51 +0000 2012",
    "id_str": "239413543487819778",
    "entities": {
      "urls": [
        {
          "expanded_url": "https://dev.twitter.com/issues/485",
          "url": "https://t.co/p5bOzH0k",
          "indices": [
            97,
            118
          ],
          "display_url": "dev.twitter.com/issues/485"
        }
      ],
      "hashtags": [

      ],
      "user_mentions": [

      ]
    },
    "in_reply_to_user_id_str": null,
    "contributors": null,
    "text": "We are working to resolve issues with application management & logging in to the dev portal: https://t.co/p5bOzH0k ^TS",
    "retweet_count": 105,
    "in_reply_to_status_id_str": null,
    "id": 239413543487819778,
    "geo": null,
    "retweeted": false,
    "possibly_sensitive": false,
    "in_reply_to_user_id": null,
    "place": null,
    "user": {
      "profile_sidebar_fill_color": "DDEEF6",
      "profile_sidebar_border_color": "C0DEED",
      "profile_background_tile": false,
      "name": "Twitter API",
      "profile_image_url": "http://a0.twimg.com/profile_images/2284174872/7df3h38zabcvjylnyfe3_normal.png",
      "created_at": "Wed May 23 06:01:13 +0000 2007",
      "location": "San Francisco, CA",
      "follow_request_sent": false,
      "profile_link_color": "0084B4",
      "is_translator": false,
      "id_str": "6253282",
      "entities": {
        "url": {
          "urls": [
            {
              "expanded_url": null,
              "url": "http://dev.twitter.com",
              "indices": [
                0,
                22
              ]
            }
          ]
        },
        "description": {
          "urls": [

          ]
        }
      },
      "default_profile": true,
      "contributors_enabled": true,
      "favourites_count": 24,
      "url": "http://dev.twitter.com",
      "profile_image_url_https": "https://si0.twimg.com/profile_images/2284174872/7df3h38zabcvjylnyfe3_normal.png",
      "utc_offset": -28800,
      "id": 6253282,
      "profile_use_background_image": true,
      "listed_count": 10775,
      "profile_text_color": "333333",
      "lang": "en",
      "followers_count": 1212864,
      "protected": false,
      "notifications": null,
      "profile_background_image_url_https": "https://si0.twimg.com/images/themes/theme1/bg.png",
      "profile_background_color": "C0DEED",
      "verified": true,
      "geo_enabled": true,
      "time_zone": "Pacific Time (US & Canada)",
      "description": "The Real Twitter API. I tweet about API changes, service issues and happily answer questions about Twitter and our API. Don't get an answer? It's on my website.",
      "default_profile_image": false,
      "profile_background_image_url": "http://a0.twimg.com/images/themes/theme1/bg.png",
      "statuses_count": 3333,
      "friends_count": 31,
      "following": null,
      "show_all_inline_media": false,
      "screen_name": "twitterapi"
    },
    "in_reply_to_screen_name": null,
    "source": "<a href="//sites.google.com/site/yorufukurou/\"" rel="\"nofollow\"">YoruFukurou</a>",
    "in_reply_to_status_id": null
  }
]
[{"created_at":"Sun Feb 22 02:59:55 +0000 2015","id":569330718586703872,"id_str":"569330718586703872","text":"Are on-demand services distracting us from real world interaction? http:\/\/t.co\/TvcLwYNn0z","source":"<a href=\"http:\/\/twitter.com\" rel=\"nofollow\">Twitter Web Client<\/a>","truncated":false,"in_reply_to_status_id":null,"in_reply_to_status_id_str":null,"in_reply_to_user_id":null,"in_reply_to_user_id_str":null,"in_reply_to_screen_name":null,"user":{"id":9466132,"id_str":"9466132","name":"vedamber","screen_name":"vedamber","location":"San Jose, CA","profile_location":null,"description":"","url":null,"entities":{"description":{"urls":[]}},"protected":false,"followers_count":11,"friends_count":21,"listed_count":0,"created_at":"Mon Oct 15 23:54:39 +0000 2007","favourites_count":0,"utc_offset":null,"time_zone":null,"geo_enabled":false,"verified":false,"statuses_count":20,"lang":"en","contributors_enabled":false,"is_translator":false,"is_translation_enabled":false,"profile_background_color":"131516","profile_background_image_url":"http:\/\/abs.twimg.com\/images\/themes\/theme14\/bg.gif","profile_background_image_url_https":"https:\/\/abs.twimg.com\/images\/themes\/theme14\/bg.gif","profile_background_tile":true,"profile_image_url":"http:\/\/pbs.twimg.com\/profile_images\/3388866742\/a7fdcc1556294f02df3080eaab1da062_normal.jpeg","profile_image_url_https":"https:\/\/pbs.twimg.com\/profile_images\/3388866742\/a7fdcc1556294f02df3080eaab1da062_normal.jpeg","profile_link_color":"009999","profile_sidebar_border_color":"EEEEEE","profile_sidebar_fill_color":"EFEFEF","profile_text_color":"333333","profile_use_background_image":true,"default_profile":false,"default_profile_image":false,"following":false,"follow_request_sent":false,"notifications":false},"geo":null,"coordinates":null,"place":null,"contributors":null,"retweet_count":0,"favorite_count":0,"entities":{"hashtags":[],"symbols":[],"user_mentions":[],"urls":[{"url":"http:\/\/t.co\/TvcLwYNn0z","expanded_url":"http:\/\/bit.ly\/1AfNKOQ","display_url":"bit.ly\/1AfNKOQ","indices":[67,89]}]},"favorited":false,"retweeted":false,"possibly_sensitive":false,"lang":"en"},{"created_at":"Sun Feb 22 00:22:43 +0000 2015","id":569291157026394112,"id_str":"569291157026394112","text":"Testing Twitter App 20","source":"<a href=\"http:\/\/twitter.com\" rel=\"nofollow\">Twitter Web Client<\/a>","truncated":false,"in_reply_to_status_id":null,"in_reply_to_status_id_str":null,"in_reply_to_user_id":null,"in_reply_to_user_id_str":null,"in_reply_to_screen_name":null,"user":{"id":9466132,"id_str":"9466132","name":"vedamber","screen_name":"vedamber","location":"San Jose, CA","profile_location":null,"description":"","url":null,"entities":{"description":{"urls":[]}},"protected":false,"followers_count":11,"friends_count":21,"listed_count":0,"created_at":"Mon Oct 15 23:54:39 +0000 2007","favourites_count":0,"utc_offset":null,"time_zone":null,"geo_enabled":false,"verified":false,"statuses_count":20,"lang":"en","contributors_enabled":false,"is_translator":false,"is_translation_enabled":false,"profile_background_color":"131516","profile_background_image_url":"http:\/\/abs.twimg.com\/images\/themes\/theme14\/bg.gif","profile_background_image_url_https":"https:\/\/abs.twimg.com\/images\/themes\/theme14\/bg.gif","profile_background_tile":true,"profile_image_url":"http:\/\/pbs.twimg.com\/profile_images\/3388866742\/a7fdcc1556294f02df3080eaab1da062_normal.jpeg","profile_image_url_https":"https:\/\/pbs.twimg.com\/profile_images\/3388866742\/a7fdcc1556294f02df3080eaab1da062_normal.jpeg","profile_link_color":"009999","profile_sidebar_border_color":"EEEEEE","profile_sidebar_fill_color":"EFEFEF","profile_text_color":"333333","profile_use_background_image":true,"default_profile":false,"default_profile_image":false,"following":false,"follow_request_sent":false,"notifications":false},"geo":null,"coordinates":null,"place":null,"contributors":null,"retweet_count":0,"favorite_count":0,"entities":{"hashtags":[],"symbols":[],"user_mentions":[],"urls":[]},"favorited":false,"retweete

*/