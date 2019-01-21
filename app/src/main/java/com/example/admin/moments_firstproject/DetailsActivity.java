package com.example.admin.moments_firstproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.admin.moments_firstproject.db.AppDatabase;
import com.example.admin.moments_firstproject.db.DbSingelton;
import com.example.admin.moments_firstproject.db.Eintrag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    String path;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        String title = intent.getStringExtra(CreateActivity.EXTRA_TITLE);
        String detail = intent.getStringExtra(CreateActivity.EXTRA_DETAIL);
        String lng = intent.getStringExtra(CreateActivity.EXTRA_LONG);
        String lat = intent.getStringExtra(CreateActivity.EXTRA_LAT);
        String date = intent.getStringExtra(CreateActivity.EXTRA_DATE);
        path = intent.getStringExtra(CreateActivity.EXTRA_IMGPATH);

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

        Bitmap temp = readImageFile();
        if (temp.getWidth() >= temp.getHeight()){

            bitmap = Bitmap.createBitmap(
                    temp,
                    temp.getWidth()/2 - temp.getHeight()/2,
                    0,
                    temp.getHeight(),
                    temp.getHeight()
            );

        }else{

            bitmap = Bitmap.createBitmap(
                    temp,
                    0,
                    temp.getHeight()/2 - temp.getWidth()/2,
                    temp.getWidth(),
                    temp.getWidth()
            );
        }
        Bitmap zwischenLagerung = Bitmap.createScaledBitmap(bitmap, img.getWidth(), img.getHeight(), true); //skalieren um aufs Bild zu pasen
        img.setImageBitmap(zwischenLagerung);


    }

    public void newPost(View view) {

        // Do something in response to button
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    private Bitmap readImageFile() {
        File file = new File(path);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (FileNotFoundException e) {
            Log.e("DECODER", "Could not find image file", e);
            return null;
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
