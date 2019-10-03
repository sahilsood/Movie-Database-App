package com.example.moviedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class RatingListActivity extends AppCompatActivity {
    private TextView titleR, genreR, ratingR, yearR, imdbR;
    private EditText descR;
    private ImageButton firstR, previousR, nextR, lastR;
    private Button finishR;
    private int index = 0;
    ArrayList<MovieInfo> listRatingObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_list);
        setTitle("Movies by Rating");

        titleR = (TextView) findViewById(R.id.tv_titleR);
        genreR = (TextView) findViewById(R.id.tv_genreR);
        ratingR = (TextView) findViewById(R.id.tv_ratingR);
        yearR = (TextView) findViewById(R.id.tv_yearR);
        imdbR = (TextView) findViewById(R.id.tv_imdbR);
        descR = (EditText) findViewById(R.id.et_descR);
        firstR = (ImageButton) findViewById(R.id.ib_firstR);
        previousR = (ImageButton) findViewById(R.id.ib_previousR);
        nextR = (ImageButton) findViewById(R.id.ib_nextR);
        lastR = (ImageButton) findViewById(R.id.ib_lastR);
        finishR = (Button) findViewById(R.id.btn_finishR);

        listRatingObj = (ArrayList) getIntent().getExtras().getSerializable(MainActivity.LISTRATING);
        Collections.sort(listRatingObj, MovieInfo.ratingSort);

        setValues(index);

        firstR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("first",""+index);
                previousR.setEnabled(false);
                previousR.setAlpha(150);
                nextR.setEnabled(true);
                nextR.setAlpha(255);
                setValues(index);
            }
        });

        lastR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("last",""+index);
                nextR.setEnabled(false);
                nextR.setAlpha(150);
                previousR.setEnabled(true);
                previousR.setAlpha(255);
                setValues(listRatingObj.size()-1);
            }
        });

        nextR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousR.setEnabled(true);
                previousR.setAlpha(255);
                index++;
                Log.i("next",""+index);
                setValues(index);
                if(index==listRatingObj.size()-1){
                    System.out.println(index+"next");
                    nextR.setEnabled(false);
                    nextR.setAlpha(150);
                }
            }
        });

        previousR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextR.setEnabled(true);
                nextR.setAlpha(255);
                index--;
                Log.i("previous",""+index);
                setValues(index);
                if(index==0){
                    previousR.setEnabled(false);
                    previousR.setAlpha(150);
                }

            }
        });

        finishR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void setValues(int index) {
        if(!listRatingObj.isEmpty()) {
            titleR.setText(listRatingObj.get(index).name);
            genreR.setText(listRatingObj.get(index).genre);
            descR.setText(listRatingObj.get(index).desc);
            yearR.setText(String.valueOf(listRatingObj.get(index).year));
            imdbR.setText(listRatingObj.get(index).imdb);
            ratingR.setText(String.valueOf(listRatingObj.get(index).rating));
            if(listRatingObj.size()==1){
                nextR.setEnabled(false);
                nextR.setAlpha(150);
                previousR.setEnabled(false);
                previousR.setAlpha(150);
            }
            else if(listRatingObj.size()>1){
                if(index==0) {
                    previousR.setEnabled(false);
                    previousR.setAlpha(150);
                }
                else if(index==-1){
                    nextR.setEnabled(false);
                    nextR.setAlpha(150);
                }
            }
        }
        else{
            firstR.setEnabled(false);
            previousR.setEnabled(false);
            nextR.setEnabled(false);
            lastR.setEnabled(false);
            firstR.setAlpha(150);
            previousR.setAlpha(150);
            nextR.setAlpha(150);
            lastR.setAlpha(150);
            Toast.makeText(this, "No movies!", Toast.LENGTH_SHORT).show();
        }
    }
}
