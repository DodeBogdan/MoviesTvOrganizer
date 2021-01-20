package com.example.moviestvorganizer.ui.movies;

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
import com.example.moviestvorganizer.R;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Movie movie = (Movie) getIntent().getExtras().get("movie");


        TextView textView = (TextView)findViewById(R.id.movieActivity_name);
        textView.setText(movie.getName());

        TextView description = (TextView)findViewById(R.id.movieActivity_description);
        description.setText(movie.getDescription());

        TextView rating = (TextView)findViewById(R.id.movieActivity_rating);
        rating.setText(String.valueOf(movie.getRating()));

        Button edit = findViewById(R.id.movieActivity_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EditMovie.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
                finish();
            }
        });

        Button delete = findViewById(R.id.movieActivity_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DBHelper db = new DBHelper(getApplicationContext());
                TextView editNameText = findViewById(R.id.movieActivity_name);

                String name = editNameText.getText().toString();

                Boolean checkDelete = db.deleteMovie(name);
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