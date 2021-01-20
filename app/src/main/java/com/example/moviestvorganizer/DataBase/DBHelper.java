package com.example.moviestvorganizer.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.moviestvorganizer.Models.Movie;
import com.example.moviestvorganizer.Models.TvShow;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "MovieAndSeries.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Movie(name Text primary key, description Text, rating int)");
        db.execSQL("create Table TvShow(name Text primary key, description Text, rating int,seasons int, episode int, season int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Movie");
        db.execSQL("drop Table if exists TvShow");
    }

    public Boolean insertDataIntoMovie(Movie movie)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", movie.getName());
        contentValues.put("description", movie.getDescription());
        contentValues.put("rating", movie.getRating());

        long result = DB.insert("Movie", null, contentValues);

        if(result == - 1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Boolean insertDataIntoTvShow(TvShow tvShow)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", tvShow.getName());
        contentValues.put("description", tvShow.getDescription());
        contentValues.put("rating", tvShow.getRating());
        contentValues.put("seasons", tvShow.getNoSeasons());
        contentValues.put("season", tvShow.getSeason());
        contentValues.put("episode", tvShow.getEpisode());

        long result = DB.insert("TvShow", null, contentValues);

        if(result == - 1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor getDataFromMovie()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Movie", null);

        return cursor;
    }

    public Cursor getDataFromTvShow()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from TvShow", null);

        return cursor;
    }

    public Boolean updateMovie(Movie initialMovie, Movie finalMovie) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", finalMovie.getName());
        contentValues.put("description", finalMovie.getDescription());
        contentValues.put("rating", finalMovie.getRating());

        Cursor cursor = DB.rawQuery("Select * from Movie where name = ?", new String[]{initialMovie.getName()});

        if (cursor.getCount() > 0) {
            long res = DB.update("Movie", contentValues, "name=?", new String[]{initialMovie.getName()});

            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return  false;
        }
    }

    public Boolean updateTvShow(TvShow initialTvShow, TvShow finalTvShow) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", finalTvShow.getName());
        contentValues.put("description", finalTvShow.getDescription());
        contentValues.put("rating", finalTvShow.getRating());
        contentValues.put("seasons", finalTvShow.getNoSeasons());
        contentValues.put("season", finalTvShow.getSeason());
        contentValues.put("episode", finalTvShow.getEpisode());

        Cursor cursor = DB.rawQuery("Select * from TvShow where name = ?", new String[]{initialTvShow.getName()});

        if (cursor.getCount() > 0) {
            long res = DB.update("TvShow", contentValues, "name=?", new String[]{initialTvShow.getName()});

            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return  false;
        }
    }


    public Boolean deleteMovie(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Movie where name = ?", new String[]{name});

        if (cursor.getCount() > 0) {
            long res = DB.delete("Movie", "name=?", new String[]{name});

            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return  false;
        }
    }

    public Boolean deleteTvShow(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from TvShow where name = ?", new String[]{name});

        if (cursor.getCount() > 0) {
            long res = DB.delete("TvShow", "name=?", new String[]{name});

            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return  false;
        }
    }
}
