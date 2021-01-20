package com.example.moviestvorganizer.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String name;
    private String description;
    private int rating;

    public Movie(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public Movie(String name) {
    }

    protected Movie(Parcel in) {
        name = in.readString();
        description = in.readString();
        rating = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(rating);
    }
}
