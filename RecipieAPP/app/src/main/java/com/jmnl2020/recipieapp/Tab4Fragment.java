package com.jmnl2020.recipieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.zip.Inflater;

public class Tab4Fragment extends Fragment {

    //1. GoogleMap Library 부터 추가[play-services-maps]

    //2. 구글 지도 사용에 대한 API키 발급받기

    //일단 구글 지도를 제어하는 객체 참조변수
    GoogleMap GMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab4, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        FragmentManager fragmentManager =getActivity().getSupportFragmentManager();

        final SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //내 멤버변수에 얻어온 GoogleMap 대입
                GMap = googleMap;

                // 원하는 좌표 객체 생성
                LatLng seoul = new LatLng(37.56, 127.63);

                //마커 옵션 객체 생성
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(seoul);
                markerOptions.title("서울 어딘가");
                markerOptions.snippet("대한민국의 수도");

                //지도에 마커 추가
                GMap.addMarker(markerOptions);

                //카메라 이동 스무스하게 + zoom
                GMap.animateCamera(CameraUpdateFactory.newLatLngZoom(seoul, 17));

            }

        });
    }
}
