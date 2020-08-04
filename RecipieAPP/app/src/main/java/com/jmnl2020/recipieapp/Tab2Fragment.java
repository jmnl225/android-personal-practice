package com.jmnl2020.recipieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab2Fragment extends Fragment {


    //대량의 데이터
    ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<>();

    RecyclerView recyclerView;
    AdapterTab2RecyclerView adapterRecyclerView;

    ////////////////////////////////////////
    //두번째 리사이클러 뷰
    RecyclerView recyclerView2;
    ArrayList<RecyclerViewItem2> recyclerViewItem2s = new ArrayList<>();
    AdapterTab2RecyclerView2 adapterRecyclerView2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // +여기서 아이템 추가
        // onCreate View 에서 추가하면 화면이 부서지고 다시 만들어질때 또 추가되므로 가장 처음 한 번 만들때 추가

        recyclerViewItems.add(new RecyclerViewItem("시원한 새콤달콤 여름 과일", R.drawable.summerfruits));
        recyclerViewItems.add(new RecyclerViewItem("달콤한 디저트 아이스크림", R.drawable.icebg1));
        recyclerViewItems.add(new RecyclerViewItem("시원한 새콤달콤 여름 과일", R.drawable.summerfruits));
        recyclerViewItems.add(new RecyclerViewItem("달콤한 디저트 아이스크림", R.drawable.icebg1));

        // second view


        recyclerViewItem2s.add(new RecyclerViewItem2("제일 맛있어! 함흥냉면", R.drawable.nm1));
        recyclerViewItem2s.add(new RecyclerViewItem2("매콤한 비빔냉면", R.drawable.nm3));
        recyclerViewItem2s.add(new RecyclerViewItem2("칡냉면", R.drawable.nm4));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        //fagment와 연결된 xml 파일을 여기서 inflate  해 주고, 거기서 나온 view를 받아서 view에 다가 find view by id 해줌.

        adapterRecyclerView = new AdapterTab2RecyclerView(getActivity(), recyclerViewItems);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapterRecyclerView);

        /////////////////////////////////////
        //Second recycler View


        adapterRecyclerView2 = new AdapterTab2RecyclerView2(getActivity(), recyclerViewItem2s);
        recyclerView2 = view.findViewById(R.id.recycler2);
        recyclerView2.setAdapter(adapterRecyclerView2);


        return view;
    }
}
