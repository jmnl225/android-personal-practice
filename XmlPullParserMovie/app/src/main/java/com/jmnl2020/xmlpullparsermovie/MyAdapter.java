package com.jmnl2020.xmlpullparsermovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<Item> items;

    LayoutInflater inflater;

    public MyAdapter(ArrayList<Item> items, LayoutInflater inflater){
        this.inflater= inflater;
        this.items= items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //리스트뷰 액자 설정

        if(convertView== null){
            convertView= inflater.inflate(R.layout.listview_item, null);
        }

        //만들어진 뷰에 내용 덮어쓰기

        TextView tv1= convertView.findViewById(R.id.tv1);
        TextView tv2= convertView.findViewById(R.id.tv2);
        TextView tv3= convertView.findViewById(R.id.tv3);
        TextView tv4= convertView.findViewById(R.id.tv4);

        //이미지 아이디 찾아 스위치로 넣어주기

        Item item= items.get(position); //새 아이템을 만들어서 대입
        tv1.setText(item.tv1);
        tv2.setText(item.tv2);
        tv3.setText(item.tv3);
        tv4.setText(item.tv4);

        return convertView;
    }


}
