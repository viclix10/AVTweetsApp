package com.codepath.apps.avtweetsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by amber_ved on 2/22/15.
 */
public class CurrentUser implements Parcelable {

    public String getCreated_at() {
        return created_at;
    }

    public String getDescription() {
        return description;
    }

    public long getFavourites_count() {
        return favourites_count;
    }

    public long getFollowers_count() {
        return followers_count;
    }

    public long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getProfile_background_image_url() {
        return profile_background_image_url;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public long getStatuses_count() {
        return statuses_count;
    }

    private String created_at;
    private String description;
    private long favourites_count;
    private long followers_count;
    private long id;
    private String location;
    private String name;
    private String profile_image_url;
    private String profile_background_image_url;
    private String  screen_name;
    private long statuses_count;

    public static CurrentUser fromJSON(JSONObject json) {
        CurrentUser u = new CurrentUser();
        try {
            u.created_at = json.getString("created_at");
            u.description = json.getString("description");
            u.favourites_count = json.getLong("favourites_count");
            u.followers_count = json.getLong("followers_count");
            u.id = json.getLong("id");
            u.location = json.getString("location");
            u.name = json.getString("name");
            u.profile_image_url = json.getString("profile_image_url");
            u.profile_background_image_url = json.getString("profile_background_image_url");
            u.screen_name = json.getString("screen_name");
            u.statuses_count = json.getLong("statuses_count");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.created_at);
        dest.writeString(this.description);
        dest.writeLong(this.favourites_count);
        dest.writeLong(this.followers_count);
        dest.writeLong(this.id);
        dest.writeString(this.location);
        dest.writeString(this.name);
        dest.writeString(this.profile_image_url);
        dest.writeString(this.profile_background_image_url);
        dest.writeString(this.screen_name);
        dest.writeLong(this.statuses_count);
    }

    public CurrentUser() {
    }

    private CurrentUser(Parcel in) {
        this.created_at = in.readString();
        this.description = in.readString();
        this.favourites_count = in.readLong();
        this.followers_count = in.readLong();
        this.id = in.readLong();
        this.location = in.readString();
        this.name = in.readString();
        this.profile_image_url = in.readString();
        this.profile_background_image_url = in.readString();
        this.screen_name = in.readString();
        this.statuses_count = in.readLong();
    }

    public static final Parcelable.Creator<CurrentUser> CREATOR = new Parcelable.Creator<CurrentUser>() {
        public CurrentUser createFromParcel(Parcel source) {
            return new CurrentUser(source);
        }

        public CurrentUser[] newArray(int size) {
            return new CurrentUser[size];
        }
    };
}

/*
    {
    "contributors_enabled": true,
    "created_at": "Sat May 09 17:58:22 +0000 2009",
    "default_profile": false,
    "default_profile_image": false,
    "description": "I taught your phone that thing you like.  The Mobile Partner Engineer @Twitter. ",
    "favourites_count": 588,
    "follow_request_sent": null,
    "followers_count": 10625,
    "following": null,
    "friends_count": 1181,
    "geo_enabled": true,
    "id": 38895958,
    "id_str": "38895958",
    "is_translator": false,
    "lang": "en",
    "listed_count": 190,
    "location": "San Francisco",
    "name": "Sean Cook",
    "notifications": null,
    "profile_background_color": "1A1B1F",
    "profile_background_image_url": "http://a0.twimg.com/profile_background_images/495742332/purty_wood.png",
    "profile_background_image_url_https": "https://si0.twimg.com/profile_background_images/495742332/purty_wood.png",
    "profile_background_tile": true,
    "profile_image_url": "http://a0.twimg.com/profile_images/1751506047/dead_sexy_normal.JPG",
    "profile_image_url_https": "https://si0.twimg.com/profile_images/1751506047/dead_sexy_normal.JPG",
    "profile_link_color": "2FC2EF",
    "profile_sidebar_border_color": "181A1E",
    "profile_sidebar_fill_color": "252429",
    "profile_text_color": "666666",
    "profile_use_background_image": true,
    "protected": false,
    "screen_name": "theSeanCook",
    "show_all_inline_media": true,
    "status": {
        "contributors": null,
        "coordinates": {
            "coordinates": [
                -122.45037293,
                37.76484123
            ],
            "type": "Point"
        },
        "created_at": "Tue Aug 28 05:44:24 +0000 2012",
        "favorited": false,
        "geo": {
            "coordinates": [
                37.76484123,
                -122.45037293
            ],
            "type": "Point"
        },
        "id": 240323931419062272,
        "id_str": "240323931419062272",
        "in_reply_to_screen_name": "messl",
        "in_reply_to_status_id": 240316959173009410,
        "in_reply_to_status_id_str": "240316959173009410",
        "in_reply_to_user_id": 18707866,
        "in_reply_to_user_id_str": "18707866",
        "place": {
            "attributes": {},
            "bounding_box": {
                "coordinates": [
                    [
                        [
                            -122.45778216,
                            37.75932999
                        ],
                        [
                            -122.44248216,
                            37.75932999
                        ],
                        [
                            -122.44248216,
                            37.76752899
                        ],
                        [
                            -122.45778216,
                            37.76752899
                        ]
                    ]
                ],
                "type": "Polygon"
            },
            "country": "United States",
            "country_code": "US",
            "full_name": "Ashbury Heights, San Francisco",
            "id": "866269c983527d5a",
            "name": "Ashbury Heights",
            "place_type": "neighborhood",
            "url": "http://api.twitter.com/1/geo/id/866269c983527d5a.json"
        },
        "retweet_count": 0,
        "retweeted": false,
        "source": "<a>Twitter for  iPhone</a>",
        "text": "@messl congrats! So happy for all 3 of you.",
        "truncated": false
    },
    "statuses_count": 2609,
    "time_zone": "Pacific Time (US & Canada)",
    "url": null,
    "utc_offset": -28800,
    "verified": false
}
*/