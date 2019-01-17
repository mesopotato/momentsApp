package com.example.admin.moments_firstproject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.moments_firstproject.db.AppDatabase;
import com.example.admin.moments_firstproject.db.Eintrag;

import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Eintrag eintrag = new Eintrag();
        eintrag.setFirstName(message);

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
                //input einspeichern
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "myDB3").build();

     /*   new Thread(() -> {
            db.dao().insertAll(eintrag);
        }).start(); */

                //abfrage
       new Thread(() ->{

            List<Eintrag> eintraege = db.dao().getAll();
            TextView textView = findViewById(R.id.textView2);
            int i = eintraege.size() -1 ;
            textView.setText(eintraege.get(i).getFirstName());
               /*
           Eintrag eintrag2 = db.dao().getLast();
           TextView textView = findViewById(R.id.textView2);
           textView.setText(eintrag2.getFirstName());
           */
        }).start();


        // Capture the layout's TextView and set the string as its text

    }
}
