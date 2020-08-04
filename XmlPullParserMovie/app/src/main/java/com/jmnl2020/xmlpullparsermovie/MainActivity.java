package com.jmnl2020.xmlpullparsermovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<Item> items = new ArrayList<>();
    MyAdapter adapter;

    String apiKey="cf705c5fed128e4b46d2fdf1531ec5f3";

    String tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리스트뷰에 내 어댑터 붙이기
        listView= findViewById(R.id.listview);

        LayoutInflater inflater= getLayoutInflater();
        adapter= new MyAdapter(items, inflater);

        listView.setAdapter(adapter);


    }

    public void clickBtn(View view) {

        //리스트뷰에 보여줄 데이터 테스트
//        items.add(new Item("a","b","c","d"));
//        adapter.notifyDataSetChanged();

        //네트워크랑 연결해서 데이터 받아올 직원 Thread 객체

        Thread t= new Thread(){
            @Override
            public void run() {

                Date date= new Date();
                date.setTime( date.getTime()-(1000*60*60*24));
                SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
                String dateStr= sdf.format(date);



                //1. 링크 붙이기
                String address= "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?"
                        +"key="+ apiKey
                        +"&targetDt="+ dateStr;
                //+ key + targetDt

                //URL에게 InputStream 만들어달라고 요청
                //어디로? address로. = > 트라이캐치
                try {
                    //steam 만들어주는 url 소환!
                    URL Url= new URL(address);

                    //스트림을 만들었으면 연결을 시켜야지?
                    InputStream is= Url.openStream();
                    //그런데 1바이트씩 읽어오잖아.. 이걸 String 문자로 읽도록만들자
                    InputStreamReader isr= new InputStreamReader(is);

                    XmlPullParser xpp; //근데 난 문자를 해석할 녀석이 필요함.

                    //이녀석을 데려오려면 공장에서 가져와야하네
//                    xpp =XmlPullParserFactory.newInstance().newPullParser();
                    XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                    xpp= factory.newPullParser(); //분석가 객체를 만들어냄!

                    //드디어 만들었다.. 분석가 객체야 이제 isr이 가져온 스트링을 분석 시작!
                    xpp.setInput(isr);

                    ///////////////////////////////////////////////


                    int eventType =xpp.getEventType(); //xpp가 분석을 해서 int로 타입을 내놓음

                    StringBuffer buffer1 = new StringBuffer(); // 문자열 후보들을 모아봅시다.
                    StringBuffer buffer2 = new StringBuffer();
                    StringBuffer buffer3 = new StringBuffer();
                    StringBuffer buffer4 = new StringBuffer();
                    StringBuffer buffer5 = new StringBuffer();


                    //그럼 xpp가 분석하는걸 페이지 끝까지 반복시키자
                    while(eventType != XmlPullParser.END_DOCUMENT){
                        //분석한 자료를 필요한 문자만 걸러내기
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:

                                break;

                            case  XmlPullParser.START_TAG:

                                String tag= xpp.getName();

                                Log.i("tag", tag);

                                if (tag.equals("dailyBoxOffice")){


                                    buffer1= new StringBuffer();
                                    buffer2= new StringBuffer();
                                    buffer3= new StringBuffer();
                                    buffer4= new StringBuffer();
                                    buffer5= new StringBuffer();

                                }else if(tag.equals("rank")){
                                    buffer1.append("순위: ");
                                    xpp.next();
                                    buffer1.append(xpp.getText()+"\n");

                                }else if(tag.equals("movieNm")){
                                    buffer2.append("영화제목: ");
                                    xpp.next();
                                    buffer2.append(xpp.getText()+"\n");

                                }else if(tag.equals("openDt")){
                                    buffer3.append("개봉일: ");
                                    xpp.next();
                                    buffer3.append(xpp.getText()+"\n");

                                }else if(tag.equals("audiCnt")){
                                    buffer4.append("오늘 관객수: ");
                                    xpp.next();
                                    buffer4.append(xpp.getText()+"\n");

                                }else if(tag.equals("audiAcc")){
                                    buffer5.append("누적 관객수: ");
                                    xpp.next();
                                    buffer5.append(xpp.getText()+"\n");
                                }

                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case  XmlPullParser.END_TAG:
                                String tagName2 = xpp.getName();
                                if(tagName2.equals("dailyBoxOffice")){
                                    //지금까지 누적된 String Buffer 를 String으로
                                    tv1= buffer1.toString();
                                    tv2= buffer2.toString();
                                    tv3= buffer3.toString();
                                    tv4= buffer4.toString();

                                    //ArrayList에 추가
                                    items.add(new Item(tv1, tv2, tv3, tv4));

                                    //리스트뷰 갱신! 하지만 화면담당은 UI Thread (Main Activity 것이므로
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            items.add(new Item(tv1, tv2, tv3, tv4));
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                                break;

                        }// switch end.

                        eventType= xpp.next();

                    }// while end.


//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this, "끝", Toast.LENGTH_SHORT).show();
//                        }
//                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

            }
        };
        t.start();


    }
}
