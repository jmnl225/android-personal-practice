package com.jmnl2020.postingex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<Post> posts;

    LayoutInflater inflater;


    public MyAdapter(ArrayList<Post> posts, LayoutInflater inflater) {

        this.posts= posts;

        this.inflater= inflater;
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //1. 재활용해서 액자틀을 사용

        if (convertView== null){
            convertView= inflater.inflate(R.layout.post, null);
        }

        //2. 만들어진 뷰 안의 내용을 새로운 내용으로 넣기

        TextView tv_title= convertView.findViewById(R.id.tv_title);
        TextView tv_writer= convertView.findViewById(R.id.tv_writer);
        TextView tv_contents= convertView.findViewById(R.id.tv_contents);

        Post post= posts.get(position);
        tv_title.setText(post.title);
        tv_writer.setText(post.writer);
        tv_contents.setText(post.contents);

        return convertView;
    }
}
