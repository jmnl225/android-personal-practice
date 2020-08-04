package com.jmnl2020.numbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //View들을 참조하는 멤버 참조변수
    EditText et;
    Button btn;
    TextView tv;

    // 1. 컴퓨터가 정한 정답 숫자

    int com; //랜덤하게 500~1000 숫자 중 아무거나.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에 만든 뷰들을 id를 이용해서 찾아와 참조변수에 대입

        et= findViewById(R.id.et);
        btn= findViewById(R.id.btn);
        tv= findViewById(R.id.tv);

        //사용자가 숫자를ㄹ 입력하고 버튼을 클릭하면
        //EditText에 입력되어있는 글씨를 얻어와서 컴퓨터가 정한 정답과 비교
        //그 결과를 TextView에 보여주기

        //컴퓨터가 정한 정답값
        Random rnd= new Random();
        com= rnd.nextInt(1000) +1; //0~500


        //버튼이 클릭되는 것을 듣는 리스너 객체 생성
        View.OnClickListener listener= new View.OnClickListener() {
            //이 리스너가 바라보고 있는ㄴ 버튼이 클릭되면 자동으로 실행. = 콜백메소드
            @Override
            public void onClick(View v) {
                //EditText의 글씨를 얻어와서
                Editable editable= et.getText();
                String s= editable.toString();

                //얻어온 글씨(String)을 정수형 숫자(int)로 변환
                int user= Integer.parseInt(s);

                //com값과 비교
                if(user < com){
                    tv.setText(user+" 보다 큰 값입니다. ");
                }else if(user > com){
                    tv.setText(user+" 보다 작은 값입니다. ");
                }else {
                    tv.setText("\n" +
                            "축하합니다! 정답입니다!!!\n");
                }

                //그 결과를 TextView에 보여주기
//                tv.setText(" Test ");

                //만약 EditText의 글씨를 지우고 싶다면..
                //지우는 메소드는 별도로 존재하지 않음.
                et.setText(""); //빈 문자열로 글씨를 변경

            }
        };

        //버튼에 리스너 객체 설정
        btn.setOnClickListener(listener);


    }}