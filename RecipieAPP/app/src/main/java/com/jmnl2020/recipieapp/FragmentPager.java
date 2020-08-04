package com.jmnl2020.recipieapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class FragmentPager extends FragmentPagerAdapter {

    Fragment[] fragments= new Fragment[5];
    String[] tabText= {"추천", "분류", "최신", "지도", "카메라"};

    public FragmentPager(@NonNull FragmentManager fm) {
        super(fm);

        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
//        fragments[3] = new Tab4Fragment();
        SupportMapFragment mapFragment= new SupportMapFragment();
        fragments[3] = mapFragment;

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

            }
        });

        fragments[4] = new Tab1Fragment();

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
    //연동시키면 기본적으로 탭버튼에 보여질 글씨를 리턴하는 메소드


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabText[position];
    }
}
