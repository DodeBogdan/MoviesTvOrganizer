package com.example.moviestvorganizer.ui.movies;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moviestvorganizer.DataBase.DBHelper;
import com.example.moviestvorganizer.Models.Movie;
import com.example.moviestvorganizer.R;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_movies, container, false);

        DBHelper db = new DBHelper(getContext());
        ArrayList<Movie> movieList = new ArrayList<>();

        Cursor res = db.getDataFromMovie();

        if(res.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Entry", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                movieList.add(new Movie(res.getString(0), res.getString(1),Integer.parseInt(res.getString(2))));
            }
        }

        ArrayList<String> names = new ArrayList<>();

        for(int index = 0 ; index < movieList.size(); index++)
        {
            names.add(movieList.get(index).getName());
        }

        ListView movie_ListView = (ListView) view.findViewById(R.id.movie_listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, names);
        movie_ListView.setAdapter(adapter);

        movie_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), MovieActivity.class);
                intent.putExtra("movie", movieList.get(position));

                startActivity(intent);
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        Button addMovie = (Button)view.findViewById(R.id.addMovie);
        addMovie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), AddMovie.class);
                startActivity(intent);
            }
        });

        return view;
    }
}