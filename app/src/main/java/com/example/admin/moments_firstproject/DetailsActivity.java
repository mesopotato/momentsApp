package com.example.admin.moments_firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    public void newPost(View view) {

        // Do something in response to button
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
}
