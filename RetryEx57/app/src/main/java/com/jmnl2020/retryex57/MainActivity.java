package com.jmnl2020.retryex57;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items = new ArrayList<>();

    MyAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가
        items.add( new Item("쵸파", "해적단 의사", R.drawable.one_chopa,"https://img.insight.co.kr/static/2020/05/20/700/f9kep8y69c910z3y9d68.jpg"));
        items.add( new Item("루피", "해적단 선장", R.drawable.one_luffy,"http://www.kbsm.net/data/newsThumb/1550436395ADD_thumb580.jpg"));
        items.add( new Item("나미", "해적단 항해사", R.drawable.one_nami5, "https://img.insight.co.kr/static/2020/05/20/700/1ydhlv9685uuh597oneq.jpg"));
        items.add( new Item("우솝", "해적단 저격수", R.drawable.one_usoup, "https://i.pinimg.com/originals/59/32/f6/5932f6f30d416ef4eec742d9fb6a072d.jpg"));
        items.add( new Item("쵸파", "해적단 의사", R.drawable.one_chopa2,"https://img.insight.co.kr/static/2020/05/20/700/f9kep8y69c910z3y9d68.jpg"));
        items.add( new Item("루피", "해적단 선장", R.drawable.one_ace,"http://www.kbsm.net/data/newsThumb/1550436395ADD_thumb580.jpg"));
        items.add( new Item("나미", "해적단 항해사", R.drawable.one_sandi, "https://img.insight.co.kr/static/2020/05/20/700/1ydhlv9685uuh597oneq.jpg"));
        items.add( new Item("우솝", "해적단 저격수", R.drawable.one_shanks, "https://i.pinimg.com/originals/59/32/f6/5932f6f30d416ef4eec742d9fb6a072d.jpg"));

        adapter = new MyAdapter(this, items);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_aa:
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                break;

            case  R.id.menu_bb:
                RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, 2);
                recyclerView.setLayoutManager(layoutManager1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
