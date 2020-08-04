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

public class AdapterTab2RecyclerView extends RecyclerView.Adapter {

    Context context;
    ArrayList<RecyclerViewItem> recyclerViewItems;

    public AdapterTab2RecyclerView(Context context, ArrayList<RecyclerViewItem> recyclerViewItems) {
        this.context = context;
        this.recyclerViewItems = recyclerViewItems;
    }


    //재활용할 뷰가 없으면 뷰를 만들어내는 작업을 수행하는 콜백메소드
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
//        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_recycler, parent,false);

        //뷰 홀더 객체를 생성&참조변수 대입 끝!
        VH holder = new VH(itemView);

        return holder;
    }

    //뷰에 값을 연결하는 작업 메소드
    //뷰에 설정된 tag객체 (ViewHolder)를 첫번째 파라미터로 전달
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh = (VH)holder;
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);

        vh.tv.setText(recyclerViewItem.msg);
        vh.iv.setImageResource(recyclerViewItem.iv);

    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }

    //이너클래스 : 아이템뷰 안의 참조변수를 뷰 id를 찾아 붙여넣기 수행
    class VH extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;

        public VH(@NonNull final View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.recycler_tv);
            iv = itemView.findViewById(R.id.recycler_iv);

            //만약 클릭을 했다면
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Snackbar.make(itemView,"클릭클릭!", Snackbar.LENGTH_SHORT).show();
                }
            });

        }
    }


}
