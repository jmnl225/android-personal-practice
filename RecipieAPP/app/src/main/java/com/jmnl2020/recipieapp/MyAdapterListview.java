package com.jmnl2020.recipieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapterListview extends BaseAdapter {

    ArrayList<Listview1item> listview1items;
    LayoutInflater inflater;

    //instructor
    public MyAdapterListview(ArrayList<Listview1item> listview1items, LayoutInflater inflater){

        this.listview1items= listview1items;
        this.inflater= inflater;

    }

    @Override
    public int getCount() {
        return listview1items.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if ( convertView == null){
            convertView = inflater.inflate(R.layout.item_listview1, null);
        }

        TextView tv1 = convertView.findViewById(R.id.tv1);
        TextView tv2= convertView.findViewById(R.id.tv2);
        ImageView iv1= convertView.findViewById(R.id.iv1);
        ImageView iv2= convertView.findViewById(R.id.iv2);

        Listview1item listview1item = listview1items.get(position);


        tv1.setText(listview1item.title);
        tv2.setText(listview1item.name);
        iv1.setImageResource(listview1item.userimg);
        iv2.setImageResource(listview1item.mainimg);

        return convertView;
    }
}
