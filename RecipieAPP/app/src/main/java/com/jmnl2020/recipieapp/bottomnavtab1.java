package com.jmnl2020.recipieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottomnavtab1 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnavtab1);

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.bottomnav1_home:
                        Intent a = new Intent(bottomnavtab1.this, MainActivity.class);
                        startActivity(a);
                        break;

                    case R.id.bottomnav2_save:
                        Intent b = new Intent(bottomnavtab1.this, bottomnavtab1.class);
                        startActivity(b);
                        break;

                    case R.id.bottomnav3_share:
                        Intent c = new Intent(bottomnavtab1.this, bottomnavtab2.class);
                        startActivity(c);
                        break;

                    case R.id.bottomnav4_call:
                        Intent d = new Intent(bottomnavtab1.this, bottomnavtab3.class);
                        startActivity(d);
                        break;

                    case R.id.bottomnav5_help:
                        Intent e = new Intent(bottomnavtab1.this, bottomnavtab4.class);
                        startActivity(e);
                        break;
                }


                return true;
            }

        });

    }
}
