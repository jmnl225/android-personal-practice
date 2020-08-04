package com.jmnl2020.part3_9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText contentView;
    Button btn;

    //퍼미션여부
    boolean fileReadPermission;
    boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       contentView= findViewById(R.id.content);
       btn= findViewById(R.id.btn);

       btn.setOnClickListener(this);

       //Permission check
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED)){
            fileReadPermission= true;
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED)){
            fileWritePermission= true;
        }
        //permission 부여가 안 됐을 경우 permission request
        if(!fileReadPermission||!fileWritePermission){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE},200);
        }

    }

    //permission 부여 요청 결고ㅏ 확인
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==200 && grantResults.length>0){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
                fileReadPermission = true;
            if(grantResults[1]== PackageManager.PERMISSION_GRANTED)
                fileWritePermission= true;
        }

    }

    @Override
    public void onClick(View v) {
        String content= contentView.getText().toString();
        //퍼미션이 부여되어 있다면
        if(fileReadPermission && fileWritePermission){
            FileWriter writer;

            String dirPath= Environment.getExternalStorageDirectory().getAbsolutePath()
                    +"/myApp";
            File dir= new File(dirPath);
            //폴더가 없으면 새로만들기
            if (!dir.exists()){
                dir.mkdir();
            }
            //myApp 폴더 밑에 myfile..txt 파일 지정
            File file = new File(dir+"/myfile.txt");
            //파일이 없다면 새로 만들기
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //file write
            try {
                writer = new FileWriter(file, true);
                writer.write(content);
                writer.flush();
                writer.close();

                //결과 확인을 위한 FileReadActivity
                Intent intent = new Intent(this, ReadFileActivity.class);

                //FileREadActivity로 화면전환
                startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }else {
            showToast("permission받지 못해 기능실행 불가");
        }
    }

    private void showToast(String message) {
        Toast toast= Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();

    }


}
