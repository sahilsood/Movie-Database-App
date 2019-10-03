package com.example.moviedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Comparator;

public class AddMovie extends AppCompatActivity implements Serializable {
    private EditText movieName, movieYear, movieImdb, movieDesc;
    private Spinner movieGenre;
    private SeekBar movieRating;
    private TextView setRating;
    private Button btnAddMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Add Movie");

        movieName = (EditText) findViewById(R.id.et_name);
        movieYear = (EditText) findViewById(R.id.et_year);
        movieImdb = (EditText) findViewById(R.id.et_imdb);
        movieDesc = (EditText) findViewById(R.id.et_desc);
        movieGenre = (Spinner) findViewById(R.id.spinnerGenre);
        movieRating = (SeekBar) findViewById(R.id.sb_rating);
        setRating = (TextView) findViewById(R.id.tv_setRating);
        btnAddMovie = (Button) findViewById(R.id.btnAddMovie);

        movieRating.setMax(5);
        movieRating.setProgress(1);
        setRating.setText(String.valueOf(movieRating.getProgress()));
        movieRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setRating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        String[] arraySpinner = new String[] {
                "Action", "Animation", "Comedy", "Documentary", "Family", "Horror", "Crime","Others"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movieGenre.setAdapter(adapter);


        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mName = movieName.getText().toString();
                String mDesc = movieDesc.getText().toString();
                String mYear = movieYear.getText().toString();
                String mImdb = movieImdb.getText().toString();
                if(mName.equals("")){
                    movieName.setError("Please Enter Movie Name");
                }
                else if(mDesc.equals("")){
                    movieDesc.setError("Please Enter Movie Description");
                }
                else if(mYear.equals("")){
                    movieYear.setError("Please Enter Movie Year");
                }
                else if(mImdb.equals("")){
                    movieImdb.setError("Please Enter Movie IMDB");
                }
                else{
                    MovieInfo movieInfo = new MovieInfo(mName,mDesc,movieGenre.getSelectedItem().toString(),Integer.parseInt(mYear),movieRating.getProgress(),mImdb);
                    Log.d("demo",movieInfo.toString());

                    if(movieInfo == null){
                        setResult(RESULT_CANCELED);
                    }else{
                        Intent intent = new Intent();
                        intent.putExtra(MainActivity.INFO,movieInfo);
                        setResult(RESULT_OK,intent);
                    }
                    finish();
                }

            }
        });



    }
}
