package com.jmnl2020.numberbaseballgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rnd;
    int a,b,c; //정답 숫자
    EditText user1, user2, user3; //사용자가 입력한 값
    Button btn;
    int strike, ball;
    int num1, num2, num3;
    TextView tv;
    String users1, users2, users3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv= findViewById(R.id.tv);
        btn= findViewById(R.id.btn);

        //Editable 변수 3개를 String으로 바꿔서 컴퓨터의 값과 비교.

        //1. 컴퓨터가 정한 랜덤값 3개
        //2. 사용자로부터 값 입력받기
        //3. 버튼을 눌러 정답과 비교
        //4. 결과 출력

        //1. 랜덤값 입력


        rnd= new Random();
        a= rnd.nextInt(9)+1;
        b= rnd.nextInt(9)+1;
        c= rnd.nextInt(9)+1;


        //2. 사용자의 값 EditText입력받기
        user1= findViewById(R.id.user1);
        user2= findViewById(R.id.user2);
        user3= findViewById(R.id.user3);


        //3. 버튼을 눌러 정답과 비교

        // 버튼이 눌리는 걸 듣는 객체...

        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼이 눌렸을때 실행되는 작업
                strike=0;
                ball=0;

                //사용자에게 숫자를 입력받고 int로 바꿈

                Editable et1= user1.getText();
                Editable et2= user2.getText();
                Editable et3= user3.getText();

                users1= et1.toString();
                users2= et2.toString();
                users3= et3.toString();

                num1= Integer.parseInt(users1);
                num2= Integer.parseInt(users2);
                num3= Integer.parseInt(users3);

                //받은 숫자를 비교

                if(num1==a) strike++;
                else if(num1==b||num1==c) {
                    ball++;
                    tv.setText("스트라이크: "+strike+"  "+"볼: "+ball);
                }
                if(num2==b) strike++;
                else if(num2==a||num2==c) ball++;
                if(num3==c) strike++;
                else if(num3==a||num3==b) ball++;

                if(strike==3){
                    tv.setText("축하합니다! 정답입니다!!");
                }

                tv.setText("스트라이크: "+strike+"         "+"볼: "+ball);

            }
        };

        btn.setOnClickListener(listener);

   }
}
