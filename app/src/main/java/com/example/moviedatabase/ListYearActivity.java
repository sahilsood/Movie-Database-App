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
import java.util.Comparator;

public class ListYearActivity extends AppCompatActivity {
    private TextView titleY, genreY, ratingY, yearY, imdbY;
    private EditText descY;
    private ImageButton firstY, previousY, nextY, lastY;
    private Button finishY;
    private int index = 0;
    ArrayList<MovieInfo> listYearObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_year);
        setTitle("Movies by Year");

        titleY = (TextView) findViewById(R.id.tv_titleY);
        genreY = (TextView) findViewById(R.id.tv_genreY);
        ratingY = (TextView) findViewById(R.id.tv_ratingY);
        yearY = (TextView) findViewById(R.id.tv_yearY);
        imdbY = (TextView) findViewById(R.id.tv_imdbY);
        descY = (EditText) findViewById(R.id.et_descY);
        firstY = (ImageButton) findViewById(R.id.ib_firstY);
        previousY = (ImageButton) findViewById(R.id.ib_previousY);
        nextY = (ImageButton) findViewById(R.id.ib_nextY);
        lastY = (ImageButton) findViewById(R.id.ib_lastY);
        finishY = (Button) findViewById(R.id.btn_finishY);

        listYearObj = (ArrayList) getIntent().getExtras().getSerializable(MainActivity.LISTYEAR);
        Collections.sort(listYearObj, MovieInfo.yearSort);

        setValues(index);

        firstY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("first",""+index);
                previousY.setEnabled(false);
                previousY.setAlpha(150);
                nextY.setEnabled(true);
                nextY.setAlpha(255);
                setValues(index);
            }
        });

        lastY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("last",""+index);
                nextY.setEnabled(false);
                nextY.setAlpha(150);
                previousY.setEnabled(true);
                previousY.setAlpha(255);
                setValues(listYearObj.size()-1);
            }
        });

        nextY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousY.setEnabled(true);
                previousY.setAlpha(255);
                index++;
                Log.i("next",""+index);
                setValues(index);
                if(index==listYearObj.size()-1){
                    System.out.println(index+"next");
                    nextY.setEnabled(false);
                    nextY.setAlpha(150);
                }
            }
        });

        previousY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextY.setEnabled(true);
                nextY.setAlpha(255);
                index--;
                Log.i("previous",""+index);
                setValues(index);
                if(index==0){
                    previousY.setEnabled(false);
                    previousY.setAlpha(150);
                }

            }
        });

        finishY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void setValues(int index) {
        if(!listYearObj.isEmpty()) {
            titleY.setText(listYearObj.get(index).name);
            genreY.setText(listYearObj.get(index).genre);
            descY.setText(listYearObj.get(index).desc);
            yearY.setText(String.valueOf(listYearObj.get(index).year));
            imdbY.setText(listYearObj.get(index).imdb);
            ratingY.setText(String.valueOf(listYearObj.get(index).rating));
            if(listYearObj.size()==1){
                nextY.setEnabled(false);
                nextY.setAlpha(150);
                previousY.setEnabled(false);
                previousY.setAlpha(150);
            }
            else if(listYearObj.size()>1){
                if(index==0) {
                    previousY.setEnabled(false);
                    previousY.setAlpha(150);
                }
                else if(index==-1){
                    nextY.setEnabled(false);
                    nextY.setAlpha(150);
                }
            }
        }
        else{
            firstY.setEnabled(false);
            previousY.setEnabled(false);
            nextY.setEnabled(false);
            lastY.setEnabled(false);
            firstY.setAlpha(150);
            previousY.setAlpha(150);
            nextY.setAlpha(150);
            lastY.setAlpha(150);
            Toast.makeText(this, "No movies!", Toast.LENGTH_SHORT).show();
        }
    }
}
