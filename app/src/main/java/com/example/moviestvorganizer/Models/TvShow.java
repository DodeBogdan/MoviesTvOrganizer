package com.example.moviestvorganizer.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.util.ArrayList;

public class TvShow implements Parcelable {
    private String name;
    private int noSeasons;
    private int season;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    private int episode;
    private String description;
    private Integer rating;

    protected TvShow(Parcel in) {
        name = in.readString();
        noSeasons = in.readInt();
        description = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readInt();
        }
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoSeasons() {
        return noSeasons;
    }

    public void setNoSeasons(int noSeasons) {
        this.noSeasons = noSeasons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public TvShow(String name, int noSeasons,int season, int episode, String description, Integer rating) {
        this.name = name;
        this.noSeasons = noSeasons;
        this.season = season;
        this.episode = episode;
        this.description = description;
        this.rating = rating;


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(noSeasons);
        dest.writeString(description);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rating);
        }
    }
}
