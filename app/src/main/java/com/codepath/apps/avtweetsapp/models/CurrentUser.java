package com.codepath.apps.avtweetsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Table(name = "CurrentUser")
public class CurrentUser implements Parcelable {

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "description")
    private String description;

    @Column(name = "favourites_count")
    private long favourites_count;

    @Column(name = "followers_count")
    private long followers_count;

    @Column(name = "id")
    private long id;

    @Column(name = "location")
    private String location;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_image_url")
    private String profile_image_url;

    @Column(name = "profile_background_image_url")
    private String profile_background_image_url;

    @Column(name = "screen_name")
    private String  screen_name;

    @Column(name = "statuses_count")
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

    public CurrentUser() {
    }

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

    public static final Creator<CurrentUser> CREATOR = new Creator<CurrentUser>() {
        public CurrentUser createFromParcel(Parcel source) {
            return new CurrentUser(source);
        }

        public CurrentUser[] newArray(int size) {
            return new CurrentUser[size];
        }
    };
}