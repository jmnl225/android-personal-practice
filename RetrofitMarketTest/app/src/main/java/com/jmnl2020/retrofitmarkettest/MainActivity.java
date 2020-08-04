package com.jmnl2020.retrofitmarkettest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<BoardItem> boardItems= new ArrayList<>();
    BoardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        adapter= new BoardAdapter(this, boardItems);
        recyclerView.setAdapter(adapter);

    }

    //액티비티가 화면에 보여질때, 자동 실행되는 라이프사이클 메소드


    @Override
    protected void onResume() {
        super.onResume();
        //서버의 데이터 읽어오기
        loadData();
    }

    void loadData(){

        boardItems.add( new BoardItem(1, "aaa", "Title", "여기는 메세지 칸입니다.", "1000", null, 1, "2020-06-22"));

        Retrofit retrofit = RetrofitHelper.getInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<BoardItem>> call = retrofitService.loadDataFromBoard();
        call.enqueue(new Callback<ArrayList<BoardItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BoardItem>> call, Response<ArrayList<BoardItem>> response) {
                if(response.isSuccessful()){
                    ArrayList<BoardItem> items = response.body();

                    boardItems.clear();
                    adapter.notifyDataSetChanged();

                    for(BoardItem item: items){
                        boardItems.add(0, item);
                        adapter.notifyItemInserted(0);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<BoardItem>> call, Throwable t) {

            }
        });

    }

    public void ClickBtn(View view) {

        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);

    }
}
