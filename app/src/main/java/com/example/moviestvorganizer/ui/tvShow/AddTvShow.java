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
import com.example.moviestvorganizer.Models.TvShow;
import com.example.moviestvorganizer.R;

public class AddTvShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tv_show);

        Button cancel = findViewById(R.id.addTvShow_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button submit = findViewById(R.id.addTvShow_add);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DBHelper db = new DBHelper(getApplicationContext());
                EditText editNameText = findViewById(R.id.addTvShow_name);
                EditText editDescriptionText = findViewById(R.id.addTvShow_description);
                EditText editRatingText = findViewById(R.id.addTvShow_rating);
                EditText editNoSeasonsText = findViewById(R.id.editTvShow_noSeasons);
                EditText editCurrentEpisodeText = findViewById(R.id.addTvShow_currentEpisode);
                EditText editCurrentSeasonText = findViewById(R.id.addTvShow_currentSeason);

                String name = editNameText.getText().toString();
                String description = editDescriptionText.getText().toString();
                int rating = Integer.parseInt(editRatingText.getText().toString());
                int noSeasons = Integer.parseInt(editNoSeasonsText.getText().toString());
                int currentEpisode = Integer.parseInt(editCurrentEpisodeText.getText().toString());
                int currentSeason = Integer.parseInt(editCurrentSeasonText.getText().toString());

                Boolean checkInsertedData = db.insertDataIntoTvShow(new TvShow(name,noSeasons,currentSeason,currentEpisode,description,rating));
                if(checkInsertedData == true)
                {
                    Toast.makeText(getApplicationContext(),"New Entry Inserted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"New NO Entry Inserted", Toast.LENGTH_SHORT).show();

                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}