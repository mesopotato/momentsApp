package com.example.admin.moments_firstproject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.moments_firstproject.db.AppDatabase;
import com.example.admin.moments_firstproject.db.DbSingelton;
import com.example.admin.moments_firstproject.db.Eintrag;
import com.example.admin.moments_firstproject.dummy.DummyContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {
    public static final String EXTRA_DETAIL = "com.example.admin.moments_firstproject.DETAIL";
    public static final String EXTRA_LONG = "com.example.admin.moments_firstproject.LONG";
    public static final String EXTRA_LAT = "com.example.admin.moments_firstproject.LAT";
    public static final String EXTRA_IMGPATH = "com.example.admin.moments_firstproject.IMGPATH";
    public static final String EXTRA_DATE = "com.example.admin.moments_firstproject.DATE";
    public static final String EXTRA_TITLE = "com.example.admin.moments_firstproject.TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        AppDatabase db = DbSingelton.getInstance(null);

        //new Thread(() -> {
        //input einspeichern
       // db.dao().insertAll(eintrag);
        //abfrage
        List<Eintrag> eintraege = db.dao().getAll();
        setContentView(R.layout.activity_display_message);
        if (eintraege.size() > 0){
            int i = eintraege.size() -1 ;

        // Get the Intent that started this activity and extract the string
            TextView textView = findViewById(R.id.textView2);
            textView.setText("MyMoments");
        }
        else {
            TextView textView = findViewById(R.id.textView2);
            textView.setText("Noch keine Moments vorhanden");}


         //.allowMainThreadQueries()
/*
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "myDB3").allowMainThreadQueries().build();

        db.dao().insertAll(eintrag);

        List<Eintrag> eintraege = db.dao().getAll();
        TextView textView = findViewById(R.id.textView2);
        int i = eintraege.size() -1;
        textView.setText(eintraege.get(i).getFirstName());
*/


    //    }).start();


     //  new Thread(() ->{

               /*
           Eintrag eintrag2 = db.dao().getLast();
           TextView textView = findViewById(R.id.textView2);
           textView.setText(eintrag2.getFirstName());
           */
    //    }).start();


        // Capture the layout's TextView and set the string as its text

    }

    @Override
    public void onListFragmentInteraction(Eintrag item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, item.getTitel());
        intent.putExtra(EXTRA_DETAIL, item.getDetail());
        intent.putExtra(EXTRA_IMGPATH, item.getImgPath());
        intent.putExtra(EXTRA_DATE, item.getDate());
        intent.putExtra(EXTRA_LAT, item.getLat());
        intent.putExtra(EXTRA_LONG, item.getLng());

        startActivity(intent);

    }
    public void newPost(View view) {

        // Do something in response to button
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

}
