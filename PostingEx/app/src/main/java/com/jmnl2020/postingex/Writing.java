package com.jmnl2020.postingex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Writing extends AppCompatActivity {

    EditText et_title, et_writer, et_contents;
    TextView tv_count;
    AlertDialog.Builder builder;
//    RadioButton rg_btn1, rg_btn2, rg_btn3, rg_btn4, rg_btn5;
//    RadioGroup radioGroup;

    //writing 페이지의 java

    //SQLite에 저장하기
    String dbName= "savefile.db";
    String tableName= "post";

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        et_title= findViewById(R.id.et_title);
        et_writer= findViewById(R.id.et_writer);
        et_contents= findViewById(R.id.et_contents);

        //SQLite 데이터베이스 파일 열기 혹은 생성 + 문서 열고 객체를 불러 명령실행
        db= openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+
                "(num integer primary key autoincrement, TITLE TEXT,  WRITER TEXT, CONTENTS TEXT)");

        //세이브 이벤트를 만드는 곳으로 이동 - > 액션바를 만들자



        /*
        //라디오버튼
        rg_btn1= findViewById(R.id.rg_btn1);
        rg_btn2= findViewById(R.id.rg_btn2);
        rg_btn3= findViewById(R.id.rg_btn3);
        rg_btn4= findViewById(R.id.rg_btn4);
        rg_btn5= findViewById(R.id.rg_btn5);
        //라디오그룹
        radioGroup= findViewById(R.id.radioGroup);


        //라디오 버튼 클릭 리스너
        RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                //이미지를 붙임
            }
        };
        radioGroup.setOnClickListener(radioButtonClickListener);
*/

        //글자 카운팅
        tv_count= findViewById(R.id.tv_count);

        //글자에 카운팅
        et_contents.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input= et_contents.getText().toString();
                tv_count.setText(input.length()+" /500");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }// onCreate end.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,0,0,"Load");
        menu.add(0,1,0,"Save");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case 0:
                Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickCancel(View view) {
        //취소 버튼을 눌렀을 때

        builder= new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("작성한 내용이 모두 사라집니다. \n계속하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //액티비티 끝
                finish();
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog= builder.create();
        dialog.show();

    }//취소버튼 끝

    public void clickOK(View view) {
        //확인 버튼을 눌렀을 때

        //1. Main Activity에 돌려줄 Edit 텍스트 얻어오기
        String title= et_title.getText().toString();
        String writer= et_writer.getText().toString();
        String contents= et_contents.getText().toString();

        // 데이터 돌려주기

        Intent intent= getIntent();
        intent.putExtra("tt", title);
        intent.putExtra("wt", writer);
        intent.putExtra("ct", contents);

        //intent야 이걸 전달해줘~
        setResult(RESULT_OK, intent);

        //이 액티비티 종료
        finish();

    }
}
