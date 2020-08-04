package com.jmnl2020.retryex57;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class detail extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        int imgID= intent.getIntExtra("img", R.drawable.bg_one01);

        iv= findViewById(R.id.iv);
        Glide.with(this).load(imgID).into(iv);

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            iv.setTransitionName("IMG");
        }

    }
}
