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

public class AddMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        Button cancel = findViewById(R.id.addMovie_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button submit = findViewById(R.id.addMovie_add);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DBHelper db = new DBHelper(getApplicationContext());
                EditText editNameText = findViewById(R.id.addMovie_name);
                EditText editDescriptionText = findViewById(R.id.addMovie_description);
                EditText editRatingText = findViewById(R.id.addMovie_rating);

                String name = editNameText.getText().toString();
                String description = editDescriptionText.getText().toString();
                int rating = Integer.parseInt(editRatingText.getText().toString());

                Boolean checkInsertedData = db.insertDataIntoMovie(new Movie(name,description,rating));
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