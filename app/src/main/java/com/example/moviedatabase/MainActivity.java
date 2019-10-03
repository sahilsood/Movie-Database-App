package com.example.moviedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button addMovie, editMovie, deleteMovie, listYear, listRating;
    public final static int REQ_CODE_ADD = 100;
    public final static int REQ_CODE_EDIT = 101;
    public final static int REQ_CODE_YEAR = 102;
    public final static int REQ_CODE_RATING = 103;
    static String INFO = "info";
    static String EDIT = "edit";
    static String INDEX = "index";
    static String LISTYEAR = "listYear";
    static String LISTRATING = "listRating";
    ArrayList<Object> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favorite Movies");

        addMovie = (Button) findViewById(R.id.btnAddNewMovie);
        editMovie = (Button) findViewById(R.id.btnEditMovie);
        deleteMovie = (Button) findViewById(R.id.btnDeleteMovie);
        listYear = (Button) findViewById(R.id.btnShowListYear);
        listRating = (Button) findViewById(R.id.btnShowListRating);

        addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(MainActivity.this,AddMovie.class);
                startActivityForResult(intentAdd, REQ_CODE_ADD);
            }
        });

        editMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> listItems = new ArrayList<String>();
                for(Object key: info){
                    try {
                        Field field = key.getClass().getDeclaredField("name");
                        Object value = field.get(key);
                        listItems.add(value.toString());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pick a Movie")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("dd", (String) items[i]);
                                Intent intentEdit = new Intent(MainActivity.this, EditActivity.class);
                                intentEdit.putExtra(EDIT, (Serializable) info.get(i));
                                intentEdit.putExtra(INDEX,i);
                                System.out.println(info.get(i).toString());
                                startActivityForResult(intentEdit, REQ_CODE_EDIT);
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });

        deleteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> listItems = new ArrayList<String>();
                for(Object key: info){
                    try {
                        Field field = key.getClass().getDeclaredField("name");
                        Object value = field.get(key);
                        listItems.add(value.toString());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pick a Movie")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                info.remove(i);
                                Toast.makeText(MainActivity.this, "Movie: "+items[i]+" Deleted!", Toast.LENGTH_SHORT).show();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });

        listYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentListYear = new Intent("com.example.implicitintent.intent.action.Year");
                intentListYear.putExtra(LISTYEAR, info);
                startActivityForResult(intentListYear, REQ_CODE_YEAR);
            }
        });

        listRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentListYear = new Intent("com.example.implicitintent.intent.action.Rating");
                intentListYear.putExtra(LISTRATING, info);
                startActivityForResult(intentListYear, REQ_CODE_RATING);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_ADD) {
            if (resultCode == RESULT_OK) {
                info.add(data.getSerializableExtra(INFO));

                Toast.makeText(this, "Movie Record Added!", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Movie Record is not Added!", Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == REQ_CODE_EDIT){
            if (resultCode == RESULT_OK) {
                info.set(data.getExtras().getInt(INDEX), data.getSerializableExtra(EDIT));
                Toast.makeText(this, "Movie Record Updated!", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Movie Record Updated!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
