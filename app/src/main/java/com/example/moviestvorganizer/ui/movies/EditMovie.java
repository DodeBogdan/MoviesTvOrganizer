package com.example.moviestvorganizer.ui.movies;

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
import com.example.moviestvorganizer.R;

public class EditMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        Movie movie = (Movie) getIntent().getExtras().get("movie");

        Button edit = findViewById(R.id.editMovie_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DBHelper db = new DBHelper(getApplicationContext());

                EditText editNameText = findViewById(R.id.editMovie_name);
                EditText editDescriptionText = findViewById(R.id.editMovie_description);
                EditText editRatingText = findViewById(R.id.editMovie_rating);

                String name = editNameText.getText().toString();
                if(name.isEmpty())
                {
                    name = movie.getName();
                }

                String description = editDescriptionText.getText().toString();
                if(description.isEmpty())
                    description = movie.getDescription();

                int rating;

                if(editRatingText.getText().toString().isEmpty())
                {
                    rating = movie.getRating();
                }
                else
                rating = Integer.parseInt(editRatingText.getText().toString());

                Movie newMovie = new Movie(name,description,rating);

                Boolean checkInsertData = db.updateMovie(movie,newMovie);
                if(checkInsertData == true)
                {
                    Toast.makeText(EditMovie.this, "Entry Update", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditMovie.this, "Entry Not update", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button cancel = findViewById(R.id.editMovie_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}