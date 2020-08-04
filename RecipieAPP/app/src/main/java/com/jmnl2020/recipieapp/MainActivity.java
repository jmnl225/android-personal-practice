package com.jmnl2020.recipieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    TabLayout tabLayout;
    ViewPager pager;

    //페이저 어댑터
    FragmentPager fragmentadapter;

    //floating Button
    FloatingActionButton fab;

    //Bottomnavigation
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout= findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.nav);


        //툴바 생성
        setSupportActionBar(toolbar);
        //뷰페이저에 아답터 설정
        fragmentadapter = new FragmentPager(getSupportFragmentManager());
        pager.setAdapter(fragmentadapter);

        tabLayout.setupWithViewPager(pager);


        //floating listener
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "스낵바 액션 이벤트!",Snackbar.LENGTH_SHORT).setAction("꿀꺽", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });


        //각 탭 화면의 액티비티 변경




        bottomNavigationView= findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()){

                    case  R.id.bottomnav1_home:
                        break;

                    case  R.id.bottomnav2_save:
                        Intent b = new Intent(MainActivity.this, bottomnavtab1.class);
                        startActivity(b);
                        break;

                    case  R.id.bottomnav3_share:
                        Intent c = new Intent(MainActivity.this, bottomnavtab2.class);
                        startActivity(c);
                        break;

                    case  R.id.bottomnav4_call:
                        Intent d = new Intent(MainActivity.this, bottomnavtab3.class);
                        startActivity(d);
                        break;

                    case  R.id.bottomnav5_help:
                        Intent e = new Intent(MainActivity.this, bottomnavtab4.class);
                        startActivity(e);
                        break;
                }



                return true;
            }
        });


    }//onCreate end.
}
