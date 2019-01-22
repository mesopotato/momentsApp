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

        setContentView(R.layout.activity_details);
        TextView TextTitle = findViewById(R.id.TextTitle);
        TextTitle.setText(eintrag.getTitel());

        TextView TextDetails = findViewById(R.id.TextDetails);
        TextDetails.setText(eintrag.getDetail());

        TextView location = findViewById(R.id.location);
        location.setText("Latitude : " + eintrag.getLat() + "\nLongitude : " + eintrag.getLng());

        TextView dateOut = findViewById(R.id.date);
        dateOut.setText(eintrag.getDate());

        ImageButton img = findViewById(R.id.imageButton);

        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        //Bitmap zwischenLagerung = Bitmap.createScaledBitmap(readImageFile(), img.getWidth(), img.getHeight(), true);
        //Bitmap rotatedBitmap = Bitmap.createBitmap(zwischenLagerung, 0, 0, zwischenLagerung.getWidth(), zwischenLagerung.getHeight(), matrix, true);
        img.setImageBitmap(readImageFile());

        //img.setBackgroundResource(android.R.drawable.dialog_holo_dark_frame);


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
    public void sendMessage(View view) {

        // Do something in response to button nämlich zur dysplay seite gehen aber nicht bevor startActivity(intent)
        Intent intent = new Intent(this, MainActivity.class);

        //** hier wird der text der eingegeben uwrde abgeholt

        startActivity(intent);
    }

}
