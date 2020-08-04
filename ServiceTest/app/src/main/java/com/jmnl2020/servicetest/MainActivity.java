package com.jmnl2020.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickPlay(View view) {
        //뮤직을 백그라운드에서 실행
        Intent intent = new Intent(this, MusicService.class);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(intent);
        else startService(intent);
    }

    public void clickStop(View view) {
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);

    }

}
