package com.jmnl2020.recipieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab3Fragment extends Fragment {

    RecyclerView tab3_recycler;
    AdapterTab3Recyclerview adapter;
    ArrayList<Tab3Item> tab3Items = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        tab3Items.add(new Tab3Item(R.drawable.food2, "스프"));
        tab3Items.add(new Tab3Item(R.drawable.food4, "찜요리"));
        tab3Items.add(new Tab3Item(R.drawable.food5, "면요리"));
        tab3Items.add(new Tab3Item(R.drawable.food2, "스프"));
        tab3Items.add(new Tab3Item(R.drawable.food4, "찜요리"));
        tab3Items.add(new Tab3Item(R.drawable.food5, "면요리"));
        tab3Items.add(new Tab3Item(R.drawable.food2, "스프"));
        tab3Items.add(new Tab3Item(R.drawable.food4, "찜요리"));
        tab3Items.add(new Tab3Item(R.drawable.food5, "면요리"));
        tab3Items.add(new Tab3Item(R.drawable.food2, "스프"));
        tab3Items.add(new Tab3Item(R.drawable.food4, "찜요리"));
        tab3Items.add(new Tab3Item(R.drawable.food5, "면요리"));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        tab3_recycler = view.findViewById(R.id.tab3_recycler);

        //화면이 만들어질 때 xml 파일 만들기

        adapter = new AdapterTab3Recyclerview(getActivity(), tab3Items);
        tab3_recycler.setAdapter(adapter);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        tab3_recycler.setLayoutManager(layoutManager);


        return view;

    }// onCreateview end.


}
