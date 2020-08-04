package com.jmnl2020.recipieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AdapterTab2RecyclerView2 extends RecyclerView.Adapter {

    Context context;
    ArrayList<RecyclerViewItem2> recyclerViewItem2s;

    //생성자
    public AdapterTab2RecyclerView2(Context context, ArrayList<RecyclerViewItem2> recyclerViewItem2s) {
        this.context = context;
        this.recyclerViewItem2s = recyclerViewItem2s;
    }


    //재활용할 뷰가 없으면 뷰를 만들어내는 작업 수행 메소드
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_recycler, parent, false);

        //뷰 홀더 객체를 대입
        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh = (VH)holder;
        RecyclerViewItem2 recyclerViewItem = recyclerViewItem2s.get(position);

        vh.tv.setText(recyclerViewItem.msg);
        vh.iv.setImageResource(recyclerViewItem.iv);

    }

    @Override
    public int getItemCount() {
        return recyclerViewItem2s.size();
    }


    //이너클래스 : 아이템뷰 안의 참조변수를 뷰 id를 찾아 붙여넣기해줌
    class VH extends RecyclerView.ViewHolder{        //뷰홀더를 상속받아 만듦

        TextView tv;
        ImageView iv;

        public VH(@NonNull final View itemView) {
            super(itemView);

            tv= itemView.findViewById(R.id.recycler_tv);
            iv= itemView.findViewById(R.id.recycler_iv);

            //클릭했을 경우 반응
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Snackbar.make(itemView, "클릭!", Snackbar.LENGTH_SHORT).show();
                }
            });

        }

    }







}
