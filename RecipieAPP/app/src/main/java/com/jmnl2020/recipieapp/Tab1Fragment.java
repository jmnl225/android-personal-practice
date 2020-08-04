package com.jmnl2020.recipieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Tab1Fragment extends Fragment {

    //listview item
    ListView listView1;
    MyAdapterListview adapterListview1;
    ArrayList<Listview1item> listview1items = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //listview1
        listview1items.add((new Listview1item("소고기 스튜", "냠냠이", R.drawable.dog, R.drawable.food1)));
        listview1items.add((new Listview1item("맛있는 거 먹자", "레시피", R.drawable.ms12, R.drawable.food2)));
        listview1items.add((new Listview1item("간단 요리법", "고수", R.drawable.ms16, R.drawable.food4)));
        listview1items.add((new Listview1item("스테이크 집에서 만들자", "백종순", R.drawable.ms17, R.drawable.food5)));
        listview1items.add((new Listview1item("라면 황금비율", "꿀꺽", R.drawable.ms_03, R.drawable.food6)));
        listview1items.add((new Listview1item("여름에는 냉면", "매운맛", R.drawable.ms_01, R.drawable.food7)));
        listview1items.add((new Listview1item("돈까스최고", "음식백단", R.drawable.ms_07, R.drawable.food8)));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_tab1, container, false);

        LayoutInflater inflater2= getLayoutInflater();
        adapterListview1= new MyAdapterListview(listview1items, inflater2);

        listView1= view.findViewById(R.id.listview1);
        listView1.setAdapter(adapterListview1);

        return view;
    }
}
