package com.jmnl2020.retrofittest2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
        listView= findViewById(R.id.listview);

    }

    public void clickBtn(View view) {
        //네트워크에서 읽어온 json을 곧바로 객체생성

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://jmnl.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit= builder.build();

        // retrofit 객체 생성 및 설정

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Call<ArrayList<BoardItem>> call = retrofitService.getBoardJson();

        //네트워크 작업을 수행하도록 call 객체를 queue에 삽입
        call.enqueue(new Callback<ArrayList<BoardItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BoardItem>> call, Response<ArrayList<BoardItem>> response) {
                if(response.isSuccessful()){

                    ArrayList<BoardItem> list = response.body();
                    //tv.setText(list.size()+""); //잘 받아왔는지 확인
                    ArrayList<String> array = new ArrayList<>();

                    for(BoardItem re: list){
                        array.add("작가: "+re.writer+" / 제목: "+re.title+"\n한문장: "+re.sentence);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, array);
                    listView.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<ArrayList<BoardItem>> call, Throwable t) {

            }
        });

    }

    //인터페이스 RetroifitService

    public interface RetrofitService{
        @GET("/RetrofitTest/board2.json")
        Call<ArrayList<BoardItem>> getBoardJson();

    }


}
