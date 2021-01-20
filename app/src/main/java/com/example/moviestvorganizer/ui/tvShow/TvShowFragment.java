package com.example.moviestvorganizer.ui.tvShow;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.moviestvorganizer.DataBase.DBHelper;
import com.example.moviestvorganizer.Models.TvShow;
import com.example.moviestvorganizer.R;
import com.example.moviestvorganizer.ui.movies.MovieActivity;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);

        DBHelper db = new DBHelper(getContext());
        ArrayList<TvShow> tvShows = new ArrayList<>();

        Cursor res = db.getDataFromTvShow();

        if(res.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Entry", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(res.moveToNext()){
                tvShows.add(new TvShow(res.getString(0),Integer.parseInt(res.getString(3)),Integer.parseInt(res.getString(4)),Integer.parseInt(res.getString(5)),res.getString(1),Integer.parseInt(res.getString(2))));
            }
        }

        ArrayList<String> names = new ArrayList<>();

        if(tvShows.size() != 0) {


            for (int index = 0; index < tvShows.size(); index++) {
                names.add(tvShows.get(index).getName());
            }

            ListView tvshowList = (ListView) view.findViewById(R.id.tvShow_listView);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, names);
            tvshowList.setAdapter(adapter);

            tvshowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(view.getContext(), TvShowActivity.class);
                    intent.putExtra("tvShow", tvShows.get(position));

                    startActivity(intent);
                    try {
                        finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            });
        }
        Button addTvShow = (Button)view.findViewById(R.id.addTvShow);
        addTvShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), AddTvShow.class);
                startActivity(intent);
            }
        });

        return view;
    }
}