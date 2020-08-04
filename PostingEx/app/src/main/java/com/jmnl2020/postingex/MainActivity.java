package com.jmnl2020.postingex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //ListView에 필요한 대량의 데이터

    ArrayList<Post> posts= new ArrayList<>();

    MyAdapter adapter;

    //MainActivity
    ListView listView;

    //dialog
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listview
        listView= findViewById(R.id.listview);

        //대량의 Data add
        posts.add(new Post("안녕하세요", "글쓴이", "첫번째 글 테스트 테스트 테스트"));



        //데이터를 적합한 뷰로 만들어주자

        LayoutInflater inflater= getLayoutInflater();

        adapter= new MyAdapter(posts, inflater);

        //리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        //롱클릭시 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("알림");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setMessage("정말 삭제하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        posts.remove(position);
                        adapter.notifyDataSetChanged();
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


                return true;
            }//롱클릭
        });



    }//onCreate end.



    // Writing 화면으로 넘기기 ~~~~~~~~~~~~~~~~~~~~~~~~
    public void clickBtn(View view) {
        //Wrting 화면으로 넘어가기

        Intent intent= new Intent(this, Writing.class);

        startActivityForResult(intent, 1);

    }// main clickbtn end.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        //내가 보낸 기사 맞니?
        switch (requestCode){
            case 1:

                if(resultCode==RESULT_OK){

//                    Toast.makeText(this,"aaa",Toast.LENGTH_SHORT).show();

                    String title= data.getStringExtra("tt");
                    String writer= data.getStringExtra("wt");
                    String contents= data.getStringExtra("ct");

                    posts.add(0,new Post(title+"", writer+"", contents+""));

                    adapter.notifyDataSetChanged();

                }
                break;
        }
    }
}
