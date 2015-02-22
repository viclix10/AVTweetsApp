package com.codepath.apps.avtweetsapp.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
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
 */

public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    //list att;
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;

    public static User fromJSON(JSONObject json) {
        User u = new User();
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }
}
