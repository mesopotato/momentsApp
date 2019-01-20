package com.example.admin.moments_firstproject;

import android.content.Intent;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.admin.moments_firstproject.db.AppDatabase;
import com.example.admin.moments_firstproject.db.DbSingelton;
import com.example.admin.moments_firstproject.db.Eintrag;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        String title = intent.getStringExtra(CreateActivity.EXTRA_TITLE);
        String detail = intent.getStringExtra(CreateActivity.EXTRA_DETAIL);
        String lng = intent.getStringExtra(CreateActivity.EXTRA_LONG);
        String lat = intent.getStringExtra(CreateActivity.EXTRA_LAT);
        String date = intent.getStringExtra(CreateActivity.EXTRA_DATE);
        String path = intent.getStringExtra(CreateActivity.EXTRA_IMGPATH);

        Eintrag eintrag = new Eintrag(title, detail, path, lng, lat, date);
        AppDatabase db = DbSingelton.getInstance(null);
        db.dao().insertAll(eintrag);

        Eintrag eintragLast = db.dao().getLast();

        setContentView(R.layout.activity_details);
        TextView TextTitle = findViewById(R.id.TextTitle);
        TextTitle.setText(eintragLast.getTitel());

        TextView TextDetails = findViewById(R.id.TextDetails);
        TextDetails.setText(eintragLast.getDetail());

        TextView location = findViewById(R.id.location);
        location.setText("Latitude : " + eintragLast.getLat() + "\nLongitude : " + eintragLast.getLng());

        TextView dateOut = findViewById(R.id.date);
        dateOut.setText(eintragLast.getDate());

        ImageButton img = findViewById(R.id.imageButton);
        img.setBackgroundResource(android.R.drawable.dialog_holo_dark_frame);
    }

    public void newPost(View view) {

        // Do something in response to button
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
}
