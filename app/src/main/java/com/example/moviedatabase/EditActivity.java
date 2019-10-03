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

import java.util.Arrays;

public class EditActivity extends AppCompatActivity {
    private EditText editMovieName, editMovieYear, editMovieImdb, editMovieDesc;
    private Spinner editMovieGenre;
    private SeekBar editMovieRating;
    private TextView editSetRating;
    private Button btnEdit;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Edit Movie");

        editMovieName = (EditText) findViewById(R.id.et_editName);
        editMovieYear = (EditText) findViewById(R.id.et_editYear);
        editMovieImdb = (EditText) findViewById(R.id.et_editImdb);
        editMovieDesc = (EditText) findViewById(R.id.et_editDesc);
        editMovieGenre = (Spinner) findViewById(R.id.editSpinnerGenre);
        editMovieRating = (SeekBar) findViewById(R.id.sb_editRating);
        editSetRating = (TextView) findViewById(R.id.tv_editSetRating);
        btnEdit = (Button) findViewById(R.id.btnEdit);


        if (getIntent() != null && getIntent().getExtras() != null) {
            final MovieInfo thisInfo = (MovieInfo) getIntent().getExtras().getSerializable(MainActivity.EDIT);
            editMovieName.setText(thisInfo.name);
            editMovieYear.setText(String.valueOf(thisInfo.year));
            editMovieImdb.setText(thisInfo.imdb);
            editMovieDesc.setText(thisInfo.desc);
            index = getIntent().getExtras().getInt(MainActivity.INDEX);
            String[] arraySpinner = new String[] {
                    "Action", "Animation", "Comedy", "Documentary", "Family", "Horror", "Crime","Others"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            editMovieGenre.setAdapter(adapter);
            editMovieGenre.setSelection(Arrays.asList(arraySpinner).indexOf(thisInfo.genre));


            editMovieRating.setMax(5);
            editMovieRating.setProgress(thisInfo.rating);
            editSetRating.setText(String.valueOf(editMovieRating.getProgress()));
            editMovieRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    editSetRating.setText(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mName = editMovieName.getText().toString();
                String mDesc = editMovieDesc.getText().toString();
                String mYear = editMovieYear.getText().toString();
                String mImdb = editMovieImdb.getText().toString();
                if(mName.equals("")){
                    editMovieName.setError("Movie Name cannot be Empty");
                }
                else if(mDesc.equals("")){
                    editMovieDesc.setError("Movie Description cannot be Empty");
                }
                else if(mYear.equals("")){
                    editMovieYear.setError("Movie Year cannot be Empty");
                }
                else if(mImdb.equals("")){
                    editMovieImdb.setError("Movie IMDB cannot be Empty");
                }
                else{
                    MovieInfo editMovieInfo = new MovieInfo(mName,mDesc,editMovieGenre.getSelectedItem().toString(),Integer.parseInt(mYear),editMovieRating.getProgress(),mImdb);

                    if(editMovieInfo == null){
                        setResult(RESULT_CANCELED);
                    }else{
                        Intent intent = new Intent();
                        intent.putExtra(MainActivity.EDIT,editMovieInfo);
                        intent.putExtra(MainActivity.INDEX,index);
                        setResult(RESULT_OK,intent);
                    }
                    finish();
                }
            }
        });
    }
}
