package com.example.moviestvorganizer.ui.tvShow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviestvorganizer.DataBase.DBHelper;
import com.example.moviestvorganizer.MainActivity;
import com.example.moviestvorganizer.Models.Movie;
import com.example.moviestvorganizer.Models.TvShow;
import com.example.moviestvorganizer.R;
import com.example.moviestvorganizer.ui.movies.EditMovie;

public class TvShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show);

        TvShow tvShow = (TvShow) getIntent().getExtras().get("tvShow");


        TextView name = (TextView)findViewById(R.id.tvShowActivity_name);
        name.setText(tvShow.getName());

        TextView description = (TextView)findViewById(R.id.tvShowActivity_description);
        description.setText(tvShow.getDescription());

        TextView rating = (TextView)findViewById(R.id.tvShowActivity_rating);
        rating.setText(tvShow.getRating().toString());

        TextView noSeasons = (TextView)findViewById(R.id.tvShowActivity_noSeasons);
        noSeasons.setText(String.valueOf(tvShow.getNoSeasons()));

        TextView currentEpisode = (TextView)findViewById(R.id.tvShowActivity_currentEpisode);
        currentEpisode.setText(String.valueOf(tvShow.getSeason()));

        TextView currentSeason = (TextView)findViewById(R.id.tvShowActivity_currentSeason);
        currentSeason.setText(String.valueOf(tvShow.getEpisode()));


        Button edit = findViewById(R.id.tvShowActivity_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EditTvShow.class);
                intent.putExtra("tvShow", tvShow);
                startActivity(intent);
                finish();
            }
        });

        Button delete = findViewById(R.id.tvShowActivity_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DBHelper db = new DBHelper(getApplicationContext());
                TextView editNameText = findViewById(R.id.tvShowActivity_name);

                String name = editNameText.getText().toString();

                Boolean checkDelete = db.deleteTvShow(name);
                if(checkDelete == true)
                {
                    Toast.makeText(getApplicationContext(),"Delete Done", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error Delete", Toast.LENGTH_SHORT).show();

                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}