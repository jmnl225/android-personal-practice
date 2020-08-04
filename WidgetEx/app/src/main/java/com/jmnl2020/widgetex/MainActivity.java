package com.jmnl2020.widgetex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ic01, ic02, ic03, ic04;
    ImageView moana, moanaimg;
    int n=0;

//    int[] arr= {R.drawable.moana01, R.drawable.moana02, R.drawable.moana03, R.drawable.moana04, R.drawable.moana05};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //이미지
        Toast t= Toast.makeText(this, " ", Toast.LENGTH_SHORT);

    }

    public void clickic(View view) {
        //토스트가 나오도록 설정

        //1. 아이디를 연결
        ic01= findViewById(R.id.ic01);
        ic02= findViewById(R.id.ic02);
        ic03= findViewById(R.id.ic03);
        ic04= findViewById(R.id.ic04);

        //이미지에 맞는 글씨가 나오도록 설정
        int idnum= view.getId();
        switch (idnum){
            case R.id.ic01:
                Toast t=Toast.makeText(this, "Heart", Toast.LENGTH_SHORT);
                t.show();
                break;
            case R.id.ic02:
                Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic03:
                Toast.makeText(this, "Send Message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic04:
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    public void clickimg(View view){
        //클릭하면 다이얼로그가 생성

        //1. 아이디를 가져옴
        moana= findViewById(R.id.moana);

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        LayoutInflater inflater= this.getLayoutInflater();
        View v= inflater.inflate(R.layout.imgview, null);

        builder.setView(v);

        moanaimg=v.findViewById(R.id.moanaimg);

        AlertDialog dialog=builder.create();
        dialog.show();

    }

    public void next(View v){
        //이미지를 클릭할때마다 다음 이미지로 바뀜

        // 무한으로 클릭할 때마다 이미지를 변경

        moanaimg.setImageResource(R.drawable.moana01+n);
        n++;
        if (n==4) n=0;

    }

}
