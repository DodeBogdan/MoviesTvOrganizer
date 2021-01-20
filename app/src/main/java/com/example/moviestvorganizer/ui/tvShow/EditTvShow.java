package com.example.moviestvorganizer.ui.tvShow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviestvorganizer.DataBase.DBHelper;
import com.example.moviestvorganizer.MainActivity;
import com.example.moviestvorganizer.Models.Movie;
import com.example.moviestvorganizer.Models.TvShow;
import com.example.moviestvorganizer.R;
import com.example.moviestvorganizer.ui.movies.EditMovie;

public class EditTvShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tv_show);

        TvShow tvShow = (TvShow) getIntent().getExtras().get("tvShow");

        Button edit = findViewById(R.id.editTvShow_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DBHelper db = new DBHelper(getApplicationContext());

                EditText editNameText = findViewById(R.id.editTvShow_name);
                EditText editDescriptionText = findViewById(R.id.editTvShow_description);
                EditText editRatingText = findViewById(R.id.editTvShow_rating);
                EditText editNoSeasonText = findViewById(R.id.editTvShow_noSeasons);
                EditText editCurrentSeasonText = findViewById(R.id.editTvShow_currentSeason);
                EditText editCurrentEpisodeText = findViewById(R.id.editTvShow_currentEpisode);

                String name = editNameText.getText().toString();
                if(name.isEmpty())
                {
                    name = tvShow.getName();
                }

                String description = editDescriptionText.getText().toString();
                if(description.isEmpty())
                    description = tvShow.getDescription();

                int rating;
                if(editRatingText.getText().toString().isEmpty())
                {
                    rating = tvShow.getRating();
                }
                else
                    rating = Integer.parseInt(editRatingText.getText().toString());

                int noSeasons;
                if(editNoSeasonText.getText().toString().isEmpty())
                {
                    noSeasons = tvShow.getNoSeasons();
                }
                else
                    noSeasons = Integer.parseInt(editRatingText.getText().toString());

                int currentSeason;
                if(editCurrentSeasonText.getText().toString().isEmpty())
                {
                    currentSeason = tvShow.getSeason();
                }
                else
                    currentSeason = Integer.parseInt(editCurrentSeasonText.getText().toString());

                int currentEpisode;
                if(editCurrentEpisodeText.getText().toString().isEmpty())
                {
                    currentEpisode = tvShow.getEpisode();
                }
                else
                    currentEpisode = Integer.parseInt(editCurrentEpisodeText.getText().toString());


                TvShow newTvShow = new TvShow(name, noSeasons, currentSeason, currentEpisode, description, rating);

                Boolean checkInsertData = db.updateTvShow(tvShow,newTvShow);
                if(checkInsertData == true)
                {
                    Toast.makeText(EditTvShow.this, "Entry Update", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditTvShow.this, "Entry Not update", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button cancel = findViewById(R.id.editTvShow_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}