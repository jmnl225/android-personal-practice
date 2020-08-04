package com.jmnl2020.restrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);

    }

    public void clickBtn(View view) {
        // 네트워크에서 읽어들인 json을 곧바로 객체 생성

        //0. Retrofit2 라이브러리 사용 준비 manifest + gradle 추가

        //1. retrofit 객체 생성 및 기본설정. 단!!! 따로 class를 만들어두면 편리!
        Retrofit retrofit = retrofitHelper.getRetrofitInstance();

        //2. 인터페이스 설계

        //3. RetrofitService 인터페이스를 객체로 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //4. 서비스객체의 원하는 기능메소드 호출. 실행.
        Call<BoardItem> call= retrofitService.getBoardJson();

        //5. 원하는 기능으로 네트워크 작업을 수행하도록 call객체를 큐에 삽입
        call.enqueue(new Callback<BoardItem>() { //삽입한 call 객체가 돌아오면 자동실행
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                Toast.makeText(MainActivity.this, "성공!", Toast.LENGTH_SHORT).show();

                if (response.isSuccessful()){
                    BoardItem item = response.body();
                    tv.setText("작가: "+item.writer+" / 제목: "+item.title+"\n한 문장: "+item.sentence);

                }

            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {
                Toast.makeText(MainActivity.this, "실패 ㅠ", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public interface RetrofitService{

        //1. GET방식으로 json 문자열 읽어오는 추상메소드
        @GET("/RetrofitTest/board.json")
        Call<BoardItem> getBoardJson();

        //MainActivity 에서 builder.baseUrl에 기존 홈페이지 주소를 적었기 때문에 그 뒤에 덧붙일 주소만 명시하면 됨
        //요청한 것을 받아 객체로 만들어야할 때, 그 객체가 어떤 것인지 표기
        // getBoardJson에는 네트워크랑 연결해서 자료를 얻어와야하는 코드가 미리 적혀있음.
        //따라서 call객체로 그 코드들을 가져오기.


    }

}
